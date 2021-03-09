package book;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class OpenCSV {
    public static final String ADDRESS_BOOK= "user.csv";

    public void write(ArrayList<Contact> finalist) throws IOException,
        CsvDataTypeMismatchException,
                CsvRequiredFieldEmptyException {

            try (Writer writer = Files.newBufferedWriter(Paths.get(ADDRESS_BOOK));
            ){
                StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();

                beanToCsv.write(finalist);
            }
        }

        public void read() throws IOException {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(ADDRESS_BOOK));
                    CSVReader csvReader = new CSVReader(reader)
            ) {
                List<String[]> records = csvReader.readAll();
                for (String[] record : records) {
                    System.out.println("Name: " + record[3]);
                    System.out.println("Surname: " + record[4]);
                    System.out.println("Address: " + record[0]);
                    System.out.println("City: " + record[1]);
                    System.out.println("State: " + record[6]);
                    System.out.println("Zip: " + record[7]);
                    System.out.println("Phone Number: " + record[5]);
                    System.out.println("Email: " + record[2]);
                    System.out.println("======================");
                }
            }
        }
}
