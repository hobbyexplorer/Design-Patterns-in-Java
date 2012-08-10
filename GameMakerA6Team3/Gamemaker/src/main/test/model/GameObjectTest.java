package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameObjectTest
{

	@Test
	public void testUpdateAngle()
	{
		GameObject tester = new GameObject(0,50,50,50,50,1,1);
		tester.setAngle(210);
		tester.setSpeed(5);
		tester.moveObject();
		//System.out.println("X: "+tester.getX());
		//System.out.println("Y: "+tester.getY());
		tester.reflectFromHorizontalSurface();
		assertEquals("Angle", 510, tester.getAngle());
		//System.out.println("X: "+tester.getX());
		//System.out.println("Y: "+tester.getY());
		tester.moveObject();
		//System.out.println("New X: "+tester.getX());
		//System.out.println("New Y: "+tester.getY());
		tester.moveObject();
		//System.out.println("New X: "+tester.getX());
		//System.out.println("New Y: "+tester.getY());
		tester.moveObject();
		//System.out.println("New X: "+tester.getX());
		//System.out.println("New Y: "+tester.getY());
		//fail("Not yet implemented");
	}

}
