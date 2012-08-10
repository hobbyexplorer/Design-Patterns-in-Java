package gamemaker.eventform;

import javax.swing.JPanel;

import controller.eventform.EventFillOutFormController;

public interface EventFillOutForm
{
	public void disposeFrame();

	public JPanel getFillOutForm();
	
	public void AddActionListenerToButtons(EventFillOutFormController eventFullOutFormController);
}
