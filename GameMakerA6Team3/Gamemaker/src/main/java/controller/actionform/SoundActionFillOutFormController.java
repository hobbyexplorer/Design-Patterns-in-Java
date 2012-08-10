package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveActionFillOutForm;
import gamemaker.actionform.SoundActionFillOutForm;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import model.Sprite;
import model.action.Move;
import model.action.PlaySound;
import controller.GameCreatePanelController;
import controller.rule.RuleController;

public class SoundActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;
	
	private model.action.Action actionToBeAdded;
	
	ActionFormsMasterController actionFormsMasterController;
	
	public SoundActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new SoundActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName()+" - Sound action fillout form controller called");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int ret = -1;
		if (Constants.CANCEL_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
		}
		else if (Constants.NEXT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			((SoundActionFillOutForm)fillOutForm).populateTextField();
			if(((SoundActionFillOutForm) fillOutForm).isTextFieldFilled()){
				fillOutForm.disposeFrame();
				actionToBeAdded = new PlaySound(((SoundActionFillOutForm) fillOutForm).getFileName());
				actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
				actionFormsMasterController.submitForm();
			}
			else ((SoundActionFillOutForm) fillOutForm).populateTextField("");
			/*fillOutForm.disposeFrame();
			//Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			// Parse the slider text and get the angle value. By default it will
			// be 90;
			//int angle = Integer.parseInt(((MoveActionFillOutForm) this.fillOutForm).getAngleText());
			actionToBeAdded = new PlaySound(((SoundActionFillOutForm) fillOutForm).getFileName());
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();*/
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			((SoundActionFillOutForm)fillOutForm).populateTextField();
			if(((SoundActionFillOutForm) fillOutForm).isTextFieldFilled()){
				fillOutForm.disposeFrame();
				actionToBeAdded = new PlaySound(((SoundActionFillOutForm) fillOutForm).getFileName());
				actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
				actionFormsMasterController.submitForm();
			}
			else ((SoundActionFillOutForm) fillOutForm).populateTextField("");
		}	
		else if (Constants.BROWSE_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			ret = ((SoundActionFillOutForm)fillOutForm).showFileChooser();			
		}
		else if (Constants.PLAY_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			((SoundActionFillOutForm)fillOutForm).playSample();
			
		}
		if(ret == JFileChooser.APPROVE_OPTION){
			((SoundActionFillOutForm)fillOutForm).populateTextField();
		}
	}

	@Override
	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(true);
	}

	@Override
	public void makeThisLastForm()
	{
		fillOutForm.makeThisLastForm();
	}

}
