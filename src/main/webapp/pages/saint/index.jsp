
<style>
    #discover {
        height: 230px;
        width:100%;
        display: inline-block;
        overflow: hidden;
        border-radius: 21px;
        border: solid 4px rgba(255,255,255,0.2);
    }
    .selection{ margin: 0px 0px 30px; }
    .scts{margin-left:39px;}
    .infrma{float:left;margin-right:19px;vertical-align: middle; height:400px;}
    .prf{width:230px;border-radius: 200px;}
     .abbr{color:#fff;font-size: 29px;width:100px;font-weight:900;padding:19px 24px;border-radius:49px;background:#0d72bf}
</style>

<div class="sct">
    <div class="glideleft infrma" style="height:400px;width:400px;">

        {{#saint.image}}
            <img src="{{saint.image}}" class="prf"/>
        {{/saint.image}}
        <h1>{{saint.name}}</h1>
        {{#saint.town}}
            <p class="information">{{saint.town}}, {{saint.nation}}</p>
        {{/saint.town}}
        <p>{{saint.bio}}</p>

        <p>Exactly where he/she is located.</p>
        <div id="discover"></div>

        <style>
            .img{width:50px;border-radius: 30px;}
        </style>

        {{#if sponsors}}
            <h2>Sponsors/Aka Fantastics</h2>

            <style>
                .fc{font-size: 29px; font-weight: 900;}
            </style>
            {{#each sponsors as sponsor, @index as i, @keypath as path}}
                <div class="fc">
                    <img src="{{sponsor.image}}" class="img"/>
                    {{sponsor.name}}
                   <p>"{{sponsor.quote}}"</p>
                </div>
            {{/each}}

        {{/if}}

    </div>

    <div class="glideleft scts" style="width:400px;">
        <h1>Make a Donation!</h1>

        <div class="selection">
            <h2>One Time Gift</h2>
            <p>It's quick, it's painless and you could be helping someone!<br/>
                <a href="#gift/false/{{saint.guid}}" class="btn">$ Give One Time!</a></p>
        </div>

        <div class="selection">
            <h2>Monthly Gift</h2>
            <p>Give a gift that keeps on giving. They will love you for life!<br/>
                <a href="#gift/true/{{saint.guid}}" class="btn green">$ Give Monthly!</a></p>
        </div>

        <div class="selection">
            <h2>Gift Packages</h2>
            <p>$ Walmart Gift Card, Boost Mobile Phone for 1 year<br/>
                <a href="#pre/{{saint.guid}}" class="btn orange">Give a Gift!</a>
            </p>
        </div>
    </div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQulbBR1VkrQsKwisM1mLEyEMRUoT2GCI&callback=create_map&libraries=&v=weekly" async></script>
<script>
    function create_map() {

        const lat = parseFloat("{{saint.lat}}");
        const lng = parseFloat("{{saint.lng}}");

        let start = {
            lat: parseFloat("{{saint.lat}}"),
            lng: parseFloat("{{saint.lng}}")
        };
        const map = new google.maps.Map(document.getElementById("discover"), {
            zoom: 2,
            center: start
        });

        if(lat != 0 && lng != 0) {
            const marker = new google.maps.Marker({
                animationDuration: 1000,
                position: {
                    lat: parseFloat("{{saint.lat}}"),
                    lng: parseFloat("{{saint.lng}}")
                },
                map: map,
            });

            const popupInfo = function () {
                console.log('z');
            }
            marker.addListener("click", popupInfo);
        }
    }
</script>
