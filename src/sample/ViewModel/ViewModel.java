package sample.ViewModel;
import javafx.collections.ObservableList;
import sample.Model.DataBase.*;
import sample.Model.Model;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer{
    private Model model;

    public ViewModel(Model model) {
        this.model=model;
    }

    @Override
    public void update(Observable o, Object arg) {
        String s = (String) arg;
        if(s.substring(0,6).equals("Email:")){
            setChanged();
            notifyObservers(s.substring(6));
        }
    }

    public void login(String userName, String password) throws Exception {
        model.login(userName, password);
    }

    public String GetUserRole(String userName)throws Exception{
        return model.GetUserRole(userName);
    }

    public ObservableList<UserCourse> getUserCourses(String UserName) throws Exception{
        return model.getUserCourses(UserName);
    }

    public ObservableList<UserGrade> getUserExams(String UserName) throws Exception{
        return model.getUserExams(UserName);
    }

    public void sendMail(String name, String Email) throws Exception {
        model.sendEmail(name,Email);
    }

    public void registerUser(String userName, String lastName, String firstName, String address,String phone, String mail,String role,String password)throws Exception{
        model.registerUser(userName,lastName,firstName,address,phone,mail,role,password);
    }

    public ObservableList<Courses> getAllCourses() throws Exception{
        return model.getAllCourses();
    }

    public boolean addExam(String Date, int cpsId, String moad){
        return model.addExam(Date,cpsId,moad);
    }











    public void registerUser(String name, String email,String password,String age, String PayPalAccount )throws Exception {
        //model.registerUser(name,email,password,age,PayPalAccount);
    }

    public User getUser(String email)throws Exception{
        return model.getUser(email);
    }

    public String getUserProduct(int id)throws Exception {
        return model.getUserProduct(id);
    }

    public void addProduct(String email, String productName, String price, boolean donationBool, boolean swapBool, String category) throws Exception {
        int donation=0,swap=0;
        if(donationBool)donation=1;
        if(swapBool)swap=1;
        model.addProduct(email,productName,price,donation,swap,category);
    }

    public void addOrder(int productId, String renterEmail ,String tenantEmail) throws Exception {
        model.addOrder(productId,renterEmail,tenantEmail);
    }

    public void addSwap(int productID1,String renterEmail1,int productID2,String renterEmail2) throws Exception{
        model.addSwap(productID1,renterEmail1,productID2,renterEmail2);
    }



    public ObservableList<ProductShow> productsPrice(Double price) throws Exception {
        return model.productsPrice(price);

    }

    public boolean hasOrders(String email) throws Exception{
        return model.hasOrders(email);
    }

    public boolean hasProducts(String email) throws Exception{
        return model.hasProducts(email);
    }

    public ObservableList<ProductShow> getUserOrders(String email) throws Exception{
        return model.getUserOrders(email);
    }

    public ObservableList<ProductShow> allProductsInDataBase() throws Exception {
        return model.allProductsInDataBase();
    }

    public ObservableList<ProductShow> getUserProducts(String email) throws Exception{
        return model.getUserProducts(email);
    }
}
