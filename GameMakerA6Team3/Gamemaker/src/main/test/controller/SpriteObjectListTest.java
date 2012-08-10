package controller;

import static org.junit.Assert.*;
import gamemaker.GameMaker;

import model.GameBoardModel;
import model.Sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpriteObjectListTest
{

	//GameMaker gameMaker = new GameMaker();
	GameBoardModel gameBoardModel = new GameBoardModel();
	Sprite sprite1 = new Sprite();
	Sprite sprite2 = new Sprite();
	int beforeAdded;
	int afterAdded;
	int afterRemoved;

	@Before
	public void setUp() throws Exception
	{

		this.sprite1.setHeight(50);
		this.sprite1.setWidth(40);
		this.sprite1.setName("Ball");
		//this.sprite1.setDeltaX(3);
		//this.sprite1.setDeltaY(4);
		
		gameBoardModel.addSprite(sprite1);

	}

	@Test
	public void test()
	{
		beforeAdded = gameBoardModel.getSpriteList().size();
		this.sprite2.setHeight(50);
		this.sprite2.setWidth(40);
		this.sprite2.setName("Ball");
		//this.sprite2.setDeltaX(3);
		//this.sprite2.setDeltaY(4);
		gameBoardModel.addSprite(sprite2);
		afterAdded = gameBoardModel.getSpriteList().size();
		gameBoardModel.removeSprite(0);
		afterRemoved = gameBoardModel.getSpriteList().size();
		//assertEquals(beforeAdded, afterAdded - 1);
		//assertEquals(beforeAdded, afterRemoved);
		//assertEquals(0, gameBoardModel.getSpriteList().indexOf(sprite2));
	}

	@After
	public void tearDown() throws Exception
	{
	}

}
