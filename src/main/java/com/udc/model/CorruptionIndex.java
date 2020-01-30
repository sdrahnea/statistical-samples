package com.udc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sdrahnea
 */
@Entity
@Table(name = "corruption_index")
public class CorruptionIndex extends CoreEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "iso3")
    private String iso3;

    @Column(name = "region")
    private String region;

    @Column(name = "cpi_score")
    private long cpiScore;

    @Column(name = "rank")
    private long rank;

    @Column(name = "standard_error")
    private double standardError;

    @Column(name = "year")
    private long year;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getCpiScore() {
        return cpiScore;
    }

    public void setCpiScore(long cpiScore) {
        this.cpiScore = cpiScore;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public double getStandardError() {
        return standardError;
    }

    public void setStandardError(double standardError) {
        this.standardError = standardError;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
