package sample.View;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Model.DataBase.UserCourse;
import sample.Model.DataBase.UserGrade;
import sample.ViewModel.ViewModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Student implements Observer {
    private ViewModel viewModel;
    private String studentName;
    private ArrayList<String> courses = new ArrayList<>();
    private ArrayList<String> exams = new ArrayList<>();

    @FXML
    public Label l_name;
    public Label l_course;
    public Label l_exam;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeStudentController();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        l_name.setText("hello " + studentName + ",");
    }

    public void logOff(ActionEvent actionEvent){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
            stage.setTitle("Mivhanet");
            stage.setScene(new Scene(root, 500, 400));
            View view = fxmlLoader.getController();
            view.setViewModel(viewModel);
            viewModel.addObserver(view);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initializeStudentController() {
        try{
            StringBuilder sbC = new StringBuilder("Your courses: \n");
            StringBuilder sbE = new StringBuilder("Your exams: \n");

            ObservableList<UserCourse> ol_userCourse = viewModel.getUserCourses(studentName);
            ObservableList<UserGrade> ol_userExam = viewModel.getUserExams(studentName);

            if(ol_userCourse == null){
                ol_userCourse = new SimpleListProperty<>();
            }

            if(ol_userExam == null){
                ol_userExam = new SimpleListProperty<>();
            }

            for (UserCourse uc : ol_userCourse){
                courses.add(uc.getCourse());
            }

            for (UserGrade ug : ol_userExam){
                exams.add(ug.getCourseName() + " moed " + ug.getMoad() + " grade " + ug.getGrade());
            }

            for (String c : courses) {
                sbC.append(c + "\n");
            }
            sbC.append("Total of " + courses.size() + " courses");

            for (String e : exams) {
                sbE.append(e + "\n");
            }
            sbE.append("Total of " + exams.size() + " exams");

            l_course.setText(sbC.toString());
            l_exam.setText(sbE.toString());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
