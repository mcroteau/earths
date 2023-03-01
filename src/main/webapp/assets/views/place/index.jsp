
<h1>Locate a Charity!</h1>
<p style="margin:0px 0px 10px;">Search Earth's Remarkable's database for Charitable
organizations in your area.</p>

<style>
    #query-wrapper{
        width:601px;
        position:relative;
    }
    #query{
        color:#144f58;
        font-size:30px !important;
        font-weight: 200;
        padding:10px 100px 10px 50px !important;
        padding-left:60px !important;
        border-radius: 50px !important;
    }
    #query::placeholder{
        color: #ceb6a0;
        font-size:14px !important;
    }
    #query-btn{
        right:0px;
        font-size: 16px !important;
        padding: 23px 22px;
        background:#427EE3;
        border:solid 1px #427EE3;
        border-top-left-radius: 0px;
        border-bottom-left-radius: 0px;
        position:absolute;
    }
    .location-select{margin:3px 30px 30px 0px;}
    .location-select select{max-width:190px;}
    #query-results-wrapper img{border:solid 4px rgba(255,255,255,0.2)}
</style>

<div id="query-wrapper">

<%--    <form action="/saints/locate">--%>
<%--        <img src="/assets/media/mrkr.png" style="position:absolute;left:10px; top:4px;width:50px;"/>--%>
<%--        <input type="text" name="q" id="query" placeholder="Search by name" value="${query}"/>--%>
<%--        <input type="submit" value="Go!" class="btn" id="query-btn"/>--%>
<%--    </form>--%>

    <div class="glideleft location-select">
        <label>Select by Nation</label>
        <select name="nation" id="nations">
            <option value="0">Select Nation</option>
            <stargzr:foreach items="${nations}" var="nation">
                <option value="${nation.id}" data-lat="${nation.latitude}" data-lng="${nation.lng}">${nation.name}</option>
            </stargzr:foreach>
        </select>
    </div>


    <div class="glideleft location-select">
        <label>Select State/Provice</label>
        <select name="state" id="states">
            <option value="0">Select State</option>
            <stargzr:foreach items="${states}" var="state">
                <option value="${state.id}" data-lat="${state.latitude}" data-lng="${state.lng}">${state.name}</option>
            </stargzr:foreach>
        </select>
    </div>


    <div class="glideleft location-select">
        <label>Select City/Town</label>
        <select name="town" id="cities">
            <option value="0">Select City</option>
            <stargzr:foreach items="${town}" var="town">
                <option value="${town.id}" data-lat="${town.latitude}" data-lng="${town.lng}">${town.name}</option>
            </stargzr:foreach>
        </select>
    </div>

    <br class="clear"/>

</div>

<div id="discover" style="width:100%;border-radius: 19px"></div>

<style>
    .abbr{color:#fff;font-size: 29px;font-weight:900;padding:26px 31px;border-radius:49px;background:#0d72bf}
</style>

<stargzr:if spec="${places.size() > 0}">

    <h1>Search Results</h1>

    <p style="font-size:13px;margin:0px 0px 3px 0px;">${places.size()} charities found!</p>

    <div id="query-results-wrapper">
        <table>
            <stargzr:foreach items="${places}" var="charity">
                <stargzr:if spec="${places.name != null}">
                    <tr>
                        <td>${places.name}<br/>
                            <span>${places.address}</span></a>
                        </td>
                    </tr>
                </stargzr:if>
            </stargzr:foreach>
        </table>
    </div>
</stargzr:if>

<style>
    table{width:60%;}
    table td{padding:10px 10px;}
    table td img{ width:99px; border-radius:100px; }
    table td a{font-size:29px !important}
    table td span{font-size:16px;}

    #discover {
        height: 230px;
        display: inline-block;
        overflow: hidden;
        border: solid 4px rgba(255,255,255,0.2);
    }
</style>


<br class="clear"/>

<div class="bottom"></div>

<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQulbBR1VkrQsKwisM1mLEyEMRUoT2GCI&callback=create_map&libraries=&v=weekly" async></script>--%>
<script>

    function create_map() {
        let start = {
            lat: 40.51403735907659,
            lng: -100.25233774999998
        };
        const map = new google.maps.Map(document.getElementById("discover"), {
            zoom: 2,
            center: start
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

            const lat = $('#nations option:selected').attr('data-lat')
            const lng = $('#nations option:selected').attr('data-lng')

            if (lat != null && lng != null) {
                map.setCenter({lat: parseFloat(lat), lng: parseFloat(lng)});
                map.setZoom(4);
            }else{
                map.setZoom(2)
            }


            $.ajax({
                url: "/states/" + $('#nations').val(),
                success : state_success
            });
        }

        const retrieve_cities = function(evt){
            $('#cities').html('<option value="0">Select City</option>');

            const lat = $('#states option:selected').attr('data-lat')
            const lng = $('#states option:selected').attr('data-lng')

            if (lat != null && lng != null) {
                map.setCenter({lat: parseFloat(lat), lng: parseFloat(lng)});
                map.setZoom(6);
            }else{
                map.setZoom(2)
            }
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

        const search_success = function(resp) {
            for (let idx = 0; idx < resp.length; idx++) {
                console.log(resp[idx])
                let saint = resp[idx];
                const marker = new google.maps.Marker({
                    animationDuration: 1000,
                    position: {
                        lat: parseFloat(saint.lat),
                        lng: parseFloat(saint.lng)
                    },
                    map: map,
                });
                marker.addListener("click", navigate("/" + saint.guid));

                function navigate(url) {
                    return function () {
                        window.location.href = url;
                    }
                }
            }
        }

        $.ajax({
            url: "/places/query?q=${query}",
            success : search_success
        });


        $('#nations').change(retrieve_states)
        $('#states').change(retrieve_cities)
        $('#cities').change(center_town);

        retrieve_states();
        retrieve_cities();

    }

</script>
