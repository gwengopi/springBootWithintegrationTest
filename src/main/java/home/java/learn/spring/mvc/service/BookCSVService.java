package home.java.learn.spring.mvc.service;


import home.java.learn.spring.mvc.csv.mapper.BookCsvRecord;

import java.io.File;
import java.util.List;

public interface BookCSVService {
        public List<BookCsvRecord> convertBookCsvRecord(File csvFile);
}
