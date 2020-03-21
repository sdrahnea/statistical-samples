package com.udc.controller;

import com.udc.model.Coronavirus;
import com.udc.model.Covid19Country;
import com.udc.service.CoronavirusService;
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

    @Autowired
    private CoronavirusService coronavirusService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HorizontalBarChartModel horizontalBarModel = new HorizontalBarChartModel();

    public static int index = 0;

    @PostConstruct
    public void init(){
    }

    public void initialise() {
        List<String> datetimes = coronavirusService.findAvailableDate("March");

        logger.info("Found %s items", datetimes.size());

        if(((index % 4) == 0) && (index / 4) < datetimes.size()){
            String dateTime = datetimes.get(index / 4);
            horizontalBarModel = updateTheChart(dateTime, coronavirusService.findByDateAndSortByCauses(dateTime));
        }
        index++;
    }

    public HorizontalBarChartModel updateTheChart(String datetime, List<Covid19Country> data) {
        horizontalBarModel.clear();

        ChartSeries countries = new ChartSeries();
        countries.setLabel("Confirmed cases");
        for(Covid19Country element: data) {
            countries.set(element.getName(), element.getCauses());
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

    private Long getMax(List<Covid19Country> list) {
        long max = 0;
        for(Covid19Country element: list) {
            if (max < element.getCauses()) {
                max = element.getCauses();
            }
        }
        return max;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        initialise();
        return horizontalBarModel;
    }

    public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
        this.horizontalBarModel = horizontalBarModel;
    }
}