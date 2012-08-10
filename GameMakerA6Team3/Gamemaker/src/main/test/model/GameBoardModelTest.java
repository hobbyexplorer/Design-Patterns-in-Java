package model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.awt.Color;

import model.action.Move;
import model.event.TimerEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameBoardModelTest
{
	private GameBoardModel gameBoardModel;
	private Sprite ball;

	@Before
	public void setUp() throws Exception
	{
		gameBoardModel = new GameBoardModel();
		ball = new Sprite();
		ball.setName("BALL");
		ball.setColor(Color.BLUE);
		ball.setHeight(20);
		ball.setWidth(20);
		ball.setShape(Shape.OVAL);
		ball.setSpeed(5);
	
	
		
	}

	@Test
	public void testGameBoardModelSpriteAndObject()
	{
		assertEquals(0, gameBoardModel.getSpriteList().size());
		gameBoardModel.addSprite(ball);
		assertEquals(1, gameBoardModel.getSpriteList().size());
		assertEquals(0, gameBoardModel.getSpriteList().get(0).getGameObjects().size());
		ball.addGameObject();
		ball.addGameObject();
		assertEquals(2, gameBoardModel.getSpriteList().get(0).getGameObjects().size());

	}

	@Test
	public void testGameBoardModelEventAndAction()
	{
		assertEquals(0, gameBoardModel.getEventList().size());
		TimerEvent ballMoveEvent = new TimerEvent(ball);
		Move ballMoveAction = new Move(ball, 50);
		
		assertEquals(0, ballMoveEvent.getActionList().size());
		ballMoveEvent.addAction(ballMoveAction);
		assertEquals(1, ballMoveEvent.getActionList().size());
		
		
		gameBoardModel.addEvent(ballMoveEvent);
		
		assertEquals(1, gameBoardModel.getEventList().size());
	
	}

	@After
	public void tearDown() throws Exception
	{

	}

}
