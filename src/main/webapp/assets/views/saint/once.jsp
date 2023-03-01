<style>
    .prf{width:79px;border-radius: 39px;}
</style>
<div class="extraordinary" style="width:530px;">

    {{#saint.image}}
    <img src="{{saint.image}}" class="prf"/>
    {{/saint.image}}
    {{^saint.image}}
    <div class="abbr">{{saint.abbrv}}</div>
    {{/saint.image}}

    <h1>{{saint.name}}</h1>
    <h2>Give One Time!</h2>


    <h2 class="amount-dsh"><mark>$<span class="amount"></span></mark></h2>

    <div class="live">
        <a href="javascript:" class="option btn conftti five" id="five" data-amount="5">$5</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="10">$10</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="20">$20</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="50">$50</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="100">$100</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="250">$250</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="500">$500</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="1000">$1K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="5000">$5K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="10000">$10K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="30000">$30K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="60000">$60K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="100000">$100K</a>&nbsp;
        <a href="javascript:" class="option btn conftti" data-amount="1000000">$1M</a>&nbsp;
    </div>
    {{#if sponsors}}
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
        <input type="hidden" name="monthly" value="false"/>
        {{#saint.id}}
            <input type="hidden" name="recipient" value="{{saint.id}}"/>
        {{/saint.id}}
        {{^saint.id}}
            <input type="hidden" name="recipient" value="earths"/>
        {{/saint.id}}

        <br class="clear"/>

        <div class="live">

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