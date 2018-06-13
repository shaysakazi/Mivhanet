package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ViewModel.ViewModel;
import java.util.Observable;
import java.util.Observer;

public class StudentRegistration implements Observer {
    private ViewModel viewModel;
    private String studentName;

    @FXML
    public Label l_name;
    public TextField tf_password;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        l_name.setText("hello, " + studentName);
    }

    public void addPassword(ActionEvent actionEvent){
        try{
            String password = tf_password.getCharacters().toString();
            viewModel.addUserPassword(studentName,password);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Student.fxml").openStream());
            stage.setTitle("Student Dash");
            stage.setScene(new Scene(root, 500, 400));
            Student studentView = fxmlLoader.getController();
            studentView.setStudentName(studentName);
            studentView.setViewModel(viewModel);
            viewModel.addObserver(studentView);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            showAlert("New Password",studentName + " you have change your password");
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


