package gamemaker;

import static org.junit.Assert.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;


import org.junit.Before;
import org.junit.Test;

public class SpriteDisplayPanelTest
{
//	SpriteObject sprite = new SpriteObject();
	SpriteDisplayPanel spriteDisplayPanel = new SpriteDisplayPanel();
	DefaultListModel listmodel = spriteDisplayPanel.getModel();
	JList list = spriteDisplayPanel.getSpriteList();

	@Before
	public void setUp() throws Exception
	{
	//	this.sprite.setSpriteName("Test");
	}

	@Test
	public void test()
	{
		// fail("Not yet implemented");
	/*	spriteDisplayPanel.addToSpriteDisplay(sprite.getSpriteName());
		assertEquals(this.sprite.getSpriteName(), this.list.getModel().getElementAt(0));
		spriteDisplayPanel.removeFromSpriteDisplay(sprite.getSpriteName());
		this.sprite.setSpriteName("NewTest1");
		spriteDisplayPanel.addToSpriteDisplay(sprite.getSpriteName());
		assertEquals("NewTest1", this.list.getModel().getElementAt(listmodel.getSize() - 1));
		assertEquals(this.sprite.getSpriteName(), this.list.getModel().getElementAt(0));*/
	}

}
