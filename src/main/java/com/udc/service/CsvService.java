package com.udc.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.udc.model.ResultDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Process information for CSV file. Load / provide required information.
 */
public class CsvService {

    private static final String CSV_FILE_SOURCE = "src/main/resources/data/csv/data.csv";

    public static List<Integer> loadFromFile() {
        List<Integer> numbers = new LinkedList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_SOURCE))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> convertAndAddToList(numbers, Arrays.copyOfRange(x, 1, 7)));
        } catch (Exception e) {
            System.out.println("" + e);
        }

        return numbers;
    }

    private static List<Integer> convertAndAddToList(List<Integer> result, String[] array) {
        for(String element: array) {
            result.add(Integer.parseInt(element.trim()));
        }
        return result;
    }

    private static List<ResultDTO> createFrequency(List<Integer> numbers) {
        List<ResultDTO> resultList =  new LinkedList<>();

        for(Integer number: numbers) {
            if(exist(number, resultList)) {
                for(ResultDTO element: resultList) {
                    if(element.getNumber() == number){
                        element.increaseCount();
                    }
                }
            } else {
                resultList.add(new ResultDTO(number));
            }
        }

        return resultList;
    }

    private static boolean exist(Integer number, List<ResultDTO> list){
        for(ResultDTO element: list) {
            if(number == element.getNumber()){
                return true;
            }
        }
        return false;
    }

    public static void main(String... arg) {
        List<Integer> numbers = loadFromFile();

        List<ResultDTO> resultList =  createFrequency(numbers);

        Collections.sort(resultList);
        Collections.reverse(resultList);

        for(ResultDTO element: resultList) {
            System.out.println(element.getNumber() + " -> " + element.getCount());
        }
    }

}
