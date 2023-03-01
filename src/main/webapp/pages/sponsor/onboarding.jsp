<style>
    h1{margin:0px 0px 12px;}
    p{width:60%;margin:0px auto 20px;}
    p span{font-weight:900;}
    .ref-code{font-size: 29px;font-weight: 700;}
    .ref-code mark{font-size: 59px; font-weight: 900;}
</style>
<div class="cntr-align">

    <h1>You are now a Sponsor!
        <svg id="extraordinary" version="1.2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 54 49" width="74" height="69">
            <style>
                .s0 { fill: #0e2e47 }
            </style>
            <path class="s0" d="m21 22.7l2.4-3.7 0.4-1.2 0.5-1.7 1.4-1.1h3.6l0.5 1.1-1.1 1.7-0.8 2.1 1.4 0.3 2.2-0.2 0.5 1.1-3.3 4.2-3.3 5.6-0.5 0.6-0.6-0.5 0.9-5.5 0.1-2-1.6-0.3h-1.6z"/>
            <path fill-rule="evenodd" class="s0" d="m15.4 39.1l-2.4-6.1-1-7v-7l1-4-0.4-5.4 2.4-2.6 6-4 7-1 10 4 2 3 1 6-1 11-3 6-3.8 5-2.2 3-4 2-3 1h-5zm1.2-28.2l-0.6 10.1v8l2 6 3.5 5 7.6-2 6.1-9.5 2.8-13.5-2-6-6.9-4.1-7.1 2.1z"/>
        </svg>
    </h1>

    <p>This means you can go out, introduce yourself to new people,
        and help them out. </p>

    <p class="ref-code">Code: <mark>{{sponsor.ref}}</mark></p>
    <p>The code above is for you to pass along to others
        that may potentially signup on our site. You may recieve
        compensation for your help.
    </p>

    {{#sponsor.mix}}
        <p>We also set you up a donation profile considering. We are
        going to try I think to help. Here is a link to your
        profile so you can share with others.<br/>
            <a href="#saints/{{sponsor.guid}}" class="btn orange">View Your Profile</a>
        </p>
    {{/sponsor.mix}}

    <p>"{{sponsor.quote}}"</p>

    <h1>Good Luck!</h1>

</div>