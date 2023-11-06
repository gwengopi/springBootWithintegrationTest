package home.java.learn.spring.mvc.csv.mapper;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookCsvRecord {

    @CsvBindByName
    private String title;
    @CsvBindByName
    private String author;
    @CsvBindByName
    private int year;
}
