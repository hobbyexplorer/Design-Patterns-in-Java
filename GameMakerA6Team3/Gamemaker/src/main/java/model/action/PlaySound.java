package model.action;

import gamemaker.Constants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;

public class PlaySound implements Action
{
	private int actionType = 0;
	private String soundFileName;
	private String actionName;

	public PlaySound(String soundFileName)
	{
		this.actionName = Constants.ACTION_SOUND;
		this.soundFileName = soundFileName;
	}

	@Override
	public void execute()
	{

		try
		{

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("sound/" + soundFileName.toLowerCase() + ".wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void execute(GameObject gameObject)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getSpriteId()
	{
		return -1;
	}

	@Override
	public int getActionType()
	{
		return this.actionType;
	}

	@Override
	public String getActionName()
	{
		return this.actionName;
	}
	
	@Override
	public Element getActionInfoXML(SaveGame saveGame) {
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");
		
		saveGame.createNode(actionElement, "soundFileName", soundFileName);
		saveGame.createNode(actionElement, "actionString", actionName);
				
		return actionElement;
	}

}
