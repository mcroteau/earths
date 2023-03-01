package io.earths;

import com.google.gson.Gson;

import io.earths.befores.EditUserBefore;
import io.earths.model.*;
import io.earths.repo.*;

import net.plsar.annotations.*;
import net.plsar.annotations.Component;
import net.plsar.annotations.network.Get;
import net.plsar.annotations.network.Post;
import net.plsar.model.*;
import net.plsar.security.SecurityManager;

import java.util.*;

@NetworkRouter
public class SaintRouter {
    public SaintRouter(){
        this.gson = new Gson();
        this.earthsHelp = new EarthsHelp();
    }

    Gson gson;
    EarthsHelp earthsHelp;

    @Bind
    UserRepo userRepo;

    @Bind
    TownRepo townRepo;

    @Bind
    StateRepo stateRepo;

    @Bind
    NationRepo nationRepo;

    @Bind
    SaintRepo saintRepo;

    @Bind
    SponsorRepo sponsorRepo;


    @Design("/layouts/guest.jsp")
    @Get("/{id}")
    public String saint(ViewCache cache,
                        @Component Long id){
        EarthsHelp earthsHelp = new EarthsHelp();
        User saint = saintRepo.get(id);
        if(saint == null){
            cache.set("message", "unable to locate user.");
            return "redirect:/";
        }

        cache.set("saint", saint);
        cache.set("title", saint.getName());
        return "pages/saints/index.jsp";
    }

    @Design("/layouts/guest.jsp")
    @Get("/saints/onboard/{guid}")
    public String onboard(ViewCache cache, @Component String guid){
        User stud = saintRepo.guid(guid);
        cache.set("stud", stud);
        return "pages/saints/onboard.jsp";
    }

    @Design("/layouts/guest.jsp")
    @Get("/extraordinary/saints/{guid}")
    public String extraordinary(ViewCache cache, @Component String guid){
        User saint = saintRepo.guid(guid);
        cache.set("saint", saint);
        return "pages/saints/extraordinary.jsp";
    }

    @Get("/{guid}")
    public String guid(ViewCache cache,
                       @Component String guid){
        User saint = saintRepo.guid(guid.toLowerCase());
        if(saint == null) {
            return "redirect:/#index";
        }

        return "redirect:/#saints/" + guid;
    }

    @Design("layouts/default.jsp")
    @Get("/saints/{guid}")
    public String data(NetworkRequest req,
                       ViewCache cache,
                       FlashMessage message,
                       SecurityManager manager,
                       @Component String guid){

        if(manager.isAuthenticated(req)){
            String email = manager.getUser(req);
            User authUser = userRepo.get(email);
            cache.set("authUser", authUser);
        }

        User saint = saintRepo.guid(guid.toLowerCase());
        if(saint == null) {
            message.set("unable to locate user.");
            return "redirect:/";
        }

        List<User> sponsors = new ArrayList<>();
        if(saint.getRefs() != null) {
            String[] refs = saint.getRefs().split(",");
            for(String refPre : refs){
                String ref = refPre.trim();
                User sponsor = sponsorRepo.ref(ref);
                if(sponsor != null){
                    sponsors.add(sponsor);
                }
            }
        }

        Town town = townRepo.get(saint.getTownId());
        if(town != null) {
            saint.setTown(town.getName());
        }
        Nation nation = nationRepo.get(saint.getNationId());
        if(nation != null){
            saint.setNationCode(nation.getIso());
        }
        char[] abc = saint.getName().toCharArray();
        saint.setAbbrv(abc[0] + ".");

        cache.set("saint", saint);
        cache.set("sponsors", sponsors);
        return "pages/saint/index.jsp";
    }

    @JsonOutput
    @Get("/saints/query")
    public String query(NetworkRequest req, ViewCache cache){
        String query = req.getValue("q");
        List<User> saints;
        if(query != null && !query.equals("")){
            saints = saintRepo.find(query);
        }else{
            saints = saintRepo.all();
        }
        cache.set("saints", saints);
        return gson.toJson(cache);
    }

