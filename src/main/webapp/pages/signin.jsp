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

    <style>
        html{padding:0px 60px;}
        .signup-signin{margin:30px 0px 30px;}
        #logo-wrapper{float:none;}
        .logo-header{font-size:49px;}
        .signup-frm{foat:none}
        .bzz{position:absolute;top:0px;left:230px;animation:none;}
    </style>
</head>
<body>

    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 570 439" width="30" height="39" class="bzz">
        <path class="bz0" d="m272.5 327c-29 0-52.5-17-52.5-38 0-21 23.5-38 52.5-38 29 0 52.5 17 52.5 38 0 21-23.5 38-52.5 38z"/>
        <path fill-rule="evenodd" class="s0" d="m375.4 154.9c62-26.1 122.1-23.3 134.6 6.3 12.5 29.6-27.5 74.6-89.4 100.7-62 26.1-122.1 23.3-134.6-6.3-12.5-29.6 27.5-74.6 89.4-100.7zm-69 91.1c8.2 19.5 55.1 18.3 104.9-2.6 49.8-21 83.4-53.8 75.1-73.3-8.2-19.5-55.1-18.3-104.9 2.7-49.7 21-83.3 53.7-75.1 73.2z"/>
        <path fill-rule="evenodd" class="s0" d="m144.8 261.4c-63.7-26.8-104.8-73.1-91.9-103.5 12.8-30.5 74.6-33.4 138.3-6.5 63.7 26.8 104.8 73.1 91.9 103.5-12.8 30.5-74.6 33.4-138.3 6.5zm117.4-16.3c8.4-20.1-26.1-53.7-77.3-75.3-51.2-21.5-99.4-22.8-107.8-2.7-8.5 20 26.1 53.7 77.3 75.2 51.1 21.6 99.3 22.8 107.8 2.8z"/>
    </svg>

    <div id="logo-wrapper">
        <a href="/#index" class="logo-header">Earths+
            <br/>
            <span class="spirit">Extraordinary</span>
        </a>
    </div>
    <br class="clear"/>

    <stargzr:if spec="${message != ''}">
        <p class="notify">${message}</p>
    </stargzr:if>

    <div class="signup-frm">
        <h1>Signin!</h1>
        <form action="/signin" method="post">
            <label>Email</label>
            <input type="text" name="email" style="width:60%;" value="${email}" placeholder="support@earths.io" />

            <label>Password</label>
            <input type="password" name="passwd" style="width:60%;" value="${passwd}" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"/>

            <br class="clear"/>

            <input type="submit" value="Signin" class="btn green signup-signin"/>

            <p>Are you a person in need? Do you need a place to live? A good meal?
                Money to help you get started in life? Register! Let us try help!
                <a href="/#signup">Sign Up!</a>.
            </p>
        </form>
    </div>

    <div class="bottom"></div>

</body>
</html>