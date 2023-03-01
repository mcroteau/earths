package io.earths;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.earths.model.CityJson;
import io.earths.model.NationJson;
import io.earths.model.*;
import io.earths.model.StateJson;
import io.earths.repo.*;
import net.plsar.Dao;
import net.plsar.PersistenceConfig;
import net.plsar.annotations.ServerStartup;
import net.plsar.drivers.Drivers;
import net.plsar.implement.ServerListener;
import net.plsar.security.SecurityManager;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ServerStartup
public class EarthsStartup implements ServerListener {

    Gson gson;

    public EarthsStartup(){
        gson = new Gson();
    }


    @Override
    public void startup() {

        PersistenceConfig persistenceConfig = new PersistenceConfig();
        persistenceConfig.setDriver(Drivers.H2);
        persistenceConfig.setUrl("jdbc:h2:~/Earths");
        persistenceConfig.setUser("sa");
        persistenceConfig.setPassword("");

        Dao dao = new Dao(persistenceConfig);
        UserRepo userRepo = new UserRepo(dao);
        RoleRepo roleRepo = new RoleRepo(dao);
        PlaceRepo placeRepo = new PlaceRepo(dao);
        NationRepo nationRepo = new NationRepo(dao);
        StateRepo stateRepo = new StateRepo(dao);
        TownRepo townRepo = new TownRepo(dao);

        Role superRole = roleRepo.get("super");
        Role saintRole = roleRepo.get("saint");
        Role sponsorRole = roleRepo.get("sponsor");
        Role kindPersonRole = roleRepo.get("kindperson");
        Role employeeRole = roleRepo.get("slave");

        if(superRole == null){
            superRole = new Role();
            superRole.setName("super");
            roleRepo.save(superRole);
        }

        if(saintRole == null){
            saintRole = new Role();
            saintRole.setName("saint");
            roleRepo.save(saintRole);
        }

        if(sponsorRole == null){
            sponsorRole = new Role();
            sponsorRole.setName("sponsor");
            roleRepo.save(sponsorRole);
        }

        if(kindPersonRole == null){
            kindPersonRole = new Role();
            kindPersonRole.setName("kindperson");
            roleRepo.save(kindPersonRole);
        }

        EarthsHelp earthsHelp = new EarthsHelp();
        Path path = Paths.get("src", "main", "webapp", "assets", "media", "une.png");
        String imageUri = path.toAbsolutePath().toString();
        StringBuilder image = new StringBuilder();
        image.append(earthsHelp.getEncodedPrefix(imageUri));
        image.append(earthsHelp.getEncoded(imageUri));

        User existing = userRepo.getPhone("9073477052");
        superRole = roleRepo.get("super");

        SecurityManager securityManager = new SecurityManager(null, null);
        if(existing == null){
            User superUser = new User();
            superUser.setUid(earthsHelp.getString(21).toLowerCase());
            superUser.setGuid(earthsHelp.getString(21).toLowerCase());
            superUser.setName("Super!");
            superUser.setPhone("9073477052");
            superUser.setEmail("mike@ae0n.net");
            superUser.setPasswd(securityManager.hash("effort"));
            superUser.setUserType("super");
            superUser.setTimeCreated(earthsHelp.getTime());
            userRepo.save(superUser);
            User savedUser = userRepo.getSaved();

            savedUser.setImage(image.toString());
            userRepo.update(savedUser);

            userRepo.savePersonRole(savedUser.getId(), superRole.getId());
            String permission = earthsHelp.getUserMaintenance() + savedUser.getId();
            userRepo.savePermission(savedUser.getId(), permission);

        }

        genMocks(image, earthsHelp, userRepo, placeRepo, townRepo, stateRepo, nationRepo);

    }

