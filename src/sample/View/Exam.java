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
    public ChoiceBox cb_cps;
    public ChoiceBox cb_moed;
    public DatePicker dp_date;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        initializeExam();
    }

    private void initializeExam(){
        try {
            ObservableList<Courses> courses = viewModel.getAllCourses();
            ArrayList<String> coursesName = new ArrayList();
            for (Courses c : courses){
                coursesName.add(c.getCourseName());
            }

            cb_cps.setItems(FXCollections.observableArrayList(coursesName));
            cb_moed.setItems(FXCollections.observableArrayList("A","B","C"));
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
            String cps = (String)cb_cps.getValue();
            String moed = (String)cb_moed.getValue();
            String date = dp_date.getValue().toString();

            //viewModel.createExam(,(String)cb_semester.getValue(),(String)cb_cm.getValue());
            cancel(actionEvent);
            showAlert("new Exam","course " + cps + " moed " + moed +
                    " date " + date +" has been added");
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
