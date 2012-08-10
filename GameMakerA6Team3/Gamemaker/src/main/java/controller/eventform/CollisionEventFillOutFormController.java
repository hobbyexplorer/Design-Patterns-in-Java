package controller.eventform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.eventform.CollisionEventFillOutForm;
import gamemaker.eventform.EventFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Sprite;
import model.action.Action;
import model.event.CollisionEvent;
import model.event.Event;
import model.event.TimerEvent;
import controller.GameCreatePanelController;
import controller.actionform.ActionFormsMasterController;
import controller.eventaction.EventActionController;
import controller.rule.RuleController;

public class CollisionEventFillOutFormController implements EventFillOutFormController, ActionListener
{
	private EventFillOutForm fillOutForm;

	Event eventToBeAdded;

	ArrayList<String> listOfActionNames;

	ActionFormsMasterController actionFormsMasterController;

	int inputKeyCode;

	public CollisionEventFillOutFormController()
	{
		actionFormsMasterController = new ActionFormsMasterController(this);
		listOfActionNames = RuleController.getInstance().getSelectedActionNames();
		parseSelectedActions();
		fillOutForm = new CollisionEventFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - collision event fillout form up and waiting for user input.");
	}

	private void parseSelectedActions()
	{
		for (String oneActionName : listOfActionNames)
		{
			actionFormsMasterController.addActionController(oneActionName);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Constants.CANCEL_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
		}
		else if (Constants.NEXT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToBeAdded = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());

			ArrayList<String> selectedSprites = RuleController.getInstance().getSelectedSprites();
			Sprite spriteOne = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(selectedSprites.get(0));
			Sprite spriteTwo = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(selectedSprites.get(1));

			eventToBeAdded = new CollisionEvent(spriteOne, spriteTwo);
			actionFormsMasterController.addEventToBeAdded(eventToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
	}

	public void addAction(Action action)
	{
		eventToBeAdded.addAction(action);
	}

	public EventFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

	@Override
	public void addCombinationToEventActionList()
	{
		EventActionController.getInstance().getEventDisplayPanel().addToEventDisplay(RuleController.getInstance().getCurrentCombination());
		eventToBeAdded.setEventString(RuleController.getInstance().getCurrentCombination());
	}

	@Override
	public void addEventToEventList(Event eventToBeAdded)
	{
		GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
	}
}
