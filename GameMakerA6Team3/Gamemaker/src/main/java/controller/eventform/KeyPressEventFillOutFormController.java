package controller.eventform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import model.Sprite;
import model.action.Action;
import model.event.Event;
import model.event.KeyPressedEvent;
import controller.GameCreatePanelController;
import controller.actionform.ActionFormsMasterController;
import controller.actionform.KeyEventFormInputListener;
import controller.eventaction.EventActionController;
import controller.rule.RuleController;
import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.eventform.EventFillOutForm;
import gamemaker.eventform.KeyPressEventFillOutForm;

public class KeyPressEventFillOutFormController implements ActionListener, EventFillOutFormController, MouseListener, KeyListener
{
	private EventFillOutForm fillOutForm;

	Event eventToBeAdded;

	ArrayList<String> listOfActionNames;

	ActionFormsMasterController actionFormsMasterController;

	KeyEventFormInputListener keyEventFormInputListener;

	int inputKeyCode;

	public KeyPressEventFillOutFormController()
	{
		actionFormsMasterController = new ActionFormsMasterController(this);
		listOfActionNames = RuleController.getInstance().getSelectedActionNames();
		parseSelectedActions();
		fillOutForm = new KeyPressEventFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - keypress event fillout form up and waiting for user input.");

		// Keypressevent form specific listener. This kind of listener is not
		// usual to all forms
		((KeyPressEventFillOutForm) fillOutForm).addMouseListenerToLabel(this);
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
			eventToBeAdded = new KeyPressedEvent(spriteToBeAdded, inputKeyCode);
			actionFormsMasterController.addEventToBeAdded(eventToBeAdded);
			actionFormsMasterController.showNextWindow();
			// add this event
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
	public void keyPressed(KeyEvent e)
	{
		keyEventFormInputListener.disposeWindow();
		String inputKey = Character.toString(e.getKeyChar());
		inputKeyCode = e.getKeyCode();
		if (inputKey.equals(" "))
		{
			inputKey = " Space bar";

		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			inputKey = " Right right";
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			inputKey = " Left arrow";
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			inputKey = " Up arrow";
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			inputKey = " Down arrow";
		}
		GameMaker.logger.logInfo(this.getClass().getName() + " - Able to listen to all the key events happening on the key event input form :" + inputKey);
		((KeyPressEventFillOutForm) fillOutForm).setKeyInputLabelText("<HTML>You selected " + inputKey + "<br> for your key event. <br>Click here again to change this<html>");
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		keyEventFormInputListener = new KeyEventFormInputListener();
		GameMaker.logger.logInfo(this.getClass().getName() + " - key press event form goes here.");
		keyEventFormInputListener.addKeyListener(this);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void addEventToEventList(Event eventToBeAdded)
	{
		GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Event added to the event list." + eventToBeAdded);
	}
}
