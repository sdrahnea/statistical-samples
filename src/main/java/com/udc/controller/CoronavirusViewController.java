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
        datetimes.add("12 March 2020");
        datetimes.add("13 March 2020");
        datetimes.add("14 March 2020");

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
        list.addAll(getDataMarch03());
        list.addAll(getDataMarch04());
        list.addAll(getDataMarch05());
        list.addAll(getDataMarch06());
        list.addAll(getDataMarch07());
        list.addAll(getDataMarch08());
        list.addAll(getDataMarch09());
        list.addAll(getDataMarch10());
        list.addAll(getDataMarch11());
        list.addAll(getDataMarch12());
        list.addAll(getDataMarch13());
        list.addAll(getDataMarch14());

        return list;
    }

    private List<Coronavirus> sortByCases(List<Coronavirus> list) {
        return list.stream()
                .sorted(Comparator.comparingLong(Coronavirus::getCases))
                .collect(Collectors.toList());
    }

    private List<Coronavirus> getDataMarch14() {
        String tempData = "14 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 81021));
        list.add(new Coronavirus(tempData, "Republic of Korea", 8086));
        list.add(new Coronavirus(tempData, "Japan", 716));
        list.add(new Coronavirus(tempData, "Singapore", 200));
        list.add(new Coronavirus(tempData, "Australia", 197));
        list.add(new Coronavirus(tempData, "Malaysia", 197));
        list.add(new Coronavirus(tempData, "Philippines", 64));
        list.add(new Coronavirus(tempData, "Vietnam ", 48));
        list.add(new Coronavirus(tempData, "Brunei Darussalam", 25));
        list.add(new Coronavirus(tempData, "Cambodia", 7));
        list.add(new Coronavirus(tempData, "New Zealand", 6));
        list.add(new Coronavirus(tempData, "Mongolia", 1));

//        Territories*
        list.add(new Coronavirus(tempData, "French Polynesia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 17660));
        list.add(new Coronavirus(tempData, "Spain", 4231));
        list.add(new Coronavirus(tempData, "France", 3640));
        list.add(new Coronavirus(tempData, "Germany", 3062));
        list.add(new Coronavirus(tempData, "Switzerland", 1125));
        list.add(new Coronavirus(tempData, "Netherlands", 804));
        list.add(new Coronavirus(tempData, "United Kingdom", 802));
        list.add(new Coronavirus(tempData, "Denmark", 801));
        list.add(new Coronavirus(tempData, "Sweden", 775));
        list.add(new Coronavirus(tempData, "Norway", 750));
        list.add(new Coronavirus(tempData, "Belgium", 599));
        list.add(new Coronavirus(tempData, "Austria", 504));
        list.add(new Coronavirus(tempData, "Czechia", 150));
        list.add(new Coronavirus(tempData, "Slovenia", 141));
        list.add(new Coronavirus(tempData, "Portugal", 112));
        list.add(new Coronavirus(tempData, "Finland", 109));
        list.add(new Coronavirus(tempData, "Israel", 100));
        list.add(new Coronavirus(tempData, "Greece", 98));
        list.add(new Coronavirus(tempData, "Ireland", 90));
        list.add(new Coronavirus(tempData, "Estonia", 79));
        list.add(new Coronavirus(tempData, "San Marino", 66));
        list.add(new Coronavirus(tempData, "Romania", 64));
        list.add(new Coronavirus(tempData, "Poland", 64));
        list.add(new Coronavirus(tempData, "Iceland", 61));
        list.add(new Coronavirus(tempData, "Luxembourg", 38));
        list.add(new Coronavirus(tempData, "Russia Federation", 34));
        list.add(new Coronavirus(tempData, "Albania", 33));
        list.add(new Coronavirus(tempData, "Serbia", 31));
        list.add(new Coronavirus(tempData, "Slovakia", 30));
        list.add(new Coronavirus(tempData, "Croatia", 27));
        list.add(new Coronavirus(tempData, "Georgia", 25));
        list.add(new Coronavirus(tempData, "Belarus", 21));
        list.add(new Coronavirus(tempData, "Hungary", 19));
        list.add(new Coronavirus(tempData, "Latvia", 16));
        list.add(new Coronavirus(tempData, "Cyprus", 14));
        list.add(new Coronavirus(tempData, "Malta", 12));
        list.add(new Coronavirus(tempData, "Azerbaijan", 11));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 11));
        list.add(new Coronavirus(tempData, "North Macedonia", 9));
        list.add(new Coronavirus(tempData, "Armenia", 8));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 8));
        list.add(new Coronavirus(tempData, "Bulgaria", 7));
        list.add(new Coronavirus(tempData, "Lithuania", 6));
        list.add(new Coronavirus(tempData, "Turkey", 5));
        list.add(new Coronavirus(tempData, "Liechtenstein", 4));
        list.add(new Coronavirus(tempData, "Ukraine", 3));
        list.add(new Coronavirus(tempData, "Andorra", 2));
        list.add(new Coronavirus(tempData, "Monaco", 2));
        list.add(new Coronavirus(tempData, "Holy See", 1));

//        Territories*
        list.add(new Coronavirus(tempData, "Faroe Islands", 3));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));
        list.add(new Coronavirus(tempData, "Guernsey", 1));
        list.add(new Coronavirus(tempData, "Jersey", 2));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "India", 82));
        list.add(new Coronavirus(tempData, "Thailand", 75));
        list.add(new Coronavirus(tempData, "Indonesia", 69));
        list.add(new Coronavirus(tempData, "Maldives", 9));
        list.add(new Coronavirus(tempData, "Sri Lanka", 6));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 11364));
        list.add(new Coronavirus(tempData, "Qatar", 262));
        list.add(new Coronavirus(tempData, "Bahrain", 210));
        list.add(new Coronavirus(tempData, "Kuwait", 100));
        list.add(new Coronavirus(tempData, "Egypt", 93));
        list.add(new Coronavirus(tempData, "Iraq", 93));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 85));
        list.add(new Coronavirus(tempData, "Lebanon", 77));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 62));
        list.add(new Coronavirus(tempData, "Pakistan", 21));
        list.add(new Coronavirus(tempData, "Oman", 19));
        list.add(new Coronavirus(tempData, "Tunisia", 16));
        list.add(new Coronavirus(tempData, "Afghanistan", 7));
        list.add(new Coronavirus(tempData, "Morocco", 7));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Sudan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 35));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 1678));
        list.add(new Coronavirus(tempData, "Canada", 176));
        list.add(new Coronavirus(tempData, "Brazil", 98));
        list.add(new Coronavirus(tempData, "Chile", 43));
        list.add(new Coronavirus(tempData, "Argentina", 34));
        list.add(new Coronavirus(tempData, "Peru", 28));
        list.add(new Coronavirus(tempData, "Panama", 27));
        list.add(new Coronavirus(tempData, "Mexico", 26));
        list.add(new Coronavirus(tempData, "Costa Rica", 23));
        list.add(new Coronavirus(tempData, "Ecuator", 23));
        list.add(new Coronavirus(tempData, "Columbia", 16));
        list.add(new Coronavirus(tempData, "Jamaica", 7));
        list.add(new Coronavirus(tempData, "Guyana", 6));
        list.add(new Coronavirus(tempData, "Paraguay", 6));
        list.add(new Coronavirus(tempData, "Dominican Republic", 5));
        list.add(new Coronavirus(tempData, "Cuba", 4));
        list.add(new Coronavirus(tempData, "Bolivia", 3));
        list.add(new Coronavirus(tempData, "Puerto Rica", 3));
        list.add(new Coronavirus(tempData, "Honduras", 2));
        list.add(new Coronavirus(tempData, "Venezuela", 2));
        list.add(new Coronavirus(tempData, "Antigua and Barbuda", 1));
        list.add(new Coronavirus(tempData, "Guadelupa", 1));
        list.add(new Coronavirus(tempData, "Saint Vincent and the Grenadies", 1));
        list.add(new Coronavirus(tempData, "Trinidad and Tobago", 1));

