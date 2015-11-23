package tetris;
	
import java.io.File;
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import tetris.view.StartPageController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class StartApp extends Application
{
	private Stage primaryStage;
	private BorderPane mainPane;
	
	public StartApp()
	{
		
	}
	
	
	
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
			loader.setLocation(StartApp.class.getResource("view/mainPane.fxml"));
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
			loader.setLocation(StartApp.class.getResource("view/startPage.fxml"));
			AnchorPane startPage = (AnchorPane) loader.load();
            BackgroundImage mainBackground = new BackgroundImage(new Image("http://aaaluminio.com.br/wp-content/uploads/2013/07/blue-polygon1.jpg",1500,900,false,true),
            		BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);

            
            startPage.setBackground(new Background(mainBackground));
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
