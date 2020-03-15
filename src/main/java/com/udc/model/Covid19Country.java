package com.udc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Covid19Country {

    @JsonProperty("name")
    private String name;

    @JsonProperty("causes")
    private Long causes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCauses() {
        return causes;
    }

    public void setCauses(Long causes) {
        this.causes = causes;
    }
}
