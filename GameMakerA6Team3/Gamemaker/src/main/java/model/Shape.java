   package model;

import gamemaker.SpritePreviewPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Enum Name : Shape
 * Responsibilities : Represents all the shapes for the sprite. 
 *
 * Collaborators : None
 * 
 * Function APIs : abstract void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible);
 *                   -Draws the particular shape for the sprite
 * 
 */
public enum Shape{
	
	/* Represents the Rectangle shape .*/
	RECTANGLE{
		
		void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible,boolean resize,boolean selected,double widthrate, double heightrate){
			if(visible)
			{
				g.setColor(color);
				g.fillRect(x, y, width, height);
			}
		}
	},
	
	/* Represents the Oval shape.*/
	OVAL{
		
		void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible,boolean resize,boolean selected,double widthrate, double heightrate){
			if(visible)
			{
				g.setColor(color);
				g.fillOval(x, y, width, height);
			}
		}
	},
	
	/* Represents the image.*/
	IMAGE{
		
		void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible,boolean resize,boolean selected,double widthrate, double heightrate){
			if(visible)
			{
				
				x=(int)((double)x*widthrate);
				y=(int)((double)y*heightrate);
				 
				width=(int)((double)width*widthrate);
				height=(int)((double)height*heightrate);
				
				//System.out.println("x  : " +x);
				///System.out.println("y  : " +y);
				//System.out.println("width  : " +width);
				//System.out.println("height  : " +height);
				Graphics2D g2d = (Graphics2D)g;
			//BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			//Graphics2D g2d2 = (Graphics2D)bImage.createGraphics();
			//g2d2.drawImage(image, 0, 0, null);
			//g2d2.dispose();
				if(imageRotationAngle!=0){
				AffineTransform affineTransform = new AffineTransform();
				affineTransform.setToTranslation(x,y);
				affineTransform.rotate(Math.toRadians(imageRotationAngle),width/2,height/2);	
			//affineTransform.scale(width/(double).getWidth(null), height/(double)bImage.getHeight(null));
				//System.out.println("imageRotationAngle"+imageRotationAngle);
				g2d.drawImage(image, affineTransform, null);}
				else{
					
					g.drawImage(image, x, y,width,height, null);
					 
					if(selected&&!resize) 
					{	g.setColor(color.BLACK);
					     //if(resize)
					    	// {g.setColor(color.RED);}
					 g.drawRect( x-2, y-2, width+2,height+2);}
					else if(selected&&resize){
						g.setColor(color.RED);
						 g.drawRect( x-2, y-2, width+2,height+2);
					}
				}
			//g.drawImage(image, x, y, width, height, null);
			}
			
		}
	},
	
	/* Represents Transperent Image. */
	TRANSPERENTIMAGE{
		
		void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible,boolean resizse,boolean selected,double widthrate, double heightrate){
			if(visible){
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.7f));

				AffineTransform affineTransform = new AffineTransform();
				affineTransform.setToTranslation(x,y);
				affineTransform.rotate(Math.toRadians(imageRotationAngle),width/2,height/2);	

				//g2d.drawImage(image, x, y, width, height, null);

				g2d.drawImage(image, affineTransform, null);
				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 1.0f));
			}
		     
		}
	};
	
	/*
	 * Draws the Particular shape for the sprite.
	 *
	 * @param g the g
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param color the color
	 * @param image the image
	 */
	abstract void draw(Graphics g, int x, int y, int width, int height, Color color, Image image, int imageRotationAngle, boolean visible, boolean resize, boolean selected,double widthrate, double heightrate);
}