//        Territories**
        list.add(new Coronavirus(tempData, "French Guiana", 6));
        list.add(new Coronavirus(tempData, "Martinique", 6));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));
        list.add(new Coronavirus(tempData, "Cayman Islands", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 26));
        list.add(new Coronavirus(tempData, "South Africa", 17));
        list.add(new Coronavirus(tempData, "Senegal", 10));
        list.add(new Coronavirus(tempData, "Burkina Faso", 2));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Democratic Republic of Congo", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "Cote d'Ivoire", 1));
        list.add(new Coronavirus(tempData, "Ethiapia", 1));
        list.add(new Coronavirus(tempData, "Gabon", 1));
        list.add(new Coronavirus(tempData, "Ghana", 1));
        list.add(new Coronavirus(tempData, "Guinea", 1));
        list.add(new Coronavirus(tempData, "Kenya", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        Territories **
        list.add(new Coronavirus(tempData, "Reunion", 5));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 697));

        return list;
    }

    private List<Coronavirus> getDataMarch13() {
        String tempData = "13 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80991));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7979));
        list.add(new Coronavirus(tempData, "Japan", 675));
        list.add(new Coronavirus(tempData, "Singapore", 187));
        list.add(new Coronavirus(tempData, "Australia", 140));
        list.add(new Coronavirus(tempData, "Malaysia", 129));
        list.add(new Coronavirus(tempData, "Philippines", 52));
        list.add(new Coronavirus(tempData, "Vietnam ", 39));
        list.add(new Coronavirus(tempData, "Brunei Darussalam", 12));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 5));
        list.add(new Coronavirus(tempData, "Mongolia", 1));

//        Territories*
        list.add(new Coronavirus(tempData, "French Polynesia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 15113));
        list.add(new Coronavirus(tempData, "Spain", 2965));
        list.add(new Coronavirus(tempData, "France", 2860));
        list.add(new Coronavirus(tempData, "Germany", 2369));
        list.add(new Coronavirus(tempData, "Switzerland", 858));
        list.add(new Coronavirus(tempData, "Denmark", 674));
        list.add(new Coronavirus(tempData, "Sweden", 620));
        list.add(new Coronavirus(tempData, "Netherlands", 614));
        list.add(new Coronavirus(tempData, "United Kingdom", 594));
        list.add(new Coronavirus(tempData, "Austria", 361));
        list.add(new Coronavirus(tempData, "Belgium", 314));
        list.add(new Coronavirus(tempData, "Norway", 489));
        list.add(new Coronavirus(tempData, "Czechia", 116));
        list.add(new Coronavirus(tempData, "Finland", 109));
        list.add(new Coronavirus(tempData, "Greece", 98));
        list.add(new Coronavirus(tempData, "Israel", 75));
        list.add(new Coronavirus(tempData, "Ireland", 70));
        list.add(new Coronavirus(tempData, "San Marino", 63));
        list.add(new Coronavirus(tempData, "Iceland", 61));
        list.add(new Coronavirus(tempData, "Slovenia", 57));
        list.add(new Coronavirus(tempData, "Poland", 49));
        list.add(new Coronavirus(tempData, "Romania", 48));
        list.add(new Coronavirus(tempData, "Portugal", 41));
        list.add(new Coronavirus(tempData, "Russia Federation", 34));
        list.add(new Coronavirus(tempData, "Georgia", 25));
        list.add(new Coronavirus(tempData, "Albania", 23));
        list.add(new Coronavirus(tempData, "Slovakia", 21));
        list.add(new Coronavirus(tempData, "Serbia", 19));
        list.add(new Coronavirus(tempData, "Luxembourg", 17));
        list.add(new Coronavirus(tempData, "Croatia", 16));
        list.add(new Coronavirus(tempData, "Hungary", 16));
        list.add(new Coronavirus(tempData, "Latvia", 16));
        list.add(new Coronavirus(tempData, "Estonia", 13));
        list.add(new Coronavirus(tempData, "Belarus", 12));
        list.add(new Coronavirus(tempData, "Azerbaijan", 11));
        list.add(new Coronavirus(tempData, "Bulgaria", 7));
        list.add(new Coronavirus(tempData, "North Macedonia", 7));
        list.add(new Coronavirus(tempData, "Cyprus", 6));
        list.add(new Coronavirus(tempData, "Malta", 6));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 4));
        list.add(new Coronavirus(tempData, "Liechtenstein", 4));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 4));
        list.add(new Coronavirus(tempData, "Lithuania", 3));
        list.add(new Coronavirus(tempData, "Ukraine", 3));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Turkey", 1));

//        Territories*
        list.add(new Coronavirus(tempData, "Faroe Islands", 2));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));
        list.add(new Coronavirus(tempData, "Guernsey", 1));
        list.add(new Coronavirus(tempData, "Jersey", 2));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 75));
        list.add(new Coronavirus(tempData, "India", 74));
        list.add(new Coronavirus(tempData, "Indonesia", 34));
        list.add(new Coronavirus(tempData, "Maldives", 8));
        list.add(new Coronavirus(tempData, "Sri Lanka", 3));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));

//        Territories**
        list.add(new Coronavirus(tempData, "Nepal", 3));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 10075));
        list.add(new Coronavirus(tempData, "Qatar", 262));
        list.add(new Coronavirus(tempData, "Bahrain", 195));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 85));
        list.add(new Coronavirus(tempData, "Kuwait", 80));
        list.add(new Coronavirus(tempData, "Iraq", 70));
        list.add(new Coronavirus(tempData, "Egypt", 67));
        list.add(new Coronavirus(tempData, "Lebanon", 66));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 21));
        list.add(new Coronavirus(tempData, "Pakistan", 2));
        list.add(new Coronavirus(tempData, "Oman", 18));
        list.add(new Coronavirus(tempData, "Afghanistan", 7));
        list.add(new Coronavirus(tempData, "Tunisia", 7));
        list.add(new Coronavirus(tempData, "Morocco", 6));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 31));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 1264));
        list.add(new Coronavirus(tempData, "Canada", 138));
        list.add(new Coronavirus(tempData, "Brazil", 77));
        list.add(new Coronavirus(tempData, "Chile", 33));
        list.add(new Coronavirus(tempData, "Argentina", 31));
        list.add(new Coronavirus(tempData, "Costa Rica", 22));
        list.add(new Coronavirus(tempData, "Peru", 22));
        list.add(new Coronavirus(tempData, "Ecuator", 17));
        list.add(new Coronavirus(tempData, "Panama", 14));
        list.add(new Coronavirus(tempData, "Mexico", 12));
        list.add(new Coronavirus(tempData, "Columbia", 9));
        list.add(new Coronavirus(tempData, "Dominican Republic", 5));
        list.add(new Coronavirus(tempData, "Paraguay", 5));
        list.add(new Coronavirus(tempData, "Bolivia", 3));
        list.add(new Coronavirus(tempData, "Cuba", 3));
        list.add(new Coronavirus(tempData, "Honduras", 2));
        list.add(new Coronavirus(tempData, "Guyana", 1));
        list.add(new Coronavirus(tempData, "Jamaica", 1));
        list.add(new Coronavirus(tempData, "Saint Vincent and the Grenadies", 1));

