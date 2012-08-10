package controller;

import gamemaker.Constants;
import gamemaker.GameMaker;

/**
 * The Class CheckCollision. Responsibilities : Checks the collision events of
 * the ball Class Collaborators : Public API:
 */
public class CheckCollision
{
	// The game board width.
	private int gameBoardWidth;

	// The game board height.
	private int gameBoardHeight;

	// Constructor CheckCollision: Instantiates a new check collision.
	public CheckCollision(int width, int height)
	{
		gameBoardWidth = width;
		gameBoardHeight = height;
	}

	// constructor CheckCollision: Check sprite collision.
/*	public void checkSpriteCollision(SpriteObject sprite)
	{
		if (sprite.getEventVector().contains(Constants.EVENT_COLLISION))
		{
			String spriteName = sprite.getSpriteName();
			for (int i = 0; i < GameMaker.spriteList.getObjectList().size(); i++)
			{
				if (!spriteName.equals(GameMaker.spriteList.getObjectList().get(i).getSpriteName()) && GameMaker.spriteList.getObjectList().get(i).isVisible())
				{
					SpriteObject checkSprite = GameMaker.spriteList.getObjectList().get(i);
					if (sprite.getSpriteX() <= checkSprite.getSpriteX() + checkSprite.getSpriteWidth() && sprite.getSpriteX() > checkSprite.getSpriteX() + checkSprite.getSpriteWidth() - sprite.getSpriteWidth() && sprite.getSpriteY() <= checkSprite.getSpriteY() + checkSprite.getSpriteHeight() && sprite.getSpriteY() > checkSprite.getSpriteY())
					{

						sprite.setSpriteX(checkSprite.getSpriteX() + checkSprite.getSpriteWidth());
						callAction(sprite, Constants.LEFT_RIGHT_COLLISION);
						callSecondSpriteAction(checkSprite);
					}

					// When the ball collides with the left-edge of the paddle
					else if (sprite.getSpriteX() + sprite.getSpriteWidth() >= checkSprite.getSpriteX() && sprite.getSpriteX() < checkSprite.getSpriteX() && sprite.getSpriteY() < checkSprite.getSpriteY() + checkSprite.getSpriteHeight() && sprite.getSpriteY() >= checkSprite.getSpriteY())
					{
						sprite.setSpriteX(checkSprite.getSpriteX() - sprite.getSpriteWidth());
						callAction(sprite, Constants.LEFT_RIGHT_COLLISION);
						callSecondSpriteAction(checkSprite);
					}

					// When the ball collides with the top-edge of the paddle
					else if (sprite.getSpriteY() + sprite.getSpriteWidth() >= checkSprite.getSpriteY() && sprite.getSpriteY() < checkSprite.getSpriteY() && sprite.getSpriteX() < checkSprite.getSpriteX() + checkSprite.getSpriteWidth() && sprite.getSpriteX() >= checkSprite.getSpriteX())
					{
						sprite.setSpriteY(checkSprite.getSpriteY() - sprite.getSpriteHeight());
						callAction(sprite, Constants.UP_DOWN_COLLISION);
						callSecondSpriteAction(checkSprite);
					}

					// When the ball collides with the bottom-edge of the paddle
					else if (sprite.getSpriteY() <= checkSprite.getSpriteY() + checkSprite.getSpriteHeight() && sprite.getSpriteY() > checkSprite.getSpriteY() + checkSprite.getSpriteHeight() - sprite.getSpriteHeight() && sprite.getSpriteX() < checkSprite.getSpriteX() + checkSprite.getSpriteWidth() && sprite.getSpriteX() >= checkSprite.getSpriteX())
					{
						sprite.setSpriteY(checkSprite.getSpriteY() + checkSprite.getSpriteHeight());
						callAction(sprite, Constants.UP_DOWN_COLLISION);
						callSecondSpriteAction(checkSprite);
					}

				}
			}
		}
	}

	 Method checkWallCollision: Checks wall collision. 
	public void checkWallCollision(SpriteObject sprite)
	{
		// check collision with right side Wall
		if (sprite.getSpriteX() + sprite.getSpriteWidth() >= gameBoardWidth)
		{
			sprite.setSpriteX(gameBoardWidth - sprite.getSpriteWidth());
			sprite.setSpriteXSpeed(sprite.getSpriteXSpeed() * (-1));
			GameMaker.victoryEvent.setHitRightWall(true);
		}
		// check collision with left side Wall
		if (sprite.getSpriteX() <= 0)
		{
			sprite.setSpriteX(0);
			sprite.setSpriteXSpeed(sprite.getSpriteXSpeed() * (-1));
			GameMaker.victoryEvent.setHitLeftWall(true);
		}
		// check collision with top Wall
		if (sprite.getSpriteY() <= 0)
		{
			sprite.setSpriteY(0);
			sprite.setSpriteYSpeed(sprite.getSpriteYSpeed() * (-1));
			GameMaker.victoryEvent.setHitTopWall(true);
		}
		// check collision with bottom Wall
		if ((sprite.getSpriteY() + sprite.getSpriteHeight() >= gameBoardHeight))
		{
			sprite.setSpriteY(gameBoardHeight - sprite.getSpriteHeight());
			sprite.setSpriteYSpeed(sprite.getSpriteYSpeed() * (-1));
			GameMaker.victoryEvent.setHitBottomWall(true);
		}

	}

	 Method checkWallCollisionOnKey: Check wall collision on key event. 
	public void checkWallCollisionOnKey(SpriteObject sprite)
	{
		// check collision with right wall
		if (sprite.getSpriteX() + sprite.getSpriteWidth() >= gameBoardWidth)
		{
			sprite.setSpriteX(gameBoardWidth - sprite.getSpriteWidth());
		}
		// check collision with left wall
		if (sprite.getSpriteX() <= 0)
		{
			sprite.setSpriteX(0);
		}
		// check collision with top wall
		if (sprite.getSpriteY() <= 0)
		{
			sprite.setSpriteY(0);
		}
		// check wall collision with bottom wall
		if ((sprite.getSpriteY() + sprite.getSpriteHeight() >= gameBoardHeight))
		{
			sprite.setSpriteY(gameBoardHeight - sprite.getSpriteHeight());
		}

	}

	
	 * Method callAction : Call relevant action when collision occurs for
	 * colliding sprite.
	 
	public void callAction(SpriteObject sprite, String collision)
	{
		// call bounce actionExecute and moveExecute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_BOUNCE))
		{
			BounceAction bounceSprite = new BounceAction(collision);
			bounceSprite.actionExecute(sprite);
			MoveAction moveSprite = new MoveAction(Constants.EVENT_TIMER);
			moveSprite.actionExecute(sprite);
		}
		// call destroy action execute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_DESTROY))
		{
			DestroyAction destroy = new DestroyAction();
			destroy.actionExecute(sprite);
		}
		// call sound action execute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_SOUND))
		{
			SoundAction sound = new SoundAction(sprite.getSoundFileToEventMap().get(Constants.EVENT_COLLISION));
			sound.actionExecute(sprite);
		}
		GameMaker.logger.logInfo(this.getClass().getName() + " - Collided Sprite action called.");
	}

	
	 * Method callSecondSpriteAction: Call sprite action for the collided
	 * sprite.
	 
	public void callSecondSpriteAction(SpriteObject sprite)
	{
		// call bounce action execute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_BOUNCE))
		{
			BounceAction bounceSprite = new BounceAction(Constants.DEFAULT_COLLISION);
			bounceSprite.actionExecute(sprite);
		}
		// call destroy action execute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_DESTROY))
		{
			DestroyAction destroy = new DestroyAction();
			destroy.actionExecute(sprite);
		}
		// call sound action execute method
		if (sprite.getEventActionVector().contains(Constants.EVENT_COLLISION + Constants.SPACE + Constants.ACTION_SOUND))
		{
			SoundAction sound = new SoundAction(sprite.getSoundFileToEventMap().get(Constants.EVENT_COLLISION));
			sound.actionExecute(sprite);
		}
		GameMaker.logger.logInfo(this.getClass().getName() + " - Collided secondary sprite action called.");
	}
*/}
