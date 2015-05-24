package ru.mipt.system;

import java.lang.Override;import java.lang.String;public class Random
{

	private double max;
	private double min;
	private java.util.Random random = new java.util.Random();


	public Random(double max, double min)
	{
		this.max = max;
		this.min = min;
	}



	public Random(double max)
	{
		this.max = max;
		this.min = 0;
	}

	public Random(int max, int min)
	{
		this.max = (double) max;
		this.min = (double) min;
	}

	public Random(int max)
	{
		this.max = (double) max;
		this.min = 0;
	}


	private double make()
	{
		return (random.nextDouble() * (max - min)) + min;
	}


	public double aDouble()
	{
		return (double) make();
	}

	public int anInt()
	{
		return (int) make();
	}

	public double aFloat()
	{
		return (float) make();
	}

	@Override
	public String toString()
	{
		return "Random{" +
				"max=" + max +
				", min=" + min +
				", random=" + make() +
				'}';
	}
}
