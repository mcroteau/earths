package io.earths;

import com.google.gson.Gson;
import io.earths.model.State;
import io.earths.model.Town;
import io.earths.model.User;
import io.earths.repo.SaintRepo;
import io.earths.repo.StateRepo;
import io.earths.repo.TownRepo;
import net.plsar.annotations.*;
import net.plsar.annotations.network.Get;
import net.plsar.model.NetworkRequest;
import net.plsar.model.ViewCache;

import java.util.ArrayList;
import java.util.List;

@NetworkRouter
public class LocateRouter {
    public LocateRouter(){
        this.gson = new Gson();
    }

    Gson gson;

    @Bind
    TownRepo townRepo;

    @Bind
    SaintRepo saintRepo;

    @Bind
    StateRepo stateRepo;

    @Design("layouts/default.jsp")
    @Get("/locate")
    public String locate(ViewCache cache){
        List<User> allSaints = saintRepo.all();
        List<User> saints = new ArrayList<>();
        for(User saint: allSaints){
            Town town = townRepo.get(saint.getTownId());
            saint.setTown(town.getName());
            saints.add(saint);
        }
        cache.set("saints", saints);
        return "pages/saint/locate.jsp";
    }

    @JsonOutput
    @Get("/states/{nationId}")
    public String states(NetworkRequest req, @Component Long nationId){
        List<State> states = stateRepo.find(nationId);
        return gson.toJson(states);
    }

    @JsonOutput
    @Get("/cities/{stateId}")
    public String cities(NetworkRequest req, @Component Long townId){
        List<Town> cities = townRepo.find(townId);
        return gson.toJson(cities);
    }
}
