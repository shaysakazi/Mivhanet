package sample.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.Model;
import sample.ViewModel.ViewModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model();
        ViewModel viewModel = new ViewModel(model);
        model.addObserver(viewModel);
        primaryStage.setTitle("Mivhanet");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
        Scene scene=new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        View view = (View)fxmlLoader.getController();
        view.setViewModel(viewModel);
        viewModel.addObserver(view);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