//        Territories**
        list.add(new Coronavirus(tempData, "French Guiana", 6));
        list.add(new Coronavirus(tempData, "Martinique", 4));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 25));
        list.add(new Coronavirus(tempData, "South Africa", 17));
        list.add(new Coronavirus(tempData, "Senegal", 10));
        list.add(new Coronavirus(tempData, "Burkina Faso", 2));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "Cote d'Ivoire", 1));
        list.add(new Coronavirus(tempData, "Democratic Republic of Congo", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch12() {
        String tempData = "12 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80981));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7869));
        list.add(new Coronavirus(tempData, "Japan", 620));
        list.add(new Coronavirus(tempData, "Singapore", 178));
        list.add(new Coronavirus(tempData, "Malaysia", 129));
        list.add(new Coronavirus(tempData, "Australia", 122));
        list.add(new Coronavirus(tempData, "Philippines", 52));
        list.add(new Coronavirus(tempData, "Vietnam ", 39));
        list.add(new Coronavirus(tempData, "Brunei Darussalam", 12));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 3));
        list.add(new Coronavirus(tempData, "Mongolia", 1));
        list.add(new Coronavirus(tempData, "French Polynesia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 12462));
        list.add(new Coronavirus(tempData, "France", 2269));
        list.add(new Coronavirus(tempData, "Spain", 2140));
        list.add(new Coronavirus(tempData, "Germany", 1567));
        list.add(new Coronavirus(tempData, "Switzerland", 645));
        list.add(new Coronavirus(tempData, "Denmark", 615));
        list.add(new Coronavirus(tempData, "Netherlands", 503));
        list.add(new Coronavirus(tempData, "Sweden", 461));
        list.add(new Coronavirus(tempData, "United Kingdom", 460));
        list.add(new Coronavirus(tempData, "Belgium", 314));
        list.add(new Coronavirus(tempData, "Austria", 302));
        list.add(new Coronavirus(tempData, "Norway", 489));
        list.add(new Coronavirus(tempData, "Greece", 98));
        list.add(new Coronavirus(tempData, "Czechia", 94));
        list.add(new Coronavirus(tempData, "Israel", 75));
        list.add(new Coronavirus(tempData, "San Marino", 63));
        list.add(new Coronavirus(tempData, "Iceland", 61));
        list.add(new Coronavirus(tempData, "Slovenia", 57));
        list.add(new Coronavirus(tempData, "Romania", 48));
        list.add(new Coronavirus(tempData, "Poland", 44));
        list.add(new Coronavirus(tempData, "Ireland", 43));
        list.add(new Coronavirus(tempData, "Portugal", 41));
        list.add(new Coronavirus(tempData, "Finland", 40));
        list.add(new Coronavirus(tempData, "Georgia", 23));
        list.add(new Coronavirus(tempData, "Russia Federation", 20));
        list.add(new Coronavirus(tempData, "Serbia", 19));
        list.add(new Coronavirus(tempData, "Latvia", 16));
        list.add(new Coronavirus(tempData, "Croatia", 16));
        list.add(new Coronavirus(tempData, "Estonia", 13));
        list.add(new Coronavirus(tempData, "Hungary", 13));
        list.add(new Coronavirus(tempData, "Belarus", 12));
        list.add(new Coronavirus(tempData, "Albania", 10));
        list.add(new Coronavirus(tempData, "Slovakia", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Bulgaria", 7));
        list.add(new Coronavirus(tempData, "North Macedonia", 7));
        list.add(new Coronavirus(tempData, "Cyprus", 6));
        list.add(new Coronavirus(tempData, "Malta", 6));
        list.add(new Coronavirus(tempData, "Luxembourg", 5));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 4));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 4));
        list.add(new Coronavirus(tempData, "Lithuania", 3));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Turkey", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));

//        Territories*
        list.add(new Coronavirus(tempData, "Faroe Islands", 2));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));
        list.add(new Coronavirus(tempData, "Guernsey", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "India", 73));
        list.add(new Coronavirus(tempData, "Thailand", 70));
        list.add(new Coronavirus(tempData, "Indonesia", 34));
        list.add(new Coronavirus(tempData, "Maldives", 8));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Sri Lanka", 2));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 9000));
        list.add(new Coronavirus(tempData, "Qatar", 262));
        list.add(new Coronavirus(tempData, "Bahrain", 189));
        list.add(new Coronavirus(tempData, "Kuwait", 80));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 74));
        list.add(new Coronavirus(tempData, "Iraq", 70));
        list.add(new Coronavirus(tempData, "Egypt", 67));
        list.add(new Coronavirus(tempData, "Lebanon", 66));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 21));
        list.add(new Coronavirus(tempData, "Pakistan", 19));
        list.add(new Coronavirus(tempData, "Oman", 18));
        list.add(new Coronavirus(tempData, "Afghanistan", 7));
        list.add(new Coronavirus(tempData, "Tunisia", 7));
        list.add(new Coronavirus(tempData, "Morocco", 5));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 30));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 987));
        list.add(new Coronavirus(tempData, "Canada", 93));
        list.add(new Coronavirus(tempData, "Brazil", 52));
        list.add(new Coronavirus(tempData, "Chile", 23));
        list.add(new Coronavirus(tempData, "Argentina", 19));
        list.add(new Coronavirus(tempData, "Ecuator", 17));
        list.add(new Coronavirus(tempData, "Peru", 17));
        list.add(new Coronavirus(tempData, "Costa Rica", 13));
        list.add(new Coronavirus(tempData, "Mexico", 11));
        list.add(new Coronavirus(tempData, "Panama", 10));
        list.add(new Coronavirus(tempData, "Columbia", 9));
        list.add(new Coronavirus(tempData, "Dominican Republic", 5));
        list.add(new Coronavirus(tempData, "Paraguay", 5));
        list.add(new Coronavirus(tempData, "Bolivia", 2));
        list.add(new Coronavirus(tempData, "Honduras", 2));
        list.add(new Coronavirus(tempData, "Jamaica", 1));

