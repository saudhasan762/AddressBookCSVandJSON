package book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIO {
    public static String ADDRESS_BOOK_FILE_NAME = "AddressBook.txt";

    public void writeData(List<Contact> addressBook){
        StringBuffer stringBuffer = new StringBuffer();
        addressBook.forEach(contact -> {
            String contactDataString = contact.toString().concat("\n");
            stringBuffer.append(contactDataString);
        });

        try {
            Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), stringBuffer.toString().getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Contact> readData(){
        List<Contact> contactList = new ArrayList<>();
        try {
            Files.lines(new File(ADDRESS_BOOK_FILE_NAME).toPath())
                    .map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        } catch (IOException e){
            e.printStackTrace();
        }
        return contactList;
    }
}
