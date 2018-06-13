package sample.Model.DataBase;

/**
 * Created by IL984626 on 30/12/2017.
 */
public class User {
    private String userName;
    private String lastName;
    private String firstName;
    private String address;
    private int phone;
    private String mail;
    private String role;
    private String password;


    public String getName() {
        return userName;
    }

    public String getEmail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    public User(String userName,String lastName, String firstName,String address, int phone, String mail, String role,String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName= firstName;
        this.address= address;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", mail=" + mail +
                ", role=" + role +
                '}';
    }
}
