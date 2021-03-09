package book;


public class Contact {

    private String first;
    private String last;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;
    Contact(){}


    Contact(String first, String last, String address, String city, String state,String email, String zip, String phoneNumber ){
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return first;
    }
    public void setFirstName(String first) {
        this.first=first;
    }
    public String getLastName() {
        return last;
    }
    public void setLastName(String last) {
        this.last = last;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city=city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip=zip;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Contact{" +
                "Name:'" + this.first + '\'' +
                "Surname:'" + this.last + '\'' +
                "Address:'" + this.address + '\'' +
                "City:'" + this.city + '\'' +
                "State:'" + this.state + '\'' +
                "Zip:'" + this.zip + '\'' +
                "Phone Number:'" + this.phoneNumber + '\'' +
                "Email: " + this.email + '\'' +
                '}';
    }
}
