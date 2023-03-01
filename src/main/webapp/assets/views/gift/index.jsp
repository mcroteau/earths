<style>
    .prf{width:79px;border-radius: 39px;}
    h2 mark{line-height: inherit !important;vertical-align: inherit !important;font-family: Roboto !important;font-weight:900;}
</style>

<div class="extraordinary" style="width:570px;">

    {{#saint.image}}
    <img src="{{saint.image}}" class="prf"/>
    {{/saint.image}}
    {{^saint.image}}
    <div class="abbr">{{saint.abbrv}}</div>
    {{/saint.image}}

    <h1>{{saint.name}}</h1>

    {{#monthly}}
        <p>Make a Monthly Donation!</p>
    {{/monthly}}
    {{^monthly}}
        <p>Make a one time donation!</p>
    {{/monthly}}

    <style>
        td{text-align: center}
    </style>

    <table class="live">
        <tr>
            <td><a href="javascript:" class="option btn conftti five" id="five" data-amount="5">$5</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="10">$10</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="20">$20</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="50">$50</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="100">$100</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="250">$250</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="500">$500</a></td>
        </tr>
        <tr>
            <td><a href="javascript:" class="option btn conftti" data-amount="1000">$1K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="5000">$5K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="10000">$10K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="30000">$30K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="60000">$60K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="100000">$100K</a></td>
            <td><a href="javascript:" class="option btn conftti" data-amount="1000000">$1M</a></td>
        </tr>
    </table>

    {{#if sponsor}}
    <style>
        p span{font-weight:900; font-size: inherit}
    </style>
    <p class="info">There are <span>{{count}}</span> people helping <span>{{saint.name}}</span>,
        would you like to add an additional amount for them?</p>

    <p class="info">The additional amount selected will be split amoung the <span>{{count}}</span>
        sponsors/aka Fantastics.

        <style>
            .fnt-ie{width:23px;border-radius: 23px;}
        </style>
        <br/>
        {{#each sponsors as sponsor}}
        {{#sponsor.image}}
            <img src="{{sponsor.image}}" class="fnt-ie"/>
        {{/sponsor.image}}
            <span>{{sponsor.name}}</span>
        {{#quote}}
            <mark>"{{sponsor.quote}}"</mark>
        {{/quote}}
            <br/>
        {{/each}}

    </p>

    <style>
        a.adtn-amt{font-size:12px !important; font-weight:300;}
    </style>

    <div class="live">
        <a href="javascript:" class="adtn-opt btn adtn-amt" data-amount="5">$5</a>&nbsp;
        <a href="javascript:" class="adtn-opt btn adtn-amt" data-amount="10">$10</a>&nbsp;
        <a href="javascript:" class="adtn-opt btn adtn-amt" data-amount="15">$15</a>&nbsp;
        <a href="javascript:" class="adtn-opt btn adtn-amt" data-amount="25">$25</a>&nbsp;
        <a href="javascript:" class="adtn-opt btn adtn-amt" data-amount="30">$30</a>&nbsp;
    </div>
    {{/if sponsors}}

    <form method="post" class="gft-frm">

        <input type="hidden" name="uid" value="{{saint.guid}}"/>
        <input type="hidden" name="amount" class="amt-inpt" value=""/>
        <input type="hidden" name="additionalAmount" class="adn-amt-inpt" value=""/>

        {{#monthly}}
            <input type="hidden" name="monthly" value="true"/>
        {{/monthly}}
        {{^monthly}}
            <input type="hidden" name="monthly" value="false"/>
        {{/monthly}}


        {{#saint.id}}
            <input type="hidden" name="recipientId" value="{{saint.id}}"/>
            <input type="hidden" name="corporate" value="false"/>
        {{/saint.id}}
        {{^saint.id}}
            <input type="hidden" name="recipientId" value="1"/>
            <input type="hidden" name="corporate" value="true"/>
        {{/saint.id}}

        <div class="live">

            <label style="margin-top:20px;">First & Last Name</label>
            <input type="text" id="name" name="name" placeholder="Andrew Jones" style="width:90%;" value="Mike Croteau"/>

            <label style="margin-top:20px;">Credit Card Information</label>
            <input type="number" id="credit-card" name="creditCard" placeholder="4242424242424242" maxlength="16" style="width:90%;" value="4242424242424242"/>

            <br class="clear"/>
            <div class="cc-details">
                <label>Month</label>
                <input type="number" id="exp-month"name="expMonth" placeholder="12" maxlength="2" value="12"/>
            </div>

            <div class="cc-details">
                <label>Year</label>
                <input type="number" id="exp-year" name="expYear" placeholder="2029" maxlength="4" value="2023"/>
            </div>

            <div class="cc-details">
                <label>CVC</label>
                <input type="number" id="cvc" name="cvc" placeholder="123" maxlength="3" value="123"/>
            </div>

            <br class="clear"/>

            <label style="margin-top:10px;">Email to use for all Gifts!</label>
            <input type="text" name="email" id="email" value="mike@stargzr.net" placeholder="support@earths.io" style="width:100%;"/>

            <style>
                .amt-btn{padding: 23px;font-weight: 400 !important;}
            </style>
            <div style="text-align: center;margin:25px 0px 0px;">
                <input type="submit" class="btn amt-btn green" value="">
            </div>
        </div>
    </form>

</div>