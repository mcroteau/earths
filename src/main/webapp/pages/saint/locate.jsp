
<h1>Locate Person in Need!</h1>
<p style="margin:0px 0px 10px;">Search Earth's Remarkable's database for people in need,
    contact, donate or become a sponsor!</p>

<style>
    #query-wrapper{
        width:601px;
        position:relative;
    }
    #query{
        color:#144f58;
        font-size:30px !important;
        font-weight: 200;
        padding:10px 100px 10px 50px !important;
        padding-left:60px !important;
        border-radius: 50px !important;
    }
    #query::placeholder{
        color: #ceb6a0;
        font-size:14px !important;
    }
    #query-btn{
        right:0px;
        font-size: 16px !important;
        padding: 23px 22px;
        background:#427EE3;
        border:solid 1px #427EE3;
        border-top-left-radius: 0px;
        border-bottom-left-radius: 0px;
        box-shadow: 0 3px 5px rgba(0,0,0,0.1);
        position:absolute;
    }
    .location-select{margin:3px 30px 30px 0px;}
    .location-select select{max-width:190px;}
    #query-results-wrapper img{border:solid 4px rgba(255,255,255,0.2)}
    #query-wrapper label{font-family: Roboto+Condensed !important}
</style>

<div id="query-wrapper">

    <form class="qf">
        <img src="/assets/media/mrkr.png" style="position:absolute;left:10px; top:4px;width:50px;"/>
        <input type="text" name="query" id="query" placeholder="Search by name" value=""/>
        <input type="button" value="Go!" class="btn qbn" id="query-btn"/>
    </form>

    <br class="clear"/>

</div>

<div id="discover" style="width:100%;border-radius: 19px"></div>

<h1>Search Results</h1>

<p style="font-size:13px;margin:0px 0px 3px 0px;">${saints.size()} people found!</p>

<style>
    .abbr{color:#fff;font-size: 29px;font-weight:900;padding:19px 24px;border-radius:49px;background:#0d72bf}
</style>


<div id="query-results-wrapper">
    <table>
        {{#each saints as saint}}
            <tr>
                <td>
                    {{#saint.image}}
                        <a href="/{{saint.guid}}" style=""><img src="{{saint.image}}"/></a>
                    {{/saint.image}}
                </td>
                <td>
                    <a href="/{{saint.guid}}" class="href-dotted">{{saint.name}}<br/>
                        <span>{{saint.town}}, {{saint.nationCode}}</span></a>
                </td>
                <td><a href="/{{saint.guid}}" class="btn">Donate $</a></td>
            </tr>
        {{/each}}
    </table>
</div>

<style>
    table{width:60%;}
    table td{padding:10px 10px;}
    table td img{ width:99px; border-radius:100px; }
    table td a{font-size:29px !important}
    table td span{font-size:16px;}

    #discover {
        height: 230px;
        display: inline-block;
        overflow: hidden;
        border: solid 4px rgba(255,255,255,0.2);
    }
</style>


<br class="clear"/>

<div class="bottom"></div>
