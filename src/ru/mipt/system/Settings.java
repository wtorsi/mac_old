package ru.mipt.system;

public final class Settings
{

	final static public int WORLD_WIDTH = 800;

	final static public int WORLD_HEIGHT = 600;

	final static public int START_AGENTS_SIZE = 50;

	public static final int START_STATIONS_SIZE = 200;

	public static final int START_HOME_SIZE = 1;


	public final class Agent
	{
		/**
		 * Const. Live decrease size multiplier
		 */
		final static public double DECREASE_MULTIPLIER = 0.001;
		/**
		 * Const. MIN_LIVE_SIZE
		 */
		final static public double MIN_ENERGY = 0.01;
		/**
		 * Const. MIN_START_ENERGY
		 */
		final static public double MIN_START_ENERGY = 2;
		/**
		 * Const. MAX_START_ENERGY
		 */
		final static public double MAX_START_ENERGY = 10;
		/**
		 * Const. MIN_SPEED
		 */
		final static public double MIN_SPEED = 0.01;
		/**
		 * Const. MAX_SPEED
		 */
		final static public double MAX_SPEED = 0.02;
		/**
		 *
		 */
		final static public double MAX_START_SIZE = 10;
		/**
		 *
		 */
		final static public double MIN_START_SIZE = 4;


		/**
		 * MAX TARGETS FOR ONE AGENT
		 */
		public static final int MAX_TARGETS = 2;
		public static final double VISIBLE_RADIUS_MULTIPLIER = 3;
		public static final double AUDIBLE_RADIUS_MULTIPLIER = 8;


		public class DecisionController
		{
			public static final double MAX_SIZE_THRESHOLD = 0.8;
			public static final double MIN_SIZE_THRESHOLD = 0.2;
		}
	}
	
	public final class Home
	{
		public static final double MAX_START_SIZE = 50;
		
		public static final double MIN_START_SIZE = 30;

		public static final double MIN_ALIVE_ENERGY = 1;

		public static final double MAX_START_ENERGY = 200;
		
		public static final double MIN_START_ENERGY = 100;

		public static final double ENERGY_EXPEND_SPEED = 0.02;


		public class Receiver
		{
			public static final double RECEIVER_RADIUS_MULTIPLIER = 1.2;
		}
	}

	public final class EnergyStorage
	{
		public static final double MAX_SIZE = 4;

		public static final double MIN_SIZE = 2;

		public static final double MIN_ENERGY = 4;

		public static final double MAX_ENERGY = 4;

		public class VideoReceiver
		{
			public static final double VIDEO_RECEIVER_RADIUS_MULTIPLIER = 1;
		}
	}

}
