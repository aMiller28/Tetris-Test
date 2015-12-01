package tetris.model;

import java.util.EventListener;

public interface GameBoardListener extends EventListener
{
	void dropped();
	void rowsEliminated();
	void gameOver();
	void illegalMove();
	void move();
	void rotate();
}
