
<a:if spec="${message != ''}">
    <p class="notify">${message}</p>
</a:if>

<p>Are you a person in need? Do you need a place to live? A good meal?
    Money to help you get started in life? Register! Let us try help!
    <a href="/#signup">Sign Up!</a>.
</p>

<div class="signup-frm">
    <h1>Signin!</h1>
    <form action="/signin" method="post">
        <label>Email</label>
        <input type="text" name="email" style="width:60%;" value="${email}" placeholder="support@earths.io" />

        <label>Password</label>
        <input type="password" name="passwd" style="width:60%;" value="${passwd}" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"/>

        <br class="clear"/>

        <input type="submit" value="Signin" class="btn green signup-signin"/>
    </form>
</div>

<div class="bottom"></div>
