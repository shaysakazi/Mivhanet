package sample.Model;

import javafx.collections.ObservableList;
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
            if(phone.matches("[a-zA-Z_]+") || phone.length()!=10)throw new NumberFormatException();
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
    public ObservableList<UserGrade> getUserExams(String UserName) throws Exception {
        return db.getUserExams(UserName);
    }

    @Override
    public ObservableList<UserCourse> getUserCourses(String UserName) throws Exception {
        return db.getUserCourses(UserName);
    }

    @Override
    public String getPasswordUser(String username)throws Exception{
        return db.getPasswordUser(username);
    }

    @Override
    public boolean addUserPassword(String userName,String password) throws Exception{
        if(password.length()<8) throw new InterruptedException();
        return db.addUserPassword(userName,password);
    }

    @Override
    public boolean addExam(String courseName, String semester, String year, String moad, String date)throws Exception{
        return db.addExam(courseName, semester, year, moad, date);
    }

    @Override
    public ObservableList<Lecturer> getAllLecturers()throws Exception{
        return db.getAllLecturers();
    }

    @Override
    public boolean addCPS(String courseName,String Semester,String year,String courseManager)throws Exception{
        return db.addCPS(courseName, Semester, year, courseManager);
    }

}
