package sample.Model;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import sample.Model.DataBase.*;
import sample.Model.Email.SendEmail;

import java.util.Observable;

public class Model extends Observable implements IModel {
    public static Db db;
    public SendEmail sendEmail;

    public Model() throws Exception {
        db = Db.getSingleton();
    }

    @Override
    public void sendEmail(String name, String email) throws Exception {
        sendEmail = new SendEmail(name,email);
        sendEmail.send();
    }

    @Override
    public void login(String userName, String password) throws Exception{
        if(db.checkUser(userName,password) == false) throw new Exception();
        else{
            setChanged();
            notifyObservers("User Name:"+userName);
        }
    }

    @Override
    public void registerUser(String userName, String lastName, String firstName, String address,String phone, String mail,String role,String password)throws Exception {
            if(userName.length() == 0|| lastName.length() == 0  || firstName.length() == 0 || address.length() == 0|| phone.length() == 0 || mail.length() == 0 ||role.length() == 0 ){
                throw new InterruptedException();
            }
            if(!mail.contains("@")){
                throw new Exception();
            }
            if(phone.matches("[a-zA-Z_]+"))throw new NumberFormatException();
            int phoneNumber = Integer.parseInt(phone);

            boolean ans = db.addUser(userName,lastName,firstName,address,phoneNumber,mail,role,password);
            if(ans == false) throw  new ArrayStoreException();
    }

    @Override
    public User getUser(String email)throws Exception {
        return db.getUser(email);
    }

    @Override
    public String GetUserRole(String userName)throws Exception{
        return db.getRoleUser(userName);
    }

    @Override
    public ObservableList<Courses> getAllCourses() throws Exception {
        return db.getAllCourses();
    }

    @Override
    public ObservableList<Semester> getAllSemester() throws Exception {
        return db.getAllSemester();
    }

    @Override
    public boolean addExam(String Date, int cpsId, String moad) {
        return db.addExam(Date,cpsId,moad);
    }

    @Override
    public ObservableList<UserGrade> getUserExams(String UserName) throws Exception {
        return db.getUserExams(UserName);
    }

    @Override
    public ObservableList<UserCourse> getUserCourses(String UserName) throws Exception {
        return db.getUserCourses(UserName);
    }










    @Override
    public String getUserProduct(int id)throws Exception {
        return db.getUserProduct(id);
    }

    @Override
    public void addProduct(String email, String productName, String priceString, int donation, int swap, String category) throws Exception {
        if(email.length() == 0|| productName.length() == 0  || priceString.length() == 0 || category.length() == 0){
            throw new InterruptedException();
        }
        if(priceString.matches("[a-zA-Z_]+"))throw new NumberFormatException();
        Double price = Double.valueOf(priceString);
        db.addProduct(productName,price,donation,swap,0,0,category,email);
    }

    @Override
    public void addOrder(int productId, String renterEmail ,String tenantEmail) throws Exception {
        db.addOrder(productId,renterEmail,tenantEmail);
    }

    @Override
    public void addSwap(int productID1,String renterEmail1,int productID2,String renterEmail2) throws Exception{
        db.addSwap(productID1,renterEmail1,productID2,renterEmail2);
    }


    public ObservableList<ProductShow> productsPrice(Double price) throws Exception {
        return db.getAllProductsPrice(price);

    }

    @Override
    public boolean hasOrders(String email) throws Exception{
        return db.hasOrders(email);
    }

    @FXML
    public boolean hasProducts(String email) throws Exception{
        return db.hasProducts(email);
    }

    @Override
    public ObservableList<ProductShow> getUserOrders(String email) throws Exception{
        return db.getUserOrders(email);
    }

    @Override
    public ObservableList<ProductShow> allProductsInDataBase() throws Exception {
        return db.getAllProducts();
    }

    @Override
    public ObservableList<ProductShow> getUserProducts(String email) throws Exception{
        return db.getUserProducts(email);
    }

}
