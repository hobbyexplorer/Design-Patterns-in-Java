package model;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

public class SpriteCommand
{

	private Sprite sprite;
	
	// The sprite ID. //
	private int spriteId;
	
	// The width. //
	private int width;

	// The height. //
	private int height;

	// The image. //
	private Image image;

	// The name. //
	private String name;

	// The image path. //
	private URL imagePath;

	// The shape//
	private Shape shape;

	// The color. //
	private Color color;

	// The angle of the movement of the sprite
	private int angle;
	
	//The angle of rotation of the image
	private int imageRotationAngle;
	
	// The speed of the movement of the sprite
	private int speed;

	// The list of objects. //
	private ArrayList<GameObjectCommand> gameObjectsCommands;

	// The object count. //
	private int objectCount;
	
	private Boolean randomAngle;
	
	public SpriteCommand(Sprite sprite)
	{
		this.sprite = sprite;
		this.gameObjectsCommands = new ArrayList<GameObjectCommand>();
		
		for(GameObject gameObject: this.sprite.getGameObjects()){	
			this.gameObjectsCommands.add(new GameObjectCommand(gameObject));
		}
		
	}
	
	public void Do()
	{
		spriteId = sprite.getSpriteId();
		width = sprite.getWidth();
		height = sprite.getHeight();
		image = sprite.getImage();
		name = sprite.getName();
		imagePath = sprite.getImagePath();
		shape = sprite.getShape();
		color = sprite.getColor();
		angle = sprite.getAngle();
		imageRotationAngle = sprite.getRotationAngle();
		speed = sprite.getSpeed();

		//System.out.println("Size of game object command list in DO: " + this.gameObjectsCommands.size());
		for(GameObjectCommand gameObjectCommand: this.gameObjectsCommands){	
			gameObjectCommand.Do();
		}
		
		objectCount = sprite.getObjectCount();
		randomAngle = sprite.getRandomAngle();
	}
	
	public void Undo()
	{
		//System.out.println("Srite commeand undo called");
		sprite.setSpriteId(this.spriteId);
		sprite.setWidth(this.width);
		sprite.setHeight(this.height);
		sprite.setImage(this.image);
		sprite.setName(this.name);
		
		sprite.setShape(this.shape);
		sprite.setColor(this.color);
		sprite.setAngle(this.angle);
		sprite.setRotationAngle(this.imageRotationAngle);
		sprite.setSpeed(this.speed);
		
		sprite.getGameObjects().removeAll(sprite.getGameObjects());
		
		for(GameObjectCommand gameObjectCommand: this.gameObjectsCommands){	
			sprite.addGameObject(gameObjectCommand.Undo());
		}
	
		sprite.setObjectCount(this.objectCount);
		sprite.setRandomAngle(this.randomAngle);
		
	}
}

