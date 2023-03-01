<a:if spec="${message != ''}">
    <p class="notify">${message}</p>
</a:if>

<h1>Profile Settings</h1>

<form action="/saints/update/${saint.id}" enctype="multipart/form-data" method="post">

    <input type="hidden" name="id" style="width:40%;" value="${saint.id}"/>
    <input type="hidden" name="userType" style="width:40%;" value="${saint.userType}"/>

    <style>
        .glideright:first-of-type{margin-left:30px;}
        .glideright p{width:100%;}
    </style>

    <stargzr:if spec="${!saint.onboarded}">
        <div class="glideright" style="width:190px;">
            <a href="/onboard/${saint.id}" class="button orange">Complete Onboarding</a>
            <p>The onboarding process is required in order to receive help
                on Earth's Extraordinary. All payments go through stripe.
                If you don't have a bank account, have no worries, we can take care of that too.</p>
        </div>
    </stargzr:if>

    <div class="glideright" style="width:290px;">
        <a href="/bank/${saint.id}" class="button">Open Free Bank Account</a>
        <p>If you don't have a bank account, have no worries,
            we take care of that too we partnered with Stripe to help us, all you
            need is an identification.</p>
    </div>

    <style>
        .edit-img{width:200px;border-radius: 120px;}
    </style>

    <div class="glideleft" style="width:610px;">

        <a href="/#saints/${saint.guid}" class="href-dotted">View Public Profile</a><br/>

        <stargzr:if spec="${saint.image != 'null'}">
            <img src="${saint.image}" class="edit-img" style="border: solid 4px rgba(255,255,255,0.2);"/>
        </stargzr:if>
        <stargzr:if spec="${saint.image == 'null'}">
            <div class="char">${saint.abbrv}</div>
        </stargzr:if>

        <label>Profile Image</label>
        <input type="file" name="image"/>

        <br class="clear"/>

        <label>Name</label>
        <input type="text" name="name" style="width:40%;" value="${saint.name}"/>
        <input type="hidden" name="email" style="width:40%;" value="${saint.email}"/>
        <input type="hidden" name="stripeAccountId" style="width:40%;" value="${saint.stripeAccountId}"/>

        <br class="clear"/>
        <style>
            h1{margin:30px 0px 0px;}
            .signup-frm{}
            .signup-signin{
                margin:30px 0px 30px;
            }
            .location-select{
                margin:7px 0px 7px;
                margin-right:40px;
                max-width:190px;
            }
            .section-header{margin:0px 0px 0px;}
        </style>

        <h1>Your Story</h1>
        <p>Give us your background. People with big hearts will see this.</p>
        <textarea name="bio" style="width:70%;border-radius: 23px !important;height:230px;">${saint.bio}</textarea>

        <h1 class="section-header" style="margin-top:50px">Location Settings</h1>

        <style>
            #discover{height:290px;border-radius:23px;border: solid 4px rgba(255,255,255,0.2);}
        </style>

        <p>Click the map to set your position. This will tell people where you are at.</p>
        <div id="discover"></div>

        <input type="hidden" name="lat" id="lat" value="${saint.lat}"/>
        <input type="hidden" name="lng" id="lng" value="${saint.lng}"/>

        <div class="glideleft location-select">
            <label>Select Country/Nation</label>
            <select name="nationId" id="nations">
                <option value="0">Select Nation</option>
                <stargzr:foreach items="${nations}" var="nation">
                    <stargzr:set var="selected" val=""/>
                    <stargzr:if spec="${nation.id == saint.nationId}">
                        <stargzr:set var="selected" val="selected"/>
                    </stargzr:if>

                    <option value="${nation.id}" data-lat="${nation.latitude}" data-lng="${nation.lnggitude}" ${selected}>${nation.name}</option>
                </stargzr:foreach>
            </select>
        </div>


        <div class="glideleft location-select">
            <label>Select State/Provice</label>
            <select name="stateId" id="states">
                <option value="0">Select State</option>
                <stargzr:foreach items="${states}" var="state">
                    <stargzr:set var="selected" val=""/>
                    <stargzr:if spec="${state.id == saint.stateId}">
                        <stargzr:set var="selected" val="selected"/>
                    </stargzr:if>
                    <option value="${state.id}" data-lat="${state.latitude}" data-lng="${state.lng}" ${selected}>${state.name}</option>
                </stargzr:foreach>
            </select>
        </div>


        <div class="glideleft location-select">
            <label>Select City/Town</label>
            <select name="townId" id="cities">
                <option value="0">Select City</option>
                <stargzr:foreach items="${towns}" var="town">
                    <stargzr:set var="selected" val=""/>
                    <stargzr:if spec="${town.id == saint.townId}">
                        <stargzr:set var="selected" val="selected"/>
                    </stargzr:if>
                    <option value="${town.id}" data-lat="${town.latitude}" data-lng="${town.lng}" ${selected}>${town.name}</option>
                </stargzr:foreach>
            </select>
        </div>

    </div>

    <label>Fantastic Reference Codes</label>
    <p class="information">Yeah, we know you are a sponsor, but you might have still been
        referred by someone. If you are being referred to this site by someone,
        or more than one Fantastic person, please enter their reference codes below
        comma seperated.</p>
    <textarea name="refs" style="width:60%;" placeholder="abc-xyz">${saint.refs}</textarea>

    <br class="clear"/>

    <input type="submit" value="Update Profile" class="btn green signup-signin"/>

