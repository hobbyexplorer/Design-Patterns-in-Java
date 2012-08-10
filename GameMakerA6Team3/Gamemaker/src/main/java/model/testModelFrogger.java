package model;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import model.action.Blink;
import model.action.BlinkRandom;
import model.action.Bounce;
import model.action.Destroy;
import model.action.Move;
import model.action.MoveAlong;
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
import model.event.CollisionEventWithException;
import model.event.GenerateAtPosition;
import model.event.GenerateRandom;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;


public class testModelFrogger {

    private GameBoardModel gameBoardModel; 	
	
	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}


	public testModelFrogger()
	{
		gameBoardModel = new GameBoardModel();
		// Define the sprites
		
		//Aircraft Sprite
		Sprite frog = new Sprite();
		frog.setName("Frog");
		frog.setHeight(24);
		frog.setWidth(24);
		frog.setShape(Shape.IMAGE);
		frog.setSpeed(50);
		frog.setImagePath("frog");
		
		//Large Logs Sprite
		Sprite logs = new Sprite();
		logs.setName("Long log");
		logs.setHeight(30);
		logs.setWidth(90);
		logs.setShape(Shape.IMAGE);
		logs.setSpeed(1);
		logs.setImagePath("longlog");
		
		//Small short logs Sprite
		Sprite shortlogs = new Sprite();
		shortlogs.setName("Short log");
		shortlogs.setHeight(30);
		shortlogs.setWidth(45);
		shortlogs.setShape(Shape.IMAGE);
		shortlogs.setSpeed(2);
		shortlogs.setImagePath("shortlog");
		
		//Large turtles Sprite
		Sprite turtle = new Sprite();
		turtle.setName("Turtle");
		turtle.setHeight(30);
		turtle.setWidth(110);
		turtle.setShape(Shape.IMAGE);
		turtle.setSpeed(1);
		turtle.setImagePath("turtles");
		
		
		//Car 1 Sprite
		Sprite car1 = new Sprite();
		car1.setName("Car 1");
		car1.setHeight(20);
		car1.setWidth(55);
		car1.setShape(Shape.IMAGE);
		car1.setSpeed(2);
		car1.setImagePath("car1");
		
		//Car 2 Sprite
		Sprite car2 = new Sprite();
		car2.setName("Car 2");
		car2.setHeight(30);
		car2.setWidth(35);
		car2.setShape(Shape.IMAGE);
		car2.setSpeed(2);
		car2.setImagePath("car2");
	
		//Car 3 Sprite
		Sprite car3 = new Sprite();
		car3.setName("Car 3");
		car3.setHeight(25);
		car3.setWidth(35);
		car3.setShape(Shape.IMAGE);
		car3.setSpeed(2);
		car3.setImagePath("car3");
		
		//Car 4 Sprite
		Sprite car4 = new Sprite();
		car4.setName("Car 4");
		car4.setHeight(25);
		car4.setWidth(30);
		car4.setShape(Shape.IMAGE);
		car4.setSpeed(2);
		car4.setImagePath("car4");
		
		//Car 5 Sprite
		Sprite car5 = new Sprite();
		car5.setName("Car 5");
		car5.setHeight(30);
		car5.setWidth(35);
		car5.setShape(Shape.IMAGE);
		car5.setSpeed(2);
		car5.setImagePath("car5");
		
		//Stage purple Sprite
		Sprite stagePurple = new Sprite();
		stagePurple.setName("Stage Purple");
		stagePurple.setHeight(38);
		stagePurple.setWidth(600);
		stagePurple.setShape(Shape.IMAGE);
		stagePurple.setSpeed(0);
		stagePurple.setImagePath("froggerstage");
		
		Sprite greenStage = new Sprite();
		greenStage.setName("Green Stage");
		greenStage.setHeight(55);
		greenStage.setWidth(600);
		greenStage.setShape(Shape.IMAGE);
		greenStage.setSpeed(0);
		greenStage.setImagePath("greenstage");
		
		Sprite blueStage = new Sprite();
		blueStage.setName("Blue Stage");
		blueStage.setHeight(275);
		blueStage.setWidth(600);
		blueStage.setShape(Shape.IMAGE);
		blueStage.setSpeed(0);
		blueStage.setImagePath("bluestage");
		
		Sprite blackStage = new Sprite();
		blackStage.setName("Black Stage");
		blackStage.setHeight(250);
		blackStage.setWidth(600);
		blackStage.setShape(Shape.IMAGE);
		blackStage.setSpeed(0);
		blackStage.setImagePath("blackstage");
		
		Sprite winStage = new Sprite();
		winStage.setName("Win Stage");
		winStage.setHeight(30);
		winStage.setWidth(33);
		winStage.setShape(Shape.IMAGE);
		winStage.setSpeed(0);
		winStage.setImagePath("winstage");
	
		
	
		
		// add the sprites to the game sprites list
		gameBoardModel.addSprite(blueStage);
		gameBoardModel.addSprite(greenStage);
		gameBoardModel.addSprite(winStage);
		gameBoardModel.addSprite(blackStage);
		gameBoardModel.addSprite(stagePurple);
		gameBoardModel.addSprite(logs);
		gameBoardModel.addSprite(shortlogs);
		gameBoardModel.addSprite(turtle);
		gameBoardModel.addSprite(car1);
		gameBoardModel.addSprite(car2);
		gameBoardModel.addSprite(car3);
		gameBoardModel.addSprite(car4);
		gameBoardModel.addSprite(car5);
		
		gameBoardModel.addSprite(frog);
			
		// Create the game objects list and
		// keep on adding the objects to this list
		GameObject newObject;
		blueStage.addGameObject();
		greenStage.addGameObject();
		newObject = blackStage.addGameObject();
		newObject.setX(0);
		newObject.setY(Constants.GAMEBOARD_HEIGHT - stagePurple.getHeight() - newObject.getObjectHeight());
		
		newObject = winStage.addGameObject();
		newObject.setX(36);
		newObject.setY(25);
		
		newObject = winStage.addGameObject();
		newObject.setX(194);
		newObject.setY(25);
	
		newObject = winStage.addGameObject();
		newObject.setX(354);
		newObject.setY(25);
	
		newObject = winStage.addGameObject();
		newObject.setX(511);
		newObject.setY(25);
	
		newObject = stagePurple.addGameObject();
		newObject.setX(0);
		newObject.setY(Constants.GAMEBOARD_HEIGHT - newObject.getObjectHeight());
		newObject = stagePurple.addGameObject();
		newObject.setX(0);
		newObject.setY(Constants.GAMEBOARD_HEIGHT - 2*newObject.getObjectHeight() - blackStage.getHeight());
		
		newObject = frog.addGameObject();
		newObject.setX(300);
		newObject.setY(575);
		
			
		PlaySound froggerHopSound = new PlaySound("froggerhop");
		KeyPressedEvent leftMoveEvent = new KeyPressedEvent(frog, KeyEvent.VK_LEFT);
		MoveLeft leftMoveAction = new MoveLeft(frog, true);
		leftMoveEvent.addAction(leftMoveAction);
		leftMoveEvent.addAction(froggerHopSound);
		gameBoardModel.addEvent(leftMoveEvent);
		
		KeyPressedEvent rightMoveEvent = new KeyPressedEvent(frog, KeyEvent.VK_RIGHT);
		MoveRight rightMoveAction = new MoveRight(frog, true);
		rightMoveEvent.addAction(rightMoveAction);
		rightMoveEvent.addAction(froggerHopSound);
		gameBoardModel.addEvent(rightMoveEvent);
		
		KeyPressedEvent upMoveEvent = new KeyPressedEvent(frog, KeyEvent.VK_UP);
		MoveUp upMoveAction = new MoveUp(frog, true);
		upMoveEvent.addAction(upMoveAction);
		upMoveEvent.addAction(froggerHopSound);
		gameBoardModel.addEvent(upMoveEvent);
			
		KeyPressedEvent downMoveEvent = new KeyPressedEvent(frog, KeyEvent.VK_DOWN);
		MoveDown downMoveAction = new MoveDown(frog, true);
		downMoveEvent.addAction(downMoveAction);
		downMoveEvent.addAction(froggerHopSound);
		gameBoardModel.addEvent(downMoveEvent);
		
			
		TimerEvent logsTimerEvent = new TimerEvent(logs);
		Move logsTimerAction = new Move(logs, 0);
		logsTimerEvent.addAction(logsTimerAction);
		gameBoardModel.addEvent(logsTimerEvent);
		
		GenerateAtPosition logsGenerateFixedEvent = new GenerateAtPosition(logs, 0, 75, 1, 0, 3, 10000);
		gameBoardModel.addEvent(logsGenerateFixedEvent);
		
		GenerateAtPosition logsGenerateFixedEvent1 = new GenerateAtPosition(logs, 0, 175, 1, 0, 2, 10000);
		gameBoardModel.addEvent(logsGenerateFixedEvent1);
		
		
		TimerEvent shortLogsTimerEvent = new TimerEvent(shortlogs);
		Move shortLogsTimerAction = new Move(shortlogs, 180);
		shortLogsTimerEvent.addAction(shortLogsTimerAction);
		gameBoardModel.addEvent(shortLogsTimerEvent);
		
		GenerateAtPosition shortLogsGenerateFixedEvent = new GenerateAtPosition(shortlogs, Constants.GAMEBOARD_WIDTH, 125, 1, 0, 3, 10000);
		gameBoardModel.addEvent(shortLogsGenerateFixedEvent);
		
		TimerEvent turtleTimerEvent = new TimerEvent(turtle);
		Move turtleTimerAction = new Move(turtle, 180);
		BlinkRandom turtleBlinkAction = new BlinkRandom(turtle, 1000, 1000);
		turtleTimerEvent.addAction(turtleTimerAction);
		turtleTimerEvent.addAction(turtleBlinkAction);
		gameBoardModel.addEvent(turtleTimerEvent);
		
		GenerateAtPosition turtleGenerateFixedEvent = new GenerateAtPosition(turtle, Constants.GAMEBOARD_WIDTH, 225, 1, 0, 3, 10000);
		gameBoardModel.addEvent(turtleGenerateFixedEvent);
		
		TimerEvent car1TimerEvent = new TimerEvent(car1);
		Move car1TimerAction = new Move(car1, 180);
		car1TimerEvent.addAction(car1TimerAction);
		gameBoardModel.addEvent(car1TimerEvent);
		
		GenerateAtPosition car1GenerateFixedEvent = new GenerateAtPosition(car1, Constants.GAMEBOARD_WIDTH, 325, 1, 0, 2, 10000);
		gameBoardModel.addEvent(car1GenerateFixedEvent);
		
		TimerEvent car2TimerEvent = new TimerEvent(car2);
		Move car2TimerAction = new Move(car2, 0);
		car2TimerEvent.addAction(car2TimerAction);
		gameBoardModel.addEvent(car2TimerEvent);
		
		GenerateAtPosition car2GenerateFixedEvent = new GenerateAtPosition(car2, 0, 375, 1, 0, 1, 10000);
		gameBoardModel.addEvent(car2GenerateFixedEvent);
		
		TimerEvent car3TimerEvent = new TimerEvent(car3);
		Move car3TimerAction = new Move(car3, 180);
		car3TimerEvent.addAction(car3TimerAction);
		gameBoardModel.addEvent(car3TimerEvent);
		
		GenerateAtPosition car3GenerateFixedEvent = new GenerateAtPosition(car3, Constants.GAMEBOARD_WIDTH, 425, 1, 0, 2, 10000);
		gameBoardModel.addEvent(car3GenerateFixedEvent);
		
		TimerEvent car4TimerEvent = new TimerEvent(car4);
		Move car4TimerAction = new Move(car4, 0);
		car4TimerEvent.addAction(car4TimerAction);
		gameBoardModel.addEvent(car4TimerEvent);
	/*	
		TimerEvent car4BlinkEvent = new TimerEvent(car4);
		BlinkRandom car4BlinkAction = new BlinkRandom(car4, 1000, 1000);
		car4BlinkEvent.addAction(car4BlinkAction);
		gameBoardModel.addEvent(car4BlinkEvent);
		
	*/	GenerateAtPosition car4GenerateFixedEvent = new GenerateAtPosition(car4, 0, 475, 1, 0, 1, 10000);
		gameBoardModel.addEvent(car4GenerateFixedEvent);
		
	
		TimerEvent car5TimerEvent = new TimerEvent(car5);
		Move car5TimerAction = new Move(car5, 180);
		car5TimerEvent.addAction(car5TimerAction);
		gameBoardModel.addEvent(car5TimerEvent);
	
		GenerateAtPosition car5GenerateFixedEvent = new GenerateAtPosition(car5, Constants.GAMEBOARD_WIDTH, 525, 1, 0, 3, 10000);
		gameBoardModel.addEvent(car5GenerateFixedEvent);
		
		CollisionEvent moveFrogOnLogEvent = new CollisionEvent(frog, logs);
		MoveAlong moveFrogOnLogAction = new MoveAlong(frog);
		moveFrogOnLogEvent.addAction(moveFrogOnLogAction);
		
		gameBoardModel.addEvent(moveFrogOnLogEvent);
		
		CollisionEvent moveFrogOnShortLogEvent = new CollisionEvent(frog, shortlogs);
		MoveAlong moveFrogOnShortLogAction = new MoveAlong(frog);
		moveFrogOnShortLogEvent.addAction(moveFrogOnShortLogAction);
		
		gameBoardModel.addEvent(moveFrogOnShortLogEvent);
	
		CollisionEvent moveFrogOnTurtleEvent = new CollisionEvent(frog, turtle);
		MoveAlong moveFrogOnTurtleAction = new MoveAlong(frog);
		moveFrogOnTurtleEvent.addAction(moveFrogOnTurtleAction);
		
		gameBoardModel.addEvent(moveFrogOnTurtleEvent);
	
		//Game over rules
		Destroy frogDestroy = new Destroy(frog);
		PlaySound froggerDieSound = new PlaySound("froggersquash");
		
		CollisionEvent frogCar1CollisionEvent = new CollisionEvent(frog, car1);
		frogCar1CollisionEvent.addAction(frogDestroy);
		frogCar1CollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogCar1CollisionEvent);
	
		CollisionEvent frogCar2CollisionEvent = new CollisionEvent(frog, car2);
		frogCar2CollisionEvent.addAction(frogDestroy);
		frogCar2CollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogCar2CollisionEvent);

		CollisionEvent frogCar3CollisionEvent = new CollisionEvent(frog, car3);
		frogCar3CollisionEvent.addAction(frogDestroy);
		frogCar3CollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogCar3CollisionEvent);
		
		CollisionEvent frogCar4CollisionEvent = new CollisionEvent(frog, car4);
		frogCar4CollisionEvent.addAction(frogDestroy);
		frogCar4CollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogCar4CollisionEvent);
		
		CollisionEvent frogCar5CollisionEvent = new CollisionEvent(frog, car5);
		frogCar5CollisionEvent.addAction(frogDestroy);
		frogCar5CollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogCar5CollisionEvent);
	/*	
		CollisionEvent frogWaterCollisionEvent = new CollisionEvent(frog, blueStage);
		frogWaterCollisionEvent.addAction(frogDestroy);
		frogWaterCollisionEvent.addAction(froggerDieSound);
		gameBoardModel.addEvent(frogWaterCollisionEvent);
		
		CollisionEvent frogGreenFieldCollisionEvent = new CollisionEvent(frog, greenStage);
		frogGreenFieldCollisionEvent.addAction(frogDestroy);
		frogGreenFieldCollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogGreenFieldCollisionEvent);
	*/	
		ArrayList<Sprite> exceptionList = new ArrayList<Sprite>();
		exceptionList.add(logs);
		exceptionList.add(shortlogs);
		exceptionList.add(turtle);
		exceptionList.add(winStage);
		
		CollisionEventWithException frogWaterCollisionEvent = new CollisionEventWithException(frog, blueStage, exceptionList);
		frogWaterCollisionEvent.addAction(frogDestroy);
		frogWaterCollisionEvent.addAction(froggerDieSound);
		
		gameBoardModel.addEvent(frogWaterCollisionEvent);
	/*	
		TimerEvent asteroidSmallTimerEvent = new TimerEvent(shortlogs);
		MoveRandom asteroidSmallTimerAction = new MoveRandom(shortlogs);
		Revolve asteroidSmallRevolve = new Revolve(shortlogs);
		asteroidSmallTimerEvent.addAction(asteroidSmallTimerAction);
		asteroidSmallTimerEvent.addAction(asteroidSmallRevolve);
		gameBoardModel.addEvent(asteroidSmallTimerEvent);
	
		
		// set shoot event on aircraft
		KeyPressedEvent aircraftShoot = new KeyPressedEvent(frog, KeyEvent.VK_SPACE);
		Shoot shootAction = new Shoot(frog, bullet);
		aircraftShoot.addAction(shootAction);
		
		gameBoardModel.addEvent(aircraftShoot);
		
		// set bullet and asteroid collision
		CollisionEvent bulletAsteroidCollisionEvent = new CollisionEvent(bullet, logs);
		Destroy bulletDestroy = new Destroy(bullet);
		Split asteroidSplitAction = new Split(logs, shortlogs);
		bulletAsteroidCollisionEvent.addAction(bulletDestroy);
		bulletAsteroidCollisionEvent.addAction(asteroidSplitAction);
		
		gameBoardModel.addEvent(bulletAsteroidCollisionEvent);
		
		// set bullet and asteroid Small collision
		CollisionEvent bulletAsteroidSmallCollisionEvent = new CollisionEvent(bullet, shortlogs);
		Destroy asteroidSmallDestroy = new Destroy(shortlogs);
		bulletAsteroidSmallCollisionEvent.addAction(bulletDestroy);
		bulletAsteroidSmallCollisionEvent.addAction(asteroidSmallDestroy);
		
		gameBoardModel.addEvent(bulletAsteroidSmallCollisionEvent);
		
		
		// set aircraft Up movement
		KeyPressedEvent aircraftUpEvent = new KeyPressedEvent(frog, KeyEvent.VK_UP);
		Move actionUp = new Move(frog, 90);
		Revolve aircraftRevolve = new Revolve(frog);
		aircraftUpEvent.addAction(actionUp);
		aircraftUpEvent.addAction(aircraftRevolve);
		
		gameBoardModel.addEvent(aircraftUpEvent);
			
		//rotate aircraft clockwise image
		KeyPressedEvent aircraftClockRotate = new KeyPressedEvent(frog, KeyEvent.VK_RIGHT);
		Rotate actionClockRotate = new Rotate(frog, 345);
		aircraftClockRotate.addAction(actionClockRotate);
		
		gameBoardModel.addEvent(aircraftClockRotate);
		
		//rotate aircraft anti-clockwise image
		KeyPressedEvent aircraftAntiClockRotate = new KeyPressedEvent(frog, KeyEvent.VK_LEFT);
		Rotate actionAntiClockRotate = new Rotate(frog, 15);
		aircraftAntiClockRotate.addAction(actionAntiClockRotate);
		
		gameBoardModel.addEvent(aircraftAntiClockRotate);
		
*/		
		}
}
