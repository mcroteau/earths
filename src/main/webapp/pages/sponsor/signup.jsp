<style>
    h1{margin:0px;}
    h1 mark{font-weight: 900}
    .signup-signin{margin-top:30px}
    .sponsor{width:300px;margin-right:40px;}
    .sponsor p{width:100%;}
    .register-frm{width:600px;}
    .tagline{font-size:29px;font-weight:400;font-family:Roboto !important;margin:0px 0px 30px;}
</style>

<h1>Become a Sponsor!</h1>
<p class="tagline">Make a little money while helping people in need!</p>

<div class="glideleft sponsor">
    <p>A Sponsor is someone who gives their time to help those in
    need without the possibility of being gifted.
    However, we did something, we offer the ability to give monetary gifts
    to those who are helping others.</p>

    <h2 style="margin-top:30px;">How does it work?</h2>
    <p>Once registered on the Earth's Extraordinary, a Sponsor can go out, meet
    new people that are in need, give them their account id and refer them to the
    site. If the person in need registers, accepts donations, the individual who
        made the donation can optionally gift the Remarkable who is helping the
        person in need.</p>
</div>

<div class="glideleft register-frm">

    <h2>Signup Now!</h2>

    <a:if spec="${message != ''}">
        <p class="notify">{{message}}</p>
    </a:if>

    <form method="post" class="sponsor-frm">

        <input type="hidden" name="onidx" value="false"/>
        <input type="hidden" name="userType" value="sponsor"/>

        <label>Name</label>
        <input type="text" name="name" style="width:60%;" placeholder="First & Last"/>

        <label>Email</label>
        <input type="text" name="email" style="width:70%;" placeholder="support@earths.io"/>

        <label>Password</label>
        <input type="password" name="passwd" style="width:60%;" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"/>

        <label>A Quote!</label>
        <p class="information">Being a sponsor means you are fantastic. Give us a
        quote for others to see.</p>
        <textarea name="quote" style="width:60%;" placeholder="tis' nobler in the mind.."></textarea>
        <br class="clear"/>

        <label>Sponsor Reference Codes</label>
        <p class="information">Yeah, we know you are a sponsor, but you might have still been
            referred by someone. If you are being referred to this site by someone,
            or more than one Fantastic person, please enter their reference codes below
            comma seperated.</p>
        <textarea name="refs" style="width:60%;" placeholder="abc-xyz"></textarea>
        <br class="clear"/>

        <p class="information">Are you also someone who needs a place to stay,
            food to eat or serious financial help?
            A checked box means YES!<br/>
            <input type="checkbox" name="saint" checked/>
        </p>


        <input type="submit" value="Become a Fantastic" class="btn green signup-signin fstc-btn"/>
    </form>
</div>

<br class="clear"/>
