package ru.mipt.system;

public class Segment
{
	private Point center;

	private Double maxRadius;
	private Double minRadius = 0.0;

	/**
	 * Normalized axis angle of the segment
	 */
	private Point axisAngle = Point.E;
	/**
	 * Normalized open angle of the segment according to axis angle
	 */
	private Point openAngle = Point.E;


	public Segment(Point center, Point axisAngle, Point openAngle, Double minRadius, Double maxRadius)
	{
		this.center = center;
		this.axisAngle = axisAngle.normalize();
		this.openAngle = openAngle.normalize();
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
	}

	public Segment(Point center, Double maxRadius)
	{
		this.center = center;
		this.maxRadius = maxRadius;
	}

	public Segment(Point center, Double minRadius, Double maxRadius)
	{
		this.center = center;
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
	}

	public Point getCenter()
	{
		return center;
	}

	public Double getMinRadius()
	{
		return minRadius;
	}

	public Double getMaxRadius()
	{
		return maxRadius;
	}


	/**
	 * Get random point in this segment
	 *
	 * @return random point in this segment
	 */
	public Point getRandomPoint()
	{
		Double angle = new Random(Math.PI * 2 - this.openAngle.getAngle(), 0.0).aDouble();

		return axisAngle
				.rotate(openAngle)
				.rotate(angle)
				.multiply(new Random(maxRadius, minRadius).aDouble()).add(center);
	}


	/**
	 * Creates opposite segment to visible maxRadius of this segment if point is outside this segment
	 *
	 * @param oppositePoint the point according to which will be created reverse segment
	 * @param maxRadius the max radius of the created segment
	 * @return opposite segment
	 */
	public Segment getReverseSegment(Point oppositePoint, Double maxRadius)
	{
		return getReverseSegment(oppositePoint, maxRadius, 0.0);
	}

	/**
	 * Creates opposite segment to visible maxRadius of this segment if point is outside this segment
	 *
	 * @param oppositePoint the point according to which will be created reverse segment
	 * @param minRadius     the min radius of the created segment
	 * @param maxRadius     the max radius of the created segment
	 * @return segment
	 */
	public Segment getReverseSegment(Point oppositePoint, Double minRadius, Double maxRadius)
	{

		Point vector = center.subtract(oppositePoint);
		Double distance = vector.magnitude();
		Double radius = this.maxRadius;

		Point angle = (distance > radius) ?
				new Point(Math.sqrt(distance * distance - radius * radius) / distance, radius / distance) :
				Point.E;

		return new Segment(oppositePoint, vector, angle, minRadius, maxRadius);
	}

	public boolean contains(Point position)
	{
		Point vector = position.subtract(center);
		Double distance = vector.magnitude();

		return distance <= maxRadius && distance >= minRadius && vector.normalize().dotProduct(axisAngle) < openAngle.cos();
	}


	@Override
	public String toString()
	{
		return "Segment{" +
				"center=" + center +
				", axisAngle=" + axisAngle +
				", openAngle=" + openAngle +
				", minRadius=" + minRadius +
				", maxRadius=" + maxRadius +
				'}';
	}
}