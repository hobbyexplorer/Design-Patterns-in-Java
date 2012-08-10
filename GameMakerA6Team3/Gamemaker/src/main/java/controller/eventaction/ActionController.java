package controller.eventaction;

import javax.swing.JList;

import org.apache.log4j.PropertyConfigurator;

import model.action.Action;
import gamemaker.ActionPanel;
import gamemaker.Constants;

public class ActionController
{
	private static ActionController instance;

	private ActionPanel actionPanel;

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionController.class);

	public static ActionController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new ActionController();
		return instance;
	}

	private ActionController()
	{
		PropertyConfigurator.configure(getClass().getResource("log4j/gamemaker_logger.properties"));

		this.actionPanel = new ActionPanel();
	}

	public ActionPanel getActionPanel()
	{
		return this.actionPanel;
	}
	
	public JList getActionList()
	{
		return actionPanel.getActionList();
	}

	public String getSelectedAction()
	{
		return actionPanel.getSelectedAction();
	}
	
	public Object[] getSelectedActions()
	{
		return actionPanel.getSelectedActions();
	}

	public Action getActionObject(String actionName)
	{
		if (actionName.equalsIgnoreCase(Constants.ACTION_BOUNCE))
		{
			LOG.info("BOUNCE action ");
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_DESTROY))
		{
			LOG.info("Destroy action ");

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE))
		{
			LOG.info("Move action ");

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_DOWN))
		{
			LOG.info("Move Down action ");

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_CHASE))
		{
			LOG.info("Chase action ");
		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_LEFT))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_RANDOM))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_RIGHT))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_MOVE_UP))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SOUND))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_REVOLVE))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_ROTATE))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SHOOT))
		{

		}
		else if (actionName.equalsIgnoreCase(Constants.ACTION_SPLIT))
		{

		}
		return null;

	}

	public void populateJList(String[] keyEventActionNames)
	{
		actionPanel.populateJList(keyEventActionNames);
	}
}
