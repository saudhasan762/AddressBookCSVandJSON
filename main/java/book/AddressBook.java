package book;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook extends AddressBookOperate{
    public static void main (String[] args) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        System.out.println("! Welcome to the Address Book System !");
        System.out.println("List of Address books: ");
        Map<String, ArrayList<Contact>> map = new HashMap<>();

        ArrayList<Contact> list1 = new ArrayList<>();
        ArrayList<Contact> list2 = new ArrayList<>();
        map.put("AddressBook1", list1);
        map.put("AddressBook2", list2);
        for(Map.Entry m : map.entrySet()) {
            System.out.println("! "+m.getKey()+" !");
        }

        s: while(true)
        {
            while(true) {
                System.out.println("Enter 1 to work on AddressBook1");
                System.out.println("Enter 2 to work on AddressBook2");
                System.out.println("Enter 3 to search for a Contact");
                System.out.println("Enter 0 to exit System");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1) {
                    System.out.println("Working on AddressBook "+choice);
                    details(list1);
                    break;
                } else if(choice == 2) {
                    System.out.println("Working on AddressBook "+choice);
                    details(list2);
                    break;
                } else if(choice == 3) {
                    joined.addAll(list1);
                    joined.addAll(list2);

                    if(joined.size()==0){
                        System.out.println("No contacts");
                    }
                    System.out.println("Enter city name");
                    String checkcity = new Scanner(System.in).nextLine();
                    System.out.println("Enter state name: ");
                    String checkstate = new Scanner(System.in).nextLine();
                    int num = 0;
                    for(Contact con : joined) {
                        if (con.getCity().equals(checkcity) || con.getState().equals(checkstate)) {
                            System.out.println(con+"");
                            num++;
                        }
                    }
                    System.out.println("Number of Contacts with same city and state: " + num);
                    break;
                } else if(choice == 0) {
                    System.out.println("System Exit");
                    break s;
                } else
                    System.out.println("Invalid Choice");
            }
        }
    }
}