    @Design("/layouts/guest.jsp")
    @Get("/saints")
    public String locate(NetworkRequest req, ViewCache cache){
        String query = req.getValue("q");

        List<User> saintsPre;
        if(query != null && !query.equals("")){
            saintsPre = saintRepo.find(query);
        }else{
            saintsPre = saintRepo.all();
        }

        List<User> saints = new ArrayList<>();
        for(User saint: saintsPre){
            char[] abc = saint.getName().toCharArray();
            saint.setAbbrv(abc[0] + ".");
            Town town = townRepo.get(saint.getTownId());
            if(town != null) {
                saint.setTown(town.getName());
            }
            Nation nation = nationRepo.get(saint.getNationId());
            if(nation != null){
                saint.setNationCode(nation.getIso3());
            }
            saints.add(saint);
        }

        List<Nation> nations = nationRepo.all();
        List<State> states = stateRepo.find(nations.get(0).getId());
        List<Town> towns = townRepo.find(states.get(0).getId());

        cache.set("nations", nations);
        cache.set("states", states);
        cache.set("towns", towns);
        cache.set("query", query);
        cache.set("saints", saints);
        return "pages/saints/locate.jsp";
    }

    @Design("/layouts/sec.jsp")
    @Get("/saints/edit/{id}")
    @Before(value = {EditUserBefore.class}, variables="{id}")
    public String edit(NetworkRequest req,
                       NetworkResponse resp,
                       ViewCache cache,
                       SecurityManager manager,
                       @Component Long id){

        User saint = saintRepo.get(id);

        List<User> sponsors = new ArrayList<>();
        if(saint.getRefs() != null) {
            String[] refs = saint.getRefs().split(",");
            for(String refPre : refs){
                String ref = refPre.trim();
                User sponsor = sponsorRepo.ref(ref);
                if(sponsor != null){
                    sponsors.add(sponsor);
                }
            }
        }

        cache.set("saint", saint);
        cache.set("sponsors", sponsors);

        List<Nation> nations = nationRepo.all();
        List<State> states = stateRepo.find(saint.getNationId());
        List<Town> towns = townRepo.find(saint.getStateId());

        cache.set("nations", nations);
        cache.set("states", states);
        cache.set("towns", towns);

        return "pages/saints/edit.jsp";
    }

    @Post("/saints/update/{id}")
    @Before(value = {EditUserBefore.class}, variables="{id}")
    public String update(NetworkRequest req, SecurityManager manager, ViewCache cache, @Component Long id){
        User user = new User();
        user = req.get(User.class);

        if(req.getValue("email").equals("")){
            cache.set("message", "please enter an email.");
            return "redirect/remarkable/edit/" + id;
        }

        RequestComponent component = req.getRequestComponent("image");
        if(component != null && !component.getFileComponents().isEmpty()){
            List<FileComponent> files = component.getFileComponents();
            FileComponent file = files.get(0);
            String prefix = earthsHelp.getEncodedPrefix(file.getFileName());
            String image = Base64.getEncoder().withoutPadding().encodeToString(file.getFileBytes());
            user.setImage(prefix + image);
            userRepo.updateImage(user);
        }

        user.setId(id);
        userRepo.update(user);

        cache.set("message", "updated.");
        return "redirect:/saints/edit/" + id;
    }

    @Design("/layouts/guest.jsp")
    @Get("/saints/passwd/edit/{id}")
    public String editpasswd(NetworkRequest req, SecurityManager manager, ViewCache cache, @Component Long id){

        if(!manager.isAuthenticated(req)){
            cache.set("message", "permission fail.");
            return "redirect:/signin";
        }

        String permission = earthsHelp.getUserMaintenance() + id;
        if(!manager.hasPermission(permission, req)){
            cache.set("message", "permission fail.");
            return "redirect:/signin";
        }

        User saint = saintRepo.get(id);
        cache.set("saint", saint);
        return "pages/saints/passwd.jsp";
    }

    @Post("/saints/passwd/update")
    public String updatepasswd(NetworkRequest req, SecurityManager manager, ViewCache cache){
        User user = req.get(User.class);

        if(!manager.isAuthenticated(req)){
            cache.set("message", "permission fail.");
            return "redirect:/signin";
        }

        String permission = earthsHelp.getUserMaintenance() + user.getId();
        if(!manager.hasPermission(permission, req)){
            cache.set("message", "permission fail.");
            return "redirect:/signin";
        }

        String passwd = manager.hash(user.getPasswd());
        user.setPasswd(passwd);
        userRepo.update(user);

        User savedUser = userRepo.getSaved();
        Long id = savedUser.getId();

        cache.set("message", "updated password.");
        return "redirect:/saints/password/edit/" + id;
    }


}
