package tetris;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tetris.view.GameController;
import tetris.view.HighScoreController;
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


/**
 * Main class used to launch the app and navigate the desired scenes based on the
 * users selections from the main menu.
 * @author awm31
 *
 */
public class StartApp extends Application
{
	private Stage primaryStage;
	private BorderPane mainPane;
	private MediaPlayer mediaPlayer;
	private Scene mainScene;
		
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Tetris");
		
		showMainPane();
		showStartPage();
	}
	
	/**
	 * Loads the pane for the application, starts the tetris theme music for the app,
	 * and displays the window on the users screen
	 */
	public void showMainPane()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(); //creates new loader for mainPane
			loader.setLocation(StartApp.class.getResource("view/mainPane.fxml"));//gets location of mainPane layout
			mainPane = (BorderPane) loader.load();//sets mainPane variable to the loaded layout
			
            Media media = new Media(StartApp.class.getResource("tetris tone loop.mp3").toString());//gets location of tetris music
            mediaPlayer = new MediaPlayer(media);//creates a new thread that plays the mp3
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);//set the media player to infinitely loop
            mediaPlayer.play();//need to tell the media player to start playing (similar to starting a thread)
    		
            //stops the media player when program closes
            //existing bug may cause media player to stop prematurely without this
            primaryStage.setOnCloseRequest(windowEvent -> {
                mediaPlayer.stop();
            });
		
			//creates new scene and sets the scene to the primary stage and displays the scene
			Scene scene = new Scene(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void showStartPage()
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
	
	public void showHighScoreDialog()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartApp.class.getResource("view/highScore.fxml"));
			AnchorPane highScoreDialog = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("High Scores");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(highScoreDialog);
			dialogStage.setScene(scene);
			
			HighScoreController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void showGameLayout()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartApp.class.getResource("view/gameLayout.fxml"));
			AnchorPane gameLayout = (AnchorPane) loader.load();
            BackgroundImage mainBackground = new BackgroundImage(new Image("http://aaaluminio.com.br/wp-content/uploads/2013/07/blue-polygon1.jpg",1500,900,false,true),
            		BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
                       
            gameLayout.setBackground(new Background(mainBackground));
            this.mainScene = new Scene (gameLayout);
			primaryStage.setScene(this.mainScene);
			primaryStage.show();
			GameController controller = loader.getController();
			controller.setMediaPlayer(mediaPlayer);
			controller.setSceneListener(this.mainScene, controller);

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
