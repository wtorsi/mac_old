package ru.mipt.system;

import ru.mipt.system.Point;

public class Geometry
{

	public static Point getRandomPoint(Point center, Double minDistance, Double maxDistance, Double minAngle, Double maxAngle)
	{
		Double angle = new Random(maxAngle, minAngle).aDouble();
		Point randomTargetPosition = new Point(
				Math.cos(angle),
				Math.sin(angle))
				.multiply(new Random(maxDistance, minDistance).aDouble());

		return center.add(randomTargetPosition);
	}




}
