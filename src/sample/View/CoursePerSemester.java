package sample.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import sample.Model.DataBase.Courses;
import sample.Model.DataBase.Lecturer;
import sample.ViewModel.ViewModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CoursePerSemester implements Observer {
    private ViewModel viewModel;

    @FXML
    public ChoiceBox cb_course;
    public ChoiceBox cb_semester;
    public ChoiceBox cb_year;
    public ChoiceBox cb_cm;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeCoursePerSemester();
    }

    private void initializeCoursePerSemester() {
        try {
            cb_semester.setItems(FXCollections.observableArrayList("A","B","C"));
            cb_year.setItems(FXCollections.observableArrayList("2017","2018"));

            ObservableList<Courses> courses = viewModel.getAllCourses();
            ArrayList<String> coursesName = new ArrayList();
            for (Courses c : courses){
                coursesName.add(c.getCourseName());
            }
            cb_course.setItems(FXCollections.observableArrayList(coursesName));

            ObservableList<Lecturer> lectures = viewModel.getAllLecturers();
            ArrayList<String> lecturesName = new ArrayList();
            for (Lecturer l : lectures){
                lecturesName.add(l.getLecturerName());
            }
            cb_cm.setItems(FXCollections.observableArrayList(lecturesName));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel(ActionEvent actionEvent){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Mazkira.fxml").openStream());
            stage.setTitle("Mazkira Dash");
            stage.setScene(new Scene(root, 500, 400));
            Mazkira mazkira = fxmlLoader.getController();
            mazkira.setViewModel(viewModel);
            viewModel.addObserver(mazkira);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void createNewCourse(ActionEvent actionEvent) {
        try{
            String course = (String)cb_course.getValue();
            String semester = (String)cb_semester.getValue();
            String year = (String)cb_year.getValue();
            String cm = (String)cb_cm.getValue();

            if(viewModel.addCPS(course,semester,year,cm)){
                cancel(actionEvent);
                showAlert("new CPS","Course " + course +  " Year " + year + " semester " +
                        semester + " manager " + cm + " has been added");
            }
            else{
                showAlert("Error","no such data");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showAlert(String alertTopic,String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(alertMessage);
        alert.setTitle(alertTopic);
        alert.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
