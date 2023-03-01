<stargzr:if spec="${message != ''}">
    <p class="notify">${message}</p>
</stargzr:if>

<h2>Data Ingest</h2>
<form action="/ingest/perform" method="post">
    <label>Place</label>
    <span class="tiny"></span>
    <input type="text" name="place" placeholder="Charity Organization" value="Charity Organization" style="width: 100%;" disabled="disabled"/>
    <label>Town/City Name</label>
    <span class="information"></span>
    <input type="text" name="location" placeholder="" value="" style="width: 100%;"/>

    <div class="button-wrapper">
        <input type="submit" value="Perform Ingest!" class="button green" style="width: 100%;"/>
    </div>
</form>
<stargzr:foreach items="${places}" var="place">
    <div>
        <h2>${place.name}</h2>
    </div>
</stargzr:foreach>