package gamemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

/**
 * Class Name: ActionPanel Class Responsibilities: Contains the panel displaying
 * various actions Class collaborators: GameMaker.java
 * 
 */
public class ActionPanel
{

	// The action panel.
	private JPanel actionPanel;

	// The action list.
	private JList actionList;

	// The actionlist scroller.
	private JScrollPane actionlistScroller;

	// The model.
	DefaultListModel model;

	// The panelborder.
	Border panelborder = BorderFactory.createTitledBorder(Constants.ACTIONPANEL_TITLE);

	public JList getActionList()
	{
		return actionList;
	}
	// Constructor ActionPanel : Instantiates a new action panel.

	public ActionPanel()
	{

		this.actionPanel = new JPanel();
		this.model = new DefaultListModel();
		this.model.addElement(Constants.ACTION_BOUNCE);
		this.model.addElement(Constants.ACTION_DESTROY);
		
		this.model.addElement(Constants.ACTION_MOVE_ALL);
		this.model.addElement(Constants.ACTION_MOVE);
		this.model.addElement(Constants.ACTION_MOVE_DOWN);
		this.model.addElement(Constants.ACTION_MOVE_LEFT);
		this.model.addElement(Constants.ACTION_MOVE_RANDOM);
		this.model.addElement(Constants.ACTION_MOVE_RIGHT);
		this.model.addElement(Constants.ACTION_MOVE_UP);
		this.model.addElement(Constants.ACTION_MOVE_ALONG);
		
		this.model.addElement(Constants.ACTION_SOUND);
		this.model.addElement(Constants.ACTION_REVOLVE);
		this.model.addElement(Constants.ACTION_ROTATE);
		this.model.addElement(Constants.ACTION_SHOOT);
		this.model.addElement(Constants.ACTION_SPLIT);
		
		this.model.addElement(Constants.ACTION_BLINK);
		this.model.addElement(Constants.ACTION_BOUNCE_ALL);
		this.model.addElement(Constants.ACTION_CONTRACT);
		this.model.addElement(Constants.ACTION_BOUNCE_VOID);
		this.model.addElement(Constants.ACTION_CHASE);
		// Removed functionality - needs work
		// this.model.addElement(Constants.ACTION_ROTATE_CLOCKWISE);
		// this.model.addElement(Constants.ACTION_ROTATE_ANTI_CLOCKWISE);

		this.actionList = new JList(this.model);
		this.actionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.actionList.setLayoutOrientation(JList.VERTICAL);
		this.actionList.setVisibleRowCount(1);

		this.actionlistScroller = new JScrollPane(actionList);
		this.actionlistScroller.setPreferredSize(new Dimension(Constants.ACTIONPANEL_LISTSCROLL_WIDTH, Constants.ACTIONPANEL_LISTSCROLL_HEIGHT));

		this.actionPanel.setBackground(Color.white);
		this.actionPanel.setVisible(true);
		this.actionPanel.setBorder(panelborder);
		this.actionPanel.setLayout(null);
		this.actionPanel.setBounds(20, 20, 160, 75);
		this.actionlistScroller.setBounds(20, 20, 160, 75);
		this.actionPanel.add(this.actionlistScroller);
		this.actionPanel.setLayout(new GridLayout(1,1));
	}

	/*
	 * Method ActionPanel: Gets the action panel.
	 */
	public JPanel getActionPanel()
	{
		return actionPanel;
	}

	/*
	 * Method SetActionPAnel: Sets the action panel.
	 */
	public void setActionPanel(JPanel actionPanel)
	{
		this.actionPanel = actionPanel;
	}

	/*
	 * MethodgetSelecyedAction : Gets the selected action.
	 */
	public String getSelectedAction()
	{
		return actionList.getSelectedValue().toString();
	}

	/*
	 * Method isSelectedAction: Checks if is selected action.
	 */
	public int isSelectedAction()
	{
		return this.actionList.getSelectedIndex();
	}

	public Object[] getSelectedActions()
	{
		return actionList.getSelectedValues();
	}

	public void populateJList(String[] keyEventActionNames)
	{
		for(String oneActionName : keyEventActionNames)
		{
			this.model.addElement(oneActionName);
		}
	}
}
