<style>
    .casa{}
    .index-href{font-size:29px;}
    .signup-index{
        float:right;
        width:50%;
        position:relative;
    }
    .signup-signin{
        margin:30px 0px 30px;
    }
    .hln{font-size:29px;font-family:Roboto !important; line-height:1.5em;font-weight: 300;margin-top:20px;width:100%}
    .hln mark{font-size:29px;font-weight:700;font-family:Roboto !important; }
    .count-details{font-size:27px; font-weight: 300; font-family: Roboto !important}
</style>


<div class="casa">

    <div class="glideleft" style="width:500px;">

        <p class="hln">
            There are problems in this World,
            <mark>Earths Extraordinary is dedicated to assisting efforts to abolish homelessness, deliver clean water and alleviate hunger.</mark>
            <a href="#intel" class="href-dotted index-href">Learn More.</a></p>

        <br class="clear"/><br class="clear"/>

        <div class="count">9.13M+
            <p class="count-details">People die each year from <br/> Starvation
                <a href="http://www.un.org" target="_blank" class="href-dotted index-href">www.un.org</a></p>
        </div>

        <div class="count">957M+
            <p class="count-details">
                957 million people<br/> globally suffer from<br/>
                food insecurities. <a href="http://www.un.org" target="_blank" class="href-dotted index-href">www.un.org</a>
            </p>
        </div>

        <div class="count">2 Minutes
            <p class="count-details">
                Every 2 minutes<br/>
                a child dies from a water-related<br/>
                disease.
                <a href="http://www.water.org" target="_blank" class="href-dotted index-href">www.water.org</a></p>
        </div>

        <div class="count">1.6B
            <p class="count-details">
                1.6+ billion people<br/> people globally live in inadequate housing conditions.
                <a href="https://ighomelessness.org/about-us/" target="_blank" class="href-dotted index-href">www.ighomelessness.org</a>
            </p>
        </div>
    </div>
    <div class="signup-index glideright">

        <h2>Signup!</h2>
        <p style="font-size:22px">Are you a person in need? Do you need a place to live? Food to eat? Money to help you get started in life? Register! Let us help you out!</p>

        <form method="post" class="saint-frm">

            <input type="hidden" name="onidx" value="true"/>
            <input type="hidden" name="userType" value="saint"/>

            {{#message}}
            <p class="notify">{{message}}</p>
            {{/message}}

            <label>Name</label>
            <input type="text" name="name" style="width:60%;" placeholder="First & Last"/>

            <label>Email</label>
            <input type="text" name="email" style="width:80%;" placeholder="support@earths.io"/>

            <label>Password</label>
            <input type="password" name="passwd" style="width:60%;" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"/>

            <label>Fantastic Reference Codes</label>
            <p class="information">If you are being referred to this site by someone,
                or more than one Fantastic person, please enter their reference codes below
                comma seperated.</p>
            <textarea name="refs" style="width:60%;" placeholder="abc-xyz"></textarea>

            <br class="clear"/>

            <input type="submit" value="Register Now!" class="btn green signup-signin saint-btn"/>

            <style>
                .pcg{display:none}
                .pcgi{position:relative;clear:both;}
            </style>

            <p class="pcg">
                Registering... please wait!
            </p>

        </form>
    </div>
</div>
