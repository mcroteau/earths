package io.earths.model;

public class SaintRef {
    public SaintRef() {}

    public SaintRef(Long sponsorId, Long saintId) {
        this.sponsorId = sponsorId;
        this.saintId = saintId;
    }

    Long id;
    Long sponsorId;
    Long saintId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFantasticId() {
        return sponsorId;
    }

    public void setFantasticId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Long getSaintId() {
        return saintId;
    }

    public void setSaintId(Long saintId) {
        this.saintId = saintId;
    }
}
