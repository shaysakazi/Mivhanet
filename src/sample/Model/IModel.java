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

    ObservableList<UserGrade> getUserExams(String UserName)throws Exception;

    ObservableList<UserCourse> getUserCourses(String UserName) throws Exception;

    String getPasswordUser(String username)throws Exception;

    boolean addUserPassword(String userName,String password)throws Exception;

    boolean addExam(String courseName, String semester, String year, String moad, String date)throws Exception;

    ObservableList<Lecturer> getAllLecturers()throws Exception;

    boolean addCPS(String courseName, String Semester, String year, String courseManager)throws Exception;
}
