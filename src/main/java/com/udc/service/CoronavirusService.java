package com.udc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udc.model.Coronavirus;
import com.udc.model.Covid19;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sdrahnea
 */
@Component
public class CoronavirusService extends AbstractService<Coronavirus> {

    private static final String COVID_19_SOURCE = "src/main/resources/data/covid-19.json";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Returns a list which contains all information from JSON file
     * @return objects
     */
    public List<Covid19> loadFromJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Covid19> list =
                    Arrays.asList(mapper.readValue(new File(COVID_19_SOURCE), Covid19[].class));
            return list;
        } catch (Exception ex) {
            logger.warn("Something bad happens: " + ex);
            return null;
        }
    }

}
