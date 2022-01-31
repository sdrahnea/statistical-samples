package com.udc.model;

import lombok.Data;

import java.text.DecimalFormat;

@Data
public class ResultRecordDTO {

    private String date;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    public ResultRecordDTO(String date, int num1, int num2, int num3, int num4, int num5, int num6) {
        this.date = date;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
    }

    @Override
    public String toString(){
        return date
                + format(num1) + format(num2) + format(num3)
                + format(num4) + format(num5) + format(num6);

    }

    private String format(int num){
        return String.format("%3d", num);
    }
}
