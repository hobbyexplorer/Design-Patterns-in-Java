package model;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;



public class Sprite {

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

	private String imagePathString;
	
	// The image path. //
	private URL imagePath;

	// The shape//
	private Shape shape;

	// The color. //
	private Color color;

	// The X Speed. //
	//private int deltaX;

	// The Y Speed. //
	//private int deltaY;
	
	// The angle of the movement of the sprite
	private int angle;
	
	//The angle of rotation of the image
	private int imageRotationAngle;
	
	//Check if angle is rotated or not
	//private boolean rotated;
	
	// The speed of the movement of the sprite
	private int speed;

	// The list of objects. //
	private ArrayList<GameObject> gameObjects;

	// The object count. //
	private int objectCount = 0;
	
	private Boolean randomAngle;
	
	private Random generator;
	private double widthrate=1;
	private double  heightrate=1;

	/*
	 * Constructor for Sprite class
	 */
	public Sprite() {
		widthrate=1;
		heightrate=1;
		spriteId = -1;
		width = 0;
		height = 0;
		name = null;
		shape = null;
		setImagePathString(null);
		imagePath = null;
		color = null;
		//deltaX = 0;
		//deltaY = 0;
		angle = 90;
		//rotated = false;
		imageRotationAngle = 0;
		//speed = 0;
		image = null;
		gameObjects = new ArrayList<GameObject>();
		randomAngle = false;
		generator = new Random();
	}

	/*
	public Sprite(Sprite sprite){
		spriteId = sprite.getSpriteId();
		width = sprite.getWidth();
		height = sprite.getHeight();
		name = sprite.getName();
		shape = sprite.getShape();
		imagePath = sprite.getImagePath();
		color = sprite.getColor();
		deltaX = sprite.getDeltaX();
		deltaY = sprite.getDeltaY();
		image = sprite.getImage();
	}
	 */

	public void setSpriteId(int spriteId)
	{
		this.spriteId = spriteId;
	}

	public int getSpriteId()
	{
		return spriteId;
	}

	/*
	 * Sets the width for the sprite
	 */
	public final void setWidth(int width) {
		this.width = width;
	}

	/*
	 * Returns the width for the sprite.
	 *
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}
	
	public final void setHeightRate(double heightrate){
		this.heightrate=heightrate;
	}
	public final void setWidthRate(double widthrate){
		this.widthrate=widthrate;
	}
	public final double getWidthRate() {
		return this.widthrate;
	}
	public final double getHeightRate() {
		return this.heightrate;
	}

	/*
	 * Sets the height for the sprite.
	 */
	public final void setHeight(int height) {
		this.height = height;
	}

	/*
	 * Returns the height for the sprite.
	 *
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}


	/*
	 * Sets the name for the sprite.
	 *
	 * @param name the new name
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/*
	 * returns the name of the sprite.
	 *
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/*
	 * Returns the image used to represent the sprite.
	 *
	 * @return the image
	 */
	public final Image getImage() {
		return image;
	}

	/*
	 * Sets the shape of the Sprite .
	 *
	 * @param shape the new shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/*
	 * Returns the shape of the sprite.
	 *
	 * @return the shape
	 */
	public Shape getShape() {
		return shape;
	}

	/*
	 * Returns the color of the sprite.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/*
	 * Sets the color of the sprite.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/*public int getDeltaX()
	{
		return deltaX;
	}

	public void setDeltaX(int deltaX)
	{
		this.deltaX = deltaX;
	}

	public int getDeltaY()
	{
		return deltaY;
	}

	public void setDeltaY(int deltaY)
	{
		this.deltaY = deltaY;
	}*/

