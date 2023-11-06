package home.java.learn.spring.mvc.service;


import com.opencsv.bean.CsvToBeanBuilder;
import home.java.learn.spring.mvc.csv.mapper.BookCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BookCSVServiceImpl implements BookCSVService {
    @Override
    public List<BookCsvRecord> convertBookCsvRecord(File csvFile) {
        List<BookCsvRecord> records;
        try {
            records = new CsvToBeanBuilder<BookCsvRecord>(new FileReader(csvFile))
                    .withType(BookCsvRecord.class)
                    .build()
                    .parse();
            return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
