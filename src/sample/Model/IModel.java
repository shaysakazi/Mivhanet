package sample.Model;


import javafx.collections.ObservableList;
import sample.Model.DataBase.*;

public interface IModel {

    void sendEmail(String name, String email)throws Exception;

    void login(String email, String password) throws Exception;

    void registerUser(String userName, String lastName, String firstName, String address,String phone, String mail,String role,String password) throws Exception;

    User getUser(String email)throws Exception;

    String GetUserRole(String userName)throws Exception;

    ObservableList<Courses> getAllCourses() throws Exception;

    ObservableList<Semester> getAllSemester() throws Exception;

    boolean addExam(String Date,int cpsId,String moad);

    ObservableList<UserGrade> getUserExams(String UserName)throws Exception;

    ObservableList<UserCourse> getUserCourses(String UserName) throws Exception;







    void addProduct(String email, String productName, String price, int donation, int swap, String category) throws Exception;

    ObservableList<ProductShow> allProductsInDataBase()throws Exception;

    String getUserProduct(int id)throws Exception;

    void addOrder(int productId, String renterEmail, String tenantEmail) throws Exception;

    ObservableList<ProductShow> getUserProducts(String email) throws Exception;

    void addSwap(int productID1, String renterEmail1, int productID2, String renterEmail2) throws Exception;

    boolean hasOrders(String email) throws Exception;

    ObservableList<ProductShow> getUserOrders(String email) throws Exception;

    boolean hasProducts(String email) throws Exception;
}
