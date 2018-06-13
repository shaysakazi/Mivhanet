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
import sample.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CoursePerSemester implements Observer {
    private ViewModel viewModel;

    @FXML
    public ChoiceBox cb_course;
    public ChoiceBox cb_semester;
    public ChoiceBox cb_cm;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeCoursePerSemester();
    }

    private void initializeCoursePerSemester() {
        try {
            cb_semester.setItems(FXCollections.observableArrayList("A","B","C"));

            ObservableList<Courses> courses = viewModel.getAllCourses();
            ArrayList<String> coursesName = new ArrayList();
            for (Courses c : courses){
                coursesName.add(c.getCourseName());
            }
            cb_course.setItems(FXCollections.observableArrayList(coursesName));

            /*
            ObservableList<Courses> lectures = viewModel.getAllLectures();
            ArrayList<String> lecturesName = new ArrayList();
            for (Lectures l : lectures){
                lecturesName.add(l.getLctureName());
            }
            //cb_cm.setItems(FXCollections.observableArrayList(viewModel.getAllLectures));
            */

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
            //viewModel.createNewCourse((String)cb_course.getValue(),(String)cb_semester.getValue(),(String)cb_cm.getValue());
            cancel(actionEvent);
            showAlert("new CPS","Course " + (String)cb_course.getValue() + " semester " +
                    (String)cb_semester.getValue() + " manager " + (String)cb_cm.getValue() + " has been added");
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
