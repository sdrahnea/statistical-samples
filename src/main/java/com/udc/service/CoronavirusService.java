package com.udc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udc.model.Coronavirus;
import com.udc.model.Covid19;
import com.udc.model.Covid19Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sdrahnea
 */
@Component
public class CoronavirusService extends AbstractService<Coronavirus> {

    private static final String COVID_19_SOURCE = "src/main/resources/data/covid-19.json";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Covid19> data = new LinkedList<>();

    /**
     * Returns a list which contains all information from JSON file
     * @return objects
     */
    public List<Covid19> loadFromJSON() {
        try {
            if(data.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                data = Arrays.asList(mapper.readValue(new File(COVID_19_SOURCE), Covid19[].class));
            }
            return data;
        } catch (Exception ex) {
            logger.warn("Something bad happens: " + ex);
            return null;
        }
    }

    /**
     * Return all dates from list
     * @return list of dates
     */
    public List<String> getAvailableDate(){
        return data.stream().map(date -> date.getDate()).collect(Collectors.toList());
    }

    /**
     * Returns all dates which meet condition
     * @param text month
     * @return list of dates
     */
    public List<String> findAvailableDate(final String text){
        if(data.isEmpty()) {
            data = loadFromJSON();
        }
        return data.stream().filter(covid19 -> covid19.getDate().toLowerCase().contains(text.toLowerCase()))
                .map(covid19 -> covid19.getDate())
                .collect(Collectors.toList());
    }

    /**
     * Return available countries by date
     * @param text month
     * @return list of countries and causes
     */
    public List<Covid19Country> findCountriesByDate(final String text) {
        if(data.isEmpty()) {
            data = loadFromJSON();
        }
        return data.stream().filter(covid19 -> covid19.getDate().toLowerCase().contains(text.toLowerCase()))
                .findFirst()
                .get()
                .getCountries();
    }

    /**
     * Find by data and sort by causes
     * @param text
     * @return list of countries and causes
     */
    public List<Covid19Country> findByDateAndSortByCauses(final String text) {
        return findCountriesByDate(text).stream().sorted(Comparator.comparing(Covid19Country::getCauses))
                .collect(Collectors.toList());
    }
}
