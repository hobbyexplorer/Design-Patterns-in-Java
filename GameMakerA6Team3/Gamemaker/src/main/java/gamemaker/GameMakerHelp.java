package gamemaker;

import java.awt.Dimension;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class Name: GameMakerHelp Class Responsibilities: Contains the help text for
 * Game maker Class collaborators: GameMaker.java
 * 
 */
public class GameMakerHelp
{
	private JPanel helpPanel;
	private JLabel help;

	// Constructor GameMakerHelp:
	public GameMakerHelp()
	{
		helpPanel = new JPanel();
		help = new JLabel(Constants.GAMEMAKERHELP_LABEL);
		help.setPreferredSize(new Dimension(Constants.GAMEMAKERHELP_LABEL_WIDTH, Constants.GAMEMAKERHELP_LABEL_HEIGHT));
		helpPanel.add(help);

	}

	/* Method getHelpPanel: returns the help panel */
	public JPanel getHelpPanel()
	{
		return helpPanel;
	}

	/* Method setHelpPanel: sets the help panel */
	public void setHelpPanel(JPanel helpPanel)
	{
		this.helpPanel = helpPanel;
	}

}
