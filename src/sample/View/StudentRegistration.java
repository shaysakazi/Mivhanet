package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public void addPassword(ActionEvent actionEvent) {
        String password = tf_password.getCharacters().toString();
        //viewModel.changeStudentPassword(studentName,password);
        showAlert("New Password",studentName + " you have change your password");
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


