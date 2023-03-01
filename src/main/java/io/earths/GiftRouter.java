package io.earths;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import io.earths.model.Gift;
import io.earths.model.Nation;
import io.earths.model.Response;
import io.earths.model.User;
import io.earths.repo.*;
import net.plsar.RouteAttributes;
import net.plsar.annotations.*;
import net.plsar.annotations.network.Get;
import net.plsar.annotations.network.Post;
import net.plsar.model.NetworkRequest;
import net.plsar.model.ViewCache;
import net.plsar.security.SecurityManager;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NetworkRouter
public class GiftRouter {
    public GiftRouter(){
        this.gson = new Gson();
        this.earthsHelp = new EarthsHelp();
        this.smsRelay = new SmsRelay();
    }

    Gson gson;
    EarthsHelp earthsHelp;
    SmsRelay smsRelay;

    @Bind
    GiftRepo giftRepo;

    @Bind
    UserRepo userRepo;

    @Bind
    NationRepo nationRepo;

    @Bind
    SaintRepo saintRepo;

    @Bind
    SponsorRepo sponsorRepo;


    @JsonOutput
    @Get("/receipt/{guid}")
    public String receipt(ViewCache cache,
                          @Component String guid){
        List<Gift> gis = giftRepo.all();
        System.out.println(gis.size());
        Gift gift = giftRepo.guid(guid);
        cache.set("gift", gift);
        return gson.toJson(cache);
    }


