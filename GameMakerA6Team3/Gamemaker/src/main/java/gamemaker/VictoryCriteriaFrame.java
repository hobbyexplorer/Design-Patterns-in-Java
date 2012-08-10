package gamemaker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.VictoryCriteriaFrameController;

/**
 * Class Name: VictoryCriteriaFrame Class Responsibilities: contains the frame
 * where one can set the victory condition Class collaborators: GameMaker.java
 * 
 */
public class VictoryCriteriaFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	// The create victory combination panel.
	private JPanel createVictoryCombinationPanel;

	// The victory combination panel.
	private JPanel victoryCombinationPanel;

	// The win lose list.
	private JList winLoseList;

	// The event type list.
	private JList eventTypeList;

	// The victory criteria list.
	private JList victoryCriteriaList;

	// The win lose list scroller.
	private JScrollPane winLoseListScroller;

	// The event type list scroller.
	private JScrollPane eventTypeListScroller;

	// The victory criteria list scroller.
	private JScrollPane victoryCriteriaListScroller;

	// The condition text.
	private JTextField conditionText;

	// The win lose model.
	private DefaultListModel winLoseModel;

	// The event type model.
	private DefaultListModel eventTypeModel;

	// The victory criteria model.
	private DefaultListModel victoryCriteriaModel;

	// The add condition button.
	private JButton addConditionButton;

	// The delete condition button.
	private JButton deleteConditionButton;

	// The done button.
	private JButton doneButton;

	// The lbl type.
	private JLabel lblType;

	// The display msg.
	private JLabel displayMsg;


	// Constructor VictoryCriteriaFrame: Instantiates a new victory criteria
	// frame.
	public VictoryCriteriaFrame()
	{

		this.setVisible(false);
		this.setLayout(new FlowLayout());
		this.createVictoryCombinationPanel = new JPanel();
		this.createVictoryCombinationPanel.setBorder(BorderFactory.createTitledBorder(Constants.CREATEVICTORYCOMBO_PANEL_TITLE));
		this.victoryCombinationPanel = new JPanel();
		this.victoryCombinationPanel.setBorder(BorderFactory.createTitledBorder(Constants.VICTORYCOMBO_PANEL_TITLE));

		this.winLoseModel = new DefaultListModel();
		this.winLoseModel.addElement(Constants.VICTORYPANEL_WIN_IF_ELEMENT);
		this.winLoseModel.addElement(Constants.VICTORYPANEL_LOOSE_IF_ELEMENT);
		this.winLoseList = new JList(this.winLoseModel);
		this.eventTypeModel = new DefaultListModel();
		this.eventTypeModel.addElement(Constants.VICTORYPANEL_TIME_IF_ELEMENT);
		this.eventTypeModel.addElement(Constants.VICTORYPANEL_DESTROYED_IF_ELEMENT);
		this.eventTypeModel.addElement(Constants.VICTORYPANEL_WALL_COLLIDES_IF_ELEMENT);
		this.eventTypeList = new JList(this.eventTypeModel);
		this.conditionText = new JTextField("", 2);
		this.conditionText.setPreferredSize(new Dimension(Constants.VICTORYPANEL_CONDITION_TEXT_WIDTH, Constants.VICTORYPANEL_CONDITION_TEXT_HEIGHT));
		this.lblType = new JLabel("");


		this.winLoseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.winLoseList.setLayoutOrientation(JList.VERTICAL);
		this.winLoseList.setVisibleRowCount(2);
//		this.winLoseList.addListSelectionListener(this.victoryCriteriaFrameController);
		this.winLoseListScroller = new JScrollPane(winLoseList);
		this.winLoseListScroller.setPreferredSize(new Dimension(Constants.WINLOSELIST_SCROLL_WIDTH, Constants.WINLOSELIST_SCROLL_HEIGHT));
		this.createVictoryCombinationPanel.add(this.winLoseListScroller);

		this.eventTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.eventTypeList.setLayoutOrientation(JList.VERTICAL);
		this.eventTypeList.setVisibleRowCount(2);
//		this.eventTypeList.addListSelectionListener(this.victoryCriteriaFrameController);
		this.eventTypeListScroller = new JScrollPane(eventTypeList);
		this.eventTypeListScroller.setPreferredSize(new Dimension(Constants.EVENTTYPELIST_SCROLL_WIDTH, Constants.EVENTTYPELIST_SCROLL_HEIGHT));
		this.createVictoryCombinationPanel.add(this.eventTypeListScroller);

		this.createVictoryCombinationPanel.add(this.conditionText);
		this.createVictoryCombinationPanel.add(this.lblType);

		this.add(this.createVictoryCombinationPanel);

		this.displayMsg = new JLabel("<html><font color=red><p>Select a victory/defeat condition for your game</p></font></html>");

		this.displayMsg.setPreferredSize(new Dimension(Constants.VICTORYPANEL_MESSAGE_TEXT_WIDTH, Constants.VICTORYPANEL_MESSAGE_TEXT_HEIGHT));
		this.add(this.displayMsg);

		this.victoryCriteriaModel = new DefaultListModel();

		this.victoryCriteriaList = new JList(this.victoryCriteriaModel);
		this.victoryCriteriaListScroller = new JScrollPane(this.victoryCriteriaList);
		this.victoryCriteriaListScroller.setPreferredSize(new Dimension(Constants.VICTORYLIST_SCROLL_WIDTH, Constants.VICTORYLIST_SCROLL_HEIGHT));
		this.victoryCriteriaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.victoryCriteriaList.setLayoutOrientation(JList.VERTICAL);
		this.victoryCriteriaList.setVisibleRowCount(4);
		this.victoryCombinationPanel.add(this.victoryCriteriaListScroller);

		this.add(this.victoryCombinationPanel);
		createButtons();
		addButtons();
		addActionListenersToButtons();
	}

	/* Method createButtons: Creates the buttons. */
	public void createButtons()
	{
		this.addConditionButton = new JButton(Constants.VICTORYPANEL_ADD_BUTTON);
		this.deleteConditionButton = new JButton(Constants.VICTORYPANEL_DELETE_BUTTON);
		this.doneButton = new JButton(Constants.VICTORYPANEL_DONE_BUTTON);
	}

	/*
	 * Method addButtons: Adds the buttons.
	 */
	public void addButtons()
	{
		this.add(this.addConditionButton);
		this.add(this.deleteConditionButton);
		this.add(this.doneButton);
	}

	/*
	 * Method addActionListenersToButtons: Adds the action listeners to buttons.
	 */
	public void addActionListenersToButtons()
	{
	/*	this.addConditionButton.addActionListener(this.victoryCriteriaFrameController);
		this.deleteConditionButton.addActionListener(this.victoryCriteriaFrameController);
		this.doneButton.addActionListener(this.victoryCriteriaFrameController);
	*/}

	/*
	 * Method loadVictoryConditions: Load victory conditions.
	 */
	public void loadVictoryConditions()
	{
/*		this.victoryCriteriaModel.clear();
		if (GameMaker.gameObject != null && GameMaker.gameObject.getVictoryConditionMap().size() > 0)
		{
			Iterator itr = GameMaker.gameObject.getVictoryConditionMap().entrySet().iterator();
			while (itr.hasNext())
			{
				Map.Entry pairs = (Map.Entry) itr.next();
				this.victoryCriteriaModel.addElement((String) pairs.getKey() + " - " + ((Integer) pairs.getValue()).toString());
			}
		}
*/	}

	/* Method getVictoryCriteriaModel: Gets the victory criteria model. */
	public DefaultListModel getVictoryCriteriaModel()
	{
		return victoryCriteriaModel;
	}

	/* Method setVictoryCriteriaModel: Sets the victory criteria model. */
	public void setVictoryCriteriaModel(DefaultListModel victoryCriteriaModel)
	{
		this.victoryCriteriaModel = victoryCriteriaModel;
	}

	/* Method getWinLoseList: Gets the win lose list. */
	public JList getWinLoseList()
	{
		return winLoseList;
	}

	/* Method setWinLoseList: Sets the win lose list. */
	public void setWinLoseList(JList winLoseList)
	{
		this.winLoseList = winLoseList;
	}

	/* Method getEventTypeList: Gets the event type list. */
	public JList getEventTypeList()
	{
		return eventTypeList;
	}

	/* Method setEventTypeList: Sets the event type list. */
	public void setEventTypeList(JList eventTypeList)
	{
		this.eventTypeList = eventTypeList;
	}

	/* Method getVictoryCriteriaList: Gets the victory criteria list. */
	public JList getVictoryCriteriaList()
	{
		return victoryCriteriaList;
	}

	/* Method setVictoryCriteriaList: Sets the victory criteria list. */
	public void setVictoryCriteriaList(JList victoryCriteriaList)
	{
		this.victoryCriteriaList = victoryCriteriaList;
	}

	/* Method getDisplayMsg: Gets the display message. */
	public JLabel getDisplayMsg()
	{
		return displayMsg;
	}

	/* Method setDisplayMsg : Sets the display message. */
	public void setDisplayMsg(JLabel displayMsg)
	{
		this.displayMsg = displayMsg;
	}

	/* Method getConditionText: Gets the condition text. */
	public JTextField getConditionText()
	{
		return conditionText;
	}

	/* Method setConditionText: Sets the condition text. */
	public void setConditionText(JTextField conditionText)
	{
		this.conditionText = conditionText;
	}

	/* Method getWinLoseModel: Gets the win lose model. */
	public DefaultListModel getWinLoseModel()
	{
		return winLoseModel;
	}

	/* Method setWinLoseModel: Sets the win lose model. */
	public void setWinLoseModel(DefaultListModel winLoseModel)
	{
		this.winLoseModel = winLoseModel;
	}

	/* Method getEventTypeModel: Gets the event type model. */
	public DefaultListModel getEventTypeModel()
	{
		return eventTypeModel;
	}

	/* Method setEventTypeModel: Sets the event type model. */
	public void setEventTypeModel(DefaultListModel eventTypeModel)
	{
		this.eventTypeModel = eventTypeModel;
	}

	/* Method getLblType: Gets the label type. */
	public JLabel getLblType()
	{
		return lblType;
	}

	/* Method setLblType: Sets the label type. */
	public void setLblType(JLabel lblType)
	{
		this.lblType = lblType;
	}

}