//        Territories**
        list.add(new Coronavirus(tempData, "French Guiana", 5));
        list.add(new Coronavirus(tempData, "Martinique", 3));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 25));
        list.add(new Coronavirus(tempData, "South Africa", 13));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Burkina Faso", 2));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "Cote d'Ivoire", 1));
        list.add(new Coronavirus(tempData, "Democratic Republic of Congo", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch11() {
        String tempData = "11 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80955));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7755));
        list.add(new Coronavirus(tempData, "Japan", 568));
        list.add(new Coronavirus(tempData, "Singapore", 166));
        list.add(new Coronavirus(tempData, "Malaysia", 129));
        list.add(new Coronavirus(tempData, "Australia", 112));
        list.add(new Coronavirus(tempData, "Philippines", 49));
        list.add(new Coronavirus(tempData, "Vietnam ", 35));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 3));
        list.add(new Coronavirus(tempData, "Brunei Darussalam", 1));
        list.add(new Coronavirus(tempData, "Mongolia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 10149));
        list.add(new Coronavirus(tempData, "France", 1774));
        list.add(new Coronavirus(tempData, "Spain", 1639));
        list.add(new Coronavirus(tempData, "Germany", 1296));
        list.add(new Coronavirus(tempData, "Switzerland", 491));
        list.add(new Coronavirus(tempData, "Netherlands", 382));
        list.add(new Coronavirus(tempData, "United Kingdom", 373));
        list.add(new Coronavirus(tempData, "Sweden", 326));
        list.add(new Coronavirus(tempData, "Norway", 277));
        list.add(new Coronavirus(tempData, "Belgium", 267));
        list.add(new Coronavirus(tempData, "Denmark", 262));
        list.add(new Coronavirus(tempData, "Austria", 182));
        list.add(new Coronavirus(tempData, "Greece", 89));
        list.add(new Coronavirus(tempData, "Israel", 75));
        list.add(new Coronavirus(tempData, "San Marino", 63));
        list.add(new Coronavirus(tempData, "Czechia", 61));
        list.add(new Coronavirus(tempData, "Iceland", 61));
        list.add(new Coronavirus(tempData, "Portugal", 41));
        list.add(new Coronavirus(tempData, "Finland", 40));
        list.add(new Coronavirus(tempData, "Ireland", 34));
        list.add(new Coronavirus(tempData, "Slovenia", 31));
        list.add(new Coronavirus(tempData, "Romania", 25));
        list.add(new Coronavirus(tempData, "Georgia", 23));
        list.add(new Coronavirus(tempData, "Poland", 22));
        list.add(new Coronavirus(tempData, "Croatia", 16));
        list.add(new Coronavirus(tempData, "Estonia", 13));
        list.add(new Coronavirus(tempData, "Hungary", 13));
        list.add(new Coronavirus(tempData, "Serbia", 12));
        list.add(new Coronavirus(tempData, "Albania", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Belarus", 9));
        list.add(new Coronavirus(tempData, "Latvia", 8));
        list.add(new Coronavirus(tempData, "North Macedonia", 7));
        list.add(new Coronavirus(tempData, "Russia Federation", 7));
        list.add(new Coronavirus(tempData, "Slovakia", 7));
        list.add(new Coronavirus(tempData, "Bulgaria", 6));
        list.add(new Coronavirus(tempData, "Luxembourg", 5));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 4));
        list.add(new Coronavirus(tempData, "Malta", 4));
        list.add(new Coronavirus(tempData, "Cyprus", 1));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Faroe Islands", 2));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));
        list.add(new Coronavirus(tempData, "Guernsey", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "India", 60));
        list.add(new Coronavirus(tempData, "Thailand", 59));
        list.add(new Coronavirus(tempData, "Indonesia", 27));
        list.add(new Coronavirus(tempData, "Maldives", 8));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 8042));
        list.add(new Coronavirus(tempData, "Bahrain", 110));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 74));
        list.add(new Coronavirus(tempData, "Kuwait", 69));
        list.add(new Coronavirus(tempData, "Iraq", 61));
        list.add(new Coronavirus(tempData, "Egypt", 59));
        list.add(new Coronavirus(tempData, "Lebanon", 41));
        list.add(new Coronavirus(tempData, "Qatar", 24));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 20));
        list.add(new Coronavirus(tempData, "Oman", 18));
        list.add(new Coronavirus(tempData, "Pakistan", 16));
        list.add(new Coronavirus(tempData, "Tunisia", 6));
        list.add(new Coronavirus(tempData, "Afghanistan", 4));
        list.add(new Coronavirus(tempData, "Morocco", 3));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 30));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 696));
        list.add(new Coronavirus(tempData, "Canada", 93));
        list.add(new Coronavirus(tempData, "Brazil", 34));
        list.add(new Coronavirus(tempData, "Argentina", 17));
        list.add(new Coronavirus(tempData, "Chile", 17));
        list.add(new Coronavirus(tempData, "Ecuator", 15));
        list.add(new Coronavirus(tempData, "Costa Rica", 13));
        list.add(new Coronavirus(tempData, "Peru", 11));
        list.add(new Coronavirus(tempData, "Panama", 8));
        list.add(new Coronavirus(tempData, "Paraguay", 5));
        list.add(new Coronavirus(tempData, "Mexico", 7));
        list.add(new Coronavirus(tempData, "Dominican Republic", 5));
        list.add(new Coronavirus(tempData, "Columbia", 3));
        list.add(new Coronavirus(tempData, "Bolivia", 2));
        list.add(new Coronavirus(tempData, "Jamaica", 1));
        list.add(new Coronavirus(tempData, "French Guiana", 5));
        list.add(new Coronavirus(tempData, "Martinique", 3));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 20));
        list.add(new Coronavirus(tempData, "South Africa", 7));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Burkina Faso", 2));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "DR Congo", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch10() {
        String tempData = "10 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80924));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7513));
        list.add(new Coronavirus(tempData, "Japan", 514));
        list.add(new Coronavirus(tempData, "Singapore", 160));
        list.add(new Coronavirus(tempData, "Malaysia", 117));
        list.add(new Coronavirus(tempData, "Australia", 92));
        list.add(new Coronavirus(tempData, "Philippines", 33));
        list.add(new Coronavirus(tempData, "Vietnam ", 31));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 2));
        list.add(new Coronavirus(tempData, "Brunei Darussalam", 1));
        list.add(new Coronavirus(tempData, "Mongolia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 9172));
        list.add(new Coronavirus(tempData, "France", 1402));
        list.add(new Coronavirus(tempData, "Germany", 1139));
        list.add(new Coronavirus(tempData, "Spain", 1024));
        list.add(new Coronavirus(tempData, "Switzerland", 332));
        list.add(new Coronavirus(tempData, "United Kingdom", 323));
        list.add(new Coronavirus(tempData, "Netherlands", 321));
        list.add(new Coronavirus(tempData, "Sweden", 248));
        list.add(new Coronavirus(tempData, "Belgium", 239));
        list.add(new Coronavirus(tempData, "Norway", 192));
        list.add(new Coronavirus(tempData, "Austria", 131));
        list.add(new Coronavirus(tempData, "Denmark", 90));
        list.add(new Coronavirus(tempData, "Greece", 73));
        list.add(new Coronavirus(tempData, "Iceland", 55));
        list.add(new Coronavirus(tempData, "San Marino", 49));
        list.add(new Coronavirus(tempData, "Finland", 40));
        list.add(new Coronavirus(tempData, "Israel", 39));
        list.add(new Coronavirus(tempData, "Czechia", 38));
        list.add(new Coronavirus(tempData, "Portugal", 30));
        list.add(new Coronavirus(tempData, "Ireland", 24));
        list.add(new Coronavirus(tempData, "Slovenia", 23));
        list.add(new Coronavirus(tempData, "Poland", 16));
        list.add(new Coronavirus(tempData, "Georgia", 15));
        list.add(new Coronavirus(tempData, "Romania", 15));
        list.add(new Coronavirus(tempData, "Croatia", 12));
        list.add(new Coronavirus(tempData, "Estonia", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Hungary", 9));
        list.add(new Coronavirus(tempData, "North Macedonia", 7));
        list.add(new Coronavirus(tempData, "Russia Federation", 7));
        list.add(new Coronavirus(tempData, "Slovakia", 7));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Latvia", 6));
        list.add(new Coronavirus(tempData, "Luxembourg", 5));
        list.add(new Coronavirus(tempData, "Bulgaria", 4));
        list.add(new Coronavirus(tempData, "Malta", 4));
        list.add(new Coronavirus(tempData, "Albania", 2));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 2));
        list.add(new Coronavirus(tempData, "Cyprus", 1));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 1));
        list.add(new Coronavirus(tempData, "Serbia", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Faroe Islands", 2));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));
        list.add(new Coronavirus(tempData, "Guernsey", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 53));
        list.add(new Coronavirus(tempData, "India", 44));
        list.add(new Coronavirus(tempData, "Indonesia", 19));
        list.add(new Coronavirus(tempData, "Maldives", 4));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 7161));
        list.add(new Coronavirus(tempData, "Bahrain", 109));
        list.add(new Coronavirus(tempData, "Kuwait", 65));
        list.add(new Coronavirus(tempData, "Iraq", 61));
        list.add(new Coronavirus(tempData, "Egypt", 59));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 59));
        list.add(new Coronavirus(tempData, "Lebanon", 41));
        list.add(new Coronavirus(tempData, "Oman", 18));
        list.add(new Coronavirus(tempData, "Qatar", 18));
        list.add(new Coronavirus(tempData, "Pakistan", 16));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 15));
        list.add(new Coronavirus(tempData, "Afghanistan", 4));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Tunisia", 2));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 26));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 472));
        list.add(new Coronavirus(tempData, "Canada", 77));
        list.add(new Coronavirus(tempData, "Brazil", 25));
        list.add(new Coronavirus(tempData, "Ecuator", 15));
        list.add(new Coronavirus(tempData, "Chile", 13));
        list.add(new Coronavirus(tempData, "Argentina", 12));
        list.add(new Coronavirus(tempData, "Costa Rica", 9));
        list.add(new Coronavirus(tempData, "Peru", 9));
        list.add(new Coronavirus(tempData, "Mexico", 7));
        list.add(new Coronavirus(tempData, "Dominican Republic", 5));
        list.add(new Coronavirus(tempData, "Columbia", 3));
        list.add(new Coronavirus(tempData, "Panama", 1));
        list.add(new Coronavirus(tempData, "Paraguay", 1));
        list.add(new Coronavirus(tempData, "French Guiana", 5));
        list.add(new Coronavirus(tempData, "Martinique", 2));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 20));
        list.add(new Coronavirus(tempData, "South Africa", 7));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch09() {
        String tempData = "9 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80904));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7382));
        list.add(new Coronavirus(tempData, "Japan", 488));
        list.add(new Coronavirus(tempData, "Singapore", 150));
        list.add(new Coronavirus(tempData, "Malaysia", 93));
        list.add(new Coronavirus(tempData, "Australia", 77));
        list.add(new Coronavirus(tempData, "Vietnam ", 30));
        list.add(new Coronavirus(tempData, "Philippines", 10));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 2));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 7375));
        list.add(new Coronavirus(tempData, "France", 1116));
        list.add(new Coronavirus(tempData, "Germany", 1112));
        list.add(new Coronavirus(tempData, "Spain", 589));
        list.add(new Coronavirus(tempData, "Switzerland", 332));
        list.add(new Coronavirus(tempData, "United Kingdom", 277));
        list.add(new Coronavirus(tempData, "Netherlands", 265));
        list.add(new Coronavirus(tempData, "Sweden", 203));
        list.add(new Coronavirus(tempData, "Belgium", 200));
        list.add(new Coronavirus(tempData, "Norway", 169));
        list.add(new Coronavirus(tempData, "Austria", 112));
        list.add(new Coronavirus(tempData, "Greece", 73));
        list.add(new Coronavirus(tempData, "Iceland", 55));
        list.add(new Coronavirus(tempData, "Israel", 39));
        list.add(new Coronavirus(tempData, "San Marino", 37));
        list.add(new Coronavirus(tempData, "Denmark", 36));
        list.add(new Coronavirus(tempData, "Czechia", 32));
        list.add(new Coronavirus(tempData, "Finland", 30));
        list.add(new Coronavirus(tempData, "Portugal", 30));
        list.add(new Coronavirus(tempData, "Ireland", 21));
        list.add(new Coronavirus(tempData, "Slovenia", 16));
        list.add(new Coronavirus(tempData, "Romania", 15));
        list.add(new Coronavirus(tempData, "Georgia", 13));
        list.add(new Coronavirus(tempData, "Croatia", 11));
        list.add(new Coronavirus(tempData, "Poland", 11));
        list.add(new Coronavirus(tempData, "Estonia", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Hungary", 9));
        list.add(new Coronavirus(tempData, "Russia Federation", 7));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Slovakia", 5));
        list.add(new Coronavirus(tempData, "Bulgaria", 4));
        list.add(new Coronavirus(tempData, "Latvia", 3));
        list.add(new Coronavirus(tempData, "Malta", 3));
        list.add(new Coronavirus(tempData, "North Macedonia", 3));
        list.add(new Coronavirus(tempData, "Albania", 2));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 2));
        list.add(new Coronavirus(tempData, "Luxembourg", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 1));
        list.add(new Coronavirus(tempData, "Serbia", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Faroe Islands", 2));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 50));
        list.add(new Coronavirus(tempData, "India", 43));
        list.add(new Coronavirus(tempData, "Indonesia", 6));
        list.add(new Coronavirus(tempData, "Maldives", 4));
        list.add(new Coronavirus(tempData, "Bangladesh", 3));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 6566));
        list.add(new Coronavirus(tempData, "Bahrain", 79));
        list.add(new Coronavirus(tempData, "Kuwait", 64));
        list.add(new Coronavirus(tempData, "Iraq", 60));
        list.add(new Coronavirus(tempData, "Egypt", 55));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 45));
        list.add(new Coronavirus(tempData, "Lebanon", 32));
        list.add(new Coronavirus(tempData, "Oman", 16));
        list.add(new Coronavirus(tempData, "Qatar", 15));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 15));
        list.add(new Coronavirus(tempData, "Pakistan", 6));
        list.add(new Coronavirus(tempData, "Afghanistan", 4));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Tunisia", 2));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Palestina", 19));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 213));
        list.add(new Coronavirus(tempData, "Canada", 62));
        list.add(new Coronavirus(tempData, "Brazil", 25));
        list.add(new Coronavirus(tempData, "Ecuator", 15));
        list.add(new Coronavirus(tempData, "Argentina", 12));
        list.add(new Coronavirus(tempData, "Chile", 10));
        list.add(new Coronavirus(tempData, "Costa Rica", 9));
        list.add(new Coronavirus(tempData, "Mexico", 7));
        list.add(new Coronavirus(tempData, "Peru", 6));
        list.add(new Coronavirus(tempData, "Columbia", 1));
        list.add(new Coronavirus(tempData, "Dominican Republic", 1));
        list.add(new Coronavirus(tempData, "Paraguay", 1));
        list.add(new Coronavirus(tempData, "French Guiana", 5));
        list.add(new Coronavirus(tempData, "Martinique", 2));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 20));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "South Africa", 3));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 2));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch08() {
        String tempData = "8 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80859));
        list.add(new Coronavirus(tempData, "Republic of Korea", 7134));
        list.add(new Coronavirus(tempData, "Japan", 455));
        list.add(new Coronavirus(tempData, "Singapore", 130));
        list.add(new Coronavirus(tempData, "Malaysia", 93));
        list.add(new Coronavirus(tempData, "Australia", 74));
        list.add(new Coronavirus(tempData, "Vietnam ", 21));
        list.add(new Coronavirus(tempData, "Philippines", 6));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 2));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 5883));
        list.add(new Coronavirus(tempData, "Germany", 795));
        list.add(new Coronavirus(tempData, "France", 706));
        list.add(new Coronavirus(tempData, "Spain", 430));
        list.add(new Coronavirus(tempData, "Switzerland", 264));
        list.add(new Coronavirus(tempData, "United Kingdom", 210));
        list.add(new Coronavirus(tempData, "Netherlands", 188));
        list.add(new Coronavirus(tempData, "Belgium", 169));
        list.add(new Coronavirus(tempData, "Sweden", 161));
        list.add(new Coronavirus(tempData, "Norway", 147));
        list.add(new Coronavirus(tempData, "Austria", 104));
        list.add(new Coronavirus(tempData, "Greece", 66));
        list.add(new Coronavirus(tempData, "Iceland", 45));
        list.add(new Coronavirus(tempData, "Denmark", 31));
        list.add(new Coronavirus(tempData, "San Marino", 27));
        list.add(new Coronavirus(tempData, "Czechia", 26));
        list.add(new Coronavirus(tempData, "Israel", 25));
        list.add(new Coronavirus(tempData, "Portugal", 21));
        list.add(new Coronavirus(tempData, "Finland", 19));
        list.add(new Coronavirus(tempData, "Ireland", 18));
        list.add(new Coronavirus(tempData, "Romania", 13));
        list.add(new Coronavirus(tempData, "Georgia", 12));
        list.add(new Coronavirus(tempData, "Slovenia", 12));
        list.add(new Coronavirus(tempData, "Croatia", 11));
        list.add(new Coronavirus(tempData, "Estonia", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Hungary", 7));
        list.add(new Coronavirus(tempData, "Russia Federation", 7));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Poland", 6));
        list.add(new Coronavirus(tempData, "Malta", 3));
        list.add(new Coronavirus(tempData, "North Macedonia", 3));
        list.add(new Coronavirus(tempData, "Slovakia", 3));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 2));
        list.add(new Coronavirus(tempData, "Bulgaria", 2));
        list.add(new Coronavirus(tempData, "Luxembourg", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Republic of Moldova", 1));
        list.add(new Coronavirus(tempData, "Serbia", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Faroe Islands", 1));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 50));
        list.add(new Coronavirus(tempData, "India", 34));
        list.add(new Coronavirus(tempData, "Indonesia", 4));
        list.add(new Coronavirus(tempData, "Maldives", 2));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 5823));
        list.add(new Coronavirus(tempData, "Kuwait", 62));
        list.add(new Coronavirus(tempData, "Bahrain", 56));
        list.add(new Coronavirus(tempData, "Iraq", 54));
        list.add(new Coronavirus(tempData, "Egypt", 48));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 45));
        list.add(new Coronavirus(tempData, "Lebanon", 28));
        list.add(new Coronavirus(tempData, "Oman", 16));
        list.add(new Coronavirus(tempData, "Qatar", 12));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 7));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Afghanistan", 4));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));
        list.add(new Coronavirus(tempData, "Palestina", 16));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 213));
        list.add(new Coronavirus(tempData, "Canada", 57));
        list.add(new Coronavirus(tempData, "Brazil", 19));
        list.add(new Coronavirus(tempData, "Ecuator", 14));
        list.add(new Coronavirus(tempData, "Argentina", 9));
        list.add(new Coronavirus(tempData, "Mexico", 7));
        list.add(new Coronavirus(tempData, "Peru", 6));
        list.add(new Coronavirus(tempData, "Chile", 5));
        list.add(new Coronavirus(tempData, "Costa Rica", 5));
        list.add(new Coronavirus(tempData, "Columbia", 1));
        list.add(new Coronavirus(tempData, "Dominican Republic", 1));
        list.add(new Coronavirus(tempData, "French Guiana", 5));
        list.add(new Coronavirus(tempData, "Martinique", 2));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 17));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "South Africa", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch07() {
        String tempData = "7 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80813));
        list.add(new Coronavirus(tempData, "Republic of Korea", 6767));
        list.add(new Coronavirus(tempData, "Japan", 408));
        list.add(new Coronavirus(tempData, "Singapore", 130));
        list.add(new Coronavirus(tempData, "Malaysia", 83));
        list.add(new Coronavirus(tempData, "Australia", 62));
        list.add(new Coronavirus(tempData, "Vietnam ", 17));
        list.add(new Coronavirus(tempData, "New Zealand", 5));
        list.add(new Coronavirus(tempData, "Philippines", 5));
        list.add(new Coronavirus(tempData, "Cambodia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 4636));
        list.add(new Coronavirus(tempData, "Germany", 639));
        list.add(new Coronavirus(tempData, "France", 613));
        list.add(new Coronavirus(tempData, "Spain", 374));
        list.add(new Coronavirus(tempData, "Switzerland", 209));
        list.add(new Coronavirus(tempData, "United Kingdom", 167));
        list.add(new Coronavirus(tempData, "Sweden", 137));
        list.add(new Coronavirus(tempData, "Netherlands", 128));
        list.add(new Coronavirus(tempData, "Norway", 113));
        list.add(new Coronavirus(tempData, "Belgium", 109));
        list.add(new Coronavirus(tempData, "Austria", 66));
        list.add(new Coronavirus(tempData, "Iceland", 45));
        list.add(new Coronavirus(tempData, "Greece", 32));
        list.add(new Coronavirus(tempData, "San Marino", 24));
        list.add(new Coronavirus(tempData, "Denmark", 23));
        list.add(new Coronavirus(tempData, "Finland", 19));
        list.add(new Coronavirus(tempData, "Israel", 19));
        list.add(new Coronavirus(tempData, "Ireland", 18));
        list.add(new Coronavirus(tempData, "Portugal", 13));
        list.add(new Coronavirus(tempData, "Czechia", 12));
        list.add(new Coronavirus(tempData, "Croatia", 11));
        list.add(new Coronavirus(tempData, "Estonia", 10));
        list.add(new Coronavirus(tempData, "Azerbaijan", 9));
        list.add(new Coronavirus(tempData, "Georgia", 9));
        list.add(new Coronavirus(tempData, "Slovenia", 9));
        list.add(new Coronavirus(tempData, "Romania", 7));
        list.add(new Coronavirus(tempData, "Russia Federation", 7));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Poland", 5));
        list.add(new Coronavirus(tempData, "Hungary", 5));
        list.add(new Coronavirus(tempData, "North Macedonia", 3));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 2));
        list.add(new Coronavirus(tempData, "Luxembourg", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Holy See", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "Serbia", 1));
        list.add(new Coronavirus(tempData, "Slovakia", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 48));
        list.add(new Coronavirus(tempData, "India", 31));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 4747));
        list.add(new Coronavirus(tempData, "Kuwait", 58));
        list.add(new Coronavirus(tempData, "Bahrain", 49));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 45));
        list.add(new Coronavirus(tempData, "Iraq", 44));
        list.add(new Coronavirus(tempData, "Lebanon", 22));
        list.add(new Coronavirus(tempData, "Oman", 16));
        list.add(new Coronavirus(tempData, "Qatar", 11));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 8));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Egypt", 3));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));
        list.add(new Coronavirus(tempData, "Palestina", 16));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 213));
        list.add(new Coronavirus(tempData, "Canada", 51));
        list.add(new Coronavirus(tempData, "Ecuator", 14));
        list.add(new Coronavirus(tempData, "Brazil", 13));
        list.add(new Coronavirus(tempData, "Chile", 5));
        list.add(new Coronavirus(tempData, "Mexico", 5));
        list.add(new Coronavirus(tempData, "Argentina", 2));
        list.add(new Coronavirus(tempData, "Columbia", 1));
        list.add(new Coronavirus(tempData, "Dominican Republic", 1));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 17));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Cameroon", 2));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "South Africa", 1));
        list.add(new Coronavirus(tempData, "Togo", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch06() {
        String tempData = "6 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80711));
        list.add(new Coronavirus(tempData, "Republic of Korea", 6284));
        list.add(new Coronavirus(tempData, "Japan", 349));
        list.add(new Coronavirus(tempData, "Singapore", 117));
        list.add(new Coronavirus(tempData, "Australia", 57));
        list.add(new Coronavirus(tempData, "Malaysia", 55));
        list.add(new Coronavirus(tempData, "Vietnam ", 16));
        list.add(new Coronavirus(tempData, "Philippines", 5));
        list.add(new Coronavirus(tempData, "New Zealand", 4));
        list.add(new Coronavirus(tempData, "Cambodia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 3858));
        list.add(new Coronavirus(tempData, "Germany", 534));
        list.add(new Coronavirus(tempData, "France", 420));
        list.add(new Coronavirus(tempData, "Spain", 257));
        list.add(new Coronavirus(tempData, "United Kingdom", 118));
        list.add(new Coronavirus(tempData, "Norway", 86));
        list.add(new Coronavirus(tempData, "Switzerland", 86));
        list.add(new Coronavirus(tempData, "Netherlands", 82));
        list.add(new Coronavirus(tempData, "Sweden", 61));
        list.add(new Coronavirus(tempData, "Belgium", 50));
        list.add(new Coronavirus(tempData, "Austria", 47));
        list.add(new Coronavirus(tempData, "Greece", 32));
        list.add(new Coronavirus(tempData, "Iceland", 26));
        list.add(new Coronavirus(tempData, "San Marino", 21));
        list.add(new Coronavirus(tempData, "Denmark", 18));
        list.add(new Coronavirus(tempData, "Israel", 15));
        list.add(new Coronavirus(tempData, "Ireland", 14));
        list.add(new Coronavirus(tempData, "Czechia", 12));
        list.add(new Coronavirus(tempData, "Finland", 12));
        list.add(new Coronavirus(tempData, "Croatia", 10));
        list.add(new Coronavirus(tempData, "Georgia", 9));
        list.add(new Coronavirus(tempData, "Portugal", 9));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Romania", 6));
        list.add(new Coronavirus(tempData, "Russia Federation", 4));
        list.add(new Coronavirus(tempData, "Azerbaijan", 3));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 2));
        list.add(new Coronavirus(tempData, "Estonia", 2));
        list.add(new Coronavirus(tempData, "Hungary", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Luxembourg", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "Slovenia", 1));
        list.add(new Coronavirus(tempData, "Poland", 1));
        list.add(new Coronavirus(tempData, "Serbia", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 47));
        list.add(new Coronavirus(tempData, "India", 30));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Bhutan", 1));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 3513));
        list.add(new Coronavirus(tempData, "Kuwait", 58));
        list.add(new Coronavirus(tempData, "Bahrain", 49));
        list.add(new Coronavirus(tempData, "Iraq", 36));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 27));
        list.add(new Coronavirus(tempData, "Lebon", 16));
        list.add(new Coronavirus(tempData, "Oman", 16));
        list.add(new Coronavirus(tempData, "Qatar", 8));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 8));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Egypt", 3));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));
        list.add(new Coronavirus(tempData, "Palestina", 7));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 148));
        list.add(new Coronavirus(tempData, "Canada", 45));
        list.add(new Coronavirus(tempData, "Ecuator", 13));
        list.add(new Coronavirus(tempData, "Brazil", 7));
        list.add(new Coronavirus(tempData, "Mexico", 5));
        list.add(new Coronavirus(tempData, "Argentina", 1));
        list.add(new Coronavirus(tempData, "Chile", 1));
        list.add(new Coronavirus(tempData, "Dominican Republic", 1));
        list.add(new Coronavirus(tempData, "Saint Martin", 2));
        list.add(new Coronavirus(tempData, "Saint Barthelemy", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 12));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Cameroon", 1));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "South Africa", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 696));

        return list;
    }

    private List<Coronavirus> getDataMarch05() {
        String tempData = "5 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80565));
        list.add(new Coronavirus(tempData, "Republic of Korea", 5766));
        list.add(new Coronavirus(tempData, "Japan", 317));
        list.add(new Coronavirus(tempData, "Singapore", 108));
        list.add(new Coronavirus(tempData, "Australia", 57));
        list.add(new Coronavirus(tempData, "Malaysia", 29));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "New Zealand", 2));
        list.add(new Coronavirus(tempData, "Cambodia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 3089));
        list.add(new Coronavirus(tempData, "France", 282));
        list.add(new Coronavirus(tempData, "Germany", 262));
        list.add(new Coronavirus(tempData, "Spain", 198));
        list.add(new Coronavirus(tempData, "United Kingdom", 89));
        list.add(new Coronavirus(tempData, "Switzerland", 56));
        list.add(new Coronavirus(tempData, "Norway", 56));
        list.add(new Coronavirus(tempData, "Netherlands", 28));
        list.add(new Coronavirus(tempData, "Austria", 37));
        list.add(new Coronavirus(tempData, "Sweden", 35));
        list.add(new Coronavirus(tempData, "Iceland", 26));
        list.add(new Coronavirus(tempData, "Belgium", 23));
        list.add(new Coronavirus(tempData, "San Marino", 16));
        list.add(new Coronavirus(tempData, "Israel", 15));
        list.add(new Coronavirus(tempData, "Denmark", 10));
        list.add(new Coronavirus(tempData, "Croatia", 9));
        list.add(new Coronavirus(tempData, "Greece", 9));
        list.add(new Coronavirus(tempData, "Portugal", 7));
        list.add(new Coronavirus(tempData, "Finland", 7));
        list.add(new Coronavirus(tempData, "Belarus", 6));
        list.add(new Coronavirus(tempData, "Azerbaijan", 3));
        list.add(new Coronavirus(tempData, "Czechia", 3));
        list.add(new Coronavirus(tempData, "Georgia", 3));
        list.add(new Coronavirus(tempData, "Romania", 4));
        list.add(new Coronavirus(tempData, "Russia Federation", 3));
        list.add(new Coronavirus(tempData, "Bosnia and Herzegovina", 3));
        list.add(new Coronavirus(tempData, "Armenia", 3));
        list.add(new Coronavirus(tempData, "Estonia", 2));
        list.add(new Coronavirus(tempData, "Hungary", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
        list.add(new Coronavirus(tempData, "Ireland", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Luxembourg", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "Slovenia", 1));
        list.add(new Coronavirus(tempData, "Poland", 1));
        list.add(new Coronavirus(tempData, "Liechtenstein", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));
        list.add(new Coronavirus(tempData, "Gibraltar", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 47));
        list.add(new Coronavirus(tempData, "India", 29));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 2922));
        list.add(new Coronavirus(tempData, "Kuwait", 58));
        list.add(new Coronavirus(tempData, "Bahrain", 49));
        list.add(new Coronavirus(tempData, "Iraq", 36));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 27));
        list.add(new Coronavirus(tempData, "Oman", 15));
        list.add(new Coronavirus(tempData, "Lebon", 13));
        list.add(new Coronavirus(tempData, "Qatar", 8));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Egypt", 2));
        list.add(new Coronavirus(tempData, "Morocco", 2));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));
        list.add(new Coronavirus(tempData, "Palestina", 4));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 129));
        list.add(new Coronavirus(tempData, "Canada", 30));
        list.add(new Coronavirus(tempData, "Ecuator", 7));
        list.add(new Coronavirus(tempData, "Mexico", 5));
        list.add(new Coronavirus(tempData, "Brazil", 3));
        list.add(new Coronavirus(tempData, "Argentina", 1));
        list.add(new Coronavirus(tempData, "Chile", 1));
        list.add(new Coronavirus(tempData, "Dominican Republic", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 5));
        list.add(new Coronavirus(tempData, "Senegal", 4));
        list.add(new Coronavirus(tempData, "Nigeria", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 706));

        return list;
    }

    private List<Coronavirus> getDataMarch04() {
        String tempData = "4 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80422));
        list.add(new Coronavirus(tempData, "Republic of Korea", 5328));
        list.add(new Coronavirus(tempData, "Japan", 284));
        list.add(new Coronavirus(tempData, "Singapore", 110));
        list.add(new Coronavirus(tempData, "Malaysia", 50));
        list.add(new Coronavirus(tempData, "Australia", 43));
        list.add(new Coronavirus(tempData, "Vietnam ", 16));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "New Zealand", 2));
        list.add(new Coronavirus(tempData, "Cambodia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 2502));
        list.add(new Coronavirus(tempData, "France", 212));
        list.add(new Coronavirus(tempData, "Germany", 196));
        list.add(new Coronavirus(tempData, "Spain", 151));
        list.add(new Coronavirus(tempData, "United Kingdom", 51));
        list.add(new Coronavirus(tempData, "Switzerland", 37));
        list.add(new Coronavirus(tempData, "Norway", 33));
        list.add(new Coronavirus(tempData, "Netherlands", 28));
        list.add(new Coronavirus(tempData, "Austria", 24));
        list.add(new Coronavirus(tempData, "Sweden", 24));
        list.add(new Coronavirus(tempData, "Iceland", 16));
        list.add(new Coronavirus(tempData, "Israel", 12));
        list.add(new Coronavirus(tempData, "Croatia", 9));
        list.add(new Coronavirus(tempData, "Belgium", 8));
        list.add(new Coronavirus(tempData, "Denmark", 8));
        list.add(new Coronavirus(tempData, "San Marino", 8));
        list.add(new Coronavirus(tempData, "Finland", 7));
        list.add(new Coronavirus(tempData, "Greece", 7));
        list.add(new Coronavirus(tempData, "Czechia", 5));
        list.add(new Coronavirus(tempData, "Romania", 4));
        list.add(new Coronavirus(tempData, "Azerbaijan", 3));
        list.add(new Coronavirus(tempData, "Georgia", 3));
        list.add(new Coronavirus(tempData, "Russia Federation", 3));
        list.add(new Coronavirus(tempData, "Estonia", 2));
        list.add(new Coronavirus(tempData, "Ireland", 2));
        list.add(new Coronavirus(tempData, "Portugal", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
        list.add(new Coronavirus(tempData, "Armenia", 1));
        list.add(new Coronavirus(tempData, "Belarus", 1));
        list.add(new Coronavirus(tempData, "Lithuania", 1));
        list.add(new Coronavirus(tempData, "Luxembourg", 1));
        list.add(new Coronavirus(tempData, "Monaco", 1));
        list.add(new Coronavirus(tempData, "North Macedonia", 1));
        list.add(new Coronavirus(tempData, "Poland", 1));
        list.add(new Coronavirus(tempData, "Ukraine", 1));

//        South-East Asia Region
        list.add(new Coronavirus(tempData, "Thailand", 43));
        list.add(new Coronavirus(tempData, "India", 5));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 1501));
        list.add(new Coronavirus(tempData, "Kuwait", 56));
        list.add(new Coronavirus(tempData, "Bahrain", 49));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 21));
        list.add(new Coronavirus(tempData, "Iraq", 26));
        list.add(new Coronavirus(tempData, "Lebon", 13));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Qatar", 6));
        list.add(new Coronavirus(tempData, "Egypt", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Morocco", 1));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 64));
        list.add(new Coronavirus(tempData, "Canada", 24));
        list.add(new Coronavirus(tempData, "Mexico", 6));
        list.add(new Coronavirus(tempData, "Dominican Republic", 2));
        list.add(new Coronavirus(tempData, "Brazil", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 5));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "Senegal", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 706));

        return list;
    }

    private List<Coronavirus> getDataMarch03() {
        String tempData = "3 March 2020";
        List<Coronavirus> list = new LinkedList<>();

//        Western Pacific Region
        list.add(new Coronavirus(tempData, "China", 80304));
        list.add(new Coronavirus(tempData, "Republic of Korea", 4812));
        list.add(new Coronavirus(tempData, "Japan", 268));
        list.add(new Coronavirus(tempData, "Singapore", 108));
        list.add(new Coronavirus(tempData, "Australia", 33));
        list.add(new Coronavirus(tempData, "Malaysia", 29));
        list.add(new Coronavirus(tempData, "Vietnam ", 15));
        list.add(new Coronavirus(tempData, "Philippines", 3));
        list.add(new Coronavirus(tempData, "New Zealand", 2));
        list.add(new Coronavirus(tempData, "Cambodia", 1));

//        European Region
        list.add(new Coronavirus(tempData, "Italy", 2036));
        list.add(new Coronavirus(tempData, "France", 191));
        list.add(new Coronavirus(tempData, "Germany", 157));
        list.add(new Coronavirus(tempData, "Spain", 114));
        list.add(new Coronavirus(tempData, "United Kingdom", 39));
        list.add(new Coronavirus(tempData, "Switzerland", 30));
        list.add(new Coronavirus(tempData, "Norway", 25));
        list.add(new Coronavirus(tempData, "Netherlands", 18));
        list.add(new Coronavirus(tempData, "Austria", 18));
        list.add(new Coronavirus(tempData, "Sweden", 15));
        list.add(new Coronavirus(tempData, "Israel", 10));
        list.add(new Coronavirus(tempData, "Iceland", 9));
        list.add(new Coronavirus(tempData, "Croatia", 8));
        list.add(new Coronavirus(tempData, "Greece", 7));
        list.add(new Coronavirus(tempData, "Finland", 6));
        list.add(new Coronavirus(tempData, "Denmark", 5));
        list.add(new Coronavirus(tempData, "Azerbaijan", 3));
        list.add(new Coronavirus(tempData, "Czechia", 3));
        list.add(new Coronavirus(tempData, "Georgia", 3));
        list.add(new Coronavirus(tempData, "Romania", 3));
        list.add(new Coronavirus(tempData, "Russia Federation", 3));
        list.add(new Coronavirus(tempData, "Portugal", 2));
        list.add(new Coronavirus(tempData, "Andorra", 1));
        list.add(new Coronavirus(tempData, "Latvia", 1));
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
        list.add(new Coronavirus(tempData, "Thailand", 43));
        list.add(new Coronavirus(tempData, "India", 5));
        list.add(new Coronavirus(tempData, "Indonesia", 2));
        list.add(new Coronavirus(tempData, "Nepal", 1));
        list.add(new Coronavirus(tempData, "Sri Lanka", 1));

//        Eastern Mediterranean Region
        list.add(new Coronavirus(tempData, "Iran", 1501));
        list.add(new Coronavirus(tempData, "Kuwait", 56));
        list.add(new Coronavirus(tempData, "Bahrain", 49));
        list.add(new Coronavirus(tempData, "United Arab Emirates", 21));
        list.add(new Coronavirus(tempData, "Iraq", 26));
        list.add(new Coronavirus(tempData, "Lebon", 13));
        list.add(new Coronavirus(tempData, "Oman", 6));
        list.add(new Coronavirus(tempData, "Pakistan", 5));
        list.add(new Coronavirus(tempData, "Qatar", 6));
        list.add(new Coronavirus(tempData, "Egypt", 2));
        list.add(new Coronavirus(tempData, "Afghanistan", 1));
        list.add(new Coronavirus(tempData, "Jordan", 1));
        list.add(new Coronavirus(tempData, "Morocco", 1));
        list.add(new Coronavirus(tempData, "Saudi Arabia", 1));
        list.add(new Coronavirus(tempData, "Tunisia", 1));

//        Region of the Americas
        list.add(new Coronavirus(tempData, "USA", 64));
        list.add(new Coronavirus(tempData, "Canada", 24));
        list.add(new Coronavirus(tempData, "Mexico", 6));
        list.add(new Coronavirus(tempData, "Dominican Republic", 2));
        list.add(new Coronavirus(tempData, "Brazil", 1));

//        African Region
        list.add(new Coronavirus(tempData, "Algeria", 5));
        list.add(new Coronavirus(tempData, "Nigeria", 1));
        list.add(new Coronavirus(tempData, "Senegal", 1));

//        International conveyance
        list.add(new Coronavirus(tempData, "International conveyance", 706));

        return list;
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

//        Eastern Mediterranean Region
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