package book;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.util.*;

public class AddressBookOperate {
    public static ArrayList<Contact> finalist = new ArrayList<>();
    public static Set<Contact> joined = new LinkedHashSet<>();
    Scanner scan = new Scanner(System.in);

    public enum IOService{CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    public void view() {
        if (finalist.size() == 0) {
            System.out.println("*No contact information!!*");
        } else {
            for (Contact contact : finalist) System.out.println(contact);
        }
    }

    public boolean add(String checkname) {
        if (finalist.size() == 0)
            return true;
        for (Contact con : finalist) {
            if (con.getFirstName().equals(checkname)) {
                System.out.println("Contact with same name already exists !");
                return false;
            }
        }
        return true;
    }

    public boolean edit(String first, String last, String address, String city, String state, String email, String zip, String phoneNumber) {
        if (finalist.size() == 0)
            return false;
        int count = 0;
        for (Contact con : finalist) {
            count++;
            if (con.getFirstName().equals(first) && con.getLastName().equals(last)) {
                con.setAddress(address);
                con.setCity(city);
                con.setState(state);
                con.setZip(zip);
                con.setPhoneNumber(phoneNumber);
                con.setEmail(email);
                finalist.set(count - 1, con);
                return true;
            }
        }
        System.out.println("There is no information about the contact in the system!!");
        return false;
    }

    public boolean delete(String first, String last) {
        if (finalist.size() == 0)
            return false;
        int count = 0;
        for (Contact con : finalist) {
            count++;
            if (con.getFirstName().equals(first) && con.getLastName().equals(last)) {
                finalist.remove(count - 1);
                System.out.println("Deleted successful");
                return true;
            }
        }
        System.out.println("This contact number does not exist!!");
        return true;
    }

    public boolean searchPerson(String city, String state) {
        if (finalist.size() == 0)
            return false;
        int num = 0;
        for (Contact con : finalist) {
            if (con.getCity().equals(city) || con.getState().equals(state)) {
                System.out.println(con);
                num++;
            }
            System.out.println("Number of Contacts with same city and name: " + num);
            return true;
        }
        System.out.println("Contact does not exist");
        return true;
    }

    public boolean sortUsingName() {
        if (finalist.size() == 0) {
            System.out.println("Nothing to Sort");
            return false;
        }
        finalist.sort(Comparator.comparing(Contact::getFirstName));
        return true;
    }

    public boolean sortUsingCity() {
        if (finalist.size() == 0) {
            System.out.println("Nothing to Sort");
            return false;
        }
        finalist.sort(Comparator.comparing(Contact::getCity));
        return true;
    }

    public boolean sortUsingState() {
        if (finalist.size() == 0) {
            System.out.println("Nothing to Sort");
            return false;
        }
        finalist.sort(Comparator.comparing(Contact::getState));
        return true;
    }

    public boolean sortUsingZip() {
        if (finalist.size() == 0) {
            System.out.println("Nothing to Sort");
            return false;
        }
        finalist.sort(Comparator.comparing(Contact::getZip));
        return true;
    }

    int chooseOption() {
        System.out.println("Enter 1 to add contact");
        System.out.println("Enter 2 to view contact");
        System.out.println("Enter 3 to edit contact");
        System.out.println("Enter 4 to delete contact");
        System.out.println("Enter 5 to search contact");
        System.out.println("Enter 6 to sort contact");
        System.out.println("Enter 7 to write data in a file");
        System.out.println("Enter 8 to read data in a file");
        System.out.println("Enter 9 to Write data in CSV file");
        System.out.println("Enter 10 to Read data in CSV file");
        System.out.println("Enter 11 to Write and Read Data from JSON");
        System.out.println("Enter 0 to switch Address Book");
        return scan.nextInt();
    }

    public void writeAddressBookInFile(IOService ioService){
        if(ioService.equals(IOService.FILE_IO)) {
            new AddressBookFileIO().writeData(finalist);
            System.out.println("Data Stored successfully in a file");
        }
    }
    public void writeAddressBookInCSVFile() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        new OpenCSV().write(finalist);
        System.out.println("Data Stored successfully in a file");
    }

    public void readAddressBookInCSVFile() throws IOException {
        new OpenCSV().read();
        System.out.println("Successfully Read");
    }

    public void readAddressBookInFile(IOService ioService){
        if(ioService.equals(IOService.FILE_IO)) {
            new AddressBookFileIO().readData();
            System.out.println("Read successfully from a file");
        }
    }

    public void csvToJsonConvert(){
        new OpenCSV().csvToJson();
        System.out.println("JSON File created Successfully");
    }

