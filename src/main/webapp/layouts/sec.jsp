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
    <script src="/assets/reaction/jquery.js"></script>
    <script src="/assets/reaction/confetti.js"></script>

</head>
<body>

<div class="container">

    <div id="header">
        <div id="logo-wrapper">
            <a href="/#index" class="logo-header">Earths+<br/>
                <span class="spirit">Extraordinary</span>
            </a>
        </div>
        <div id="hrefs-wrapper">
            <div class="nav-href-wrapper">
                <stargzr:authenticated>
                    Welcome back <a href="/saints/edit/${saintid}" style="margin-top:7px;float:none;" class="href-dotted">${saintname}</a>!&nbsp;
                </stargzr:authenticated>
                <stargzr:guest>
                    <a href="/#signin" style="margin-top:7px;float:none;">Signin</a>
                </stargzr:guest>
            </div>
            <div class="nav-href-wrapper">

                <a href="/#index">Go Home!</a>
            </div>
        </div>

        <br class="clear"/>
    </div>

    <br class="clear"/>

    <stargzr:content/>

    <br class="clear"/>

    <p class="information cntr-align nanum" style="margin-top:60px;width:100%">
        Made on Planet
        <img src="/assets/media/earth.png" style="width:30px;opacity:0.9;display:block;margin:4px auto;"/><br/>
    </p>

    <stargzr:authenticated>
        <a href="/signout" style="margin-top:7px;float:none;" class="href-dotted">Signout</a>
    </stargzr:authenticated>

</div>

<div class="bottom"></div>

</body>
</html>