package gamemaker.eventform;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controller.eventform.EventFillOutFormController;

public class GenerateRandomEventFillOutForm implements EventFillOutForm
{
	private JFrame generateRandomEventFillOutFormFrame;

	private JPanel generateRandomEventFillOutFormPanel;

	private JLabel titleLabel;

	private JLabel helpLabel;

	public GenerateRandomEventFillOutForm()
	{
		generateRandomEventFillOutFormFrame = new JFrame();
		generateRandomEventFillOutFormFrame.setUndecorated(true);
		generateRandomEventFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		generateRandomEventFillOutFormFrame.setResizable(false);
		generateRandomEventFillOutFormFrame.setLocationRelativeTo(null);
		generateRandomEventFillOutFormFrame.setVisible(true);

		generateRandomEventFillOutFormPanel = new JPanel();
		generateRandomEventFillOutFormPanel.setLayout(null);
		generateRandomEventFillOutFormPanel.setBackground(Color.white);
		generateRandomEventFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		generateRandomEventFillOutFormFrame.add(generateRandomEventFillOutFormPanel);
		
		
		
		titleLabel = new JLabel("Generate Random  event fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		generateRandomEventFillOutFormPanel.add(titleLabel);
		
		helpLabel = new JLabel("Help text goes here...");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y+30, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		generateRandomEventFillOutFormPanel.add(helpLabel);
		generateRandomEventFillOutFormFrame.show();
	 
	}

	@Override
	public void disposeFrame()
	{
		this.generateRandomEventFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddActionListenerToButtons(EventFillOutFormController eventFullOutFormController)
	{
		// TODO Auto-generated method stub
		
	}
}
