package model;

import java.util.ArrayList;
import java.util.List;

public class GameMacroCommand
{

	private List<SpriteCommand> commands;


	public GameMacroCommand() {
		commands = new ArrayList<SpriteCommand>();
	}
	
	public void Do() {
		for(SpriteCommand spriteCommand: commands){	
			spriteCommand.Do();
		}
	}

	public void Undo() {
		for(SpriteCommand spriteCommand: commands){	
			spriteCommand.Undo();
		}
	}

	public void add(SpriteCommand spriteCommand){
		commands.add(spriteCommand);
	}

	/*
	 * removes GameObjectCommand from the commands list
	 *
	 * @param gameObjectCommand the game object command
	 */
	public void remove(SpriteCommand spriteCommand){
		commands.remove(spriteCommand);
	}

	/*
	 * Removes everything from the commands list.
	 */
	public void removeAll(){
		commands.removeAll(getCommands());
	}

	/*
	 * Returns the commands list.
	 *
	 * @return the commands
	 */
	public List<SpriteCommand> getCommands() {
		return commands;
	}

	/*
	 * Sets the commands list.
	 *
	 * @param commands the new command
	 */
	public void setCommand(List<SpriteCommand> commands) {
		this.commands = commands;
	}



	
}