	public void setGameObjects(ArrayList<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
	}


	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}

	public int getObjectCount()
	{
		return objectCount;
	}

	public void setObjectCount(int objectCount)
	{
		this.objectCount = objectCount;
	}

	public GameObject addGameObject()
	{
		GameObject newObject = new GameObject(this.objectCount, 0, 0, this.getHeight(), this.getWidth(),widthrate, heightrate);
		newObject.setColor(this.getColor());
		
		if(randomAngle)
			newObject.setAngle(randomGenerator());
		else
			newObject.setAngle(this.getAngle());
		
		newObject.setImageRotationAngle(this.getRotationAngle());
		newObject.setSpeed(this.getSpeed());
		//newObject.setDeltaX(this.getDeltaX());
		//newObject.setDeltaY(this.getDeltaY());
		newObject.setImage(this.getImage());
		newObject.setShape(this.getShape());

		this.gameObjects.add(newObject);

		++this.objectCount;

		return newObject;
	}
	
	public void addGameObject(GameObject newObject)
	{
		this.gameObjects.add(newObject);
	}
	
	

	public void removeLastGameObject()
	{
		int last = this.gameObjects.size() - 1;
		this.gameObjects.remove(last);
	}

	public void removeGameObject(int objectId)
	{
		GameObject removeObject = null;
		Iterator<GameObject> itr = gameObjects.iterator();
		while(itr.hasNext())
		{
			removeObject = itr.next();
			if(removeObject.getObjectId() == objectId)
				break;
		}
		this.gameObjects.remove(removeObject);
	}
	
	public void removeUnunsedObjects()
	{
		GameObject selectedObject;
		for(int index=0; index<gameObjects.size(); index++)
		{
			selectedObject = gameObjects.get(index);
			if(!betweenInclusive(selectedObject.getX(), -100, Constants.GAMEBOARD_WIDTH + 100) || 
					!betweenInclusive(selectedObject.getY(), -100, Constants.GAMEBOARD_HEIGHT + 100)){
				this.gameObjects.remove(selectedObject);
			}
			
		}
	}

	public void setImagePathString(String imagePathString)
	{
		this.imagePathString = imagePathString;
	}

	public String getImagePathString()
	{
		return imagePathString;
	}

	/*
	 * Returns the image path used.
	 *
	 * @return the image path
	 */
	public URL getImagePath() {
		return imagePath;
	}

	/*
	 * Sets the image path .
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.setImagePathString(imagePath);
		//this.imagePath = ClassLoader.getSystemResource("image/" + imagePath.toLowerCase() + ".gif");		
		this.imagePath = getClass().getClassLoader().getResource("image/" + imagePath + ".gif");
		this.image = createImage();
	}

	/*
	 * Returns the image used to represent the sprite.
	 *
	 * @param imagePath the image path
	 */
	public Image createImage() {
		BufferedImage tempImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		if(getImagePath() == null)
			return null;
		Image tempImage = null;

		try {
			tempImage = ImageIO.read(getImagePath());
			Graphics2D g2d = (Graphics2D)tempImg.createGraphics();
			g2d.scale((double)this.getWidth()/(double)tempImage.getWidth(null), (double)this.getHeight()/(double)tempImage.getHeight(null));
			g2d.drawImage(tempImage, 0, 0, null);
			g2d.dispose();
			tempImage = Toolkit.getDefaultToolkit().createImage(tempImg.getSource());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tempImage;
	}
	
	/*
	 * This function takes in the angle of movement and the speed and converts it to deltaX and deltaY
	 */
	/*public void setSpriteAndObjectDelta(int angle){
		double radian = Math.toRadians(angle);
		this.deltaX = (int) Math.round(Math.cos(radian)*(double)this.deltaX);
		this.deltaY = (int) Math.round(Math.sin(radian)*(double)this.deltaY);
		
		GameObject object;
		Iterator<GameObject> itr = gameObjects.iterator();
		
		while(itr.hasNext())
		{
			object = itr.next();
			object.setDeltaX(this.deltaX);
			object.setDeltaY(this.deltaY);
		}
	}*/
	

	public void setImage(Image image)
	{
		this.image = image;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	

	public void draw(Graphics g)
	{
		Iterator<GameObject> itr = gameObjects.iterator();
		while(itr.hasNext())
		{
			
			GameObject tmp = itr.next();
			tmp.setHeightRate(heightrate);
		    tmp.setWidthRate(widthrate);
			tmp.draw(g);
			//System.out.println("heightrate  : " + heightrate);
			
			
		}
	}

	public void setAngle(int angle)
	{
		this.angle = angle;
		Iterator<GameObject> itr = gameObjects.iterator();
		while(itr.hasNext())
		{
			itr.next().setAngle(angle);
		}
	}

	public int getAngle()
	{
		return angle;
	}

	public void setRotationAngle(int rotationAngle)
	{
		this.imageRotationAngle = rotationAngle;
	}

	public int getRotationAngle()
	{
		return imageRotationAngle;
	}
	
	public Boolean getRandomAngle()
	{
		return randomAngle;
	}

	public void setRandomAngle(Boolean randomAngle)
	{
		this.randomAngle = randomAngle;
	}

	public void setRandomAngleForObject()
	{
		this.randomAngle = true;
		
		Iterator<GameObject> itr = gameObjects.iterator();
		while(itr.hasNext())
			itr.next().setAngle(randomGenerator());
	}

	public int randomGenerator()
	{
		return generator.nextInt(361);
	}
	
	public boolean betweenInclusive(int value, int min, int max)
	   {
	       return value>=min && value<=max;    
	   }
	
	

	public String getPropertyValue(String propertyName){
		String propertyValue = null;
		if(propertyName.equals("spriteId")){
			propertyValue= (Integer.toString(getSpriteId()));
		}else if(propertyName.equals("name")){
			propertyValue= (getName());
		}else if(propertyName.equals("width")){
			propertyValue= (Integer.toString(getWidth()));
		}else if(propertyName.equals("height")){
			propertyValue= (Integer.toString(getHeight()));
		}else if(propertyName.equals("color")){
			if(getColor() != null){
			propertyValue= (Integer.toString(getColor().getRGB()));
			}
		}else if(propertyName.equals("name")){
			propertyValue= (getName());
		}else if(propertyName.equals("imagePathString")){
			propertyValue= (getImagePathString());
		}else if(propertyName.equals("shape")){
			propertyValue= (getShape().name());
		}else if(propertyName.equals("angle")){
			propertyValue= (Integer.toString(getAngle()));
		}else if(propertyName.equals("imageRotationAngle")){
			propertyValue= (Integer.toString(getRotationAngle()));
		}else if(propertyName.equals("speed")){
			propertyValue= (Integer.toString(getSpeed()));
		}else if(propertyName.equals("objectCount")){
			propertyValue= (Integer.toString(getObjectCount()));
		}else if(propertyName.equals("randomAngle")){
			propertyValue= (getRandomAngle().toString());
		}
		return propertyValue;
	}
	
	/*
	 * Sets the appropriate values for different properties of the sprite
	 *
	 * @param propertyName the property name
	 * @param propertyValue the property value
	 * @return the string
	 */
	public String setPropertyValue(String propertyName, String propertyValue){
		
		if(propertyName.equals("spriteId")){
			setSpriteId(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("name")){
			setName(propertyValue);
		}else if(propertyName.equals("width")){
			setWidth(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("height")){
			setHeight(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("color")){
			if(propertyValue != null){
			setColor(new Color(Integer.parseInt(propertyValue)));
			}
		}else if(propertyName.equals("imagePathString")){
			if(propertyValue != null){
			setImagePath(propertyValue);
			}
		}else if(propertyName.equals("shape")){
			if(propertyValue.equals("OVAL")){
				setShape(Shape.OVAL);
			}else if(propertyValue.equals("RECTANGLE")){
				setShape(Shape.RECTANGLE);
			}else if(propertyValue.equals("IMAGE")){
				setShape(Shape.IMAGE);
			}else if(propertyValue.equals("TRANSPERENTIMAGE")){
				setShape(Shape.TRANSPERENTIMAGE);
			}
		}else if(propertyName.equals("angle")){
			setAngle(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("imageRotationAngle")){
			setRotationAngle(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("speed")){
			setSpeed(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("objectCount")){
			setObjectCount(Integer.parseInt(propertyValue));
		}else if(propertyName.equals("randomAngle")){
			if(propertyValue.equals("true")){
				setRandomAngle(true);
			}else if(propertyValue.equals("false")){
				setRandomAngle(false);
			}
		}	
		return propertyValue;
	}

}
