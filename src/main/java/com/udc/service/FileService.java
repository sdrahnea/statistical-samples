package com.udc.service;

import com.udc.model.CorruptionIndex;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    /**
     * Convert data to sql scripts
     * @param data              list of objects
     * @param destinationFile   destination file apth
     */
    public void convertCsvDataToSQLScrip(List<CorruptionIndex> data, String destinationFile){

    }

    /**
     * Read information from file
     * @param filePath source file
     * @return  list of object
     */
    public List<CorruptionIndex> readFromFile(String filePath){
        return null;
    }

}
