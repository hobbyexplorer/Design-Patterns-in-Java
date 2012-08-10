package gamemaker.actionform;

import javax.swing.JPanel;
import controller.actionform.ActionFillOutFormController;

public interface ActionFillOutForm
{
	public void disposeFrame();

	public JPanel getFillOutForm();

	public void AddActionListenerToButtons(ActionFillOutFormController actionFillOutFormController);
	
	public void setVisible(boolean visibility);
	
	public void makeThisLastForm();
}