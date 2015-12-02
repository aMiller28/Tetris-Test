package tetris.model;

import java.util.Random;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Class for creating a Tetrimino
 * Group class is used to take each block of the shape
 * and apply the children blocks to the shape node to
 * form a shape observable list
 * @author awm31
 *
 */
public class Tetrimino extends Group implements Cloneable
{
	//Definition of the I shape matrix and shape color
   	private TetriminoShape I = new TetriminoShape(new int [][]{
		{0,0,0,0},
		{1,1,1,1},
		{0,0,0,0},
		{0,0,0,0}
		
	}, Color.CYAN);
	
	//Definition of the J shape matrix and shape color
	private TetriminoShape J = new TetriminoShape(new int [][]{
		{0,0,0},
		{1,1,1},
		{0,0,1}
		
	}, Color.BLUE);

	//Definition of the L shape matrix and shape color
	private TetriminoShape L = new TetriminoShape(new int [][]{
		{0,0,0},
		{1,1,1},
		{1,0,0}
		
	}, Color.ORANGE);
	
	//Definition of the O shape matrix and shape color
	private TetriminoShape O = new TetriminoShape(new int [][]{
		{1,1},
		{1,1}
		
	}, Color.YELLOW);
	
	//Definition of the S shape matrix and shape color
	private TetriminoShape S = new TetriminoShape(new int [][]{
		{0,1,1},
		{1,1,0},
		{0,0,0}
		
	}, Color.LIME);
	
	//Definition of the T shape matrix and shape color
	private TetriminoShape T = new TetriminoShape(new int [][]{
		{0,0,0},
		{1,1,1},
		{0,1,0}
		
	}, Color.PURPLE);
	
	//Definition of the Z shape matrix and shape color
	private TetriminoShape Z = new TetriminoShape(new int [][]{
		{1,1,0},
		{0,1,1},
		{0,0,0}
		
	}, Color.RED);
	
	//Random shape generator
	private Random randomShape = new Random();
	//Array of each shape to be used with the random shape generator
	private TetriminoShape[] tetriminoShapes = new TetriminoShape[]{I, J, L, O, S, T, Z};
	//The matrix for the defined shape matrix
	private int [][] shapeMatrix;
	//Paints the tetrimino the color defined by the shape definition
	private Paint paintTetrimino;
	//The holder for the created tetrimino shape
	private TetriminoShape tetriminoShape;
	//The holder for the size of the individual blocks that the shape is composed of
	private ReadOnlyDoubleProperty blockSize;
	
	/**
	 * The constuctor for a tetrimino
	 * @param tetriminoShape the shape selected for the tetrimino
	 * @param blockSize the size of the individual blocks to build the shape
	 */
	public Tetrimino(TetriminoShape tetriminoShape, ReadOnlyDoubleProperty blockSize)
	{
		this.shapeMatrix = tetriminoShape.shapeMatrix;
		this.tetriminoShape = tetriminoShape;
		this.blockSize = blockSize;
		this.paintTetrimino = tetriminoShape.color;
		
		//Creates the tetrimino shape by creating the collection of child nodes that form the group
		//of the shape to be created
		 for (int i = 0; i < shapeMatrix.length; i++)
		 {
	            for (int j = 0; j < shapeMatrix[i].length; j++)
	            {
	            	//Creates the block to occupy the position in the group for the block
	                Rectangle rectangle = new Rectangle();
	                    
	                rectangle.setWidth(blockSize.doubleValue());
	                rectangle.setHeight(blockSize.get());
	                rectangle.setTranslateY(blockSize.get() * i);
	                rectangle.setTranslateX(blockSize.get() * j);
	                
	                //If a block to be displayed is built fill the block
	                //with the defined color of the tetrimino being built
	                if (shapeMatrix[i][j] == 1) 
	                {
	                    rectangle.setFill(tetriminoShape.color);
	                    rectangle.setFill(paintTetrimino);
	                    rectangle.setArcHeight(7);
	                    rectangle.setArcWidth(7);
	                } 
	                else 
	                {
	                    rectangle.setOpacity(0);
	                }
	                getChildren().add(rectangle);
	            }
		 }
		
	}
	
    /**
     * Class for the shape of the tetrimino
     * @author awm31
    */
    private class TetriminoShape
    {
        private Color color;
        private int[][] shapeMatrix;

        /**
         * Constructor for the tetrimino shape takes in the shape matrix
         * and color for the defined tetrimino to be used for implementation
         * of the blocks to build the tetrimino
         * @param shapeMatrix matrix of selected tetrimino shape
         * @param color the defined color for that defined shape
         */
        private TetriminoShape(int[][] shapeMatrix, Color color)
        {
            this.color = color;
            this.shapeMatrix = shapeMatrix;
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     * Used to make copies of the tetrimino without
     * assigning a different vairable each time a copy
     * is made
     */
    @Override
    public Tetrimino clone()
    {
		return new Tetrimino(tetriminoShape, blockSize);
    	
    }
    
    /**
     * Random shape generator for tetriminos, selectes a random shape from
     * the tetrimino shape array.
     * @param blockSize the size of the blocks that build the shape
     * @return random tetrimino select from the random selection
     */
    public Tetrimino getRandomTetrimino(ReadOnlyDoubleProperty blockSize)
    {
    	TetriminoShape tetriminoShape = tetriminoShapes[randomShape.nextInt(7)];
    	return new Tetrimino(tetriminoShape, blockSize) ;
    }
    
    /**
     * Gets the color to paint the blocks of the shape
     * @return paint color
     */
    public Paint getPaintTetrimino()
    {
    	return paintTetrimino;
    }
    
    /**
     * Gets the matrix for the shape of the tetrimino
     * @return matrix of the tetrimino shape
     */
    public int[][] getShapeMatrix()
    {
    	return shapeMatrix;
    }
	
	/**
	 * Sets the value for the shape of the tetrimino
	 * @param shapeMatrix the matrix value to be set
	 */
	public void setShapeMatrix(int[][] shapeMatrix)
	{
		this.shapeMatrix = shapeMatrix;
	}
}
