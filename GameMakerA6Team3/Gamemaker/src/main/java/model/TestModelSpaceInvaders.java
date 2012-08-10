package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import model.action.Bounce;
import model.action.BounceAll;
import model.action.Contract;
import model.action.Destroy;
import model.action.GameOver;
import model.action.Move;
import model.action.MoveAll;
import model.action.MoveDown;
import model.action.MoveLeft;
import model.action.MoveRight;
import model.action.MoveUp;
import model.action.PlaySound;
import model.action.Revolve;
import model.action.SetGameTime;
import model.action.Shoot;
import model.action.ShootRandom;
import model.event.CollisionEvent;
import model.event.CountEvent;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;

public class TestModelSpaceInvaders
{
    private GameBoardModel gameBoardModel; 	
	
	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}

	public TestModelSpaceInvaders()
	{
		gameBoardModel = new GameBoardModel();
		
		//Black stage
		Sprite blackStage = new Sprite();
		blackStage.setName("Black Stage");
		blackStage.setHeight(600);
		blackStage.setWidth(600);
		blackStage.setShape(Shape.IMAGE);
		blackStage.setSpeed(0);
		blackStage.setImagePath("blackstage");
		
		//Shield
		Sprite shield = new Sprite();
		shield.setName("Shield");
		shield.setHeight(50);
		shield.setWidth(50);
		shield.setShape(Shape.IMAGE);
		shield.setSpeed(0);
		shield.setImagePath("shield");
		
	
		// HORIZONTAL WALL SPRITE
		Sprite horizontalWall = new Sprite();
		horizontalWall.setName("H_WALL");
		horizontalWall.setColor(Color.LIGHT_GRAY);
		horizontalWall.setHeight(10);
		horizontalWall.setWidth(600);
		horizontalWall.setShape(Shape.RECTANGLE);
		
		
		// VERTICAL WALL SPRITE
		Sprite verticalWall = new Sprite();
		verticalWall.setName("V_WALL");
		verticalWall.setColor(Color.LIGHT_GRAY);
		verticalWall.setHeight(600);
		verticalWall.setWidth(10);
		verticalWall.setShape(Shape.RECTANGLE);
		
		// Enemy1 SPRITE 
		Sprite enemy1 = new Sprite();
		enemy1.setName("Enemy1");
		enemy1.setColor(Color.BLUE);
		enemy1.setHeight(20);
		enemy1.setWidth(40);
		enemy1.setShape(Shape.IMAGE);
		enemy1.setSpeed(1);
		enemy1.setImagePath("enemy1");
	
		// Tank sprite
		Sprite tank = new Sprite();
		tank.setName("Tank");
		tank.setColor(Color.BLUE);
		tank.setHeight(20);
		tank.setWidth(40);
		tank.setShape(Shape.IMAGE);
		tank.setSpeed(15);
		tank.setImagePath("tank");
	

		// BULLET
		Sprite bullet = new Sprite();
		bullet.setName("bullet");
		bullet.setHeight(6);
		bullet.setWidth(3);
		bullet.setShape(Shape.RECTANGLE);
		bullet.setSpeed(10);
		
		// BULLET
		Sprite enemyBullet = new Sprite();
		enemyBullet.setName("Enemy bullet");
		enemyBullet.setHeight(18);
		enemyBullet.setWidth(14);
		enemyBullet.setShape(Shape.IMAGE);
		enemyBullet.setSpeed(0);
		enemyBullet.setImagePath("enemybullet");
		
		
		gameBoardModel.addSprite(blackStage);
		gameBoardModel.addSprite(shield);
		gameBoardModel.addSprite(horizontalWall);
		gameBoardModel.addSprite(verticalWall);
		gameBoardModel.addSprite(enemy1);
		gameBoardModel.addSprite(tank);
		gameBoardModel.addSprite(bullet);
		gameBoardModel.addSprite(enemyBullet);
		
		GameObject newObject;
		blackStage.addGameObject();
		
		newObject = shield.addGameObject();
		newObject.setX(40);
		newObject.setY(450);
		
		newObject = shield.addGameObject();
		newObject.setX(160);
		newObject.setY(450);
		
		newObject = shield.addGameObject();
		newObject.setX(280);
		newObject.setY(450);
		
		newObject = shield.addGameObject();
		newObject.setX(400);
		newObject.setY(450);
		
		newObject = shield.addGameObject();
		newObject.setX(520);
		newObject.setY(450);
		
		
		horizontalWall.addGameObject();
		horizontalWall.addGameObject();
		
		verticalWall.addGameObject();
		verticalWall.addGameObject();
		
		horizontalWall.getGameObjects().get(0).setX(0);
		horizontalWall.getGameObjects().get(0).setY(0);
		horizontalWall.getGameObjects().get(1).setX(0);
		horizontalWall.getGameObjects().get(1).setY(590);
		
		verticalWall.getGameObjects().get(0).setX(0);
		verticalWall.getGameObjects().get(0).setY(0);
		verticalWall.getGameObjects().get(1).setX(590);
		verticalWall.getGameObjects().get(1).setY(0);
		
		newObject = tank.addGameObject();
		newObject.setX(290);
		newObject.setY(525);
		
		TimerEvent tankTimerEvent = new TimerEvent(tank);
		SetGameTime gameTime = new SetGameTime(120, "You Lose");
		tankTimerEvent.addAction(gameTime);
		
		gameBoardModel.addEvent(tankTimerEvent);
		
		TimerEvent enemy1MoveEvent = new TimerEvent(enemy1);
		Move enemy1MoveAction = new Move(enemy1, 0);
		ShootRandom enemy1ShootRandom = new ShootRandom(enemy1, enemyBullet, 2000, 270, 5);
		enemy1MoveEvent.addAction(enemy1MoveAction);
		enemy1MoveEvent.addAction(enemy1ShootRandom);
		
		gameBoardModel.addEvent(enemy1MoveEvent);
		
		int brickX = 30;
		int brickY = 30;
		int brickCounter = 0;
		
		int tempBrickX = brickX, tempBrickY = brickY;
		
		// create brick objects
		for(brickCounter = 0; brickCounter < 40; ++brickCounter)
			enemy1.addGameObject();
		
		GameObject object;
		brickCounter = 0;
		Iterator<GameObject> itr = enemy1.getGameObjects().iterator();
		while(itr.hasNext())
		{
			brickCounter++;
			object = itr.next();
			object.setX(tempBrickX);
			object.setY(tempBrickY);
			
			tempBrickX += (enemy1.getWidth() + 2);
			
			if(brickCounter%10 == 0)
			{
				tempBrickY += (enemy1.getHeight() + 2);
				tempBrickX = brickX;
			}
		
		}
		
	/*	
		CollisionEvent ballHWallCollisionEvent = new CollisionEvent(enemy1, horizontalWall);
		BounceAll ballHBounce = new BounceAll(enemy1);
		MoveAll ballHMove = new MoveAll(enemy1, 270, 15);
		ballHWallCollisionEvent.addAction(ballHBounce);
		ballHWallCollisionEvent.addAction(ballHMove);
		
		
		gameBoardModel.addEvent(ballHWallCollisionEvent);
	
	*/	
		CollisionEvent ballVWallCollisionEvent = new CollisionEvent(enemy1, verticalWall);
		BounceAll ballVBounce = new BounceAll(enemy1);
		MoveAll ballVMove = new MoveAll(enemy1, 270, 15);
		ballVWallCollisionEvent.addAction(ballVBounce);
		ballVWallCollisionEvent.addAction(ballVMove);
		
		gameBoardModel.addEvent(ballVWallCollisionEvent);

		
		// set shoot event on tank
		KeyPressedEvent tankShoot = new KeyPressedEvent(tank, KeyEvent.VK_SPACE);
		Shoot shootAction = new Shoot(tank, bullet);
		tankShoot.addAction(shootAction);
		
		gameBoardModel.addEvent(tankShoot);
	
		TimerEvent bulletTimerEvent = new TimerEvent(bullet);
		Move bulletTimerAction = new Move(bullet, 45);
		bulletTimerEvent.addAction(bulletTimerAction);
		
		gameBoardModel.addEvent(bulletTimerEvent);
		
		//enemy bullet timer event
		TimerEvent enemybulletTimerEvent = new TimerEvent(enemyBullet);
		Move enemybulletTimerAction = new Move(enemyBullet, 45);
		enemybulletTimerEvent.addAction(enemybulletTimerAction);
		
		gameBoardModel.addEvent(enemybulletTimerEvent);
		
		
		PlaySound tankMoveSound = new PlaySound("froggerhop");
		KeyPressedEvent leftMoveEvent = new KeyPressedEvent(tank, KeyEvent.VK_LEFT);
		MoveLeft leftMoveAction = new MoveLeft(tank,false);
		leftMoveEvent.addAction(leftMoveAction);
		leftMoveEvent.addAction(tankMoveSound);
		gameBoardModel.addEvent(leftMoveEvent);
		
		KeyPressedEvent rightMoveEvent = new KeyPressedEvent(tank, KeyEvent.VK_RIGHT);
		MoveRight rightMoveAction = new MoveRight(tank, false);
		rightMoveEvent.addAction(rightMoveAction);
		rightMoveEvent.addAction(tankMoveSound);
		gameBoardModel.addEvent(rightMoveEvent);
		
		// set bullet and enemy1 collision
		CollisionEvent bulletEnemy1CollisionEvent = new CollisionEvent(bullet, enemy1);
		Destroy bulletDestroy = new Destroy(bullet);
		Destroy enemy1Destroy = new Destroy(enemy1);
		bulletEnemy1CollisionEvent.addAction(bulletDestroy);
		bulletEnemy1CollisionEvent.addAction(enemy1Destroy);
		
		gameBoardModel.addEvent(bulletEnemy1CollisionEvent);
	
		// set bullet and shield collision
		CollisionEvent bulletShieldCollisionEvent = new CollisionEvent(bullet, shield);
		Contract shieldContract = new Contract(shield, 10);
		bulletShieldCollisionEvent.addAction(bulletDestroy);
		bulletShieldCollisionEvent.addAction(shieldContract);
		
		gameBoardModel.addEvent(bulletShieldCollisionEvent);
	
		// set enemy bullet and shield collision
		CollisionEvent enemybulletShieldCollisionEvent = new CollisionEvent(enemyBullet, shield);
		Destroy enemyBulletDestroy = new Destroy(enemyBullet);
		enemybulletShieldCollisionEvent.addAction(enemyBulletDestroy);
		enemybulletShieldCollisionEvent.addAction(shieldContract);
		
		gameBoardModel.addEvent(enemybulletShieldCollisionEvent);
	
		// set enemy bullet and tank collision
		CollisionEvent enemybulletTankCollisionEvent = new CollisionEvent(enemyBullet, tank);
		Destroy tankDestroy = new Destroy(tank);
		GameOver gameOverAction = new GameOver(tank, "Go Away You Lose !!", false);
		enemybulletTankCollisionEvent.addAction(enemyBulletDestroy);
		enemybulletTankCollisionEvent.addAction(gameOverAction);
		enemybulletTankCollisionEvent.addAction(tankDestroy);
		
		gameBoardModel.addEvent(enemybulletTankCollisionEvent);
	
		//enemy1 and tank collision
		CollisionEvent enemy1TankCollisionEvent = new CollisionEvent(enemy1, tank);
	
		enemy1TankCollisionEvent.addAction(tankDestroy);
		enemy1TankCollisionEvent.addAction(gameOverAction);
		gameBoardModel.addEvent(enemy1TankCollisionEvent);
		
		//Count event on enemy1
		CountEvent enemy1CountEvent = new CountEvent(enemy1);
		GameOver enemy1CountGameOver = new GameOver(enemy1, "Congratulations YOU WIN !!!", true);
		enemy1CountGameOver.setCondition(20, false, true, true);
		enemy1CountEvent.addAction(enemy1CountGameOver);
		
		gameBoardModel.addEvent(enemy1CountEvent);
		
		
	
	
	}
}
