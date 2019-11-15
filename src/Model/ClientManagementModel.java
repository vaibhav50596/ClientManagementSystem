package Model;

public class ClientManagementModel {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String clientType;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getClientType() {
        return clientType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + clientType;
    }
}
