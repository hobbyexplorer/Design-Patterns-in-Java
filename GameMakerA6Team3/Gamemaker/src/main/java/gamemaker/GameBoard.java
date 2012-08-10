 package gamemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import log4j.GameMakerLogger;
import model.GameObject1;
import model.Sprite;
import controller.GameBoardController;

/**
 * Class Name: GameBoard Class Responsibilities: Contains the game board Class
 * collaborators: GameMaker.java
 */
public class GameBoard
{
	private ArrayList<Sprite> spriteList;

	// The savevalue.
	boolean savevalue = false;

	public static final GameMakerLogger logger = new GameMakerLogger("gamemaker_logger.properties");
	
	// The winvalue.
	boolean winvalue = false;

	// The losevalue.
	boolean losevalue = false;

	// The loadvalue.
	boolean loadvalue = false;

	// The image.
	private BufferedImage image;
	
	private Image oldimg;
	private Image newimg;
	
	private int defaultHeight;
	private int defaultWidth;
	public static double currentHeightRate;
	public static double currentWidthRate;
	
	private GameObject1 rect = new GameObject1(0, 10, 10, 50, 50, 1, 1);

	// The displaymsg.
	WinLoseMessage displaymsg = new WinLoseMessage();
	
	// The game board.
	private JPanel gameBoard = new JPanel()
	{
		private static final long serialVersionUID = 1L;
		
		public void setDefaultDimension(){
			setDefaultHeight();
			setDefaultWidth();
		}
		public void  setDefaultHeight()
		{
			defaultHeight=this.getHeight();
		}
		public void  setDefaultWidth()
		{
			defaultWidth=this.getWidth();
		}
		public int getDefaultHeight(){
			return defaultHeight;
		}
		public int getDefaultWidth(){
			return defaultWidth;
		}
		 
		 
		public double getCurrentWidthRate(){
			
			
			currentWidthRate=(double)this.getWidth()/(double)getDefaultWidth();
			
			return currentWidthRate;
			
		}
public double getCurrentHeightRate(){
			
			
			currentHeightRate=(double)this.getHeight()/(double)getDefaultHeight();
			
			return currentHeightRate;
			
		}
		
		
		int i=0;
		public void paint(Graphics graphics)
		{
			
			if(i++==0)
				setDefaultDimension();
			
			
			
			
			super.paint(graphics);
			//Graphics2D g2D = (Graphics2D)graphics;
			graphics.drawImage(image, 0, 0, this);
		 
			getCurrentHeightRate();
			getCurrentWidthRate();
			
			for(int index=0; index<getSpriteList().size(); index++)
			{
				getSpriteList().get(index).setHeightRate(getCurrentHeightRate());
				getSpriteList().get(index).setWidthRate(getCurrentWidthRate());
			 
				getSpriteList().get(index).draw(graphics);
				
			}
		
			if(GameBoardController.getInstance().isStopGameFlag())
			{
				Font f = new Font ("Serif", Font.PLAIN, 30);
				graphics.setFont(f);
				graphics.setColor(Color.RED);
				FontMetrics metrics = new FontMetrics(f) {  }; 
					
				
				Rectangle2D bounds = metrics.getStringBounds(GameBoardController.getInstance().getGameMessage(), null);  
				int length = (int) bounds.getWidth();  

				int xPosition = (Constants.GAMEBOARD_WIDTH-length)/2;
				graphics.drawString(GameBoardController.getInstance().getGameMessage(), xPosition, Constants.GAMEBOARD_HEIGHT/2);

			}

		/*	if (!winvalue && !losevalue)
			{
				for (int i = 0; i < GameMaker.spriteList.getObjectList().size(); i++)
				{
					SpriteObject sprite = GameMaker.spriteList.getObjectList().get(i);
					if (sprite.isVisible())
						graphics.drawImage(sprite.getImage(), sprite.getSpriteX(), sprite.getSpriteY(), sprite.getSpriteWidth(), sprite.getSpriteHeight(), null);

				}
			}

			else if (winvalue)
			{
				displaymsg.showWinMessage(graphics);
			}
			else if (losevalue)
			{
				displaymsg.showLoseMessage(graphics);
			}
*/		}

	};

	/* Method setImageToBackground: Sets the image to background. */
	
	
	
	
	public void setImageToBackground(String imageName)
	{
		try
		{
			image = ImageIO.read(getClass().getClassLoader().getResource("image/" + imageName.toLowerCase() + ".jpg"));
		}
		catch (IOException ex)
		{
		}
	}

	public void setSpriteList(ArrayList<Sprite> spriteList)
	{
		this.spriteList = spriteList;
	}

	public ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}

	/*
	 * Method displayMessage: Display message.
	 */
	public void displayMessage()
	{

	}

	/* Method isSavevalue: Checks if is savevalue. */
	public boolean isSavevalue()
	{
		return savevalue;
	}

	/* Method setSavevalue: Sets the savevalue. */
	public void setSavevalue(boolean savevalue)
	{
		this.savevalue = savevalue;
	}

	/* Method isWinvalue: Checks if is winvalue. */
	public boolean isWinvalue()
	{
		return winvalue;
	}

	/* Method setWinvalue: Sets the winvalue. */
	public void setWinvalue(boolean winvalue)
	{
		this.winvalue = winvalue;
	}

	/* Method isLosevalue: Checks if is losevalue. */
	public boolean isLosevalue()
	{
		return losevalue;
	}

	/* Method setLosevalue: Sets the losevalue. */
	public void setLosevalue(boolean losevalue)
	{
		this.losevalue = losevalue;
	}

	/* Method isLoadvalue: Checks if is loadvalue. */
	public boolean isLoadvalue()
	{
		return loadvalue;
	}

	/*
	 * Method setLoadvalue: Sets the loadvalue.
	 */
	public void setLoadvalue(boolean loadvalue)
	{
		this.loadvalue = loadvalue;
	}

	// Constructor GameBoard: Instantiates a new game board.

	public GameBoard()
	{

		// this.timerPanel.setLayout(null);

		getGameBoard().setBackground(Color.white);
		getGameBoard().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		//Dimension gameBoardDimension = new Dimension(Constants.GAMEBOARD_WIDTH/2, Constants.GAMEBOARD_HEIGHT/2);
	//	getGameBoard().setPreferredSize(gameBoardDimension);
		getGameBoard().setFocusable(true);
		
	//	getGameBoard().addKeyListener(GameBoardController.getInstance());
	//	getGameBoard().addMouseMotionListener(GameBoardController.getInstance());
	//	getGameBoard().addMouseListener(GameBoardController.getInstance());
		getGameBoard().requestFocus();
		//System.out.println("Width: " + getGameBoard().getWidth() + "Height: " + getGameBoard().getHeight());
		
	}

	/* Method getGameBoard : Gets the game board. */
	public JPanel getGameBoard()
	{
		return this.gameBoard;
	}

	/* Method setGameBoard: Sets the game board. */
	public void setGameBoard(JPanel gameBoard)
	{
		this.gameBoard = gameBoard;
	}

	public double getCurrentHeightRate()
	{
		 
		
		return currentHeightRate;
	}
	public double getCurrentWidthRate()
	{
		 
		
		return currentWidthRate;
	}
}