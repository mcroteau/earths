<style>
    h1{font-size:70px}
    mark{font-weight:600;font-family: Roboto !important;}
</style>
<div class="casa-sccs">
    <h2>Congratulations!</h2>
    <h1><mark>Do you need a bank account?</mark></h1>

    <div class="glideleft" style="width:400px">
        <h3>No! Im Okay!</h3>
        <p>Ah, we see. Ok. Let's enter your banking information
        on Stripe so you get direct deposits.</p>
        <a href="/saints/onboarding/{{guid}}" class="btn">Enter Banking Info!</a>
    </div>
    <div class="glideleft" style="width:400px;margin-left:20px;">
        <h3>Yes! Lets create an Free Bank Account!</h3>
        <p>Fantastic, lets setup the account now!</p>
        <a href="/saints/bank/{{guid}}" class="btn green">Open Bank Account</a>
    </div>
    <br class="clear"/>

    <h2>You have a Profile!</h2>
    <p>You have a profile on Earth's Extraordinary!
        Would you like to see it?</p>
    <a href="#saints/{{saint.guid}}" class="btn orange">Let's see it! Go!</a>
</div>