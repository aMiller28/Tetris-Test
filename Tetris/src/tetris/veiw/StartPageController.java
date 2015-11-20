package tetris.veiw;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tetris.StartApp;

public class StartPageController
{
	@FXML
	private AnchorPane mainPage;
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
		this.stage = stage;
	}
	
	@FXML
	private void initialize()
	{

	}
	
	@FXML
	private void handleStartGame()
	{
		
	}
	
	@FXML
	private void handleHighScores()
	{
		
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
