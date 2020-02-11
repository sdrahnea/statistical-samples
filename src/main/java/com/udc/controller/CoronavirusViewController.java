package com.udc.controller;

import com.udc.model.Coronavirus;
import com.udc.model.Dashboard;
import com.udc.model.DynamicChart;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

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

        List<Coronavirus> data = populateInfo();

        if(((index % 4) == 0) && (index / 4) < datetimes.size()){
            horizontalBarModel = updateTheChart(datetimes.get(index / 4), getData(datetimes.get(index / 4), data));
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