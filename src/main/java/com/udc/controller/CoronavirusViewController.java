package com.udc.controller;

import com.udc.model.Coronavirus;
import org.primefaces.model.chart.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/coronavirus-view")
@ManagedBean
@RequestScoped
@CrossOrigin("*")
public class CoronavirusViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HorizontalBarChartModel horizontalBarModel = new HorizontalBarChartModel();

    public static int index = 0;

    @PostConstruct
    public void init(){
    }

    public void initialise() {
        List<String> datetimes = new LinkedList<>();
        datetimes.add("20 January 2020");
        datetimes.add("21 January 2020");
        datetimes.add("23 January 2020");
        datetimes.add("24 January 2020");
        datetimes.add("25 January 2020");
        datetimes.add("26 January 2020");
        datetimes.add("27 January 2020");
        datetimes.add("28 January 2020");
        datetimes.add("29 January 2020");
        datetimes.add("30 January 2020");
        datetimes.add("31 January 2020");
        datetimes.add("1 February 2020");
        datetimes.add("2 February 2020");
        datetimes.add("3 February 2020");
        datetimes.add("4 February 2020");
        datetimes.add("5 February 2020");
        datetimes.add("6 February 2020");
        datetimes.add("7 February 2020");
        datetimes.add("8 February 2020");
        datetimes.add("9 February 2020");
        datetimes.add("10 February 2020");
        datetimes.add("11 February 2020");
        datetimes.add("12 February 2020");
        datetimes.add("13 February 2020");
        datetimes.add("14 February 2020");
        datetimes.add("15 February 2020");
        datetimes.add("16 February 2020");
        datetimes.add("17 February 2020");
        datetimes.add("18 February 2020");
        datetimes.add("19 February 2020");
        datetimes.add("20 February 2020");
        datetimes.add("21 February 2020");
        datetimes.add("22 February 2020");
        datetimes.add("23 February 2020");
        datetimes.add("24 February 2020");
        datetimes.add("25 February 2020");
        datetimes.add("26 February 2020");
        datetimes.add("27 February 2020");
        datetimes.add("28 February 2020");
        datetimes.add("29 February 2020");
        datetimes.add("1 March 2020");
        datetimes.add("2 March 2020");
        datetimes.add("3 March 2020");
        datetimes.add("4 March 2020");
        datetimes.add("5 March 2020");
        datetimes.add("6 March 2020");
        datetimes.add("7 March 2020");
        datetimes.add("8 March 2020");
        datetimes.add("9 March 2020");
        datetimes.add("10 March 2020");
        datetimes.add("11 March 2020");

        List<Coronavirus> data = populateInfo();

        if(((index % 4) == 0) && (index / 4) < datetimes.size()){
            String dateTime = datetimes.get(index / 4);
            horizontalBarModel = updateTheChart(dateTime, sortByCases(getData(dateTime, data)));
        }
        index++;
    }

    public HorizontalBarChartModel updateTheChart(String datetime, List<Coronavirus> data) {
        horizontalBarModel.clear();

        ChartSeries countries = new ChartSeries();
        countries.setLabel("Confirmed cases");
        for(Coronavirus element: data) {
            countries.set(element.getCountry(), element.getCases());
        }

        horizontalBarModel.addSeries(countries);

        horizontalBarModel.setTitle("Coronovirus Dynamic (2019-nCoV)");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        horizontalBarModel.setAnimate(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel(datetime);
        xAxis.setTickFormat("%d");
        xAxis.setMin(0);
        xAxis.setMax(getMax(data));

//        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
//        yAxis.setLabel("Countries");

        return horizontalBarModel;
    }

    private Long getMax(List<Coronavirus> list) {
        long max = 0;
        for(Coronavirus element: list) {
            if (max < element.getCases()) {
                max = element.getCases();
            }
        }
        return max;
    }

    private List<Coronavirus> getData(String datetime, List<Coronavirus> data) {
        List<Coronavirus> result = new LinkedList<>();
        for(Coronavirus element: data) {
            if (element.getDatetime().equalsIgnoreCase(datetime)
                    && !element.getCountry().equalsIgnoreCase("China")
            ){
                result.add(element);
            }
        }
        return result;
    }

    private List<Coronavirus> populateInfo() {
        List<Coronavirus> list = new LinkedList<>();
        String tempData = "20 January 2020";
        //  20 January 2020
        list.add(new Coronavirus(tempData, "China", 278));
        list.add(new Coronavirus(tempData, "Thailand", 2));
        list.add(new Coronavirus(tempData, "Republic of Korea", 1));
        list.add(new Coronavirus(tempData, "Japan", 1));

        // 21 January 2020
        list.add(new Coronavirus("21 January 2020", "China", 309));
        list.add(new Coronavirus("21 January 2020", "Thailand", 2));
        list.add(new Coronavirus("21 January 2020", "Republic of Korea", 1));
        list.add(new Coronavirus("21 January 2020", "Japan", 1));

        // 22 January 2020 is missing

        // 23 January 2020
        list.add(new Coronavirus("23 January 2020", "China", 571));
        list.add(new Coronavirus("23 January 2020", "Thailand", 4));
        list.add(new Coronavirus("23 January 2020", "Republic of Korea", 1));
        list.add(new Coronavirus("23 January 2020", "Japan", 1));
        list.add(new Coronavirus("23 January 2020", "USA", 1));

        //  24 January 2020
        list.add(new Coronavirus("24 January 2020", "China", 830));
        list.add(new Coronavirus("24 January 2020", "Thailand", 4));
        list.add(new Coronavirus("24 January 2020", "Republic of Korea", 2));
        list.add(new Coronavirus("24 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("24 January 2020", "Japan", 1));
        list.add(new Coronavirus("24 January 2020", "Singapore", 1));
        list.add(new Coronavirus("24 January 2020", "USA", 1));

        //  25 January 2020
        list.add(new Coronavirus("25 January 2020", "China", 1297));
        list.add(new Coronavirus("25 January 2020", "Thailand", 4));
        list.add(new Coronavirus("25 January 2020", "French Republic", 3));
        list.add(new Coronavirus("25 January 2020", "Japan", 3));
        list.add(new Coronavirus("25 January 2020", "Singapore", 3));
        list.add(new Coronavirus("25 January 2020", "Australia", 3));
        list.add(new Coronavirus("25 January 2020", "Republic of Korea", 2));
        list.add(new Coronavirus("25 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("25 January 2020", "USA", 2));
        list.add(new Coronavirus("25 January 2020", "Nepal", 1));

        //  26 January 2020
        list.add(new Coronavirus("26 January 2020", "China", 1985));
        list.add(new Coronavirus("26 January 2020", "Thailand", 5));
        list.add(new Coronavirus("26 January 2020", "Singapore", 4));
        list.add(new Coronavirus("26 January 2020", "Australia", 4));
        list.add(new Coronavirus("26 January 2020", "French Republic", 3));
        list.add(new Coronavirus("26 January 2020", "Japan", 3));
        list.add(new Coronavirus("26 January 2020", "Malaysia", 3));
        list.add(new Coronavirus("26 January 2020", "Republic of Korea", 2));
        list.add(new Coronavirus("26 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("26 January 2020", "USA", 2));
        list.add(new Coronavirus("26 January 2020", "Nepal", 1));

        //  27 January 2020
        list.add(new Coronavirus("27 January 2020", "China", 2761));
        list.add(new Coronavirus("27 January 2020", "USA", 5));
        list.add(new Coronavirus("27 January 2020", "Thailand", 5));
        list.add(new Coronavirus("27 January 2020", "Japan", 4));
        list.add(new Coronavirus("27 January 2020", "Singapore", 4));
        list.add(new Coronavirus("27 January 2020", "Australia", 4));
        list.add(new Coronavirus("27 January 2020", "Republic of Korea", 4));
        list.add(new Coronavirus("27 January 2020", "Malaysia", 4));
        list.add(new Coronavirus("27 January 2020", "French Republic", 3));
        list.add(new Coronavirus("27 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("27 January 2020", "Nepal", 1));
        list.add(new Coronavirus("27 January 2020", "Canada", 1));

        //  28 January 2020
        list.add(new Coronavirus("28 January 2020", "China", 4537));
        list.add(new Coronavirus("28 January 2020", "Thailand", 14));
        list.add(new Coronavirus("28 January 2020", "Singapore", 7));
        list.add(new Coronavirus("28 January 2020", "Japan", 6));
        list.add(new Coronavirus("28 January 2020", "USA", 5));
        list.add(new Coronavirus("28 January 2020", "Australia", 5));
        list.add(new Coronavirus("28 January 2020", "Republic of Korea", 4));
        list.add(new Coronavirus("28 January 2020", "Malaysia", 4));
        list.add(new Coronavirus("28 January 2020", "French Republic", 3));
        list.add(new Coronavirus("28 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("28 January 2020", "Canada", 2));
        list.add(new Coronavirus("28 January 2020", "Nepal", 1));
        list.add(new Coronavirus("28 January 2020", "Cambodia", 1));
        list.add(new Coronavirus("28 January 2020", "Sri Lanka", 1));
        list.add(new Coronavirus("28 January 2020", "Germany", 1));

        //  29 January 2020
        list.add(new Coronavirus("29 January 2020", "China", 5997));
        list.add(new Coronavirus("29 January 2020", "Thailand", 14));
        list.add(new Coronavirus("29 January 2020", "Singapore", 7));
        list.add(new Coronavirus("29 January 2020", "Japan", 7));
        list.add(new Coronavirus("29 January 2020", "Australia", 7));
        list.add(new Coronavirus("29 January 2020", "USA", 5));
        list.add(new Coronavirus("29 January 2020", "Republic of Korea", 4));
        list.add(new Coronavirus("29 January 2020", "Malaysia", 4));
        list.add(new Coronavirus("29 January 2020", "French Republic", 4));
        list.add(new Coronavirus("29 January 2020", "Germany", 4));
        list.add(new Coronavirus("29 January 2020", "United Arab Emirates", 4));
        list.add(new Coronavirus("29 January 2020", "Canada", 3));
        list.add(new Coronavirus("29 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("29 January 2020", "Nepal", 1));
        list.add(new Coronavirus("29 January 2020", "Cambodia", 1));
        list.add(new Coronavirus("29 January 2020", "Sri Lanka", 1));

        //  30 January 2020
        list.add(new Coronavirus("30 January 2020", "China", 7736));
        list.add(new Coronavirus("30 January 2020", "Thailand", 14));
        list.add(new Coronavirus("30 January 2020", "Japan", 11));
        list.add(new Coronavirus("30 January 2020", "Singapore", 10));
        list.add(new Coronavirus("30 January 2020", "Australia", 7));
        list.add(new Coronavirus("30 January 2020", "Malaysia", 7));
        list.add(new Coronavirus("30 January 2020", "USA", 5));
        list.add(new Coronavirus("30 January 2020", "French Republic", 5));
        list.add(new Coronavirus("30 January 2020", "Republic of Korea", 4));
        list.add(new Coronavirus("30 January 2020", "Germany", 4));
        list.add(new Coronavirus("30 January 2020", "United Arab Emirates", 4));
        list.add(new Coronavirus("30 January 2020", "Canada", 3));
        list.add(new Coronavirus("30 January 2020", "Vietnam ", 2));
        list.add(new Coronavirus("30 January 2020", "Nepal", 1));
        list.add(new Coronavirus("30 January 2020", "Cambodia", 1));
        list.add(new Coronavirus("30 January 2020", "Sri Lanka", 1));
        list.add(new Coronavirus("30 January 2020", "Philippines", 1));
        list.add(new Coronavirus("30 January 2020", "India", 1));
        list.add(new Coronavirus("30 January 2020", "Finland", 1));

        //  31 January 2020
        list.add(new Coronavirus("31 January 2020", "China", 9720));
        list.add(new Coronavirus("31 January 2020", "Thailand", 14));
        list.add(new Coronavirus("31 January 2020", "Japan", 14));
        list.add(new Coronavirus("31 January 2020", "Singapore", 13));
        list.add(new Coronavirus("31 January 2020", "Republic of Korea", 11));
        list.add(new Coronavirus("31 January 2020", "Australia", 9));
        list.add(new Coronavirus("31 January 2020", "Malaysia", 8));
        list.add(new Coronavirus("31 January 2020", "Vietnam ", 6));
        list.add(new Coronavirus("31 January 2020", "USA", 6));
        list.add(new Coronavirus("31 January 2020", "French Republic", 6));
        list.add(new Coronavirus("31 January 2020", "Germany", 5));
        list.add(new Coronavirus("31 January 2020", "United Arab Emirates", 4));
        list.add(new Coronavirus("31 January 2020", "Canada", 3));
        list.add(new Coronavirus("31 January 2020", "Italy", 2));
        list.add(new Coronavirus("31 January 2020", "Nepal", 1));
        list.add(new Coronavirus("31 January 2020", "Cambodia", 1));
        list.add(new Coronavirus("31 January 2020", "Sri Lanka", 1));
        list.add(new Coronavirus("31 January 2020", "Philippines", 1));
        list.add(new Coronavirus("31 January 2020", "India", 1));
        list.add(new Coronavirus("31 January 2020", "Finland", 1));

        //  1 February 2020
        list.add(new Coronavirus("1 February 2020", "China", 11821));
        list.add(new Coronavirus("1 February 2020", "Thailand", 19));
        list.add(new Coronavirus("1 February 2020", "Japan", 17));
        list.add(new Coronavirus("1 February 2020", "Singapore", 16));
        list.add(new Coronavirus("1 February 2020", "Republic of Korea", 12));
        list.add(new Coronavirus("1 February 2020", "Australia", 12));
        list.add(new Coronavirus("1 February 2020", "Malaysia", 8));
        list.add(new Coronavirus("1 February 2020", "USA", 7));
        list.add(new Coronavirus("1 February 2020", "Germany", 7));
        list.add(new Coronavirus("1 February 2020", "Vietnam ", 6));
        list.add(new Coronavirus("1 February 2020", "French Republic", 6));
        list.add(new Coronavirus("1 February 2020", "United Arab Emirates", 4));
        list.add(new Coronavirus("1 February 2020", "Canada", 4));
        list.add(new Coronavirus("1 February 2020", "Russian Federation", 2));
        list.add(new Coronavirus("1 February 2020", "Italy", 2));
        list.add(new Coronavirus("1 February 2020", "United Kingdom", 2));
        list.add(new Coronavirus("1 February 2020", "Nepal", 1));
        list.add(new Coronavirus("1 February 2020", "Cambodia", 1));
        list.add(new Coronavirus("1 February 2020", "Sri Lanka", 1));
        list.add(new Coronavirus("1 February 2020", "Philippines", 1));
        list.add(new Coronavirus("1 February 2020", "India", 1));
        list.add(new Coronavirus("1 February 2020", "Finland", 1));
        list.add(new Coronavirus("1 February 2020", "Spain", 1));
        list.add(new Coronavirus("1 February 2020", "Sweden", 1));

        //  2 February 2020
        list.add(new Coronavirus("2 February 2020", "China", 14411));
        list.add(new Coronavirus("2 February 2020", "Japan", 20));
        list.add(new Coronavirus("2 February 2020", "Thailand", 19));
        list.add(new Coronavirus("2 February 2020", "Singapore", 18));
        list.add(new Coronavirus("2 February 2020", "Republic of Korea", 15));
        list.add(new Coronavirus("2 February 2020", "Australia", 12));
        list.add(new Coronavirus("2 February 2020", "Malaysia", 8));
        list.add(new Coronavirus("2 February 2020", "USA", 8));
        list.add(new Coronavirus("2 February 2020", "Germany", 8));
        list.add(new Coronavirus("2 February 2020", "Vietnam ", 7));
        list.add(new Coronavirus("2 February 2020", "French Republic", 6));
        list.add(new Coronavirus("2 February 2020", "United Arab Emirates", 5));
        list.add(new Coronavirus("2 February 2020", "Canada", 4));
        list.add(new Coronavirus("2 February 2020", "Russian Federation", 2));
        list.add(new Coronavirus("2 February 2020", "Italy", 2));
        list.add(new Coronavirus("2 February 2020", "United Kingdom", 2));
        list.add(new Coronavirus("2 February 2020", "Philippines", 2));
        list.add(new Coronavirus("2 February 2020", "India", 2));
        list.add(new Coronavirus("2 February 2020", "Nepal", 1));
        list.add(new Coronavirus("2 February 2020", "Cambodia", 1));
        list.add(new Coronavirus("2 February 2020", "Sri Lanka", 1));
        list.add(new Coronavirus("2 February 2020", "Finland", 1));
        list.add(new Coronavirus("2 February 2020", "Spain", 1));
        list.add(new Coronavirus("2 February 2020", "Sweden", 1));

        //  3 February 2020
        tempData = "3 February 2020";
        list.add(new Coronavirus(tempData, "China", 17238));
        list.add(new Coronavirus(tempData, "Japan", 20));
        list.add(new Coronavirus(tempData, "Thailand", 19));
        list.add(new Coronavirus(tempData, "Singapore", 18));
        list.add(new Coronavirus(tempData, "Republic of Korea", 15));
        list.add(new Coronavirus(tempData, "Australia", 12));
        list.add(new Coronavirus(tempData, "USA", 11));
        list.add(new Coronavirus(tempData, "Germany", 10));
        list.add(new Coronavirus(tempData, "Malaysia", 8));
        list.add(new Coronavirus(tempData, "Vietnam ", 8));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 5));
        list.add(new Coronavirus(tempData, "Canada", 4));
        list.add(new Coronavirus(tempData, "India", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "Italy", 2));
        list.add(new Coronavirus(tempData, "United Kingdom", 2));
        list.add(new Coronavirus(tempData, "Philippines", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));

        //  4 February 2020
        tempData = "4 February 2020";
        list.add(new Coronavirus(tempData, "China", 20471));
        list.add(new Coronavirus(tempData, "Japan", 20));
        list.add(new Coronavirus(tempData, "Thailand", 19));
        list.add(new Coronavirus(tempData, "Singapore", 18));
        list.add(new Coronavirus(tempData, "Republic of Korea", 16));
        list.add(new Coronavirus(tempData, "Australia", 12));
        list.add(new Coronavirus(tempData, "Germany", 12));
        list.add(new Coronavirus(tempData, "Malaysia", 10));
        list.add(new Coronavirus(tempData, "Vietnam ", 9));
        list.add(new Coronavirus(tempData, "USA", 8));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 5));
        list.add(new Coronavirus(tempData, "Canada", 4));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "Italy", 2));
        list.add(new Coronavirus(tempData, "United Kingdom", 2));
        list.add(new Coronavirus(tempData, "Philippines", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));

        //  5 February 2020
        tempData = "5 February 2020";
        list.add(new Coronavirus(tempData, "China", 24363));
        list.add(new Coronavirus(tempData, "Japan", 33));
        list.add(new Coronavirus(tempData, "Thailand", 25));
        list.add(new Coronavirus(tempData, "Singapore", 24));
        list.add(new Coronavirus(tempData, "Republic of Korea", 18));
        list.add(new Coronavirus(tempData, "Australia", 13));
        list.add(new Coronavirus(tempData, "Germany", 12));
        list.add(new Coronavirus(tempData, "USA", 11));
        list.add(new Coronavirus(tempData, "Malaysia", 10));
        list.add(new Coronavirus(tempData, "Vietnam ", 10));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "Italy", 2));
        list.add(new Coronavirus(tempData, "United Kingdom", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  6 February 2020
        tempData = "6 February 2020";
        list.add(new Coronavirus(tempData, "China", 28060));
        list.add(new Coronavirus(tempData, "Singapore", 28));
        list.add(new Coronavirus(tempData, "Japan", 25));
        list.add(new Coronavirus(tempData, "Thailand", 25));
        list.add(new Coronavirus(tempData, "Republic of Korea", 23));
        list.add(new Coronavirus(tempData, "Australia", 14));
        list.add(new Coronavirus(tempData, "Germany", 12));
        list.add(new Coronavirus(tempData, "Malaysia", 12));
        list.add(new Coronavirus(tempData, "USA", 12));
        list.add(new Coronavirus(tempData, "Vietnam ", 10));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "Italy", 2));
        list.add(new Coronavirus(tempData, "United Kingdom", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  7 February 2020
        tempData = "7 February 2020";
        list.add(new Coronavirus(tempData, "China", 31211));
        list.add(new Coronavirus(tempData, "Singapore", 30));
        list.add(new Coronavirus(tempData, "Japan", 25));
        list.add(new Coronavirus(tempData, "Thailand", 25));
        list.add(new Coronavirus(tempData, "Republic of Korea", 24));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Malaysia", 14));
        list.add(new Coronavirus(tempData, "Germany", 13));
        list.add(new Coronavirus(tempData, "USA", 12));
        list.add(new Coronavirus(tempData, "Vietnam ", 12));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "United Kingdom", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  8 February 2020
        tempData = "8 February 2020";
        list.add(new Coronavirus(tempData, "China", 34598));
        list.add(new Coronavirus(tempData, "Singapore", 33));
        list.add(new Coronavirus(tempData, "Thailand", 32));
        list.add(new Coronavirus(tempData, "Japan", 25));
        list.add(new Coronavirus(tempData, "Republic of Korea", 24));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Malaysia", 15));
        list.add(new Coronavirus(tempData, "Germany", 14));
        list.add(new Coronavirus(tempData, "Vietnam ", 13));
        list.add(new Coronavirus(tempData, "USA", 12));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 7));
        list.add(new Coronavirus(tempData, "French Republic", 6));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "United Kingdom", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  9 February 2020
        tempData = "9 February 2020";
        list.add(new Coronavirus(tempData, "China", 37251));
        list.add(new Coronavirus(tempData, "Singapore", 40));
        list.add(new Coronavirus(tempData, "Thailand", 32));
        list.add(new Coronavirus(tempData, "Republic of Korea", 27));
        list.add(new Coronavirus(tempData, "Japan", 26));
        list.add(new Coronavirus(tempData, "Malaysia", 17));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Germany", 14));
        list.add(new Coronavirus(tempData, "Vietnam ", 14));
        list.add(new Coronavirus(tempData, "USA", 12));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 7));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "United Kingdom", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Spain", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  10 February 2020
        tempData = "10 February 2020";
        list.add(new Coronavirus(tempData, "China", 40235));
        list.add(new Coronavirus(tempData, "Singapore", 43));
        list.add(new Coronavirus(tempData, "Thailand", 32));
        list.add(new Coronavirus(tempData, "Republic of Korea", 27));
        list.add(new Coronavirus(tempData, "Japan", 26));
        list.add(new Coronavirus(tempData, "Malaysia", 18));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Germany", 14));
        list.add(new Coronavirus(tempData, "Vietnam ", 14));
        list.add(new Coronavirus(tempData, "USA", 12));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 7));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "United Kingdom", 4));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        //  11 February 2020
        tempData = "11 February 2020";
        list.add(new Coronavirus(tempData, "China", 42708));
        list.add(new Coronavirus(tempData, "Singapore", 45));
        list.add(new Coronavirus(tempData, "Thailand", 33));
        list.add(new Coronavirus(tempData, "Republic of Korea", 28));
        list.add(new Coronavirus(tempData, "Japan", 26));
        list.add(new Coronavirus(tempData, "Malaysia", 18));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Germany", 14));
        list.add(new Coronavirus(tempData, "USA", 13));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 8));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        tempData = "12 February 2020";
        list.add(new Coronavirus(tempData, "China", 44730));
        list.add(new Coronavirus(tempData, "Singapore", 47));
        list.add(new Coronavirus(tempData, "Thailand", 33));
        list.add(new Coronavirus(tempData, "Republic of Korea", 28));
        list.add(new Coronavirus(tempData, "Japan", 28));
        list.add(new Coronavirus(tempData, "Malaysia", 18));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 13));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 8));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        tempData = "13 February 2020";
        list.add(new Coronavirus(tempData, "China", 46550));
        list.add(new Coronavirus(tempData, "Singapore", 50));
        list.add(new Coronavirus(tempData, "Thailand", 33));
        list.add(new Coronavirus(tempData, "Republic of Korea", 28));
        list.add(new Coronavirus(tempData, "Japan", 28));
        list.add(new Coronavirus(tempData, "Malaysia", 18));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 14));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        tempData = "14 February 2020";
        list.add(new Coronavirus(tempData, "China", 48548));
        list.add(new Coronavirus(tempData, "Singapore", 58));
        list.add(new Coronavirus(tempData, "Thailand", 33));
        list.add(new Coronavirus(tempData, "Japan", 33));
        list.add(new Coronavirus(tempData, "Republic of Korea", 28));
        list.add(new Coronavirus(tempData, "Malaysia", 19));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));

        tempData = "15 February 2020";
        list.add(new Coronavirus(tempData, "China", 50054));
        list.add(new Coronavirus(tempData, "Singapore", 67));
        list.add(new Coronavirus(tempData, "Japan", 41));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Republic of Korea", 28));
        list.add(new Coronavirus(tempData, "Malaysia", 21));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "16 February 2020";
        list.add(new Coronavirus(tempData, "China", 51174));
        list.add(new Coronavirus(tempData, "Singapore", 72));
        list.add(new Coronavirus(tempData, "Japan", 53));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Republic of Korea", 29));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 8));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "17 February 2020";
        list.add(new Coronavirus(tempData, "China", 70635));
        list.add(new Coronavirus(tempData, "Singapore", 75));
        list.add(new Coronavirus(tempData, "Japan", 59));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Republic of Korea", 30));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "18 February 2020";
        list.add(new Coronavirus(tempData, "China", 72528));
        list.add(new Coronavirus(tempData, "Singapore", 77));
        list.add(new Coronavirus(tempData, "Japan", 65));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Republic of Korea", 31));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "19 February 2020";
        list.add(new Coronavirus(tempData, "China", 74280));
        list.add(new Coronavirus(tempData, "Singapore", 81));
        list.add(new Coronavirus(tempData, "Japan", 73));
        list.add(new Coronavirus(tempData, "Republic of Korea", 51));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "20 February 2020";
        list.add(new Coronavirus(tempData, "China", 74675));
        list.add(new Coronavirus(tempData, "Republic of Korea", 104));
        list.add(new Coronavirus(tempData, "Japan", 85));
        list.add(new Coronavirus(tempData, "Singapore", 84));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Iran", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "21 February 2020";
        list.add(new Coronavirus(tempData, "China", 75569));
        list.add(new Coronavirus(tempData, "Republic of Korea", 204));
        list.add(new Coronavirus(tempData, "Japan", 93));
        list.add(new Coronavirus(tempData, "Singapore", 85));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Australia", 15));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "USA", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Iran", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Italy", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));

        tempData = "22 February 2020";
        list.add(new Coronavirus(tempData, "China", 76392));
        list.add(new Coronavirus(tempData, "Republic of Korea", 346));
        list.add(new Coronavirus(tempData, "Japan", 105));
        list.add(new Coronavirus(tempData, "Singapore", 86));
        list.add(new Coronavirus(tempData, "USA", 35));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Australia", 21));
        list.add(new Coronavirus(tempData, "Iran", 18));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Italy", 9));
        list.add(new Coronavirus(tempData, "Canada", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Israel", 1));

        tempData = "23 February 2020";
        list.add(new Coronavirus(tempData, "China", 77042));
        list.add(new Coronavirus(tempData, "Republic of Korea", 602));
        list.add(new Coronavirus(tempData, "Japan", 132));
        list.add(new Coronavirus(tempData, "Singapore", 89));
        list.add(new Coronavirus(tempData, "Italy", 76));
        list.add(new Coronavirus(tempData, "USA", 35));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Iran", 28));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Australia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 13));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Canada", 9));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Israel", 1));

        tempData = "24 February 2020";
        list.add(new Coronavirus(tempData, "China", 77262));
        list.add(new Coronavirus(tempData, "Republic of Korea", 763));
        list.add(new Coronavirus(tempData, "Japan", 144));
        list.add(new Coronavirus(tempData, "Italy", 124));
        list.add(new Coronavirus(tempData, "Singapore", 89));
        list.add(new Coronavirus(tempData, "Iran", 43));
        list.add(new Coronavirus(tempData, "USA", 35));
        list.add(new Coronavirus(tempData, "Thailand", 34));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Australia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 13));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Canada", 9));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Kuwait", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Israel", 1));

        tempData = "25 February 2020";
        list.add(new Coronavirus(tempData, "China", 77780));
        list.add(new Coronavirus(tempData, "Republic of Korea", 977));
        list.add(new Coronavirus(tempData, "Italy", 229));
        list.add(new Coronavirus(tempData, "Japan", 157));
        list.add(new Coronavirus(tempData, "Singapore", 90));
        list.add(new Coronavirus(tempData, "Iran", 61));
        list.add(new Coronavirus(tempData, "USA", 53));
        list.add(new Coronavirus(tempData, "Thailand", 37));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Australia", 22));
        list.add(new Coronavirus(tempData, "Germany", 16));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 13));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "Canada", 10));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Bahrain", 8));
        list.add(new Coronavirus(tempData, "Kuwait", 8));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Israel", 2));
        list.add(new Coronavirus(tempData, "Oman", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Iraq", 1));

        tempData = "26 February 2020";
        list.add(new Coronavirus(tempData, "China", 78191));
        list.add(new Coronavirus(tempData, "Republic of Korea", 1261));
        list.add(new Coronavirus(tempData, "Italy", 322));
        list.add(new Coronavirus(tempData, "Japan", 164));
        list.add(new Coronavirus(tempData, "Iran", 95));
        list.add(new Coronavirus(tempData, "Singapore", 91));
        list.add(new Coronavirus(tempData, "USA", 53));
        list.add(new Coronavirus(tempData, "Thailand", 40));
        list.add(new Coronavirus(tempData, "Bahrain", 26));
        list.add(new Coronavirus(tempData, "Australia", 23));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 18));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 13));
        list.add(new Coronavirus(tempData, "France", 12));
        list.add(new Coronavirus(tempData, "Kuwait", 12));
        list.add(new Coronavirus(tempData, "Canada", 10));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Iraq", 5));
        list.add(new Coronavirus(tempData, "Oman", 4));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Spain", 2));
        list.add(new Coronavirus(tempData, "Israel", 2));
        list.add(new Coronavirus(tempData, "Austria", 2));
        list.add(new Coronavirus(tempData, "Croatia", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Finland", 1));
        list.add(new Coronavirus(tempData, "Sweden", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Switzerland", 1));
        list.add(new Coronavirus(tempData, "Algeria", 1));

        tempData = "27 February 2020";
        list.add(new Coronavirus(tempData, "China", 78630));
        list.add(new Coronavirus(tempData, "Republic of Korea", 1766));
        list.add(new Coronavirus(tempData, "Italy", 400));
        list.add(new Coronavirus(tempData, "Japan", 186));
        list.add(new Coronavirus(tempData, "Iran", 141));
        list.add(new Coronavirus(tempData, "Singapore", 93));
        list.add(new Coronavirus(tempData, "USA", 59));
        list.add(new Coronavirus(tempData, "Kuwait", 43));
        list.add(new Coronavirus(tempData, "Thailand", 40));
        list.add(new Coronavirus(tempData, "Bahrain", 33));
        list.add(new Coronavirus(tempData, "Australia", 23));
        list.add(new Coronavirus(tempData, "Malaysia", 22));
        list.add(new Coronavirus(tempData, "Germany", 21));
        list.add(new Coronavirus(tempData, "France", 18));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 13));
        list.add(new Coronavirus(tempData, "Spain", 12));
        list.add(new Coronavirus(tempData, "Canada", 11));
        list.add(new Coronavirus(tempData, "United Kingdom", 9));
        list.add(new Coronavirus(tempData, "Iraq", 6));
        list.add(new Coronavirus(tempData, "Oman", 4));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Croatia", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Israel", 2));
        list.add(new Coronavirus(tempData, "Austria", 2));
        list.add(new Coronavirus(tempData, "Finland", 2));
        list.add(new Coronavirus(tempData, "Sweden", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Switzerland", 1));
        list.add(new Coronavirus(tempData, "Algeria", 1));
        list.add(new Coronavirus(tempData, "Brazil", 1));
        list.add(new Coronavirus(tempData, "Denmark", 1));
        list.add(new Coronavirus(tempData, "Estonia", 1));
        list.add(new Coronavirus(tempData, "Georgia", 1));
        list.add(new Coronavirus(tempData, "Greece", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "Norway", 1));
        list.add(new Coronavirus(tempData, "Romania", 1));

        tempData = "28 February 2020";
        list.add(new Coronavirus(tempData, "China", 78961));
        list.add(new Coronavirus(tempData, "Republic of Korea", 2337));
        list.add(new Coronavirus(tempData, "Italy", 650));
        list.add(new Coronavirus(tempData, "Iran", 245));
        list.add(new Coronavirus(tempData, "Japan", 210));
        list.add(new Coronavirus(tempData, "Singapore", 96));
        list.add(new Coronavirus(tempData, "USA", 59));
        list.add(new Coronavirus(tempData, "Kuwait", 43));
        list.add(new Coronavirus(tempData, "Thailand", 40));
        list.add(new Coronavirus(tempData, "France", 38));
        list.add(new Coronavirus(tempData, "Bahrain", 33));
        list.add(new Coronavirus(tempData, "Germany", 26));
        list.add(new Coronavirus(tempData, "Spain", 25));
        list.add(new Coronavirus(tempData, "Malaysia", 24));
        list.add(new Coronavirus(tempData, "Australia", 23));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 19));
        list.add(new Coronavirus(tempData, "United Kingdom", 16));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Canada", 11));
        list.add(new Coronavirus(tempData, "Sweden", 7));
        list.add(new Coronavirus(tempData, "Iraq", 7));
        list.add(new Coronavirus(tempData, "Switzerland", 6));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Austria", 4));
        list.add(new Coronavirus(tempData, "Norway", 4));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Croatia", 3));
        list.add(new Coronavirus(tempData, "Israel", 3));
        list.add(new Coronavirus(tempData, "Greece", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Finland", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Algeria", 1));
        list.add(new Coronavirus(tempData, "Brazil", 1));
        list.add(new Coronavirus(tempData, "Denmark", 1));
        list.add(new Coronavirus(tempData, "Estonia", 1));
        list.add(new Coronavirus(tempData, "Georgia", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "Romania", 1));
        list.add(new Coronavirus(tempData, "New Zealand", 1));
        list.add(new Coronavirus(tempData, "Belarus", 1));
        list.add(new Coronavirus(tempData, "Lithuanias", 1));
        list.add(new Coronavirus(tempData, "Netherlands", 1));
        list.add(new Coronavirus(tempData, "Nigeria", 1));

        tempData = "29 February 2020";
        list.add(new Coronavirus(tempData, "China", 79394));
        list.add(new Coronavirus(tempData, "Republic of Korea", 3150));
        list.add(new Coronavirus(tempData, "Italy", 888));
        list.add(new Coronavirus(tempData, "Iran", 245));
        list.add(new Coronavirus(tempData, "Japan", 230));
        list.add(new Coronavirus(tempData, "Singapore", 98));
        list.add(new Coronavirus(tempData, "USA", 59));
        list.add(new Coronavirus(tempData, "France", 57));
        list.add(new Coronavirus(tempData, "Germany", 57));
        list.add(new Coronavirus(tempData, "Kuwait", 45));
        list.add(new Coronavirus(tempData, "Thailand", 42));
        list.add(new Coronavirus(tempData, "Bahrain", 38));
        list.add(new Coronavirus(tempData, "Spain", 32));
        list.add(new Coronavirus(tempData, "Malaysia", 24));
        list.add(new Coronavirus(tempData, "Australia", 24));
        list.add(new Coronavirus(tempData, "United Kingdom", 20));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 19));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Sweden", 12));
        list.add(new Coronavirus(tempData, "Canada", 11));
        list.add(new Coronavirus(tempData, "Switzerland", 10));
        list.add(new Coronavirus(tempData, "Iraq", 8));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Norway", 6));
        list.add(new Coronavirus(tempData, "Austria", 5));
        list.add(new Coronavirus(tempData, "Croatia", 5));
        list.add(new Coronavirus(tempData, "Israel", 5));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Greece", 3));
        list.add(new Coronavirus(tempData, "Romania", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Finland", 2));
        list.add(new Coronavirus(tempData, "Pakistan", 2));
        list.add(new Coronavirus(tempData, "Georgia", 2));
        list.add(new Coronavirus(tempData, "Denmark", 2));
        list.add(new Coronavirus(tempData, "Netherlands", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Algeria", 1));
        list.add(new Coronavirus(tempData, "Brazil", 1));
        list.add(new Coronavirus(tempData, "Estonia", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "New Zealand", 1));
        list.add(new Coronavirus(tempData, "Belarus", 1));
        list.add(new Coronavirus(tempData, "Lithuanias", 1));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "San Marino", 1));

        tempData = "1 March 2020";
        list.add(new Coronavirus(tempData, "China", 79968));
        list.add(new Coronavirus(tempData, "Republic of Korea", 3736));
        list.add(new Coronavirus(tempData, "Italy", 1128));
        list.add(new Coronavirus(tempData, "Iran", 593));
        list.add(new Coronavirus(tempData, "Japan", 239));
        list.add(new Coronavirus(tempData, "Singapore", 102));
        list.add(new Coronavirus(tempData, "France", 100));
        list.add(new Coronavirus(tempData, "USA", 59));
        list.add(new Coronavirus(tempData, "Germany", 57));
        list.add(new Coronavirus(tempData, "Kuwait", 45));
        list.add(new Coronavirus(tempData, "Spain", 45));
        list.add(new Coronavirus(tempData, "Thailand", 42));
        list.add(new Coronavirus(tempData, "Bahrain", 40));
        list.add(new Coronavirus(tempData, "Australia", 25));
        list.add(new Coronavirus(tempData, "Malaysia", 24));
        list.add(new Coronavirus(tempData, "United Kingdom", 23));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 19));
        list.add(new Coronavirus(tempData, "Canada", 19));
        list.add(new Coronavirus(tempData, "Switzerland", 18));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Norway", 15));
        list.add(new Coronavirus(tempData, "Sweden", 13));
        list.add(new Coronavirus(tempData, "Iraq", 13));
        list.add(new Coronavirus(tempData, "Austria", 10));
        list.add(new Coronavirus(tempData, "Croatia", 7));
        list.add(new Coronavirus(tempData, "Israel", 7));
        list.add(new Coronavirus(tempData, "Netherlands", 7));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Pakistan", 4));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Greece", 3));
        list.add(new Coronavirus(tempData, "Romania", 3));
        list.add(new Coronavirus(tempData, "Georgia", 3));
        list.add(new Coronavirus(tempData, "Denmark", 3));
        list.add(new Coronavirus(tempData, "Russian Federation", 2));
        list.add(new Coronavirus(tempData, "India", 2));
        list.add(new Coronavirus(tempData, "Finland", 2));
        list.add(new Coronavirus(tempData, "Brazil", 2));
        list.add(new Coronavirus(tempData, "Lebanon", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Egypt", 1));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Algeria", 1));
        list.add(new Coronavirus(tempData, "Estonia", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "New Zealand", 1));
        list.add(new Coronavirus(tempData, "Belarus", 1));
        list.add(new Coronavirus(tempData, "Lithuanias", 1));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "San Marino", 1));
        list.add(new Coronavirus(tempData, "Azerbaijan", 1));
        list.add(new Coronavirus(tempData, "Ireland", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Qatar", 1));
        list.add(new Coronavirus(tempData, "Ecuador", 1));

        list.addAll(getDataMarch02());

        return list;
    }

    private List<Coronavirus> sortByCases(List<Coronavirus> list) {
        return list.stream()
                .sorted(Comparator.comparingLong(Coronavirus::getCases))
                .collect(Collectors.toList());
    }

    private List<Coronavirus> getDataMarch02() {
        String tempData = "2 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80174));
        list.add(new Coronavirus(tempData, "Republic of Korea", 4212));
        list.add(new Coronavirus(tempData, "Japan", 254));
        list.add(new Coronavirus(tempData, "Singapore", 106));
        list.add(new Coronavirus(tempData, "Australia", 27));
        list.add(new Coronavirus(tempData, "Malaysia", 24));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "Cambodia", 1));
        list.add(new Coronavirus(tempData, "New Zealand", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 1689));
        list.add(new Coronavirus(tempData, "Germany", 129));
        list.add(new Coronavirus(tempData, "France", 100));
        list.add(new Coronavirus(tempData, "Spain", 45));
        list.add(new Coronavirus(tempData, "United Kingdom", 36));
        list.add(new Coronavirus(tempData, "Switzerland", 24));
        list.add(new Coronavirus(tempData, "Norway", 19));
        list.add(new Coronavirus(tempData, "Sweden", 14));
        list.add(new Coronavirus(tempData, "Netherlands", 13));
        list.add(new Coronavirus(tempData, "Austria", 10));
        list.add(new Coronavirus(tempData, "Netherlands", 13));
        list.add(new Coronavirus(tempData, "Austria", 10));
        list.add(new Coronavirus(tempData, "Croatia", 7));
        list.add(new Coronavirus(tempData, "Greece", 7));
        list.add(new Coronavirus(tempData, "Israel", 7));
        list.add(new Coronavirus(tempData, "Finland", 6));
        list.add(new Coronavirus(tempData, "Denmark", 4));
        list.add(new Coronavirus(tempData, "Azerbaijan", 3));
        list.add(new Coronavirus(tempData, "Czechia", 3));
        list.add(new Coronavirus(tempData, "Georgia", 3));
        list.add(new Coronavirus(tempData, "Romania", 3));
        list.add(new Coronavirus(tempData, "Iceland", 2));
        list.add(new Coronavirus(tempData, "Russia Federation", 2));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Belarus", 1));
        list.add(new Coronavirus(tempData, "Belgium", 1));
        list.add(new Coronavirus(tempData, "Estonia", 1));
        list.add(new Coronavirus(tempData, "Ireland", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Luxembourg", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "San Marino", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 42));
        list.add(new Coronavirus(tempData, "India", 3));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));
        list.add(new Coronavirus(tempData, "Iran", 978));
        list.add(new Coronavirus(tempData, "Kuwait", 56));
        list.add(new Coronavirus(tempData, "Bahrain", 47));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 21));
        list.add(new Coronavirus(tempData, "Iraq", 19));
        list.add(new Coronavirus(tempData, "Lebon", 10));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Pakistan", 4));
        list.add(new Coronavirus(tempData, "Qatar", 3));
        list.add(new Coronavirus(tempData, "Egypt", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 62));
        list.add(new Coronavirus(tempData, "Canada", 19));
        list.add(new Coronavirus(tempData, "Mexico", 5));
        list.add(new Coronavirus(tempData, "Dominican Republic", 2));
        list.add(new Coronavirus(tempData, "Brazil", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 1));
        list.add(new Coronavirus(tempData, "Nigeria", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 706));


        return list;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        initialise();
        return horizontalBarModel;
    }

    public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
        this.horizontalBarModel = horizontalBarModel;
    }
}