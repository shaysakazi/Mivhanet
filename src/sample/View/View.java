package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ViewModel.ViewModel;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    @FXML
    private ViewModel viewModel;
    public TextField tf_username;
    public TextField tf_password;

    @Override
    public void update(Observable o, Object arg) {

    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void login(ActionEvent actionEvent){
        try {
            String userName = tf_username.getCharacters().toString();
            String password = tf_password.getCharacters().toString();
            viewModel.login(userName,password);
            String role = viewModel.GetUserRole(userName);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root;
            if(role.equals("student")){
                root = fxmlLoader.load(getClass().getResource("Student.fxml").openStream());
                stage.setTitle("Student Dash");
                stage.setScene(new Scene(root, 500, 400));
                Student studentView = fxmlLoader.getController();
                studentView.setStudentName(userName);
                studentView.setViewModel(viewModel);
                viewModel.addObserver(studentView);
            }
            else if(role.equals("mazkira")){
                root = fxmlLoader.load(getClass().getResource("Mazkira.fxml").openStream());
                stage.setTitle("Mazkira Dash");
                stage.setScene(new Scene(root, 500, 400));
                Mazkira mazkiraView = fxmlLoader.getController();
                mazkiraView.setViewModel(viewModel);
                mazkiraView.setMazkiraName(tf_username.getCharacters().toString());
                viewModel.addObserver(mazkiraView);
            }
            else{
                throw new Exception("not a valid role");
            }
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            showAlert("login failed", "password or email is not correct");
        }
    }

    public void test(ActionEvent actionEvent){
        try {


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String alertTopic,String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(alertMessage);
        alert.setTitle(alertTopic);
        alert.setContentText("Error");
        alert.show();
    }

}
