package io.earths.model;

import java.util.List;

public class User {

    Long id;
    String uid;
    String guid;
    String ref;
    String phone;
    String email;
    String passwd;
    String name;
    String image;
    Integer age;
    Integer yearsNeed;
    String bio;
    String quote;
    String needs;
    String userType;

    boolean onboarded;
    String stripeAccountId;
    String stripeCustomerId;
    Long timeCreated;

    String abbrv;
    String town;
    String nationCode;

    boolean onidx;

    boolean hasBank;
    boolean hasCell;
    boolean hasAddress;

    Double lat;
    Double lng;

    String address;
    String address2;
    String zipCode;

    Long townId;
    Long stateId;
    Long nationId;

    String refs;
    boolean sponsor;

    List<User> sponsors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getYearsNeed() {
        return yearsNeed;
    }

    public void setYearsNeed(Integer yearsNeed) {
        this.yearsNeed = yearsNeed;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isOnboarded() {
        return onboarded;
    }

    public void setOnboarded(boolean onboarded) {
        this.onboarded = onboarded;
    }

    public String getStripeAccountId() {
        return stripeAccountId;
    }

    public void setStripeAccountId(String stripeAccountId) {
        this.stripeAccountId = stripeAccountId;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getAbbrv() {
        return abbrv;
    }

    public void setAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public boolean isOnidx() {
        return onidx;
    }

    public void setOnidx(boolean onidx) {
        this.onidx = onidx;
    }

    public boolean isHasBank() {
        return hasBank;
    }

    public void setHasBank(boolean hasBank) {
        this.hasBank = hasBank;
    }

    public boolean isHasCell() {
        return hasCell;
    }

    public void setHasCell(boolean hasCell) {
        this.hasCell = hasCell;
    }

    public boolean isHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(boolean hasAddress) {
        this.hasAddress = hasAddress;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    public String getRefs() {
        return refs;
    }

    public void setRefs(String refs) {
        this.refs = refs;
    }

    public boolean isFantastic() {
        return sponsor;
    }

    public void setFantastic(boolean sponsor) {
        this.sponsor = sponsor;
    }

    public List<User> getFantastics() {
        return sponsors;
    }

    public void setFantastics(List<User> sponsors) {
        this.sponsors = sponsors;
    }
}
