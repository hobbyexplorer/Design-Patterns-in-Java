package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.ScoreFillOutForm;
import gamemaker.actionform.ScoreForCollisionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Sprite;
import model.action.Action;
import model.action.ScoreForCollision;

public class ScoreForCollisionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	private Sprite selectedSprite;

	public ScoreForCollisionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new ScoreForCollisionFillOutForm();

		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Score  action fill out form controller called.");
		// ((TwoSpriteMoveAllActionFillOutForm)fillOutForm).
		// ActionController.getInstance().getActionObject(ActionController.getInstance().getSelectedAction());
		// GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
	}

	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(visibility);
	}

	public void makeThisLastForm()
	{
		fillOutForm.makeThisLastForm();
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

			int scorePoint = Integer.parseInt(((ScoreFillOutForm) this.fillOutForm).getScorePoint());
			actionToBeAdded = new ScoreForCollision(scorePoint);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();

			int scorePoint = Integer.parseInt(((ScoreForCollisionFillOutForm) this.fillOutForm).getScorePoint());
			actionToBeAdded = new ScoreForCollision(scorePoint);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}
}
