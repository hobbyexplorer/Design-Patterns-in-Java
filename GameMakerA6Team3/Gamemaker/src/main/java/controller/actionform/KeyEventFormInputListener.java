package controller.actionform;

import gamemaker.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import controller.eventform.KeyPressEventFillOutFormController;

public class KeyEventFormInputListener
{
	private JFrame keyPressEventFillOutFrame;
	private JPanel keyPressEventFillOutPanel;
	private JLabel titleLabel;
	public KeyEventFormInputListener()
	{
		keyPressEventFillOutFrame = new JFrame();
		keyPressEventFillOutFrame.setUndecorated(true);
		keyPressEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		keyPressEventFillOutFrame.setResizable(false);
		keyPressEventFillOutFrame.setLocationRelativeTo(null);
		keyPressEventFillOutFrame.setVisible(true);

		keyPressEventFillOutPanel = new JPanel();
		keyPressEventFillOutPanel.setLayout(null);
		keyPressEventFillOutPanel.setBackground(Color.white);
		keyPressEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		keyPressEventFillOutFrame.add(keyPressEventFillOutPanel);

		titleLabel = new JLabel("Press any key now");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		keyPressEventFillOutPanel.add(titleLabel);
	}

	public void addKeyListener(KeyPressEventFillOutFormController keyPressEventFillOutFormController)
	{
		keyPressEventFillOutPanel.requestFocus();
		keyPressEventFillOutPanel.addKeyListener(keyPressEventFillOutFormController);
	}

	public void disposeWindow()
	{
		this.keyPressEventFillOutFrame.dispose();
	}
}
