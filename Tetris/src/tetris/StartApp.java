package tetris;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import tetris.veiw.StartPageController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class StartApp extends Application
{
	private Stage primaryStage;
	private BorderPane mainPane;
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Tetris");
		
		showMainPane();
		showStartPage();
	}
	
	private void showMainPane()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartApp.class.getResource("veiw/mainPane.fxml"));
			mainPane = (BorderPane) loader.load();
			
			Scene scene = new Scene(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private void showStartPage()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartApp.class.getResource("veiw/startPage.fxml"));
			AnchorPane startPage = (AnchorPane) loader.load();
			
			mainPane.setCenter(startPage);
			
			StartPageController controller = loader.getController();
			controller.setStartApp(this);
		}
		catch(IOException ex)
		{
		   ex.printStackTrace();	
		}

	}
	
	
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
