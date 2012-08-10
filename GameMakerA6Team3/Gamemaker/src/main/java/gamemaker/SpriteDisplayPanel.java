package gamemaker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


/**
 * Class Name: SpriteDisplayPanel Class Responsibilities: Contains the contents
 * of the sprite display Class collaborators: GameMaker.java
 * 
 */
public class SpriteDisplayPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5850847057100140574L;

	// The create sprite button
	private JButton createSpriteButton;
	
	// The remove sprite button
	private JButton deleteSpriteButton;
	
	// The sprite display panel. */
	private JPanel spriteDisplayPanel;

	// The sprite list. */
	private JList spriteList;

	// The model. */
	private DefaultListModel model;

	// The sprite display scroll. */
	private JScrollPane spriteDisplayScroll;

	// Constructor SpriteDisplayPanel: Instantiates a new sprite display panel.
	public SpriteDisplayPanel()
	{
		//this.setBounds(x, y, width, height)
		this.spriteDisplayPanel = new JPanel();
		// spriteDisplayPanel = new SpriteDisplayPanel();
		this.model = new DefaultListModel();
		
		this.spriteList = new JList(this.model);
		this.spriteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.spriteDisplayScroll = new JScrollPane(this.spriteList);
		//this.spriteDisplayScroll.setPreferredSize(new Dimension(Constants.SPRITEDISPLAY_SCROLL_WIDTH, Constants.SPRITEDISPLAY_SCROLL_HEIGHT));

		getSpriteDisplayPanel().setBorder(BorderFactory.createTitledBorder(Constants.SPRITEDISPLAY_PANEL_TITLE));
		getSpriteDisplayPanel().setLayout(new FlowLayout());
		getSpriteDisplayPanel().setVisible(true);

		this.spriteList.setLayoutOrientation(JList.VERTICAL);
		this.spriteList.setVisibleRowCount(1);
		this.spriteDisplayScroll.setBounds(20, 23, 190, 145);
		this.spriteDisplayPanel.setBackground(Color.white);
		this.spriteDisplayPanel.add(spriteDisplayScroll);
		
		createSpriteButton = new JButton(Constants.CREATE_SPRITE_BUTTON);

		deleteSpriteButton = new JButton(Constants.DELETE_SPRITE_BUTTON);
		
		this.spriteDisplayPanel.setLayout(null);
		this.createSpriteButton.setBounds(10, 180, 100, 20);
		this.deleteSpriteButton.setBounds(110, 180, 110, 20);
		JPanel createdeleteSpriteButtonPanel = new JPanel();
		createdeleteSpriteButtonPanel.add(createSpriteButton);
		createdeleteSpriteButtonPanel.add(deleteSpriteButton);
		createdeleteSpriteButtonPanel.setLayout(new GridLayout(1,2));
		spriteDisplayPanel.add(createdeleteSpriteButtonPanel);
		spriteDisplayPanel.setLayout(new GridLayout(2,1));
		//this.spriteDisplayPanel.add(createSpriteButton);
		//this.spriteDisplayPanel.add(deleteSpriteButton);
		
		// this.spriteDisplayPanelController = new
		// SpriteDisplayPanelController(this.spriteList);
		// this.spriteList.addListSelectionListener(this.spriteDisplayPanelController);
	}

	public JButton getCreateSpriteButton()
	{
		return createSpriteButton;
	}

	public void setCreateSpriteButton(JButton createSpriteButton)
	{
		this.createSpriteButton = createSpriteButton;
	}

	public JButton getDeleteSpriteButton()
	{
		return deleteSpriteButton;
	}

	public void setDeleteSpriteButton(JButton deleteSpriteButton)
	{
		this.deleteSpriteButton = deleteSpriteButton;
	}
	

	/* Method getSpriteList : Gets the sprite list. */
	public JList getSpriteList()
	{
		return spriteList;
	}

	/* Method setSpriteList: Sets the sprite list. */
	public void setSpriteList(JList spriteList)
	{
		this.spriteList = spriteList;
	}

	/* Method getModel: Gets the model. */
	public DefaultListModel getModel()
	{
		return model;
	}

	/* Method setModel: Sets the model. */
	public void setModel(DefaultListModel model)
	{
		this.model = model;
	}

	/* Method getSpriteDisplayScroll: Gets the sprite display scroll. */
	public JScrollPane getSpriteDisplayScroll()
	{
		return spriteDisplayScroll;
	}

	/* Method setSpriteDisplayScroll : Sets the sprite display scroll. */
	public void setSpriteDisplayScroll(JScrollPane spriteDisplayScroll)
	{
		this.spriteDisplayScroll = spriteDisplayScroll;
	}

	/* Method getSpriteDisplayPanel: Gets the sprite display panel. */
	public JPanel getSpriteDisplayPanel()
	{
		return this.spriteDisplayPanel;
	}

	/* Method setSpriteDisplayPanel : Sets the sprite display panel. */
	public void setSpriteDisplayPanel(JPanel spriteDisplayPanel)
	{
		this.spriteDisplayPanel = spriteDisplayPanel;
	}

	/* Method addToSpriteDisplay: Adds the to sprite display. */
	public void addToSpriteDisplay(String spriteName)
	{
		this.model.addElement(spriteName);
	}

	/* Method removeFromSpriteDisplay: Removes the from sprite display. */
	public void removeFromSpriteDisplay(String spriteName)
	{
		this.model.removeElement(spriteName);
	}

	/* Method getSelectedSprite: Gets the selected sprite. */
	public int getSelectedSprite()
	{
		return this.spriteList.getSelectedIndex();
	}
	
	public String getSelectedSpriteName()
	{
		return this.spriteList.getSelectedValue().toString();
	}

	/* Method emptySpriteDisplay: Empty sprite display. */
	public void emptySpriteDisplay()
	{
		this.model.removeAllElements();
	}

	/* Method setSelectedSprite: Sets the selected sprite. */
	public void setSelectedSprite(String spriteName)
	{
		this.spriteList.setSelectedValue(spriteName, true);
	}

	/*
	 * Method isEventListEmpty : Checks if is event list empty.
	 */
	public boolean isEventListEmpty()
	{

		if (this.model.isEmpty())
			return true;

		return false;
	}

	public ArrayList<String> getSelectedSpriteNames()
	{
		ArrayList<String> selectedValuesToRetrun = new ArrayList<String>();
		for(Object oneSelectedValue : this.spriteList.getSelectedValues())
		{
			selectedValuesToRetrun.add(oneSelectedValue.toString());
		}
		return selectedValuesToRetrun;
	}
}
