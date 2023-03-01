package io.earths;

import com.google.gson.Gson;
import io.earths.model.State;
import io.earths.model.Town;
import io.earths.repo.StateRepo;
import io.earths.repo.TownRepo;
import net.plsar.annotations.Bind;
import net.plsar.annotations.Component;
import net.plsar.annotations.JsonOutput;
import net.plsar.annotations.NetworkRouter;
import net.plsar.annotations.network.Get;
import net.plsar.model.NetworkRequest;

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
    StateRepo stateRepo;

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
