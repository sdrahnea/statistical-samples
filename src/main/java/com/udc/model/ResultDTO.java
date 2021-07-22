package com.udc.model;

import lombok.Data;

@Data
public class ResultDTO implements Comparable<ResultDTO> {

    private Integer number;
    private Integer count = 0;

    public ResultDTO(Integer number) {
        this.number = number;
        this.count = 1;
    }

    public void increaseCount(){
        this.count++;
    }

    @Override
    public int compareTo(ResultDTO other) {

        if(getCount() == null || other.getCount() == null) {
            return 0;
        }

        return getCount().compareTo(other.getCount());
    }
}