    public static void details(ArrayList<Contact> list) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        finalist = list;
        AddressBook Book = new AddressBook();
        s:
        while (true) {
            int choice = Book.chooseOption();
            s2:
            while (true) {
                if (choice == 1) {
                    int numOfPerson;
                    System.out.println("Enter number of Persons to add");
                    numOfPerson = new Scanner(System.in).nextInt();
                    for (int i = 1; i <= numOfPerson; i++) {
                        System.out.println("Enter the First name to check");
                        String checkname = new Scanner(System.in).nextLine();
                        if (!Book.add(checkname))
                            break s2;
                        System.out.println("Enter the First name for Person " + i);
                        String first = new Scanner(System.in).nextLine();
                        System.out.println("Enter last name for Person " + i);
                        String last = new Scanner(System.in).nextLine();
                        System.out.println("Enter Address for Person " + i);
                        String address = new Scanner(System.in).nextLine();
                        System.out.println("Enter city for person " + i);
                        String city = new Scanner(System.in).nextLine();
                        System.out.println("Enter state for person " + i);
                        String state = new Scanner(System.in).nextLine();
                        System.out.println("Enter zip for Person " + i);
                        String zip = new Scanner(System.in).nextLine();
                        System.out.println("Enter Phone number for Person " + i);
                        String phoneNumber = new Scanner(System.in).nextLine();
                        System.out.println("Enter email for Person " + i);
                        String email = new Scanner(System.in).nextLine();
                        Contact con = new Contact(first, last, address, city, state, zip, phoneNumber, email);
                        finalist.add(con);
                        System.out.println("Added Successfully");
                    }
                    break;
                } else if (choice == 2) {
                    Book.view();
                    break;
                } else if (choice == 3) {
                    System.out.println("Enter first name: ");
                    String first = new Scanner(System.in).nextLine();
                    System.out.println("Enter last name: ");
                    String last = new Scanner(System.in).nextLine();
                    System.out.println("Enter Address: ");
                    String address = new Scanner(System.in).nextLine();
                    System.out.println("Enter city: ");
                    String city = new Scanner(System.in).nextLine();
                    System.out.println("Enter state");
                    String state = new Scanner(System.in).nextLine();
                    System.out.println("Enter zip");
                    String zip = new Scanner(System.in).nextLine();
                    System.out.println("Enter Phone number :");
                    String phoneNumber = new Scanner(System.in).nextLine();
                    System.out.println("Enter email: ");
                    String email = new Scanner(System.in).nextLine();
                    if (!Book.edit(first, last, address, city, state, zip, phoneNumber, email))
                        break;
                    else
                        System.out.println("Edited Successfully");
                    break;
                } else if (choice == 4) {
                    System.out.println("Enter first name");
                    String first = new Scanner(System.in).nextLine();
                    System.out.println("Enter last name: ");
                    String last = new Scanner(System.in).nextLine();
                    if (!Book.delete(first, last)) {
                        break;
                    }
                    break;
                } else if (choice == 5) {
                    System.out.println("Enter city name");
                    String city = new Scanner(System.in).nextLine();
                    System.out.println("Enter state name: ");
                    String state = new Scanner(System.in).nextLine();
                    if (!Book.searchPerson(city, state)) {
                        break;
                    }
                    break;
                } else if (choice == 6) {
                    System.out.println("Enter Choice to sort With: ");
                    System.out.println("Enter 1 to sort with name");
                    System.out.println("Enter 2 to sort with city");
                    System.out.println("Enter 3 to sort with state");
                    System.out.println("Enter 4 to sort with zip");
                    int sortWith = new Scanner(System.in).nextInt();
                    if (sortWith == 1) {
                        if (Book.sortUsingName()) {
                            System.out.println("Sorted");
                            break;
                        }
                    } else if (sortWith == 2) {
                        if (Book.sortUsingCity()) {
                            System.out.println("Sorted");
                            break;
                        }
                    } else if (sortWith == 3) {
                        if (Book.sortUsingState()) {
                            System.out.println("Sorted");
                            break;
                        }
                    } else if (sortWith == 4) {
                        if (Book.sortUsingZip()) {
                            System.out.println("Sorted");
                            break;
                        }
                    }
                    break;
                } else if(choice == 7) {
                    Book.writeAddressBookInFile(IOService.FILE_IO);
                    break ;
                } else if(choice == 8) {
                    Book.readAddressBookInFile(IOService.FILE_IO);
                    break ;
                } else if(choice == 9){
                   Book.writeAddressBookInCSVFile();
                   break ;
                } else if(choice == 10){
                    Book.readAddressBookInCSVFile();
                    break ;
                } else if(choice == 11){
                    Book.csvToJsonConvert();
                    break ;
                } else if (choice == 0) {
                    System.out.println("Return to main");
                    break s;
                } else
                    System.out.println("Invalid choice");
            }
        }
    }
}
