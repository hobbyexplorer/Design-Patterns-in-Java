package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import model.action.Bounce;
import model.action.BounceVoid;
import model.action.Destroy;
import model.action.Move;
import model.action.MoveDown;
import model.action.MoveLeft;
import model.action.MoveRight;
import model.action.MoveUp;
import model.action.PlaySound;
import model.action.Revolve;
import model.action.Rotate;
import model.action.Shoot;
import model.action.Split;
import model.event.CollisionEvent;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;


public class TestModel {

    private GameBoardModel gameBoardModel; 	
	
	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}


	public TestModel()
	{
		gameBoardModel = new GameBoardModel();
		// Define the sprites
		
		//Image Sprite
		Sprite paddleImage = new Sprite();
		paddleImage.setName("rect");
		paddleImage.setHeight(50);
		paddleImage.setWidth(50);
		paddleImage.setShape(Shape.TRANSPERENTIMAGE);
		paddleImage.setSpeed(5);
		paddleImage.setImagePath("paddle1");
		
		Sprite brickImage = new Sprite();
		brickImage.setName("Brick");
		brickImage.setHeight(9);
		brickImage.setWidth(9);
		brickImage.setShape(Shape.IMAGE);
		brickImage.setSpeed(5);
		brickImage.setImagePath("brick1");
	
		
		// BALL SPRITE 
		Sprite ball = new Sprite();
		ball.setName("BALL");
		ball.setColor(Color.BLUE);
		ball.setHeight(20);
		ball.setWidth(20);
		ball.setShape(Shape.OVAL);
		ball.setSpeed(5);
		ball.setAngle(45);
		
		
		// PADDLE SPRITE
		Sprite paddle = new Sprite();
		paddle.setName("PADDLE");
		paddle.setColor(Color.BLACK);
		paddle.setHeight(15);
		paddle.setWidth(100);
		paddle.setShape(Shape.RECTANGLE);
		paddle.setSpeed(25);
		
		// BRICK SPRITE
		Sprite brick = new Sprite();
		brick.setName("BRICK");
		brick.setColor(Color.DARK_GRAY);
		brick.setHeight(20);
		brick.setWidth(40);
		brick.setShape(Shape.RECTANGLE);
		
		
		// HORIZONTAL WALL SPRITE
		Sprite horizontalWall = new Sprite();
		horizontalWall.setName("H_WALL");
		horizontalWall.setColor(Color.LIGHT_GRAY);
		horizontalWall.setHeight(10);
		horizontalWall.setWidth(500);
		horizontalWall.setShape(Shape.RECTANGLE);
		
		
		// VERTICAL WALL SPRITE
		Sprite verticalWall = new Sprite();
		verticalWall.setName("V_WALL");
		verticalWall.setColor(Color.LIGHT_GRAY);
		verticalWall.setHeight(500);
		verticalWall.setWidth(10);
		verticalWall.setShape(Shape.RECTANGLE);
		
		// BULLET
		Sprite bullet = new Sprite();
		bullet.setName("bullet");
		bullet.setHeight(12);
		bullet.setWidth(12);
		bullet.setShape(Shape.IMAGE);
		bullet.setSpeed(25);
		bullet.setImagePath("ball2");
	
		
		// add the sprites to the game sprites list
		gameBoardModel.addSprite(ball);
		gameBoardModel.addSprite(paddle);
		gameBoardModel.addSprite(brick);
		gameBoardModel.addSprite(horizontalWall);
		gameBoardModel.addSprite(verticalWall);
		gameBoardModel.addSprite(paddleImage);
		gameBoardModel.addSprite(bullet);
		gameBoardModel.addSprite(brickImage);
		
		// Create the game objects list and
		// keep on adding the objects to this list
		
		
//		ball.addGameObject();
//		ball.addGameObject();
		
		//paddleImage.addGameObject();
		/*
		int brickX = 30;
		int brickY = 30;
		int brickCounter = 0;
		
		int tempBrickX = brickX, tempBrickY = brickY;
		
		// create brick objects
		for(brickCounter = 0; brickCounter < 26; ++brickCounter)
			brick.addGameObject();
		
		GameObject object;
		brickCounter = 0;
		Iterator<GameObject> itr = brick.getGameObjects().iterator();
		while(itr.hasNext())
		{
			brickCounter++;
			object = itr.next();
			object.setX(tempBrickX);
			object.setY(tempBrickY);
			
			tempBrickX += (brick.getWidth() + 2);
			
			if(brickCounter == 13)
			{
				tempBrickY += (brick.getHeight() + 2);
				tempBrickX = brickX;
			}
		
		}
	*/
		paddle.addGameObject();
		horizontalWall.addGameObject();
		horizontalWall.addGameObject();
		
		verticalWall.addGameObject();
		verticalWall.addGameObject();
		
		paddle.getGameObjects().get(0).setX(275);
		paddle.getGameObjects().get(0).setY(450);
		
		horizontalWall.getGameObjects().get(0).setX(0);
		horizontalWall.getGameObjects().get(0).setY(0);
		horizontalWall.getGameObjects().get(1).setX(0);
		horizontalWall.getGameObjects().get(1).setY(490);
		
		verticalWall.getGameObjects().get(0).setX(0);
		verticalWall.getGameObjects().get(0).setY(0);
		verticalWall.getGameObjects().get(1).setX(310);
		verticalWall.getGameObjects().get(1).setY(0);
		
		ball.addGameObject();
		ball.getGameObjects().get(0).setX(310);
		ball.getGameObjects().get(0).setY(425);
	
		paddleImage.addGameObject();
		paddleImage.addGameObject();
/*		
		// create wall objects
		GameObject wallObject = new GameObject(horizontalWall);
		wallObject.setX(0);
		wallObject.setY(0);
		wallObject.setVisiblility(true);
			
		objectList.add(wallObject);
		
		wallObject = new GameObject(horizontalWall);
		wallObject.setX(0);
		wallObject.setY(490);
		wallObject.setVisiblility(true);
			
		objectList.add(wallObject);
		
		wallObject = new GameObject(verticalWall);
		wallObject.setX(0);
		wallObject.setY(0);
		wallObject.setVisiblility(true);
			
		objectList.add(wallObject);
		
		wallObject = new GameObject(verticalWall);
		wallObject.setX(490);
		wallObject.setY(0);
		wallObject.setVisiblility(true);
			
		objectList.add(wallObject);
		
		// now create the event list and add to it
		
		eventList = new ArrayList<Event>();
*/		
		TimerEvent timerEvent = new TimerEvent(bullet);
		Move action = new Move(bullet, 45);
		timerEvent.addAction(action);
		gameBoardModel.addEvent(timerEvent);
	
		// set shoot event on paddleImage
		KeyPressedEvent paddleShoot = new KeyPressedEvent(paddleImage, KeyEvent.VK_SPACE);
		Shoot shootAction = new Shoot(paddleImage, bullet);
		paddleShoot.addAction(shootAction);
		
		gameBoardModel.addEvent(paddleShoot);
		
		//movement right on paddle image on right key pressed
		KeyPressedEvent paddleRight = new KeyPressedEvent(paddleImage, KeyEvent.VK_RIGHT);
		MoveRight paddleMoveRight = new MoveRight(paddleImage, true);
		paddleRight.addAction(paddleMoveRight);
		
		gameBoardModel.addEvent(paddleRight);
		
		//movement left on paddle image on left key pressed
		KeyPressedEvent paddleLeft = new KeyPressedEvent(paddleImage, KeyEvent.VK_LEFT);
		MoveLeft paddelMoveLeft = new MoveLeft(paddleImage, true);
		paddleLeft.addAction(paddelMoveLeft);
		gameBoardModel.addEvent(paddleLeft);
		
		//movement right on paddle image on right key pressed
		KeyPressedEvent paddleUp = new KeyPressedEvent(paddleImage, KeyEvent.VK_UP);
		MoveUp paddleMoveUp = new MoveUp(paddleImage, true);
		paddleUp.addAction(paddleMoveUp);
		gameBoardModel.addEvent(paddleUp);
		
		//movement left on paddle image on left key pressed
		KeyPressedEvent paddleDown = new KeyPressedEvent(paddleImage, KeyEvent.VK_DOWN);
		MoveDown paddelMoveDown = new MoveDown(paddleImage, true);
		paddleDown.addAction(paddelMoveDown);
		gameBoardModel.addEvent(paddleDown);
		
		
		// set bullet and brick collision
		CollisionEvent bulletBrickCollisionEvent = new CollisionEvent(bullet, brick);
	//	Destroy bulletBrickDestroy = new Destroy(brick);
		Split brickSplitAction = new Split(brick, brickImage);
		Destroy brickBulletDestroy = new Destroy(bullet);
		bulletBrickCollisionEvent.addAction(brickSplitAction);
		bulletBrickCollisionEvent.addAction(brickBulletDestroy);
		
		gameBoardModel.addEvent(bulletBrickCollisionEvent);
		
		// set paddle left movement
		KeyPressedEvent paddleLeftEvent = new KeyPressedEvent(paddle, KeyEvent.VK_LEFT);
		MoveLeft actionLeft = new MoveLeft(paddle, false);
		paddleLeftEvent.addAction(actionLeft);
		
		gameBoardModel.addEvent(paddleLeftEvent);
		
		// set paddle right movement
		KeyPressedEvent paddleRightEvent = new KeyPressedEvent(paddle, KeyEvent.VK_RIGHT);
		MoveRight actionRight = new MoveRight(paddle, false);
		paddleRightEvent.addAction(actionRight);
		
		gameBoardModel.addEvent(paddleRightEvent);
		
		//bounce void on paddle image
		CollisionEvent paddleHWallCollisionEvent = new CollisionEvent(horizontalWall, paddleImage);
		BounceVoid bounceVoidHorBounceVoid = new BounceVoid(paddleImage);
		paddleHWallCollisionEvent.addAction(bounceVoidHorBounceVoid);
		
		gameBoardModel.addEvent(paddleHWallCollisionEvent);
		
		//bounce void on paddle image
		CollisionEvent paddleWWallCollisionEvent = new CollisionEvent(verticalWall, paddleImage);
		BounceVoid bounceVoidVerBounceVoid = new BounceVoid(paddleImage);
		paddleWWallCollisionEvent.addAction(bounceVoidVerBounceVoid);
		
		gameBoardModel.addEvent(paddleWWallCollisionEvent);
		
		//rotate paddle image
		KeyPressedEvent paddleImageRotate = new KeyPressedEvent(paddleImage, KeyEvent.VK_9);
		Rotate actionRotate = new Rotate(paddleImage, 5);
		paddleImageRotate.addAction(actionRotate);
		
		gameBoardModel.addEvent(paddleImageRotate);
		
		// set ball movement
		TimerEvent ballMoveEvent = new TimerEvent(ball);
		Move ballMoveAction = new Move(ball, 50);
		ballMoveEvent.addAction(ballMoveAction);
		Revolve ballRevolve = new Revolve(ball);
		ballMoveEvent.addAction(ballRevolve);
		gameBoardModel.addEvent(ballMoveEvent);
		
		// set ball split
		KeyPressedEvent ballSplitEvent = new KeyPressedEvent(ball, KeyEvent.VK_SHIFT);
		Split ballSplitAction = new Split(ball, bullet);
		ballSplitEvent.addAction(ballSplitAction);
		
		gameBoardModel.addEvent(ballSplitEvent);
		
		//rotate ball
		KeyPressedEvent ballRotateEvent = new KeyPressedEvent(ball, KeyEvent.VK_9);
		Rotate ballRotateAction = new Rotate(ball, 15);
		ballRotateEvent.addAction(ballRotateAction);
		
		gameBoardModel.addEvent(ballRotateEvent);
		
		// set ball and wall collision
		CollisionEvent ballHWallCollisionEvent = new CollisionEvent(ball, horizontalWall);
		Bounce ballHBounce = new Bounce(ball);
		ballHWallCollisionEvent.addAction(ballHBounce);
		
		
		gameBoardModel.addEvent(ballHWallCollisionEvent);
		
		CollisionEvent ballVWallCollisionEvent = new CollisionEvent(ball, verticalWall);
		Bounce ballVBounce = new Bounce(ball);
		ballVWallCollisionEvent.addAction(ballVBounce);
		
		gameBoardModel.addEvent(ballVWallCollisionEvent);
	/*		
		for(int objectCounter = 0; objectCounter < objectList.size(); ++objectCounter)
		{
			GameObject currentObject = objectList.get(objectCounter);
			if(currentObject.getSprite() == horizontalWall)
			{
				CollisionEvent ballWallCollisionEvent = new CollisionEvent(ballObject, currentObject);
				ReflectVerticalAction vAction = new ReflectVerticalAction(ballObject);
				ballWallCollisionEvent.addAction(vAction);
				eventList.add(ballWallCollisionEvent);
			}
			else if(currentObject.getSprite() == verticalWall)
			{
				CollisionEvent ballWallCollisionEvent = new CollisionEvent(ballObject, currentObject);
				ReflectHorizontalAction hAction = new ReflectHorizontalAction(ballObject);
				ballWallCollisionEvent.addAction(hAction);
				eventList.add(ballWallCollisionEvent);
			}
		}
		
		// set paddle and wall collision
		for(int objectCounter = 0; objectCounter < objectList.size(); ++objectCounter)
		{
			GameObject currentObject = objectList.get(objectCounter);
			if(currentObject.getSprite() == verticalWall)
			{
				CollisionEvent paddleWallCollisionEvent = new CollisionEvent(paddleObject, currentObject);
				StopMovementAction stopAction = new StopMovementAction(currentObject, paddleObject);
				paddleWallCollisionEvent.addAction(stopAction);
				eventList.add(paddleWallCollisionEvent);
			}
		}
		
*/		
		// set ball and brick collision
		CollisionEvent ballBrickCollisionEvent = new CollisionEvent(ball, brick);
		Destroy brickDestroy = new Destroy(brick);
		PlaySound sound = new PlaySound("beep");
		Bounce bounceBall = new Bounce(ball);
		ballBrickCollisionEvent.addAction(brickDestroy);
		ballBrickCollisionEvent.addAction(sound);
		ballBrickCollisionEvent.addAction(bounceBall);	
		
		gameBoardModel.addEvent(ballBrickCollisionEvent);
	
/*		for(int objectCounter = 0; objectCounter < objectList.size(); ++objectCounter)
		{
			GameObject currentObject = objectList.get(objectCounter);
			if(currentObject.getSprite() == brick)
			{
				CollisionEvent ballBrickCollisionEvent = new CollisionEvent(ballObject, currentObject);
				
				AutoReflectAction reflectAction = new AutoReflectAction(currentObject, ballObject);
				ballBrickCollisionEvent.addAction(reflectAction);
				
				DestroyAction brickDestroyAction = new DestroyAction(currentObject);
				ballBrickCollisionEvent.addAction(brickDestroyAction);
				eventList.add(ballBrickCollisionEvent);
			}
		}
	*/	
		// set ball and paddle collision

		CollisionEvent ballPaddleCollisionEvent = new CollisionEvent(ball, paddle);
		Bounce bounceBallonPaddle = new Bounce(ball);
		ballPaddleCollisionEvent.addAction(bounceBallonPaddle);	
		
		gameBoardModel.addEvent(ballPaddleCollisionEvent);

/*		for(int objectCounter = 0; objectCounter < objectList.size(); ++objectCounter)
		{
			GameObject currentObject = objectList.get(objectCounter);
			if(currentObject.getSprite() == paddle)
			{
				CollisionEvent ballPaddleCollisionEvent = new CollisionEvent(ballObject, currentObject);
				
				AutoReflectAction reflectAction = new AutoReflectAction(currentObject, ballObject);
				ballPaddleCollisionEvent.addAction(reflectAction);
				
				eventList.add(ballPaddleCollisionEvent);
			}
		}
*/	}
}
