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
import javax.mail.MessagingException;
import java.util.Observable;
import java.util.Observer;

public class Register implements Observer {
    private ViewModel viewModel;

    @FXML
    public TextField tf_userName;
    public TextField tf_email;
    public TextField tf_userType;
    public TextField tf_firstName;
    public TextField tf_lastName;
    public TextField tf_address;
    public TextField tf_phone;

    @Override
    public void update(Observable o, Object arg) {

    }

    public void registerUser(ActionEvent actionEvent) throws Exception {
        try {
            String userName = tf_userName.getCharacters().toString();
            String email = tf_email.getCharacters().toString();
            String userType = tf_userType.getCharacters().toString();
            String firstName = tf_firstName.getCharacters().toString();
            String lastName = tf_lastName.getCharacters().toString();
            String address = tf_address.getCharacters().toString();
            String phone = tf_phone.getCharacters().toString();

            viewModel.sendMail(userName, email);
            viewModel.registerUser(userName, lastName, firstName, address, phone,email,userType,"");
            cancel(actionEvent);
            showAlert("New " + userType,"New " + userType + " name: " + userName + " has been added");
        }
        catch (NumberFormatException e) {
            showAlert("Register failed", "Please provide a valid phone number");
        }
        catch (ArrayStoreException e) {
            showAlert("Register failed", "The email is already in the system, Please try again...");
        }
        catch (InterruptedException e){
            showAlert("Register failed","You need to fill all fields, Please try again...");
        }
        catch (MessagingException e) {
            showAlert("Register failed","There is no connection, you need to connect to the internet");
        }
        catch (Exception e){
            showAlert("Register failed","You need to write correct Mail format, Please try again");
        }
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void cancel(ActionEvent actionEvent)throws Exception {
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

    private void showAlert(String alertTopic,String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(alertMessage);
        alert.setTitle(alertTopic);
        alert.show();
    }

}
