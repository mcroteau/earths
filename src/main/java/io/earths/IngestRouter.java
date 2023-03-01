package io.earths;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.earths.model.*;
import io.earths.repo.NationRepo;
import io.earths.repo.PlaceRepo;
import io.earths.repo.StateRepo;
import io.earths.repo.TownRepo;
import net.plsar.RouteAttributes;
import net.plsar.annotations.Bind;
import net.plsar.annotations.Design;
import net.plsar.annotations.NetworkRouter;
import net.plsar.annotations.network.Get;
import net.plsar.annotations.network.Post;
import net.plsar.model.NetworkRequest;
import net.plsar.model.ViewCache;
import net.plsar.security.SecurityManager;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NetworkRouter
public class IngestRouter {
    public IngestRouter(){
        this.gson = new Gson();
        this.earthsHelp = new EarthsHelp();
    }

    Gson gson;
    EarthsHelp earthsHelp;

    @Bind
    PlaceRepo placeRepo;

    @Bind
    TownRepo townRepo;

    @Bind
    StateRepo stateRepo;

    @Bind
    NationRepo nationRepo;

    @Design("/layouts/guest.jsp")
    @Get("/ingest")
    public String index(){ return "pages/ingest/index.jsp"; }

    @Post("/ingest/perform")
    public String execute(NetworkRequest req, SecurityManager security, ViewCache cache) throws IOException {
        if(!security.isAuthenticated(req)){
            cache.set("message", "Please signin to continue...");
            return "redirect:/signin";
        }
        if(!security.hasRole(earthsHelp.getSuper(), req)){
            cache.set("message", "Please signin to continue...");
            return "redirect:/signin";
        }
        RouteAttributes routeAttributes = req.getRouteAttributes();
        String key = routeAttributes.get("goog.key");

        String placenames = req.getValue("place");
        String townname = req.getValue("location");

        if((placenames == null ||
                placenames.equals("")) &&
                (townname == null ||
                        townname.equals(""))){
            cache.set("message", "nope, not going to happen...");
            return "redirect:/ingest";
        }

        int count = 0;
        List<Place> stored = new ArrayList<>();
        String[] bits = placenames.split(",");
        for(String placename : bits) {
            if(placename.equals(""))continue;

            String q = placename + " in " + townname;
            String query = URLEncoder.encode(q, "utf-8");

            URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + query + "&radius=50000&key=" + key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream in = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(in);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            Type nationsListType = new TypeToken<ArrayList<NationJson>>(){}.getType();
            List<Place> places = gson.fromJson(sb.toString(), nationsListType);

            Town storedTown = townRepo.get(townname);

            if (storedTown == null) {
                Town town = new Town();
                town.setName(townname);
                townRepo.save(town);
                storedTown = townRepo.get(townname);
            }

            if(storedTown != null) {
                State state = stateRepo.getTown(storedTown.getId());
                Nation nation = nationRepo.getTown(storedTown.getId());

                for (int z = 0; z < places.size(); z++) {
                    Place placeJson = places.get(z);
                    String lat = placeJson.getGeometry().getLocation().getLat();
                    String lon = placeJson.getGeometry().getLocation().getLng();

                    String indexable = placeJson.getName() + " " + placeJson.getFormatted_address();
                    Place storedBusiness = placeRepo.find(lat, lon);
                    if (storedBusiness == null) {
                        Place place = new Place();
                        place.setName(place.getName());
                        place.setIndexable(indexable);
                        place.setLat(lat);
                        place.setLon(lon);
                        place.setTownId(storedTown.getId());
                        place.setStateId(state.getId());
                        place.setNationId(nation.getId());
                        place.setAddress(placeJson.getFormatted_address());
                        placeRepo.save(place);
                    }
                }
            }

            count = places.size();
            cache.set("places", places);
        }

        cache.set("message", "Successfully ingested " + count + " places!");
        return "redirect:/ingest";
    }
}
