package io.earths;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import io.earths.model.*;
import io.earths.repo.*;
import net.plsar.RouteAttributes;
import net.plsar.annotations.*;
import net.plsar.annotations.Component;
import net.plsar.annotations.network.Get;
import net.plsar.annotations.network.Post;
import net.plsar.model.*;
import net.plsar.security.SecurityManager;

import java.util.List;

@NetworkRouter
public class IdentityRouter {
    public IdentityRouter(){
        this.gson = new Gson();
        this.earthsHelp = new EarthsHelp();
    }

    Gson gson;
    EarthsHelp earthsHelp;

    @Bind
    UserRepo userRepo;

    @Bind
    SaintRepo saintRepo;

    @Bind
    SponsorRepo sponsorRepo;

    @Bind
    TownRepo townRepo;

    @Bind
    StateRepo stateRepo;

    @Bind
    NationRepo nationRepo;

    @Design("layouts/default.jsp")
    @Get("/signup")
    public String signup(ViewCache cache){
        List<Nation> nations = nationRepo.all();
        List<State> states = stateRepo.find(nations.get(0).getId());
        List<Town> towns = townRepo.find(states.get(0).getId());

        cache.set("nations", nations);
        cache.set("states", states);
        cache.set("towns", towns);
        return "pages/signup.jsp";
    }

    @Get("/signin")
    public String signin(){
        return "pages/signin.jsp";
    }

    @Design("/layouts/guest.jsp")
    @Get("/saints/signin")
    public String signinune(){
        return "pages/signin.jsp";
    }

    //todo:remove the prefix from uri and test
    @Design("/layouts/guest.jsp")
    @Post("/signin")
    public String signin(NetworkRequest req, NetworkResponse resp, SecurityManager manager, FlashMessage flashMessage, ViewCache cache){

        if(manager.isAuthenticated(req)){
            manager.signout(req, resp);
            flashMessage.set("success signed out.");
            return "redirect:/signin";
        }

        User user = req.get(User.class);

        if(user.getEmail() == null || user.getEmail().equals("")){
            cache.set("message", "authentication required. bad email & password combination.");
            return "redirect:/signin";
        }
        if(user.getPasswd() == null || user.getPasswd().equals("")){
            cache.set("message", "authentication required. bad email & password combination.");
            return "redirect:/signin";
        }

        List<User> users = saintRepo.all();
        System.out.println("z: " + users.size());
        for(User saint : users){
            System.out.println(saint.getEmail());
        }

        User storedUser = saintRepo.email(user.getEmail());
        if(storedUser == null){
            cache.set("message", "unable to find email.");
            return "redirect:/signin";
        }

        System.out.println("z:" + storedUser.getEmail());
        if(!manager.signin(user.getEmail(), user.getPasswd(), req, resp)){
            cache.set("message", "authentication required. bad email & password combination.");
            return "redirect:/signin";
        }

        //todo:
//        req.getSession(true).set("saintguid", storedUser.getGuid());
//        req.getSession(true).set("saintname", storedUser.getName());
//        req.getSession(true).set("saintemail", storedUser.getEmail());
//        req.getSession(true).set("saintid", storedUser.getId());

        Long id = storedUser.getId();
        return "redirect:/saints/edit/" + id;
    }

    @Get("/signout")
    public String signout(NetworkRequest req, NetworkResponse resp, SecurityManager manager, ViewCache cache){
        manager.signout(req, resp);
        cache.set("message", "success. signed out.");
        return "redirect:/signin";
    }

