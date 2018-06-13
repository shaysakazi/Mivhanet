package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.ViewModel.ViewModel;
import java.util.Observable;
import java.util.Observer;

public class Mazkira implements Observer{
    private ViewModel viewModel;
    private String mazkiraName;

    @FXML
    public Label l_hello;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setMazkiraName(String mazkiraName) {
        this.mazkiraName = mazkiraName;
        l_hello.setText("hello, " + mazkiraName);
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

    public void newCourse(ActionEvent actionEvent){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("CoursePerSemester.fxml").openStream());
            stage.setTitle("new CoursePerSemester");
            stage.setScene(new Scene(root, 500, 400));
            CoursePerSemester coursePerSemester = fxmlLoader.getController();
            coursePerSemester.setViewModel(viewModel);
            viewModel.addObserver(coursePerSemester);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void newExam(ActionEvent actionEvent){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Exam.fxml").openStream());
            stage.setTitle("new Exam");
            stage.setScene(new Scene(root, 500, 400));
            Exam exam = fxmlLoader.getController();
            exam.setViewModel(viewModel);
            viewModel.addObserver(exam);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void register(ActionEvent actionEvent){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Register.fxml").openStream());
            stage.setTitle("Registration");
            stage.setScene(new Scene(root, 500, 500));
            Register register = fxmlLoader.getController();
            register.setViewModel(viewModel);
            viewModel.addObserver(register);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
