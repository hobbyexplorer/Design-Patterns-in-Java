package gamemaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import controller.BackGroundSelectionController;

public class GameBoardRightClick extends JPopupMenu implements ActionListener
{
	private static final long serialVersionUID = 4750373836281533887L;
	JMenuItem deleteItem;
	JMenuItem properties;

	public GameBoardRightClick()
	{
		properties = new JMenuItem("Properties");
		properties.addActionListener(this);
		add(properties);

		deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(this);
		add(deleteItem);
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource().equals(deleteItem))
		{

		}
		if (actionEvent.getSource().equals(properties))
		{
			BackGroundSelectionController.getInstance().getBackGroundFile();
		}
	}
}