package book;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class OpenCSV {
    public static final String ADDRESS_BOOK_CSV = "users.csv";
    public static final String ADDRESS_BOOK_JSON = "users.json";

    public void write(ArrayList<Contact> finalist) throws IOException,
        CsvDataTypeMismatchException,
                CsvRequiredFieldEmptyException {

            try (Writer writer = Files.newBufferedWriter(Paths.get(ADDRESS_BOOK_CSV))
            ){
                StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();

              //  List<Contact> contactList = new ArrayList<>();
               // contactList.add(new Contact("Saud","Hasan","Turner","Dehra","Uttra","248002","812563","saud@saud"));
                beanToCsv.write(finalist);
            }
        }

        public void read() throws IOException {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(ADDRESS_BOOK_CSV));
                    CSVReader csvReader = new CSVReader(reader)
            ) {
                List<String[]> records = csvReader.readAll();
                for (String[] record : records) {
                    System.out.println("First: " + record[3]);
                    System.out.println("Last: " + record[4]);
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

        /*public void parseToBean() throws IOException {
            try (Reader reader = Files.newBufferedReader(Paths.get(ADDRESS_BOOK_CSV))
            ){
                CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Contact.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<Contact> contactIterator = csvToBean.iterator();
                while (contactIterator.hasNext()){
                    Contact contact = contactIterator.next();
                    System.out.println("Name: "+contact.getFirstName());
                    System.out.println("Surname: "+contact.getLastName());
                    System.out.println("Address: "+contact.getAddress());
                    System.out.println("City: "+contact.getCity());
                    System.out.println("State: "+contact.getState());
                    System.out.println("Email: "+contact.getEmail());
                    System.out.println("PhoneNo: "+contact.getPhoneNumber());
                    System.out.println("Country: "+contact.getZip());
                    System.out.println("=============================");

                }
            }
        }*/

        public void csvToJson(){
            try {
                Reader reader = Files.newBufferedReader(Paths.get(ADDRESS_BOOK_CSV));
                CsvToBeanBuilder<Contact> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(Contact.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<Contact> csvToBean = csvToBeanBuilder.build();
                List<Contact> csvUsers = csvToBean.parse();
                Gson gson = new Gson();
                String json = gson.toJson(csvUsers);
                FileWriter writer = new FileWriter(ADDRESS_BOOK_JSON);
                writer.write(json);
                writer.close();
                BufferedReader br = new BufferedReader(new FileReader(ADDRESS_BOOK_JSON));
                Contact[] usrObj = gson.fromJson(br,Contact[].class);
                List<Contact> contactList = Arrays.asList(usrObj);
                Iterator<Contact> contactIterator = contactList.iterator();
                while (contactIterator.hasNext()){
                    Contact contact = contactIterator.next();
                    System.out.println("Name: "+contact.getFirstName());
                    System.out.println("Surname: "+contact.getLastName());
                    System.out.println("Address: "+contact.getAddress());
                    System.out.println("City: "+contact.getCity());
                    System.out.println("State: "+contact.getState());
                    System.out.println("Email: "+contact.getEmail());
                    System.out.println("PhoneNo: "+contact.getPhoneNumber());
                    System.out.println("Country: "+contact.getZip());
                    System.out.println("=============================");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
}
