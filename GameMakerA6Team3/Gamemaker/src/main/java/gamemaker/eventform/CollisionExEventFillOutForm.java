package gamemaker.eventform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import model.Sprite;
import controller.GameCreatePanelController;
import controller.eventform.EventFillOutFormController;

public class CollisionExEventFillOutForm implements EventFillOutForm
{
	// Current JFrame
	private JFrame collisionExEventFillOutFrame;

	// Current JPanel
	private JPanel collisionExEventFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	private ArrayList<String> listOfSprites;

	private JList spriteList;

	private JLabel questionLabel;

	private ArrayList<Sprite> selectedSprites;

	private DefaultListModel listOfAllSpriteNames;

	private JScrollPane spriteListScrollPane;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public CollisionExEventFillOutForm()
	{
		collisionExEventFillOutFrame = new JFrame();
		collisionExEventFillOutFrame.setUndecorated(true);
		collisionExEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		collisionExEventFillOutFrame.setResizable(false);
		collisionExEventFillOutFrame.setLocationRelativeTo(null);
		collisionExEventFillOutFrame.setVisible(true);

		collisionExEventFillOutPanel = new JPanel();
		collisionExEventFillOutPanel.setLayout(null);
		collisionExEventFillOutPanel.setBackground(Color.white);
		collisionExEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		collisionExEventFillOutFrame.add(collisionExEventFillOutPanel);

		titleLabel = new JLabel("Collision with exception");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		collisionExEventFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		collisionExEventFillOutPanel.add(nextButton);
		collisionExEventFillOutPanel.add(cancelButton);

		questionLabel = new JLabel("Select list of sprites to be excempted from this collision: ");
		questionLabel.setBounds(10, 150, 350, 30);
		collisionExEventFillOutPanel.add(questionLabel);

		listOfAllSpriteNames = new DefaultListModel();
		for (Sprite oneSprite : GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList())
		{
			listOfAllSpriteNames.addElement(oneSprite.getName());
		}

		spriteList = new JList(this.listOfAllSpriteNames);
		spriteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		spriteListScrollPane = new JScrollPane(spriteList);
		spriteListScrollPane.setBounds(100, 200, 100, 100);

		collisionExEventFillOutPanel.add(spriteListScrollPane);

		helpLabel = new JLabel("Click next to add actions");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		collisionExEventFillOutPanel.add(helpLabel);
		collisionExEventFillOutPanel.show();
	}

	public ArrayList<Sprite> getSelectedSprites()
	{
		ArrayList<Sprite> selectedSprites = new ArrayList<Sprite>();
		for (Object selectedSprite : this.spriteList.getSelectedValues())
		{
			selectedSprites.add(GameCreatePanelController.getInstance().getGameBoardModel().getSprite(selectedSprite.toString()));
		}
		GameMaker.logger.logInfo(this.getClass().getName() + " selectedSprites in the list are:  " + selectedSprites);
		return selectedSprites;
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.collisionExEventFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.collisionExEventFillOutPanel;
	}

	/**
	 * Adds action listeners to the cancel button and next button.
	 */
	@Override
	public void AddActionListenerToButtons(EventFillOutFormController eventFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) eventFillOutFormController);
		this.nextButton.addActionListener((ActionListener) eventFillOutFormController);
	}
}
