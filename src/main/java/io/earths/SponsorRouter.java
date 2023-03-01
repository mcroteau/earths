package io.earths;

import com.google.gson.Gson;
import io.earths.model.*;
import io.earths.repo.*;
import net.plsar.annotations.*;
import net.plsar.annotations.network.Get;
import net.plsar.model.ViewCache;

import java.util.ArrayList;
import java.util.List;

@NetworkRouter
public class SponsorRouter {
    public SponsorRouter() {
        this.gson = new Gson();
    }

    Gson gson;

    @Bind
    TownRepo townRepo;

    @Bind
    NationRepo nationRepo;

    @Bind
    SaintRepo saintRepo;

    @Bind
    SponsorRepo sponsorRepo;

    @JsonOutput
    @Get("/sponsors/{guid}")
    public String data(ViewCache cache,
                       @Component String guid){

        User sponsor = sponsorRepo.guid(guid.toLowerCase());
        if(sponsor == null) {
            sponsor = new User();
            Response response = new Response(false,"unable to locate user.");
            response.setRedirect("index");
            sponsor.setResponse(response);
            return gson.toJson(sponsor);
        }

        Town town = townRepo.get(sponsor.getTownId());
        if(town != null) {
            sponsor.setTown(town.getName());
        }
        Nation nation = nationRepo.get(sponsor.getNationId());
        if(nation != null){
            sponsor.setNationCode(nation.getIso3());
        }
        char[] abc = sponsor.getName().toCharArray();
        sponsor.setAbbrv(abc[0] + ".");

        List<User> sponsors = new ArrayList<>();
        if(sponsor.getRefs() != null) {
            String[] refs = sponsor.getRefs().split(",");
            for(String refPre : refs){
                String ref = refPre.trim();
                User user = sponsorRepo.ref(ref);
                if(user != null){
                    sponsors.add(user);
                }
            }
        }

        cache.set("sponsor", sponsor);
        cache.set("sponsors", sponsors);
        return gson.toJson(cache);
    }
}
