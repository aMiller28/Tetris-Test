package tetris.view;

import javafx.fxml.FXML;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	
	public void setSceneListener(Scene mainScene, GameController controller)
	{
		mainScene.setOnKeyPressed(k-> {
			if(k.getCode() == KeyCode.SPACE)
			{
				this.paused = !this.paused;
				
				if(this.paused)
				{
					pauseGame();							
				}
				else
				{
					unPauseGame();
					//Play Movement
				}
			}
        	else if(this.gameOver && k.getCode() == KeyCode.ENTER)
        	{
        		this.initialize();
        	}
			
			if (k.getCode() == KeyCode.LEFT && !paused) 
			{
                 controller.getGameBoard().moveLeftRight(HorizontalDirection.LEFT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.RIGHT && !paused)
            {
                 controller.getGameBoard().moveLeftRight(HorizontalDirection.RIGHT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.UP && !paused)
            {
                 controller.getGameBoard().rotate(HorizontalDirection.LEFT);
                 k.consume();
            }

            if (k.getCode() == KeyCode.DOWN)
            {
                 if (!descending)
                 {
                     if (!paused)
                     {
                         controller.getGameBoard().fastDescend();
                     }
                     descending = true;
                     k.consume();
                 }
             }
		});
		
		mainScene.setOnKeyReleased(k -> {
			if(k.getCode() == KeyCode.DOWN)
			{
				descending = false;
				controller.getGameBoard().moveDown();
			}
		});
	}
	
	private void unPauseGame()
	{
		mediaPlayer.play();
		gameBoard.play();
		this.well.getChildren().remove(this.status);
	}

	public boolean paused()
	{
	    return paused;
	}

	public void newGameStart() 
	{
		initialize();
	    gameBoard.start();
	}

	private void pauseGame()
	{
		this.status.setText("PAUSED");
		this.status.setLayoutX(this.well.getWidth()/2);
		this.status.setLayoutX(this.well.getHeight()/2 + 50);
		this.status.setVisible(true);
		this.well.getChildren().add(this.status);
	    mediaPlayer.pause();
	    gameBoard.pause();
	}

	public void stop() 
	{
	    mediaPlayer.stop();
	    gameBoard.clear();
	    setScore("0");
	    paused = false;
	}

	public void play() 
	{
	    paused = false;
	    gameBoard.play();
	}

	public GameBoard getGameBoard()
	{
		return gameBoard;
	}
	
	public void setScore(String score)
	{
		this.score.setText(score);
	}
	
	
	
	
}
