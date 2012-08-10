package controller.actionform;

import java.util.ArrayList;

import model.action.Action;
import model.event.Event;

import org.apache.log4j.PropertyConfigurator;

import controller.eventform.CollisionEventFillOutFormController;
import controller.eventform.CollisionExEventFillOutFormController;
import controller.eventform.CountEventFillOutFormController;
import controller.eventform.EventFillOutFormController;
import controller.eventform.TimerEventFillOutFormController;
import gamemaker.Constants;

public class ActionFormsMasterController
{
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionFormsMasterController.class);
	private int currentFrameId = -1;
	private ArrayList<ActionFillOutFormController> actionWindowSequence;
	private EventFillOutFormController eventFillOutFormController;

	private Event eventToBeAdded;

	public void addEventToBeAdded(Event eventToBeAdded)
	{
		this.eventToBeAdded = eventToBeAdded;
	}

	public EventFillOutFormController getEventFillOutFormController()
	{
		return eventFillOutFormController;
	}

	public void addActionController(String actionName)
	{
		actionWindowSequence.add(getActionFillOutFormController(actionName));
	}

	public ActionFormsMasterController(EventFillOutFormController eventFillOutFormController)
	{
		actionWindowSequence = new ArrayList<ActionFillOutFormController>();
		this.eventFillOutFormController = eventFillOutFormController;
		PropertyConfigurator.configure(getClass().getResource("../../log4j/gamemaker_logger.properties"));
	}

	public void showNextWindow()
	{
		int nextId = getNextFrameId();
		ActionFillOutFormController nextWindow = actionWindowSequence.get(nextId);
		if (nextId == actionWindowSequence.size() - 1)
		{
			nextWindow.makeThisLastForm();
		}
		nextWindow.setVisible(true);
	}

	public void showPreviousWindow()
	{
		actionWindowSequence.get(getPreviousFrameId());
	}

	public ActionFillOutFormController getActionFillOutFormController(String actionName)
	{
		if (actionName.equalsIgnoreCase(Constants.ACTION_BOUNCE))
		{
			return new BounceActionFillOutFormController(this);
		}
		if (actionName.equalsIgnoreCase(Constants.ACTION_BOUNCE_VOID))
		{
			return new BounceVoidActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_DESTROY))
		{
			return new DestroyActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE))
		{
			LOG.info("Move action ");
			return new MoveActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_DOWN))
		{
			LOG.info("Move Down action ");
			return new MoveDownActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_LEFT))
		{
			LOG.info("Move Left action ");
			return new MoveLeftActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_ALONG))
		{
			LOG.info("Move Along action ");
			return new MoveAlongActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_ALL))
		{
			// If the Event is any of collision event return a two sprite move
			// action fill out form. Else return a one sprite fill out form.
			LOG.info("Move All action ");
			if (eventFillOutFormController instanceof CollisionEventFillOutFormController || eventFillOutFormController instanceof CollisionExEventFillOutFormController)
			{
				return new MoveAllActionFillOutFormController(this);
			}
			else
			{
				return new MoveAllActionFillOutFormController(this);
			}
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_RANDOM))
		{
			LOG.info("Move Random action ");
			return new MoveRandomActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_RIGHT))
		{
			LOG.info("Move Right action ");
			return new MoveRightActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_UP))
		{
			LOG.info("Move Up action ");
			return new MoveUpActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SOUND))
		{
			LOG.info("Sound action");
			return new SoundActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_REVOLVE))
		{
			LOG.info("Revolve action");
			return new RevolveActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_ROTATE))
		{
			LOG.info("Rotate action ");
			return new RotateActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SHOOT))
		{
			LOG.info("Shoot action ");
			return new ShootActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SPLIT))
		{
			LOG.info("Split action ");
			return new SplitActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_BLINK))
		{
			LOG.info("Blink action ");
			return new BlinkActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_BLINK_RANDOM))
		{
			LOG.info("Blink Random action ");
			return new BlinkAtRandomActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_BOUNCE_ALL))
		{
			LOG.info("Bounce All action ");
			return new BounceAllActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SET_GAME_TIME))
		{
			LOG.info("Set Game Time action ");
			return new SetGameTimeActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_GAME_OVER))
		{
			LOG.info("Game over action ");
			if (eventFillOutFormController instanceof CollisionEventFillOutFormController || eventFillOutFormController instanceof CollisionExEventFillOutFormController)
			{
				return new GameOverActionOnCollisionFillOutFormController(this);
			}
			else if (eventFillOutFormController instanceof CountEventFillOutFormController)
			{
				return new GameOverActionOnCountFillOutFormController(this);
			}
		}

		else if (actionName.equalsIgnoreCase(Constants.ACTION_EXPAND))
		{
			LOG.info(" Expand action ");
			return new ExpandActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_CONTRACT))
		{
			LOG.info(" Contract action ");
			return new ContractActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_CHASE))
		{
			LOG.info(" Chase action ");
			return new ChaseActionFillOutFormController(this);
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SCORE))
		{
			LOG.info(" Score action ");
			if (eventFillOutFormController instanceof TimerEventFillOutFormController || eventFillOutFormController instanceof CollisionEventFillOutFormController || eventFillOutFormController instanceof CollisionEventFillOutFormController)
			{
				return new ScoreForCollisionFillOutFormController(this);
			}
			else
			{
				return new ScoreFillOutformController(this);
			}
		}
		return null;
	}

	public void submitForm()
	{
		eventFillOutFormController.addEventToEventList(this.eventToBeAdded);
		eventFillOutFormController.addCombinationToEventActionList();
	}

	private int getNextFrameId()
	{
		currentFrameId++;
		return currentFrameId;
	}

	private int getPreviousFrameId()
	{
		currentFrameId--;
		return currentFrameId;
	}

	private void resetWindowCounter()
	{
		currentFrameId = -1;
	}

	public void addActionToCurrentEvent(Action actionToBeAdded)
	{
		this.eventToBeAdded.addAction(actionToBeAdded);
	}
}
