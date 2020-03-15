package com.udc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sdrahnea
 */
@Entity
@Table(name = "coronavirus")
public class Coronavirus extends CoreEntity {

  @JsonProperty("country")
  @Column(name = "country")
  private String country;

  @JsonProperty("cases")
  @Column(name = "cases")
  private Long cases;

  @Column(name = "datetime")
  private String datetime;

  public Coronavirus(String country, long cases){
    this.country = country;
    this.datetime = datetime;
  }

  public Coronavirus(String datetime, String country, long cases){
    this.country = country;
    this.datetime = datetime;
    this.cases = cases;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Long getCases() {
    return cases;
  }

  public void setCases(Long cases) {
    this.cases = cases;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }
}
