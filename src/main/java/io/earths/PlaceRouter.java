package io.earths;

import com.google.gson.Gson;
import io.earths.model.*;
import io.earths.repo.NationRepo;
import io.earths.repo.PlaceRepo;
import io.earths.repo.StateRepo;
import io.earths.repo.TownRepo;
import net.plsar.annotations.Bind;
import net.plsar.annotations.Design;
import net.plsar.annotations.JsonOutput;
import net.plsar.annotations.NetworkRouter;
import net.plsar.annotations.network.Get;
import net.plsar.model.NetworkRequest;
import net.plsar.model.ViewCache;

import java.math.BigDecimal;
import java.util.List;

@NetworkRouter
public class PlaceRouter {
    public PlaceRouter(){
        this.gson = new Gson();
    }

    @Bind
    PlaceRepo placeRepo;

    @Bind
    TownRepo townRepo;

    @Bind
    StateRepo stateRepo;

    @Bind
    NationRepo nationRepo;

    Gson gson;

    @Design("layouts/guest.jsp")
    @Get("/places")
    public String index(ViewCache cache){
        /*
        top = 49.3457868 # north lat
        left = -124.7844079 # west long
                right = -66.9513812 # east long
                bottom =  24.7433195 # south lat
        */

        BigDecimal topLat = new BigDecimal(49.3457868);
        BigDecimal bottomLat = new BigDecimal(24.7433195);



        List<Nation> nations = nationRepo.all();
        List<State> states = stateRepo.find(nations.get(0).getId());
        List<Town> towns = townRepo.find(states.get(0).getId());

        cache.set("nations", nations);
        cache.set("states", states);
        cache.set("towns", towns);
        return "pages/place/index.jsp";
    }

    @JsonOutput
    @Get("/places/query")
    public String query(NetworkRequest req){
        Long nationId = Long.valueOf(req.getValue("nationId"));
        Long stateId = Long.valueOf(req.getValue("stateId"));
        Long townId = Long.valueOf(req.getValue("townId"));

        if(townId != null){
            List<Place> places = placeRepo.findTown(townId);
            return gson.toJson(places);
        }

        if(stateId != null){
            List<Place> places = placeRepo.findState(stateId);
            return gson.toJson(places);
        }

        List<Place> places = placeRepo.findNation(nationId);
        return gson.toJson(places);
    }

}