    @JsonOutput
    @Post("/gift")
    public String gift(NetworkRequest req,
                          SecurityManager security,
                          ViewCache cache) {

        Gift gift = req.get(Gift.class);
        User saint = saintRepo.guid(gift.getUid());
        String guid = earthsHelp.getGift(42).toLowerCase();
        gift.setGuid(guid);

        if (gift.isCorporate()) {
            saint = new User();
            saint.setStripeAccountId("acct_1IMOfEC8zACgHOFS");
        }

        if (gift.getAmount() == null) {
            Response response = new Response(false, "Please enter an amount you would like to gift!");
            return gson.toJson(response);
        }

        //todo:clean email on reg
        gift.setEmail(earthsHelp.getSpaces(gift.getEmail()));
        if (!earthsHelp.isValidMailbox(gift.getEmail())) {
            Response response = new Response(false, "forgive us, we need a good email address.");
            return gson.toJson(response);
        }
        gift.setCreditCard(earthsHelp.getSpaces(gift.getCreditCard()));
        if (gift.getCreditCard().equals("")) {
            Response response = new Response(false, "credit card invalid.");
            return gson.toJson(response);
        }
        if (gift.getExpMonth().equals("")) {
            Response response = new Response(false, "credit card exp month invalid.");
            return gson.toJson(response);
        }
        if (gift.getExpYear().equals("")) {
            Response response = new Response(false, "credit card exp year invalid.");
            return gson.toJson(response);
        }
        if (gift.getCvc().equals("")) {
            Response response = new Response(false, "credit card cvc code invalid.");
            return gson.toJson(response);
        }


        try {

            RouteAttributes routeAttributes = req.getRouteAttributes();
            String apiKey = routeAttributes.get("stripe.key");

            Stripe.apiKey = apiKey;

            User kindPerson = userRepo.get(gift.getEmail());

            if (kindPerson == null) {
                kindPerson = new User();
                kindPerson.setEmail(gift.getEmail());
                kindPerson.setPasswd(security.hash(guid));
                kindPerson.setTimeCreated(earthsHelp.getTime());
                userRepo.save(kindPerson);
                kindPerson = userRepo.getSaved();

                userRepo.savePersonRole(kindPerson.getId(), "kindperson");
                String permission = earthsHelp.getUserMaintenance() + kindPerson.getId();
                userRepo.savePermission(kindPerson.getId(), permission);

            }

            gift.setKindPerson(kindPerson);
            gift.setKindPersonId(kindPerson.getId());
            gift.setKindPersonName(kindPerson.getName());
            gift.setProcessed(false);
            gift.setGiftTime(earthsHelp.getTime());

            Map<String, Object> card = new HashMap<>();
            card.put("number", gift.getCreditCard());
            card.put("exp_month", gift.getExpMonth());
            card.put("exp_year", gift.getExpYear());
            card.put("cvc", gift.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);

            if (saint.getRefs() != null && gift.getAdditionalAmount() != null) {

                List<User> sponsors = getSaintSponsors(saint);

                BigDecimal sponsoramt = gift.getAdditionalAmount().divide(new BigDecimal(sponsors.size()), new MathContext(3));
                Long sponsoramtcents = sponsoramt.movePointRight(2).longValue();

                for(User sponsor : sponsors){

                    Nation nation = nationRepo.get(sponsor.getNationId());

                    System.out.println("zsi:" + sponsor.getStripeAccountId());
                    RequestOptions tokenRequestOptions = RequestOptions.builder()
                            .setStripeAccount(sponsor.getStripeAccountId())
                            .build();
                    Token token = Token.create(params, tokenRequestOptions);

                    Map<String, Object> customerParams = new HashMap<>();
                    customerParams.put("email", gift.getEmail());
                    customerParams.put("source", token.getId());
                    RequestOptions customRequestOptions = RequestOptions.builder()
                            .setStripeAccount(sponsor.getStripeAccountId())
                            .build();
                    Customer customer = com.stripe.model.Customer.create(customerParams, customRequestOptions);


                    Map<String, Object> chargeParams = new HashMap<>();
                    chargeParams.put("amount", sponsoramtcents);
                    chargeParams.put("customer", customer.getId());
                    chargeParams.put("card", token.getCard().getId());
                    chargeParams.put("currency", "usd");

                    System.out.println("zgft:" + sponsor.getStripeAccountId());
                    RequestOptions chargeRequestOptions = RequestOptions.builder().setStripeAccount(sponsor.getStripeAccountId()).build();
                    com.stripe.model.Charge.create(chargeParams, chargeRequestOptions);
                }
            }

            if (gift.isMonthly()) {

                System.out.println("is monthly");

                Long amtcents = gift.getAmount().movePointRight(2).longValue();

                System.out.println("zsi:" + saint.getStripeAccountId());
                RequestOptions tokenRequestOptions = RequestOptions.builder()
                        .setStripeAccount(saint.getStripeAccountId())
                        .build();
                Token token = Token.create(params, tokenRequestOptions);

                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", gift.getEmail());
                customerParams.put("source", token.getId());
                RequestOptions customRequestOptions = RequestOptions.builder()
                        .setStripeAccount(saint.getStripeAccountId())
                        .build();
                Customer customer = com.stripe.model.Customer.create(customerParams, customRequestOptions);


                Map<String, Object> productParams = new HashMap<>();
                productParams.put("name", "earths extraordinary");

                RequestOptions productRequestOptions = RequestOptions.builder().setStripeAccount(saint.getStripeAccountId()).build();
                com.stripe.model.Product product = com.stripe.model.Product.create(productParams, productRequestOptions);

                Map<String, Object> interval = new HashMap<>();
                interval.put("interval", "month");

                Map<String, Object> priceParams = new HashMap<>();
                priceParams.put("product", product.getId());
                priceParams.put("unit_amount", amtcents);
                priceParams.put("currency", "usd");
                priceParams.put("recurring", interval);

                RequestOptions priceRequestOptions = RequestOptions.builder().setStripeAccount(saint.getStripeAccountId()).build();
                Price price = Price.create(priceParams, priceRequestOptions);


                List<Object> items = new ArrayList<>();
                Map<String, Object> item1 = new HashMap<>();
                item1.put("price", price.getId());
                items.add(item1);

                Map<String, Object> subscriptionParams = new HashMap<>();
                subscriptionParams.put("customer", customer.getId());
                subscriptionParams.put("items", items);
                if(!gift.isCorporate()) {
                    subscriptionParams.put("application_fee_percent", new BigDecimal(1.2, new MathContext(3)));
                }
                RequestOptions requestOptions = RequestOptions.builder().setStripeAccount(saint.getStripeAccountId()).build();
                Subscription subscription = com.stripe.model.Subscription.create(subscriptionParams, requestOptions);

                gift.setAmountCents(amtcents);
                gift.setSubscriptionId(subscription.getId());
                giftRepo.save(gift);
                Gift storedGift = giftRepo.getSaved();
                System.out.println(storedGift);
            }

            if (!gift.isMonthly()) {

                System.out.println("!monthly");

                BigDecimal appfee = gift.getAmount().multiply(new BigDecimal(0.012, new MathContext(3)));
                Long appfeecents = appfee.movePointRight(2).longValue();
                BigDecimal amountafter = gift.getAmount().subtract(appfee);
                Long amountcentsafter = amountafter.movePointRight(2).longValue();

                System.out.println("zsi:" + saint.getStripeAccountId());
                RequestOptions tokenRequestOptions = RequestOptions.builder()
                        .setStripeAccount(saint.getStripeAccountId())
                        .build();
                Token token = Token.create(params, tokenRequestOptions);

                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", gift.getEmail());
                customerParams.put("source", token.getId());
                RequestOptions customRequestOptions = RequestOptions.builder()
                        .setStripeAccount(saint.getStripeAccountId())
                        .build();
                Customer customer = com.stripe.model.Customer.create(customerParams, customRequestOptions);

                Map<String, Object> chargeParams = new HashMap<>();
                chargeParams.put("amount", amountcentsafter);
                chargeParams.put("customer", customer.getId());
                chargeParams.put("card", token.getCard().getId());
                if(!gift.isCorporate()) {
                    chargeParams.put("application_fee", appfeecents);
                }
                chargeParams.put("currency", "usd");

                System.out.println("zgft:" + saint.getStripeAccountId());
                RequestOptions chargeRequestOptions = RequestOptions.builder().setStripeAccount(saint.getStripeAccountId()).build();
                Charge charge = com.stripe.model.Charge.create(chargeParams, chargeRequestOptions);

                gift.setAmountCents(amountcentsafter);
                gift.setChargeId(charge.getId());
                giftRepo.save(gift);

                Gift storedGift = giftRepo.getSaved();
                System.out.println(storedGift);
            }


        }catch (StripeException sxe) {
            sxe.printStackTrace();
            Response response = new Response(false, "fail.");
            cache.set("gift", gift);
            cache.set("response", response);
            return gson.toJson(cache);
        }

        Response response = new Response(true, "effort?.");
        cache.set("gift", gift);
        cache.set("response", response);
        return gson.toJson(cache);
    }

    List<User> getSaintSponsors(User saint){
        List<User> sponsors = new ArrayList();
        String[] refs = saint.getRefs().split(",");
        for (String refPre : refs) {
            System.out.println(refPre);
            String ref = refPre.trim();
            User sponsor = sponsorRepo.ref(ref);
            if (sponsor != null) {
                sponsors.add(sponsor);
            }
        }
        return sponsors;
    }

}