    @Get("/stripe/onboard/{uid}")
    public String stripe(NetworkRequest req, NetworkResponse resp, ViewCache cache, @Component String uid){
        User stud = userRepo.getUid(uid);
        if(stud == null){
            cache.set("message", "unable to locate, will you try signing in?");
            return "redirect:/signin";
        }

        AccountCreateParams accountParams =
                AccountCreateParams.builder()
                        .setType(AccountCreateParams.Type.STANDARD)
                        .build();

        try {
            String usertype = stud.getUserType();
            RouteAttributes routeAttributes = req.getRouteAttributes();
            String apiKey = routeAttributes.get("stripe.key");
            String refreshUrl = routeAttributes.get("system.host");
            String returnUrl = refreshUrl + "/" + usertype + "/sponsor/" + uid;

            Stripe.apiKey = apiKey;

            AccountLinkCreateParams linkParams =
                    AccountLinkCreateParams.builder()
                            .setAccount(stud.getStripeAccountId())
                            .setRefreshUrl(refreshUrl)
                            .setReturnUrl(returnUrl)
                            .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                            .build();

            AccountLink accountLink = AccountLink.create(linkParams);
            req.setRedirect(true);
            req.setRedirectLocation(accountLink.getUrl());

        }catch(Exception ex){
            ex.printStackTrace();
            cache.set("message", "We are sorry!");
            return "redirect:/";
        }
        return "";
    }

    @JsonOutput
    @Post("/register")
    public String register(NetworkRequest req, SecurityManager manager, FlashMessage message, ViewCache cache){
        User user = req.get(User.class);
        user.setEmail(earthsHelp.getSpaces(user.getEmail()));

        User storedUser = userRepo.get(user.getEmail());
        if(storedUser != null){

            if(user.getUserType().equals(storedUser.getUserType())){
                message.set("you are already in our system! alrght!");
                if(user.getUserType().equals("sponsor")){
                    return "redirect:/sponsor";
                }
                return "redirect:/signup";
            }

            if(!user.getUserType().equals(storedUser.getUserType())){
                storedUser.setUserType("mix");

                if(user.getUserType().equals("sponsor")){
                    storedUser.setFantastic(true);
                }
                userRepo.update(storedUser);
                message.set("you are now both someone in need and a sponsor!");
                return "redirect:/saint/mix";
            }

        }

        if(user.getName() == null || user.getName().equals("")){
            message.set("forgive us, we need a name.");
            if(user.getUserType().equals("sponsor")){
                return "redirect:/sponsor";
            }
            return "redirect:/signup";
        }

        if(user.getEmail() == null || user.getEmail().equals("")){
            message.set("please enter a valid email.");
            if(user.getUserType().equals("sponsor")){
                return "redirect:/sponsor";
            }
            return "redirect:/signup";
        }

        if(user.getPasswd() == null || user.getPasswd().equals("")){
            message.set("please enter a password.");
            if(user.getUserType().equals("sponsor")){
                return "redirect:/sponsor";
            }
            return "redirect:/signup";
        }

        String passwd = manager.hash(user.getPasswd());
        user.setPasswd(passwd);
        user.setUid(earthsHelp.getString(18).toLowerCase());//that took effort
        user.setGuid(earthsHelp.getString(18).toLowerCase());

        if(user.getUserType().equals("sponsor")){
            user.setRef(earthsHelp.getRef(6).trim());
            user.setFantastic(true);
        }

        String saint = req.getValue("saint");
        System.out.println("saint:" + saint);
        if(saint != null && user.getUserType().equals("sponsor") && saint.equals("on")){
            user.setUserType("mix");
        }

        userRepo.save(user);
        User savedUser = userRepo.getSaved();
        Long id = savedUser.getId();

        String permission = earthsHelp.getUserMaintenance() + id;
        userRepo.savePermission(id, permission);

        User superUser = userRepo.get("mike@plsar.net");
        Long superId = superUser.getId();
        String superPermission = earthsHelp.getUserMaintenance() + superId;
        userRepo.savePermission(superId, superPermission);


        AccountCreateParams accountParams =
                AccountCreateParams.builder()
                        .setType(AccountCreateParams.Type.STANDARD)
                        .build();

        try {

            RouteAttributes routeAttributes = req.getRouteAttributes();
            String apiKey = routeAttributes.get("stripe.key");
            Stripe.apiKey = apiKey;

            Account account = Account.create(accountParams);
            savedUser.setStripeAccountId(account.getId());
            System.out.println("zreg:" + savedUser.getStripeAccountId());
            userRepo.update(savedUser);

        }catch(StripeException strp){
            strp.printStackTrace();
        }

        if(user.getUserType().equals("sponsor")){
            message.set("successfully registered.");
            return "redirect:/sponsor/registered";
        }

        message.set("successfully registered.");
        return "redirect:/saint/registered";
    }
}
