package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import model.action.Bounce;
import model.action.Destroy;
import model.action.Move;
import model.action.MoveDown;
import model.action.MoveLeft;
import model.action.MoveRandom;
import model.action.MoveRight;
import model.action.MoveUp;
import model.action.PlaySound;
import model.action.Revolve;
import model.action.Rotate;
import model.action.Shoot;
import model.action.Split;
import model.event.CollisionEvent;
import model.event.GenerateRandom;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;


public class TestModelAsteroids {

    private GameBoardModel gameBoardModel; 	
	
	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}


	public TestModelAsteroids()
	{
		gameBoardModel = new GameBoardModel();
		// Define the sprites
		
		//Aircraft Sprite
		Sprite aircraft = new Sprite();
		aircraft.setName("aircraft");
		aircraft.setHeight(30);
		aircraft.setWidth(20);
		aircraft.setShape(Shape.TRANSPERENTIMAGE);
		aircraft.setSpeed(10);
		aircraft.setImagePath("aircraft");
		
		//Large Asteroid Sprite
		Sprite asteroids = new Sprite();
		asteroids.setName("asteroids");
		asteroids.setHeight(50);
		asteroids.setWidth(40);
		asteroids.setShape(Shape.TRANSPERENTIMAGE);
		asteroids.setSpeed(1);
		asteroids.setImagePath("aaasteroids");
		
		//Small Asteroid Sprite
		Sprite asteroidsSmall = new Sprite();
		asteroidsSmall.setName("asteroidsSmall");
		asteroidsSmall.setHeight(25);
		asteroidsSmall.setWidth(20);
		asteroidsSmall.setShape(Shape.TRANSPERENTIMAGE);
		asteroidsSmall.setSpeed(2);
		asteroidsSmall.setImagePath("aaasteroids");
		
		// BULLET
		Sprite bullet = new Sprite();
		bullet.setName("bullet");
		bullet.setHeight(6);
		bullet.setWidth(3);
		bullet.setShape(Shape.RECTANGLE);
		bullet.setSpeed(15);
		
	
		
		// add the sprites to the game sprites list
		gameBoardModel.addSprite(aircraft);
		gameBoardModel.addSprite(asteroids);
		gameBoardModel.addSprite(asteroidsSmall);
		gameBoardModel.addSprite(bullet);
		
		// Create the game objects list and
		// keep on adding the objects to this list
		
		
		aircraft.addGameObject();
		asteroids.addGameObject();
		asteroids.addGameObject();
		
		asteroids.addGameObject();
		asteroids.addGameObject();
		
			
		TimerEvent bulletTimerEvent = new TimerEvent(bullet);
		Move bulletTimerAction = new Move(bullet, 45);
		bulletTimerEvent.addAction(bulletTimerAction);
		gameBoardModel.addEvent(bulletTimerEvent);
		
		TimerEvent asteroidTimerEvent = new TimerEvent(asteroids);
		MoveRandom asteroidTimerAction = new MoveRandom(asteroids);
		Revolve asteroidRevolve = new Revolve(asteroids);
		asteroidTimerEvent.addAction(asteroidTimerAction);
		asteroidTimerEvent.addAction(asteroidRevolve);
		gameBoardModel.addEvent(asteroidTimerEvent);
		
		int[] sides = {0,3};
		GenerateRandom asteroidGenerateRandomEvent = new GenerateRandom(asteroids, sides, 4, 20, 10, 60);
		gameBoardModel.addEvent(asteroidGenerateRandomEvent);
		
		TimerEvent asteroidSmallTimerEvent = new TimerEvent(asteroidsSmall);
		MoveRandom asteroidSmallTimerAction = new MoveRandom(asteroidsSmall);
		Revolve asteroidSmallRevolve = new Revolve(asteroidsSmall);
		asteroidSmallTimerEvent.addAction(asteroidSmallTimerAction);
		asteroidSmallTimerEvent.addAction(asteroidSmallRevolve);
		gameBoardModel.addEvent(asteroidSmallTimerEvent);
	
		
		// set shoot event on aircraft
		KeyPressedEvent aircraftShoot = new KeyPressedEvent(aircraft, KeyEvent.VK_SPACE);
		Shoot shootAction = new Shoot(aircraft, bullet);
		aircraftShoot.addAction(shootAction);
		
		gameBoardModel.addEvent(aircraftShoot);
		
		// set bullet and asteroid collision
		CollisionEvent bulletAsteroidCollisionEvent = new CollisionEvent(bullet, asteroids);
		Destroy bulletDestroy = new Destroy(bullet);
		Split asteroidSplitAction = new Split(asteroids, asteroidsSmall);
		bulletAsteroidCollisionEvent.addAction(bulletDestroy);
		bulletAsteroidCollisionEvent.addAction(asteroidSplitAction);
		
		gameBoardModel.addEvent(bulletAsteroidCollisionEvent);
		
		// set bullet and asteroid Small collision
		CollisionEvent bulletAsteroidSmallCollisionEvent = new CollisionEvent(bullet, asteroidsSmall);
		Destroy asteroidSmallDestroy = new Destroy(asteroidsSmall);
		bulletAsteroidSmallCollisionEvent.addAction(bulletDestroy);
		bulletAsteroidSmallCollisionEvent.addAction(asteroidSmallDestroy);
		
		gameBoardModel.addEvent(bulletAsteroidSmallCollisionEvent);
		
		
		// set aircraft Up movement
		KeyPressedEvent aircraftUpEvent = new KeyPressedEvent(aircraft, KeyEvent.VK_UP);
		Move actionUp = new Move(aircraft, 90);
		Revolve aircraftRevolve = new Revolve(aircraft);
		aircraftUpEvent.addAction(actionUp);
		aircraftUpEvent.addAction(aircraftRevolve);
		
		gameBoardModel.addEvent(aircraftUpEvent);
			
		//rotate aircraft clockwise image
		KeyPressedEvent aircraftClockRotate = new KeyPressedEvent(aircraft, KeyEvent.VK_RIGHT);
		Rotate actionClockRotate = new Rotate(aircraft, 345);
		aircraftClockRotate.addAction(actionClockRotate);
		
		gameBoardModel.addEvent(aircraftClockRotate);
		
		//rotate aircraft anti-clockwise image
		KeyPressedEvent aircraftAntiClockRotate = new KeyPressedEvent(aircraft, KeyEvent.VK_LEFT);
		Rotate actionAntiClockRotate = new Rotate(aircraft, 15);
		aircraftAntiClockRotate.addAction(actionAntiClockRotate);
		
		gameBoardModel.addEvent(aircraftAntiClockRotate);
		
		
		}
}
