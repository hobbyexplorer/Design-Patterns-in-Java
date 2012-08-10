package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameObject1Test
{

	@Test
	public void testContract()
	{
		Sprite testerSprite = new Sprite();	
		testerSprite.setHeight(15);
		testerSprite.setWidth(100);	
		testerSprite.setImagePath("ball");
		//GameObject tester = new GameObject(0, 10, 10, 15, 100);
		GameObject tester;
		tester = testerSprite.addGameObject();
		tester.setX(10);
		tester.setY(10);		
	
		tester.contract(5);
		assertEquals("height", 15, tester.getObjectHeight());
		assertEquals("width", 100, tester.getObjectWidth());
		assertEquals("X", 10, tester.getX());
		assertEquals("Y", 10, tester.getY());
	}

	@Test
	public void testExpand()
	{
		Sprite testerSprite = new Sprite();	
		testerSprite.setHeight(15);
		testerSprite.setWidth(100);	
		testerSprite.setImagePath("ball");	
		//GameObject tester = new GameObject(0, 10, 10, 15, 100);
		GameObject tester;
		tester = testerSprite.addGameObject();
		tester.setX(10);
		tester.setY(10);		
	
		tester.expand(5);
		assertEquals("height", 16, tester.getObjectHeight());
		assertEquals("width", 105, tester.getObjectWidth());
		assertEquals("X", 8, tester.getX());
		assertEquals("Y", 10, tester.getY());
		//System.out.println("height test: " + tester.getObjectHeight());
		//System.out.println("width test: " + tester.getObjectWidth());
		//System.out.println("X test: " + tester.getX());
		//System.out.println("Y test: " + tester.getY());
		
	}

}
