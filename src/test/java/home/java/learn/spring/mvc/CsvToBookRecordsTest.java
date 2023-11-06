package home.java.learn.spring.mvc;

import home.java.learn.spring.mvc.csv.mapper.BookCsvRecord;
import home.java.learn.spring.mvc.service.BookCSVServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

public class CsvToBookRecordsTest {

    BookCSVServiceImpl bookCSVService = new BookCSVServiceImpl();

    @Test
    public void test() throws Exception {

        File file = ResourceUtils.getFile("classpath:csv/Book_Input.csv");
        List<BookCsvRecord> records = bookCSVService.convertBookCsvRecord(file);

        for (BookCsvRecord record : records) {
            System.out.println(record);
        }}
}
