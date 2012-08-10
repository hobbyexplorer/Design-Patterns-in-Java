package model;

import java.awt.Color;
import java.awt.Image;

public class GameObjectCommand
{

	private GameObject gameObject;
	private int x;
	private int y;
	private int height;
	private int width;
	private int speed;
	private int angle;
	private Shape shape;
	private Color color;
	private int objectId;
	private Image image;
	private boolean visible;
	private long lastVisibilityToggle;
	private int imageRotationAngle;
	private boolean rotatedFlag;


	public GameObjectCommand(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}

	public void Do() 
	{
		this.x = gameObject.getX();
		this.y = gameObject.getY();
		this.height = gameObject.getObjectHeight();
		this.width = gameObject.getObjectWidth();
		this.speed = gameObject.getSpeed();
		this.angle = gameObject.getAngle();
		this.shape = gameObject.getShape();
		this.color = gameObject.getColor();
		this.objectId = gameObject.getObjectId();
		this.image = gameObject.getImage();
		this.visible = gameObject.isVisible();
		this.lastVisibilityToggle = gameObject.getLastVisibilityToggle();
		this.imageRotationAngle = gameObject.getImageRotationAngle();
		this.rotatedFlag = gameObject.isRotatedFlag();

	}
	
	public GameObject Undo()
	{
		GameObject newObject = new GameObject();
		newObject.setX(this.x);
		newObject.setY(this.y);
		newObject.setObjectHeight(this.height);
		newObject.setObjectWidth(this.width);
		newObject.setSpeed(this.speed);
		newObject.setAngle(this.angle);
		newObject.setShape(this.shape);
		newObject.setColor(this.color);
		newObject.setObjectId(this.objectId);
		newObject.setImage(this.image);
		newObject.setVisible(this.visible);
		newObject.setLastVisibilityToggle(this.lastVisibilityToggle);
		newObject.setImageRotationAngle(this.imageRotationAngle);
		newObject.setRotatedFlag(this.rotatedFlag);
		
		return newObject;
	}


}
