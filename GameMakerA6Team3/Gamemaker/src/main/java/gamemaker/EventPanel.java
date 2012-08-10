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
 * Class Name: EventPanel Class Responsibilities: Contains the event panel Class
 * collaborators: GameMaker.java
 */
public class EventPanel
{

	// / The event panel.
	private JPanel eventPanel;

	// The event list.
	private JList eventList;

	// The eventlist scroller.
	private JScrollPane eventlistScroller;

	// The model.
	DefaultListModel model;

	// The panelborder.
	Border panelborder = BorderFactory.createTitledBorder(Constants.EVENTPANEL_TITLE);

	public JList getEventList()
	{
		return eventList;
	}

	/**
	 * Constructor EventPanel: Instantiates a new event panel.
	 */
	public EventPanel()
	{
		this.eventPanel = new JPanel();

		this.model = new DefaultListModel();

		this.model.addElement(Constants.EVENT_TIMER);
		this.model.addElement(Constants.EVENT_KEY_PRESSED);
		this.model.addElement(Constants.EVENT_COLLISION);
		this.model.addElement(Constants.EVENT_GENERATE_AT_POSITION);
		this.model.addElement(Constants.EVENT_GENERATE_RANDOM);
		this.model.addElement(Constants.EVENT_COLLISION_WITH_EXCEPTION);
		this.model.addElement(Constants.EVENT_COUNT);
		
		this.eventList = new JList(this.model);

		this.eventList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.eventList.setLayoutOrientation(JList.VERTICAL);
		this.eventList.setVisibleRowCount(1);

		this.eventlistScroller = new JScrollPane(eventList);
		this.eventlistScroller.setPreferredSize(new Dimension(Constants.EVENTPANEL_LISTSCROLL_WIDTH, Constants.EVENTPANEL_LISTSCROLL_HEIGHT));

		this.eventPanel.setBackground(Color.white);
		this.eventPanel.setVisible(true);
		this.eventPanel.setBorder(panelborder);
		this.eventPanel.setLayout(null);
		this.eventPanel.setBounds(20, 20, 160, 75);
		this.eventlistScroller.setBounds(20, 20, 160, 75);
		this.eventPanel.add(this.eventlistScroller);
		this.eventPanel.setLayout(new GridLayout(1,1));

	}

	/* Method getEventPanel: Gets the event panel. */
	public JPanel getEventPanel()
	{
		return eventPanel;
	}

	/* Method setEventPanel Sets the event panel. */
	public void setEventPanel(JPanel eventPanel)
	{
		this.eventPanel = eventPanel;
	}

	/* Method getSelectedEvent: Gets the selected event. */
	public String getSelectedEvent()
	{
		return (String) this.eventList.getSelectedValue();
	}

	/* Method isSelectedEvent: Checks if is selected event. */
	public int isSelectedEvent()
	{

		return this.eventList.getSelectedIndex();

	}

	public void populateJList(String[] keyEventActionNames)
	{
		for (String oneActionName : keyEventActionNames)
		{
			this.model.addElement(oneActionName);
		}
	}

	public void removeAllElementsInEventList()
	{
		this.model.removeAllElements();
	}

}
