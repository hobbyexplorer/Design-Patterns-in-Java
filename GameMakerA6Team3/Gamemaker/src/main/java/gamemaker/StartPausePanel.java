package gamemaker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class Name: StartPausePanel Class Responsibilities: Contains the panel
 * containing the start, pause and re build buttons Class collaborators:
 * GameMaker.java
 * 
 */
public class StartPausePanel
{

	// The start.
	private JButton start;

	// The pause.
	private JButton pause;

	// The rebuild.
	private JButton rebuild;

	// The start pause panel.
	private JPanel startPausePanel;

	

	// Constructor StartPausePanel: Instantiates a new start pause panel. */
	public StartPausePanel()
	{
		this.startPausePanel = new JPanel();
		 
		this.startPausePanel.setBackground(Color.white);
		this.start = new JButton(Constants.STARTPAUSEPANEL_START_BUTTON);
		this.pause = new JButton(Constants.STARTPAUSEPANEL_PAUSE_BUTTON);
		this.rebuild = new JButton(Constants.STARTPAUSEPANEL_REBUILD_BUTTON);
		this.startPausePanel.setLayout(new GridLayout());
	 
		this.startPausePanel.add(this.start);
		this.startPausePanel.add(this.pause);
		this.startPausePanel.add(this.rebuild);
/*		this.start.addActionListener(this.startPausePanelController);
		this.pause.addActionListener(this.startPausePanelController);
		this.rebuild.addActionListener(this.startPausePanelController);
*/	}

	/* Method getStart: Gets the start. */
	public JButton getStart()
	{
		return start;
	}

	/* Method setStart: Sets the start. */
	public void setStart(JButton start)
	{
		this.start = start;
	}

	/* Method setStart: Gets the pause. */
	public JButton getPause()
	{
		return pause;
	}

	/* Method setPause: Sets the pause. */
	public void setPause(JButton pause)
	{
		this.pause = pause;
	}

	/* Method getStartPausePanel: Gets the start pause panel. */
	public JPanel getStartPausePanel()
	{
		return startPausePanel;
	}

	/* Method setStartPausePanel:Sets the start pause panel. */
	public void setStartPausePanel(JPanel startPausePanel)
	{
		this.startPausePanel = startPausePanel;
	}

	/* Method getRebuild: Gets the rebuild. */
	public JButton getRebuild()
	{
		return rebuild;
	}

}
