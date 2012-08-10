package controller;

import gamemaker.GameMaker;

import java.util.Iterator;
import java.util.Map;

/**
 * Class Name: VictoryEvent. Class responsibility: Contains the victory events
 * Class Collaborators:
 */
public class VictoryEvent
{

	// The hit top wall.
	boolean hitTopWall = false;

	// The hit bottom wall.
	boolean hitBottomWall = false;

	// The hit left wall.
	boolean hitLeftWall = false;

	// The hit right wall.
	boolean hitRightWall = false;

	// constructor VictoryEvent: Instantiates a new victory event.
	public VictoryEvent()
	{
		// TODO Auto-generated constructor stub
	}

	/* Method checkVictoryLose: Check victory lose. */
/*	public String checkVictoryLose()
	{

		if (GameMaker.gameObject != null && GameMaker.gameObject.getVictoryConditionMap().size() > 0)
		{

			Iterator itr = GameMaker.gameObject.getVictoryConditionMap().entrySet().iterator();
			while (itr.hasNext())
			{
				boolean isSatisfied = false;
				Map.Entry pairs = (Map.Entry) itr.next();
				String[] condition = ((String) pairs.getKey()).split(" - ");
				int checkValue = ((Integer) pairs.getValue()).intValue();
				if (condition[1].equalsIgnoreCase("Destroyed"))
				{
					isSatisfied = checkDestroyed(checkValue);
				}
				else if (condition[1].equalsIgnoreCase("Time is"))
				{
					isSatisfied = checkTime(checkValue);
				}
				else if (condition[1].equalsIgnoreCase("Collides wall"))
				{
					isSatisfied = checkWallCollision(checkValue);
				}
				if (isSatisfied)
				{
					if (condition[0].contains("Win"))
					{
						GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Game won.");
						return "Win";
					}
					else if (condition[0].contains("Lose"))
					{
						GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Game lost.");
						return "Lose";
					}
				}
			}
		}
		return "None";
	}

	 Method checkDestoryed: Check destroyed event. 
	public boolean checkDestroyed(int checkValue)
	{
		int countDestroyed = 0;
		for (int i = 0; i < GameMaker.spriteList.getObjectList().size(); i++)
		{
			if (!GameMaker.spriteList.getObjectList().get(i).isVisible())
			{
				countDestroyed++;
			}
		}
		if (countDestroyed >= checkValue)
		{
			return true;
		}
		return false;
	}

	 Method checkTime: Checks time. 
	public boolean checkTime(int checkValue)
	{
		if (GameMaker.timercount / 1000 >= checkValue)
		{
			return true;
		}
		return false;
	}

	 Method checkWallCollision: Check wall collision. 
	public boolean checkWallCollision(int checkValue)
	{
		if (checkValue == 1)
		{
			return hitTopWall;
		}
		else if (checkValue == 2)
		{
			return hitBottomWall;
		}
		else if (checkValue == 3)
		{
			return hitLeftWall;
		}
		else if (checkValue == 4)
		{
			return hitRightWall;
		}
		return false;
	}

	 Method isHitTopWall :Checks if is hit top wall. 
	public boolean isHitTopWall()
	{
		return hitTopWall;
	}

	 Method setHitTopWall : Sets the hit top wall. 
	public void setHitTopWall(boolean hitTopWall)
	{
		this.hitTopWall = hitTopWall;
	}

	 Method isHitBottomWall :Checks if is hit bottom wall. 
	public boolean isHitBottomWall()
	{
		return hitBottomWall;
	}

	 Method setHitBottomWall: Sets the hit bottom wall. 
	public void setHitBottomWall(boolean hitBottomWall)
	{
		this.hitBottomWall = hitBottomWall;
	}

	 Method isHitLeftWall: Checks if is hit left wall. 
	public boolean isHitLeftWall()
	{
		return hitLeftWall;
	}

	 Method setHitLeftWall: Sets the hit left wall. 
	public void setHitLeftWall(boolean hitLeftWall)
	{
		this.hitLeftWall = hitLeftWall;
	}

	 Method isHitRightWall : Checks if is hit right wall. 
	public boolean isHitRightWall()
	{
		return hitRightWall;
	}

	 Method setHitRightWall : Sets the hit right wall. 
	public void setHitRightWall(boolean hitRightWall)
	{
		this.hitRightWall = hitRightWall;
	}
*/
}
