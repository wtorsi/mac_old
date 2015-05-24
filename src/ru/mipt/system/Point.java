package ru.mipt.system;

import javafx.beans.NamedArg;

public class Point
{
	/**
	 * Point or vector with both coordinates set to 0.
	 */
	public static final Point ZERO = new Point(0.0, 0.0);
	public static final Point E = new Point(1.0, 0.0);
	
	/**
	 * The x coordinate.
	 *
	 * @defaultValue 0.0
	 */
	private Double x;
	/**
	 * The y coordinate.
	 *
	 * @defaultValue 0.0
	 */
	private Double y;
	/**
	 * Cache the hash code to make computing hashes faster.
	 */
	private int hash = 0;
	
	/**
	 * Creates a new instance of {@code Point}.
	 *
	 * @param x the x coordinate of the point
	 * @param y the y coordinate of the point
	 */
	public Point(@NamedArg("x") Double x, @NamedArg("y") Double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * The x coordinate.
	 *
	 * @return the x coordinate
	 */
	public final Double getX()
	{
		return x;
	}


	/**
	 *
	 * @return cos of this vector
	 */
	public final Double cos()
	{
		return normalize().getX();
	}


	/**
	 *
	 * @return sin of this vector
	 */
	public final Double sin()
	{
		return normalize().getY();
	}

	/**
	 * The y coordinate.
	 *
	 * @return the y coordinate
	 */
	public final Double getY()
	{
		return y;
	}


	/**
	 * @return the angle from -Math.PI to Math.PI in radians
	 */
	public final Double getAngle()
	{
		return Math.acos(x) * (y < 0 ? -1 : 1);
	}
	
	/**
	 * Computes the distance between this point and point {@code (x1, y1)}.
	 *
	 * @param x1 the x coordinate of other point
	 * @param y1 the y coordinate of other point
	 * @return the distance between this point and point {@code (x1, y1)}.
	 */
	public Double distance(Double x1, Double y1)
	{
		Double a = getX() - x1;
		Double b = getY() - y1;
		return Math.sqrt(a * a + b * b);
	}
	
	/**
	 * Computes the distance between this point and the specified {@code point}.
	 *
	 * @param point the other point
	 * @return the distance between this point and the specified {@code point}.
	 * @throws NullPointerException if the specified {@code point} is null
	 */
	public Double distance(Point point)
	{
		return distance(point.getX(), point.getY());
	}
	
	/**
	 * Returns a point with the specified coordinates added to the coordinates
	 * of this point.
	 *
	 * @param x the X coordinate addition
	 * @param y the Y coordinate addition
	 * @return the point with added coordinates
	 * @since JavaFX 8.0
	 */
	public Point add(Double x, Double y)
	{
		return new Point(
				getX() + x,
				getY() + y);
	}
	
	/**
	 * Returns a point with the coordinates of the specified point added to the
	 * coordinates of this point.
	 *
	 * @param point the point whose coordinates are to be added
	 * @return the point with added coordinates
	 * @throws NullPointerException if the specified {@code point} is null
	 * @since JavaFX 8.0
	 */
	public Point add(Point point)
	{
		return add(point.getX(), point.getY());
	}
	
	/**
	 * Returns a point with the specified coordinates subtracted from
	 * the coordinates of this point.
	 *
	 * @param x the X coordinate subtraction
	 * @param y the Y coordinate subtraction
	 * @return the point with subtracted coordinates
	 * @since JavaFX 8.0
	 */
	public Point subtract(Double x, Double y)
	{
		return new Point(
				getX() - x,
				getY() - y);
	}
	
	/**
	 * Returns a point with the coordinates of this point multiplied
	 * by the specified factor
	 *
	 * @param factor the factor multiplying the coordinates
	 * @return the point with multiplied coordinates
	 * @since JavaFX 8.0
	 */
	public Point multiply(Double factor)
	{
		return new Point(getX() * factor, getY() * factor);
	}
	
	/**
	 * Returns a point with the coordinates of the specified point subtracted
	 * from the coordinates of this point.
	 *
	 * @param point the point whose coordinates are to be subtracted
	 * @return the point with subtracted coordinates
	 * @throws NullPointerException if the specified {@code point} is null
	 * @since JavaFX 8.0
	 */
	public Point subtract(Point point)
	{
		return subtract(point.getX(), point.getY());
	}
	
	/**
	 * Normalizes the relative magnitude vector represented by this instance.
	 * Returns a vector with the same direction and magnitude equal to 1.
	 * If this is a zero vector, a zero vector is returned.
	 *
	 * @return the normalized vector represented by a {@code Point} instance
	 * @since JavaFX 8.0
	 */
	public Point normalize()
	{
		final Double mag = magnitude();
		
		if (mag == 0.0) {
			return new Point(0.0, 0.0);
		}
		
		return new Point(
				getX() / mag,
				getY() / mag);
	}
	
	/**
	 * Returns a point which lies in the middle between this point and the
	 * specified coordinates.
	 *
	 * @param x the X coordinate of the second endpoint
	 * @param y the Y coordinate of the second endpoint
	 * @return the point in the middle
	 * @since JavaFX 8.0
	 */
	public Point midpoint(Double x, Double y)
	{
		return new Point(
				x + (getX() - x) / 2.0,
				y + (getY() - y) / 2.0);
	}
	
	/**
	 * Returns a point which lies in the middle between this point and the
	 * specified point.
	 *
	 * @param point the other endpoint
	 * @return the point in the middle
	 * @throws NullPointerException if the specified {@code point} is null
	 * @since JavaFX 8.0
	 */
	public Point midpoint(Point point)
	{
		return midpoint(point.getX(), point.getY());
	}
	
	/**
	 * Computes the angle (in degrees) between the vector represented
	 * by this point and the specified vector.
	 *
	 * @param x the X magnitude of the other vector
	 * @param y the Y magnitude of the other vector
	 * @return the angle between the two vectors measured in degrees
	 * @since JavaFX 8.0
	 */
	public Double angle(Double x, Double y)
	{
		final Double ax = getX();
		final Double ay = getY();
		
		final Double delta = (ax * x + ay * y) / Math.sqrt(
				(ax * ax + ay * ay) * (x * x + y * y));
		
		if (delta > 1.0) {
			return 0.0;
		}
		if (delta < -1.0) {
			return Math.PI;
		}
		
		return Math.acos(delta);
	}
	
	/**
	 * Computes the angle (in degrees) between the vector represented
	 * by this point and the vector represented by the specified point.
	 *
	 * @param point the other vector
	 * @return the angle between the two vectors measured in degrees,
	 * {@code NaN} if any of the two vectors is a zero vector
	 * @throws NullPointerException if the specified {@code point} is null
	 * @since JavaFX 8.0
	 */
	public Double angle(Point point)
	{
		return angle(point.getX(), point.getY());
	}
	
	/**
	 * Computes the angle (in degrees) between the three points with this point
	 * as a vertex.
	 *
	 * @param p1 one point
	 * @param p2 other point
	 * @return angle between the vectors (this, p1) and (this, p2) measured
	 * in radians, {@code NaN} if the three points are not different
	 * from one another
	 * @throws NullPointerException if {@code p1} or {@code p2} is null
	 * @since JavaFX 8.0
	 */
	public Double angle(Point p1, Point p2)
	{
		final Double x = getX();
		final Double y = getY();
		
		final Double ax = p1.getX() - x;
		final Double ay = p1.getY() - y;
		final Double bx = p2.getX() - x;
		final Double by = p2.getY() - y;
		
		final Double delta = (ax * bx + ay * by) / Math.sqrt(
				(ax * ax + ay * ay) * (bx * bx + by * by));
		
		if (delta > 1.0) {
			return 0.0;
		}
		if (delta < -1.0) {
			return Math.PI;
		}
		
		return Math.acos(delta);
	}
	
	/**
	 * Computes magnitude (length) of the relative magnitude vector represented
	 * by this instance.
	 *
	 * @return magnitude of the vector
	 * @since JavaFX 8.0
	 */
	public Double magnitude()
	{
		final Double x = getX();
		final Double y = getY();
		
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Computes dot (scalar) product of the vector represented by this instance
	 * and the specified vector.
	 *
	 * @param x the X magnitude of the other vector
	 * @param y the Y magnitude of the other vector
	 * @return the dot product of the two vectors
	 * @since JavaFX 8.0
	 */
	public Double dotProduct(Double x, Double y)
	{
		return getX() * x + getY() * y;
	}
	
	/**
	 * Computes dot (scalar) product of the vector represented by this instance
	 * and the specified vector.
	 *
	 * @param vector the other vector
	 * @return the dot product of the two vectors
	 * @throws NullPointerException if the specified {@code vector} is null
	 * @since JavaFX 8.0
	 */
	public Double dotProduct(Point vector)
	{
		return dotProduct(vector.getX(), vector.getY());
	}
	

	/**
	 * @param angle the angle is a vector
	 * @return the rotated vector
	 */
	public Point rotate(Point angle)
	{
		angle = angle.normalize();
		return new Point(angle.getX() * getX() - angle.getY() * getY(), angle.getY() * getX() + angle.getX() * getY());
	}


	/**
	 * @param angle the angle in radians
	 * @return the rotated vector
	 */
	public Point rotate(Double angle)
	{
		Point angleVector = new Point(Math.cos(angle), Math.sin(angle));
		return rotate(angleVector);
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare
	 * @return true if this point is the same as the obj argument; false otherwise
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if (obj instanceof Point) {
			Point other = (Point) obj;
			return getX() == other.getX() && getY() == other.getY();
		} else return false;
	}
	
	/**
	 * Returns a hash code value for the point.
	 *
	 * @return a hash code value for the point.
	 */
	@Override
	public int hashCode()
	{
		if (hash == 0) {
			long bits = 7L;
			bits = 31L * bits + Double.doubleToLongBits(getX());
			bits = 31L * bits + Double.doubleToLongBits(getY());
			hash = (int) (bits ^ (bits >> 32));
		}
		return hash;
	}
	
	/**
	 * Returns a string representation of this {@code Point}.
	 * This method is intended to be used only for informational purposes.
	 * The content and format of the returned string might vary between
	 * implementations.
	 * The returned string might be empty but cannot be {@code null}.
	 */
	@Override
	public String toString()
	{
		return "Point [x = " + getX() + ", y = " + getY() + "]";
	}
	
}
