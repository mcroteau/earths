<html>
<head>
    <title>Earths+ Extraordinary : ${title}</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, userCredential-scalable=no">

    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>

    <link rel="icon" type="image/png" href="/assets/media/mrkr.png">
    <link rel="stylesheet" href="/assets/grid.css">
    <link rel="stylesheet" href="/assets/default.css">
    <link rel="stylesheet" href="/assets/irish.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ractive/1.4.0/ractive.min.js" integrity="sha512-PZFuxjUwWqMaq5255WJAk2qsa+4uEGdwWgn9qa10j+RBsRF0XqDRMs7nTB6HH9GPTeWXi+YV+z/a2jtnnXpZjA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/assets/reaction/jquery.js"></script>
    <script src="/assets/reaction/confetti.js"></script>
    <script src="/assets/reaction/signals.js"></script>
    <script src="/assets/reaction/hasher.js"></script>
    <script src="/assets/reaction/crossroads.js"></script>
<%--    <script src="/assets/reaction/bshh.js"></script>--%>

</head>
<body>
    <%--todo: move all css to irish.css--%>

    <style>
        .container{z-index:100;}
        .a,.b,.c{position:absolute;top:0px;bottom:0px;left:0px;right:0px;}
        .a{z-index: 1;opacity:0.1}
        .b{
            z-index:1;
            opacity:0.9;
            background: rgb(230, 244, 254);
            background: linear-gradient(180deg, #A1C6B2 0%, rgba(255,255,255,1) 100%);
            background-attachment: fixed;
            background:#fff;
        }
        .c{z-index: 100}
        #mdl-bkdp{display:none;top:0px;left:0px;bottom:0px;right:0px;position:fixed;z-index:3;background:rgba(34,45,64,0.02);}
        #mdl{width:350px !important;text-align:center;margin:100px calc(50% - 175px);padding:10px;border-radius: 24px;background:#fff;}
    </style>
<%--    <div class="b"></div>--%>

    <div id="mdl-bkdp">
        <div id="mdl" class="extraordinary">
            <p style="width:100%;">Welcome to Earth"s Extraordinary.
                We are dedicated to assisting efforts to abolish homelessness,
                deliver clean water and alleviate hunger.</p>

            <p class="notify">We use cookies, are you okay with this?</p>
            <a href="http://www.un.com" class="href-dotted">No</a>
            <a href="javascript:" class="btn green" id="g" style="margin:auto;">Yes, I would like to move forward</a>
        </div>
    </div>

    <div class="container">

        <div id="header">
            <div id="logo-wrapper">
                <a href="#index" class="logo-header">Earths
                    <br/>
                    <span class="spirit">extraordinary</span>
                </a>
            </div>
            <div id="hrefs-wrapper">
                <div class="nav-href-wrapper">
                    <stargzr:authenticated>
                        Welcome back <a href="/saints/edit/${saintid}" style="margin-top:7px;float:none;" class="href-dotted">${saintname}</a>!&nbsp;
                    </stargzr:authenticated>
                    <stargzr:guest>
                        <a href="#signup" style="margin-top:7px;float:none;">Signup!</a>
                    </stargzr:guest>
                </div>
                <div class="nav-href-wrapper">
                    <a href="#gift" class="btn" style="margin-top:-7px;">Make a Donation</a>
                </div>
                <div class="nav-href-wrapper">
                    <a href="#sponsors">Become a<br/> Sponsor!</a>
                </div>
                <div class="nav-href-wrapper">
                    <a href="#locate">Locate a<br/>Person in Need</a>
                </div>
            </div>

            <br class="clear"/>
        </div>

        <br class="clear"/>

    </div>

    <div class="bkdp">
        <div class="msg">
            <h1>Working...</h1>
            <p>Your request is being processed...</p>
        </div>
    </div>

    <style>
        .bkdp{top:0px;left:0px;bottom:0px;right:0px;position:fixed;z-index:3;background:rgba(34,45,64,0.03);display:none;}
        .msg{width:350px;margin:100px calc(50% - 175px);padding:13px;border-radius: 24px;box-shadow:0px 3px 109px rgba(0,0,0,0.3);background:#fff;position:relative;}
        .msgz{position:absolute !important;top:auto;left:auto; right:20px;bottom:20px;}
    @keyframes grow {
        0% {transform: scale(1);}
        30%{transform: scale(1.3);}
        41%{transform: scale(1);}
        100% {transform: scale(1);}
    }
    </style>

    <div class="container index">

        <div id="view-wrapper"></div>
        <br class="clear"/>

        <p class="information cntr-align" style="margin-top:60px;width:100%">
            Made on Planet
            <img src="/assets/media/earth.png" class="earth"/><br/>
        </p>

        <stargzr:authenticated>
            <a href="/signout" style="margin-top:7px;float:none;" class="href-dotted">Signout</a>
        </stargzr:authenticated>

        <div class="bottom"></div>
    </div>

    <style>
        #discover{height:190px;margin:0px 0px 30px;border: solid 4px rgba(255,255,255,0.2);}
    </style>

    <stargzr:foreach items="${sponsors}" var="sponsor">
        ${sponsor.ref},
    </stargzr:foreach>




    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQulbBR1VkrQsKwisM1mLEyEMRUoT2GCI&callback=create_map&libraries=&v=weekly" async></script>
<script>

    let bgm = new Audio();
    bgm.src = "https://cdn.pixabay.com/download/audio/2023/01/29/audio_a51c6acffe.mp3";
    // bgm.src = "https://cdn.pixabay.com/download/audio/2021/09/16/audio_f914525cb6.mp3";
    let ck = new Audio();
    ck.src = "/assets/media/ck.mp3";
    function create_map(){
        console.log('.');
    }


    $(document).ready(function(){

        if(!sessionStorage.getItem("aprvd")){
            $("#mdl-bkdp").show();
        }
        $("#g").click(function(){
            $("#mdl-bkdp").remove();
            sessionStorage.setItem("aprvd", true);
        });
        $(".bzz").click(function(){
            if(!playing()){
                bgm.play();
            }else{
                bgm.pause()
            }
        });

        crossroads.addRoute("index", function(id) {
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/casa/index.jsp").then(function (vw) {
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
                $(".saint-btn").click(function(evt){
                    evt.preventDefault();
                    ck.play();
                    $(".bkdp").show();
                    $(".saint-btn").prop("disabled", true);
                    $.post("/register", $(".saint-frm").serialize(), function(data) {
                            console.log(data);
                            if(!data.response.okay) {
                                ractive.set("message", data.response.message);
                                $(".saint-btn").prop("disabled", false);
                            }
                            if(data.response.okay){
                                hasher.setHash("consent/" + data.guid);
                            }
                            $(".bkdp").hide();
                        },
                        "json"
                    );
                });
            });
        });

        crossroads.addRoute("intel", function(){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/casa/intel.jsp").then(function (vw) {
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
            });
        });

        let ty = new Audio();
        ty.src = 'https://cdn.pixabay.com/download/audio/2021/11/24/audio_ff7f56c397.mp3'
        crossroads.addRoute("consent/{guid}", function(guid){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/saint/consent.jsp").then(function (vw) {
                ty.play();
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw,
                    data : { guid: guid }
                });
                $("#view-wrapper").fadeIn(200);
                $('.okay').click(function(){
                    hasher.setHash("saints/onboarding/" + data.guid);
                })
            });
        });

        crossroads.addRoute("saints/onboarding/{guid}", function(guid){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/saint/onboarding.jsp").then(function (vw) {
                $.get('/saints/' + guid).then(function(data){
                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data : data.cache
                    });
                    $("#view-wrapper").fadeIn(200);
                })
            });
        });

        crossroads.addRoute("gift", function() {
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/gift/select.jsp").then(function(vw){
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
            });
        });

        crossroads.addRoute("gift/{monthly}/{guid}", function(monthly, guid){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/gift/index.jsp").then(function (vw) {
                if(guid === "earths.extraordinary") {
                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data : { monthly : (monthly === "true") }
                    });
                    setgftevnts();
                    setupfrm();
                    $("#view-wrapper").fadeIn(200);
                }
                if(guid != null) {
                    $.get("/saints/" + guid).then(function (data) {
                        data.cache.monthly = (monthly === "true");
                        data.cache.count = data.cache.sponsors.length;
                        let ractive = new Ractive({
                            target: "view-wrapper",
                            template: vw,
                            data: data.cache
                        });
                        setgftevnts();
                        setupfrm();
                        $("#view-wrapper").fadeIn(200);
                    })
                }
            });
        });

        crossroads.addRoute("locate", function(){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/saint/locate.jsp").then(function (vw) {
                $.get("/saints/query?q=", function(resp) {

                    let saints = resp.cache.saints;

                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data: resp.cache
                    });


                    $('#query').keydown(function(evt){
                        evt.preventDefault();
                        console.log(evt.keyCode);
                        if(evt.keyCode === 'Enter'){
                            $.get("/saints/query?q=", function(resp) {

                            });
                        }
                        console.log('.');
                    })

                    $('#query-btn').click(function(evt){
                        evt.preventDefault();
                        console.log('.');
                    })


                    for (let idx = 0; idx < saints.length; idx++) {
                        console.log(saints[idx])
                        let saint = saints[idx];

                        let start = {
                            lat: 42.7432082,
                            lng: -89.5296815
                        };
                        const map = new google.maps.Map(document.getElementById("discover"), {
                            zoom: 2,
                            center: start
                        });


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
                });
                $("#view-wrapper").fadeIn(200);
            });
        });

        crossroads.addRoute("sponsors", function(){

            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/sponsor/signup.jsp").then(function(vw){
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
                $(".fstc-btn").click(function(evt){
                    evt.preventDefault();
                    ck.play();
                    $(".bkdp").show();
                    $(".fstc-btn").prop("disabled", true);
                    $.post("/register", $(".sponsor-frm").serialize(), function(data) {
                        console.log(".", data);
                            if(!data.response.okay) {
                                ractive.set("message", data.response.message);
                                $(".fstc-btn").prop("disabled", false);
                            }
                            if(data.response.okay){
                                hasher.setHash("sponsors/onboarding/" + data.guid);
                            }
                            $(".bkdp").hide();
                        },
                        "json"
                    );
                });
            });
        });

        crossroads.addRoute("sponsors/onboarding/{guid}", function(guid){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/sponsor/onboarding.jsp").then(function(vw){
                $.get("/sponsors/" + guid).then(function(data){
                    let sponsor = data.cache.sponsor;
                    sponsor.ref = sponsor.ref.toUpperCase();
                    data = { sponsor : sponsor }
                    if(sponsor.userType === "mix"){
                        data.sponsor.mix = true;
                    }
                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data : data
                    });
                    $("#view-wrapper").fadeIn(200);
                });
            });
        });

        crossroads.addRoute("receipt/{guid}", function(guid){
            startConfetti();
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/gift/receipt.jsp").then(function(vw){
                $.get("/receipt/" + guid).then(function(data){
                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data : data.cache
                    });
                    $("#view-wrapper").fadeIn(200);
                });
            });
        });

        crossroads.addRoute("signin", function(){
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/signin.jsp").then(function(vw){
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
            });
        });

        crossroads.addRoute("signup", function(id) {
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/signup.jsp").then(function (vw) {
                let ractive = new Ractive({
                    target: "view-wrapper",
                    template: vw
                });
                $("#view-wrapper").fadeIn(200);
                $(".saint-btn").click(function(evt){
                    evt.preventDefault();
                    ck.play();
                    $(".bkdp").show();
                    $(".saint-btn").prop("disabled", true);
                    $.post("/register", $(".saint-frm").serialize(), function(data) {
                            console.log(data);
                            if(!data.response.okay) {
                                ractive.set("message", data.response.message);
                                $(".saint-btn").prop("disabled", false);
                            }
                            if(data.response.okay){
                                hasher.setHash("consent/" + data.guid);
                            }
                            $(".bkdp").hide();
                        },
                        "json"
                    );
                });
            });
        });

        crossroads.addRoute("saints/{guid}", function(guid) {
            console.log('here...');
            $("#view-wrapper").fadeOut(200);
            $.get("/assets/views/saint/index.jsp").then(function(vw){
                console.log('.');
                $.get("/saints/" + guid).then(function(data){
                    console.log(data);
                    let ractive = new Ractive({
                        target: "view-wrapper",
                        template: vw,
                        data : data.cache
                    });
                    $("#view-wrapper").fadeIn(200);
                });
            });
        });

        crossroads.addRoute("noop", function() {});
        function parseHash(newHash, oldHash){
            crossroads.parse(newHash);
        }
        hasher.initialized.add(parseHash);
        hasher.changed.add(parseHash);
        hasher.init();

        if(hasher.getHash() === "")hasher.setHash("index");

        const plym = function(){
            if(!playing()){
                bgm.play();
            }
        }

        const setupfrm = function(){
            $(".amt-btn").click(function(evt){
                evt.preventDefault();
                ty.play();
                $(".bkdp").show();
                $(".amt-btn").prop("disabled", true);
                $.post("/gift", $(".gft-frm").serialize(), function(data) {
                        if(!data.cache.response.okay) {
                            ractive.set("message", data.message);
                            $(".amt-btn").prop("disabled", false);
                        }
                        if(data.cache.response.okay){
                            hasher.setHash("receipt/" + data.cache.gift.guid);
                        }
                        $(".bkdp").hide();
                    },
                    "json"
                );
            });
        }

        const setgftevnts = function(){
            let $amt = $(".amount"),
                $self = $("#define"),
                $amtInpt = $(".amt-inpt"),
                $adtnInpt = $(".adn-amt-inpt");

                $amtBtn = $(".amt-btn"),
                $options = $(".option"),
                $adtnopts = $(".adtn-opt"),
                $confetti = $(".conftti"),
                $five = $("#five");

            const guid ="${saint.guid}";

            let base = 0, additional = 0;
            $options.click(function (evnt) {

                var $target = $(evnt.target)
                $options.removeClass("active")
                $target.toggleClass("active")
                let amount = $target.attr("data-amount")

                let amt = amount;
                let it = false;
                if(amount.includes("000000")){
                    amt = amount.replaceAll("000000", "");
                    amt += "m";
                    it = true;
                }

                if(!it && amount.includes("000")){
                    amt = amount.replaceAll("000", "");
                    amt += "k";
                }

                base = amount;

                $amt.html(amt);
                $amtInpt.val(amount);

                let sum = parseInt(amount) + parseInt(additional);
                $amtBtn.val("Donate $" + sum + "");

            });

            $adtnopts.click(function (evnt) {

                var $target = $(evnt.target)
                $adtnopts.removeClass("active")
                $target.toggleClass("active")
                let amount = $target.attr("data-amount")

                let amt = amount;
                additional = amount;
                $adtnInpt.val(amount);

                let sum = parseInt(base) + parseInt(amount);
                $amtBtn.val("Donate $" + sum + "");

            });

            $confetti.click(function(evt){
                startConfetti();
                setTimeout(function(){
                    stopConfetti();
                }, 300)
            })

            $(".five").click();
        }

        const playing = function () {
            return bgm
                && bgm.currentTime > 0
                && !bgm.paused
                && !bgm.ended
                && bgm.readyState > 2;
        }

    })




</script>



</body>
</html>