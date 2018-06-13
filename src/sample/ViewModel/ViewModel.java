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

    public String getPasswordUser(String username)throws Exception{
        return model.getPasswordUser(username);
    }

    public boolean addUserPassword(String userName,String password) throws Exception{
        return model.addUserPassword(userName,password);
    }

    public boolean addExam(String courseName, String semester, String year, String moad, String date)throws Exception{
        return model.addExam(courseName, semester, year, moad, date);
    }

    public ObservableList<Lecturer> getAllLecturers()throws Exception{
        return model.getAllLecturers();
    }

    public boolean addCPS(String courseName,String Semester,String year,String courseManager)throws Exception{
        return model.addCPS(courseName, Semester, year, courseManager);
    }
}
