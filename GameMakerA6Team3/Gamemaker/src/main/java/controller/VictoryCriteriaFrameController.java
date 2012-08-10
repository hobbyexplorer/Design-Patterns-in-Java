package controller;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.VictoryCriteriaFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Class Name: VictoryCriteriaFrameController Class responsibility: Contains the
 * victory criteria frame contents Class Collaborators:
 */
public class VictoryCriteriaFrameController implements ActionListener, ListSelectionListener
{

	private static VictoryCriteriaFrameController instance;
	
	
	// The victory criteria frame.
	private VictoryCriteriaFrame victoryCriteriaFrame;

	public static VictoryCriteriaFrameController getInstance()
	{
		if(instance!=null)
		{
			return instance;
		}
		instance = new VictoryCriteriaFrameController();
		return instance;
	}
	// Constructor VictoryCriteriaFrameController : Instantiates a new victory
	// criteria frame controller.
	public VictoryCriteriaFrameController()
	{
		this.victoryCriteriaFrame = new VictoryCriteriaFrame();
	//	getVictoryCriteriaFrame().winLoseList.addListSelectionListener(this.victoryCriteriaFrameController);
	//	getVictoryCriteriaFrame().eventTypeList.addListSelectionListener(this.victoryCriteriaFrameController);
//		getVictoryCriteriaFrame().addConditionButton.addActionListener(this.victoryCriteriaFrameController);
//		getVictoryCriteriaFrame().deleteConditionButton.addActionListener(this.victoryCriteriaFrameController);
//		getVictoryCriteriaFrame().doneButton.addActionListener(this.victoryCriteriaFrameController);
	
	}

	public VictoryCriteriaFrame getVictoryCriteriaFrame()
	{
		return victoryCriteriaFrame;
	}

	public void setVictoryCriteriaFrame(VictoryCriteriaFrame victoryCriteriaFrame)
	{
		this.victoryCriteriaFrame = victoryCriteriaFrame;
	}

	/* Method actionPerformed : */
	public void actionPerformed(ActionEvent actionEvent)
	{
/*
		if (actionEvent.getActionCommand().equals(Constants.VICTORYPANEL_ADD_BUTTON))
		{
			if (this.victoryCriteriaFrame.getWinLoseList().getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(this.victoryCriteriaFrame, "Please select if you are defining Win or Lose.");
				return;
			}
			if (this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(this.victoryCriteriaFrame, "Please select the conditions for win/lose.");
				return;
			}
			if (this.victoryCriteriaFrame.getConditionText().getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this.victoryCriteriaFrame, "Please enter the value for condition in the text box");
				return;
			}
			String winLose = (String) this.victoryCriteriaFrame.getWinLoseModel().getElementAt(this.victoryCriteriaFrame.getWinLoseList().getSelectedIndex());
			String eventType = (String) this.victoryCriteriaFrame.getEventTypeModel().getElementAt(this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex());
			String conditionValue = this.victoryCriteriaFrame.getConditionText().getText();
			if (GameMaker.gameObject.getVictoryConditionMap().get(winLose + " - " + eventType) == null)
			{
				this.victoryCriteriaFrame.getVictoryCriteriaModel().addElement(winLose + " - " + eventType + " - " + conditionValue);
			}
			GameMaker.gameObject.getVictoryConditionMap().put(winLose + " - " + eventType, new Integer(conditionValue));
			GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Added Victory condition.");
		}
		else if (actionEvent.getActionCommand().equals(Constants.VICTORYPANEL_DELETE_BUTTON))
		{
			String selectedValue = (String) this.victoryCriteriaFrame.getVictoryCriteriaList().getSelectedValue();
			String[] winloseEvent = selectedValue.split(" - ");
			if (GameMaker.gameObject.getVictoryConditionMap().get(winloseEvent[0] + " - " + winloseEvent[1]) != null)
				GameMaker.gameObject.getVictoryConditionMap().remove(winloseEvent[0] + " - " + winloseEvent[1]);
			this.victoryCriteriaFrame.getVictoryCriteriaModel().removeElement(this.victoryCriteriaFrame.getVictoryCriteriaList().getSelectedValue());
			GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Deleted victory condition.");
		}
		else if (actionEvent.getActionCommand().equals(Constants.VICTORYPANEL_DONE_BUTTON))
		{
			this.victoryCriteriaFrame.setVisible(false);
		}
*/	}

	/* Method valueChanged: value changed event */
	public void valueChanged(ListSelectionEvent e)
	{
		String dispMsg = "";
		if (this.victoryCriteriaFrame.getWinLoseList().getSelectedIndex() == 0)
			dispMsg = "You are defining victory if ";
		else if (this.victoryCriteriaFrame.getWinLoseList().getSelectedIndex() == 1)
			dispMsg = "You are defining defeat if ";
		if (this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex() == 0)
		{
			dispMsg += "Timer reaches ";
			this.victoryCriteriaFrame.getLblType().setText("Seconds");
			if (this.victoryCriteriaFrame.getConditionText().getText().isEmpty())
			{
				dispMsg += " - Enter number of second in the text box.";
			}
			else
			{
				dispMsg += " = " + this.victoryCriteriaFrame.getConditionText().getText();
			}
		}
		else if (this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex() == 1)
		{
			dispMsg += "Sprites destroyed ";
			this.victoryCriteriaFrame.getLblType().setText("Sprite objects");
			if (this.victoryCriteriaFrame.getConditionText().getText().isEmpty())
			{
				dispMsg += " - Enter number of sprites in the text box.";
			}
			else
			{
				dispMsg += " = " + this.victoryCriteriaFrame.getConditionText().getText();
			}
		}
		else if (this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex() == 2)
		{
			dispMsg += "Collision with wall ";
			this.victoryCriteriaFrame.getLblType().setText("<html>1-Top  2-Bottom <br></br>3-Left  4-Right</html>");
			if (this.victoryCriteriaFrame.getConditionText().getText().isEmpty())
			{
				dispMsg += " - Enter Wall number in the text box.";
			}
			else
			{
				dispMsg += " = " + this.victoryCriteriaFrame.getConditionText().getText();
			}
		}
		else if (this.victoryCriteriaFrame.getEventTypeList().getSelectedIndex() == -1)
		{
			dispMsg += "- Choose event type ";
			this.victoryCriteriaFrame.getLblType().setText("");
		}
		this.victoryCriteriaFrame.getDisplayMsg().setText("<html><font color=red><p>" + dispMsg + "</p></font></html>");
	}

}