</form>



<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQulbBR1VkrQsKwisM1mLEyEMRUoT2GCI&callback=create_map&libraries=&v=weekly" async></script>
<script>

    function create_map() {

        let start = {
            lat: 42.7432082,
            lng: -89.5296815
        };

        const map = new google.maps.Map(document.getElementById("discover"), {
            zoom: 3,
            center: start
        });

        let activeMarker = new google.maps.Marker({
            animationDuration: 1000,
            position: {
                lat: parseFloat("${saint.lat}"),
                lng: parseFloat("${saint.lng}")
            },
            map: map,
        });

        // let infoWindow = new google.maps.InfoWindow({
        //     content: "Click the map to set your position. This will tell people where you are at.",
        //     position: start,
        // });
        // infoWindow.open(map);

        let marker = null;
        map.addListener("click", (mapsMouseEvent) => {

            const pn = mapsMouseEvent.latLng;

            if(marker != null) {
                marker.setMap(null);
                activeMarker.setMap(null)
            }

            marker = new google.maps.Marker({
                animationDuration: 1000,
                position: pn,
                map: map,
            });

            $('#lat').val(pn.lat)
            $('#lng').val(pn.lng)

            marker.setMap(map);
            // Close the current InfoWindow.
            // infoWindow.close();
        });


        const center_town = function(evt){
            const lat = $('#cities option:selected').attr('data-lat')
            const lng = $('#cities option:selected').attr('data-lng')

            if (lat != null && lng != null) {
                map.setCenter({lat: parseFloat(lat), lng: parseFloat(lng)});
                map.setZoom(9);
            }else{
                map.setZoom(2)
            }
        }

        const retrieve_states = function(evt) {
            $('#states').html('<option value="0">Select State</option>');
            $.ajax({
                url: "/states/" + $('#nations').val(),
                success : state_success
            });
        }

        const retrieve_cities = function(evt){
            $('#cities').html('<option value="0">Select City</option>');
            $.ajax({
                url: "/cities/" + $('#states').val(),
                success : cities_success
            });
        }

        const state_success = function(resp){
            $(resp).each(function(idx, state){
                $('#states').append('<option value="' + state.id + '" data-lat="' + state.lat + '" data-lng="' + state.lng + '">' + state.name + '</option>');
            })
        }

        const cities_success = function(resp){
            $(resp).each(function(idx, town){
                $('#cities').append('<option value="' + town.id + '" data-lat="' + town.lat + '" data-lng="' + town.lng + '">' + town.name + '</option>');
            })
        }

        $('#nations').change(retrieve_states)
        $('#states').change(retrieve_cities)
        $('#cities').change(center_town);

        retrieve_states();
        retrieve_cities();

    }

</script>