    public void genMocks(StringBuilder image, EarthsHelp earthsHelp, UserRepo userRepo, PlaceRepo placeRepo, TownRepo townRepo, StateRepo stateRepo, NationRepo nationRepo){
        try {

            Path nationsPath = Paths.get("src", "main", "resources", "countries+states+cities.json");
            File nationsFile = new File(nationsPath.toAbsolutePath().toString());

            BufferedReader nationsReader = new BufferedReader(new FileReader(nationsFile));
            StringBuilder nationsBuilder = new StringBuilder();

            String line = null;
            String ls = System.getProperty("line.separator");

            while ((line = nationsReader.readLine()) != null) {
                nationsBuilder.append(line);
                nationsBuilder.append(ls);
            }
            nationsBuilder.deleteCharAt(nationsBuilder.length() - 1);
            nationsReader.close();

            Type nationsListType = new TypeToken<ArrayList<NationJson>>(){}.getType();
            List<NationJson> nations = gson.fromJson(nationsBuilder.toString(), nationsListType);

            Integer count = 0;
            for(NationJson nationJson : nations){
                if(count > 3)break;count++;
                Nation nation = new Nation();
                nation.setName(nationJson.getName());
                nation.setIso(nationJson.getIso2());
                nation.setIso3(nationJson.getIso3());
                nation.setCapital(nationJson.getCapital());
                nation.setNativeName(nationJson.getNative());
                nation.setCurrency(nationJson.getCurrency());
                nation.setCurrencyName(nationJson.getCurrency_name());
                nation.setCurrencySymbol(nationJson.getCurrency_symbol());
                nation.setPhoneCode(nationJson.getPhone_code());
                nation.setNumericCode(nationJson.getNumeric_code());
                nation.setRegion(nationJson.getRegion());
                nation.setSubregion(nationJson.getSubregion());
                nation.setLat(Double.valueOf(nationJson.getLatitude()));
                nation.setLng(Double.valueOf(nationJson.getLongitude()));
                nation.setEmoji(nationJson.getEmojiU());
                nationRepo.save(nation);
                Nation savedNation = nationRepo.getSaved();
                Long nationId = savedNation.getId();

                for(StateJson stateJson : nationJson.getStates()){
                    State state = new State(nationId, stateJson.getName());
                    state.setCode(stateJson.getCode());
                    if(stateJson.getLatitude() != null && stateJson.getLongitude() != null){
                        state.setLat(Double.valueOf(stateJson.getLatitude()));
                        state.setLng(Double.valueOf(stateJson.getLongitude()));
                    }
                    stateRepo.save(state);
                    State savedState = stateRepo.getSaved();
                    Long stateId = savedState.getId();

                    for(CityJson cityJson: stateJson.getCities()){
                        Town town = new Town(stateId, nationId, cityJson.getName());
                        town.setCode(cityJson.getCode());
                        town.setLat(Double.valueOf(cityJson.getLatitude()));
                        town.setLng(Double.valueOf(cityJson.getLongitude()));
                        townRepo.save(town);
                    }
                }count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




        String[] places = { "Miami Homeless Shelter", "New Durham Chapel", "Los Angeles Mission" };

        Place one = new Place();
        one.setName(places[0]);
        one.setUri(earthsHelp.toUri(places[0]));
        one.setAddress("345 Iway, Witchitaw, Kansas");
        one.setLat("45.452191");
        one.setLon("-123.9128525");
        one.setTownId(1L);
        placeRepo.save(one);

        Place dos = new Place();
        dos.setName(places[1]);
        one.setUri(earthsHelp.toUri(places[1]));
        dos.setAddress("625 H. Street, Kalamazoo, Michigan");
        dos.setLat("38.891032");
        dos.setLon("-77.168679");
        dos.setTownId(1L);
        placeRepo.save(dos);

        Place tres = new Place();
        tres.setName(places[2]);
        one.setUri(earthsHelp.toUri(places[2]));
        tres.setAddress("4724 1/4 Admiralty Way, Marina Del Rey, CA 90292");
        tres.setLat("33.9544111");
        tres.setLon("-118.4867234");
        tres.setTownId(1L);
        placeRepo.save(tres);


        String[] saints = {
                "Zeus Peach", "Aphrodite Berry", "Ares Sky", "Dantes Inferno"
        };
        String[] sponsors = new String[]{
                "Mitch Rithmithgan", "Cheech Nordom", "Tito Chavez", "Lisa Churnem", "Manuel Smith"
        };
        String[] employees = new String[]{
                "Rithmithgani Mitch", "Nordomahgahni Cheech", "Chavezihopa Tito", "Churnemechna Lisa", "Smithhani Manuel"
        };

        List<String> refs = new ArrayList<>();
        createPeople(image, "sponsor", sponsors, userRepo, nationRepo, earthsHelp, refs);
        createPeople(image, "saint", saints, userRepo, nationRepo, earthsHelp, refs);
//        createPeople(earthsHelp.getEmployee(), employees, userRepo, earthsHelp);

    }

    void createPeople(StringBuilder image, String group, String[] people, UserRepo userRepo, NationRepo nationRepo, EarthsHelp earthsHelp, List<String> refs){

        SecurityManager securityManager = new SecurityManager(null, null);

        String[] lats = {"34.009534","34.035476","33.959244","34.196973", "34.196971"};
        String[] lons = {"-118.322326","-118.216904","-118.075999","-118.467387","-118.467300"};

        Nation nation = nationRepo.iso3("USA");
        for(int idx = 0; idx < people.length; idx++) {
            String name = people[idx];
            User user = new User();//steve weatherby
            user.setUid(earthsHelp.getString(21).toLowerCase());
            user.setGuid(earthsHelp.getString(21).toLowerCase());
            user.setEmail(group + idx + "@ae0n.net");
            user.setUserType(group);
            user.setPasswd(securityManager.hash("effort"));
            user.setTimeCreated(earthsHelp.getTime());
            String ref = earthsHelp.getRef(6);
            user.setRef(ref);
            userRepo.save(user);

            User savedUser = userRepo.getSaved();
            savedUser.setNationId(1L);
            savedUser.setStateId(1L);
            savedUser.setTownId(2L);

            System.out.println("z:" + savedUser.getUserType());
            String refsvalue = "";
            for(String refvalue : refs){
                refsvalue += refvalue + ",";
            }

            savedUser.setRefs(refsvalue);
            savedUser.setLat(Double.valueOf(lats[idx]));
            savedUser.setLng(Double.valueOf(lons[idx]));
            savedUser.setBio("As a kid, I dreamed of becoming an electrician but had kids too early, had to leave them with their mother and soon found myself on the street.");
            savedUser.setNeeds("yes:yes:no:yes:no:no:no");//or let wales, england or isle of man get him
            userRepo.update(savedUser);

            if(refs.size() < 3) {
                refs.add(ref);
            }

            userRepo.savePersonRole(savedUser.getId(), group);
            String permission = earthsHelp.getUserMaintenance() + savedUser.getId();
            userRepo.savePermission(savedUser.getId(), permission);

            savedUser.setName(name);
            savedUser.setPhone("9073477052");
            savedUser.setOnboarded(true);
            savedUser.setStripeAccountId("acct_1MXCQ7FmJqzDcbKO");
            userRepo.update(savedUser);


            savedUser.setImage(image.toString());
            userRepo.updateImage(savedUser);

            UserPlace userPlace = new UserPlace();
            userPlace.setUserId(savedUser.getId());
            userPlace.setPlaceId(2L);

            userRepo.savePlace(userPlace);
        }
    }
}
