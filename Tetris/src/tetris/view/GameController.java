package tetris.view;

import javafx.fxml.FXML;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import tetris.StartApp;
import tetris.model.GameBoard;

public class GameController
{

	@FXML
	private BorderPane gamePage;
	@FXML
	private Label currentLevel;
	@FXML
	private Label score;
	@FXML
	private Label lines;
	@FXML
	private Pane nextTetriminoPreview;
	@FXML
	private StackPane well;
	@FXML
	private Label status;
	private StartApp startApp;
	private MediaPlayer mediaPlayer;
	private GameBoard gameBoard;
	boolean gameOver;
	boolean paused;
	boolean descending;
	

	
	
	public void setMediaPlayer(MediaPlayer mediaPlayer)
	{
		this.mediaPlayer = mediaPlayer;
	}
	
	@FXML
	public void initialize()
	{
		this.descending = false;
		this.gameBoard = new GameBoard();
		this.currentLevel.setText("1"); 
		this.score.setText("0");
		this.lines.setText("0");
		this.status.setText("");
		this.gameOver = false;
		this.paused = false;
		
	}
	
    @FXML
    public void handlePause()
    {
    	
    }

	public void setSceneListener(Scene mainScene, GameController controller)
	{
		mainScene.setOnKeyPressed(k-> {
			if(k.getCode() == KeyCode.SPACE)
			{
				this.paused = !this.paused;
				
				if(this.paused)
				{
					//Pause Movement
					this.status.setText("PAUSED");
					this.status.setLayoutX(this.well.getWidth()/2);
					this.status.setLayoutX(this.well.getHeight()/2 + 50);
					this.status.setVisible(true);
					this.well.getChildren().add(this.status);
							
				}
				else
				{
					//Play Movement
					this.well.getChildren().remove(this.status);
				}
			}
        	else if(this.gameOver && k.getCode() == KeyCode.ENTER)
        	{
        		this.initialize();
        	}
			
			if (k.getCode() == KeyCode.LEFT && !paused) 
			{
                 controller.getGameBoard().move(HorizontalDirection.LEFT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.RIGHT && !paused) {
                 controller.getGameBoard().move(HorizontalDirection.RIGHT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.UP && !paused) {
                 controller.getGameBoard().rotate(HorizontalDirection.LEFT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.DOWN) {
                 if (!descending) {
                     if (!paused)
                     {
                         controller.getGameBoard().descendFast();
                     }
                     descending = true;
                     k.consume();
                 }
             }
		});
	}
	
	public GameBoard getGameBoard()
	{
		return gameBoard;
	}
	
	
	
	
}
