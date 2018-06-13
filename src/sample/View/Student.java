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

public class Student implements Observer {
    private ViewModel viewModel;
    private String studentName;
    private String[] courses;
    private String[] exams;

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
        l_name.setText("hello, " + studentName);
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
        StringBuilder sbC = new StringBuilder("Your courses: \n");
        StringBuilder sbE = new StringBuilder("Your exams: \n");

        //courses = viewModel.getStudentCourses(studentName);
        //exams = viewModel.getStudentExames(studentName);

        courses = new String[2];
        courses[0] = "nitoz";
        courses[1] = "mmm";

        exams = new String[3];
        exams[0] = "nitoz moed a";
        exams[1] = "mmm moed a";
        exams[2] = "mmm moed b";


        for (String c : courses) {
            sbC.append(c + "\n");
        }
        sbC.append("total of " + courses.length + " courses");

        for (String e : exams) {
            sbE.append(e + "\n");
        }
        sbE.append("total of " + exams.length + " exams");

        l_course.setText(sbC.toString());
        l_exam.setText(sbE.toString());
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
