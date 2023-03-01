package io.earths;

import com.google.gson.Gson;
import io.earths.model.User;
import io.earths.repo.SponsorRepo;
import io.earths.repo.SaintRepo;
import net.plsar.annotations.Bind;
import net.plsar.annotations.Design;
import net.plsar.annotations.JsonOutput;
import net.plsar.annotations.NetworkRouter;
import net.plsar.annotations.network.Get;
import net.plsar.model.ViewCache;

import java.util.List;

@NetworkRouter
public class BasicRouter {
    public BasicRouter(){
        this.gson = new Gson();
    }

    Gson gson;

    @Bind
    SaintRepo saintRepo;

    @Bind
    SponsorRepo sponsorRepo;

    @Design("layouts/earth.jsp")
    @Get("/")
    public String index(ViewCache cache){
        List<User> sponsors = sponsorRepo.all();
        cache.set("sponsors", sponsors);
        return "pages/index.jsp";
    }

    @Get("/policy")
    public String policy(){
        return "pages/policy.jsp";
    }

    @Get("/terms")
    public String terms(){
        return "pages/terms.jsp";
    }

    @JsonOutput
    @Get("/locate/data")
    public String locate(ViewCache cache){
        List<User> saints = saintRepo.all();
        return gson.toJson(saints);
    }

}
