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
            <a href="#index" class="logo-header">Earths+
                <br/>
                <span class="spirit">Extraordinary</span>
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

    <div id="view-wrapper">
        <stargzr:content/>
    </div>
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

<a:foreach items="${sponsors}" var="sponsor">
    ${sponsor.ref},
</a:foreach>


<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQulbBR1VkrQsKwisM1mLEyEMRUoT2GCI&callback=create_map&libraries=&v=weekly" async></script>

</body>
</html>