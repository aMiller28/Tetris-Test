package tetris.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tetris.StartApp;

public class StartPageController
{
	@FXML
	private BorderPane mainPage;
	private Stage stage;
	
	private StartApp startApp;
	
	public StartPageController()
	{
		
	}
	
	
	public void setStartApp(StartApp startApp)
	{
		this.startApp = startApp;
	}
	
	public void setDialogStage(Stage stage)
	{

	}
	
	@FXML
	private void initialize()
	{

	}
	
	@FXML
	private void handleStartGame()
	{
		startApp.showGameLayout();
	}
	
	@FXML
	private void handleHighScores()
	{
		startApp.showHighScoreDialog();
	}
	
	@FXML
	private void handleResumeGame()
	{
		
	}
	
	@FXML
	private void handleQuitGame()
	{
		
		Platform.exit();
	}


	
	
	
	
	
}
