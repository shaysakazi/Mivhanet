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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import sample.Model.DataBase.Courses;
import sample.ViewModel.ViewModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Exam implements Observer {
    private ViewModel viewModel;

    @FXML
    public ChoiceBox cb_course;
    public ChoiceBox cb_semester;
    public ChoiceBox cb_year;
    public ChoiceBox cb_moed;
    public DatePicker dp_date;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeExam();
    }

    private void initializeExam(){
        try {
            cb_moed.setItems(FXCollections.observableArrayList("A","B","C"));
            cb_semester.setItems(FXCollections.observableArrayList("A","B","C"));
            cb_year.setItems(FXCollections.observableArrayList("2017","2018"));

            ObservableList<Courses> courses = viewModel.getAllCourses();
            ArrayList<String> coursesName = new ArrayList();
            for (Courses c : courses){
                coursesName.add(c.getCourseName());
            }
            cb_course.setItems(FXCollections.observableArrayList(coursesName));
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

    public void createNewExam(ActionEvent actionEvent) {
        try{
            String course = (String)cb_course.getValue();
            String semester = (String)cb_semester.getValue();
            String year = (String)cb_year.getValue();
            String moed = (String)cb_moed.getValue();
            String date = dp_date.getValue().toString();

            if(viewModel.addExam(course,semester,year,moed,date)){
                cancel(actionEvent);
                showAlert("new Exam","course " + course + " semester " + semester + " year " + year
                        + " moed " + moed + " date " + date +" has been added");
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
