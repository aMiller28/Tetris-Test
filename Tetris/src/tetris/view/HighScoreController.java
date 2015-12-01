package tetris.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import tetris.model.Player;

public class HighScoreController
{
	@FXML
	private TableColumn<Player,String> name;
	@FXML 
	private TableColumn<Player, Integer> score;
	
	private Stage dialogStage;
	
	@FXML
	private void initialize()
	{
		
	}
	
	public void setDialogStage(Stage dialogStage)
	{
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleExit()
	{
		dialogStage.close();
	}
	
}
