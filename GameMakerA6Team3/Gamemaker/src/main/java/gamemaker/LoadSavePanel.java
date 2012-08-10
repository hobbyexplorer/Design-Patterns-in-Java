package gamemaker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class Name: LoadSavePanel Class Responsibilities: Contains the load and save
 * buttons as a part of the panel Class collaborators: GameMaker.java
 * 
 */
public class LoadSavePanel
{

	// The save.
	private JButton save;

	// The load.
	private JButton load;

	// The load save panel.
	private JPanel loadSavePanel;

	// The load save controller.
	
	// Constructor LoadSavePanel : Instantiates a new load save panel.

	public LoadSavePanel( )
	{
		this.loadSavePanel = new JPanel();
		this.loadSavePanel.setBackground(Color.white);
		this.save = new JButton(Constants.LOADSAVEPANEL_SAVE_BUTTON);
		this.load = new JButton(Constants.LOADSAVEPANEL_LOAD_BUTTON);
		 
		this.loadSavePanel.setLayout(new GridLayout());
		this.loadSavePanel.add(this.load);
		this.loadSavePanel.add(this.save);
//		this.save.addActionListener(this.loadSaveController);
//		this.load.addActionListener(this.loadSaveController);
	}

	/* Method getLoadSavePanel: Gets the load save panel. */
	public JPanel getLoadSavePanel()
	{
		return loadSavePanel;
	}

	/* Method setLoadSavePanel: Sets the load save panel. */
	public void setLoadSavePanel(JPanel loadSavePanel)
	{
		this.loadSavePanel = loadSavePanel;
	}

	/* Method setSave: Sets the save. */
	public void setSave(JButton save)
	{
		this.save = save;
	}

	/* Methos getSave: Gets the save. */
	public JButton getSave()
	{
		return save;
	}

	/* Method getLoad: Gets the load. */
	public JButton getLoad()
	{
		return load;
	}

	/* Method setLoad: Sets the load. */
	public void setLoad(JButton load)
	{
		this.load = load;
	}
}