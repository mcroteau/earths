<style>
    .signup-frm{}
    .signup-signin{
        margin:30px 0px 30px;
    }
</style>
<p>Already have an account? <a href="/signin" class="href-dotted">Signin!</a></p>

<div class="signup-frm">
    <h1>Register!</h1>
    <p>Are you a person in need? Do you need a place to live? A good meal? Money to help you get started in life? Register! Let us try to help!</p>

    <h3>What happens when you register?</h3>
    <p>After you register and finish the onboarding process, you will be able to receive donations directly on this site. We even go as
    far as working with a company to help you set up a bank account if you do not already have one.</p>

    <form method="post" class="saint-frm">

        {{#message}}
            <p class="notify">{{message}}</p>
        {{/message}}

        <input type="hidden" name="onidx" value="false"/>
        <input type="hidden" name="userType" value="saint"/>

        <label>Name</label>
        <input type="text" name="name" style="width:40%;" placeholder="first & last name" value="{{saint.name}}"/>

        <label>Email</label>
        <input type="text" name="email" style="width:70%;" placeholder="support@earths.io" value="{{saint.email}}"/>

        <label>Password</label>
        <input type="password" name="passwd" style="width:60%;" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;" value=""/>

        <label>Fantastic Reference Codes</label>
        <p class="information">If you are being referred to this site by someone,
            or more than one Fantastic person, please enter their reference codes below
            comma seperated.</p>
        <textarea name="refs" style="width:60%;" placeholder="abc-xyz"></textarea>

        <br class="clear"/>

        <input type="submit" value="Register Now!" class="btn green signup-signin saint-btn"/>
    </form>
</div>

<div class="bottom"></div>
