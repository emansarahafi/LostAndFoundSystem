// Members: Ali Abdulla, Eman Sarah Afi, Sara Abdelhakeem

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
@Override
public void start(Stage stage) throws Exception {
	Parent root = FXMLLoader.load(getClass().getResource("Lost&Found.fxml"));
	stage.setTitle("AUBH Lost and Found");
	Scene scene = new Scene(root);
	stage.setMinWidth(930);
	stage.setMinHeight(710);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	stage.setScene(scene);
	stage.show();
}

public static void main(String[] args) {
	launch(args);
	}
}