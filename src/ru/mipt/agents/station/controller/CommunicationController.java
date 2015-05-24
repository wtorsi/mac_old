package ru.mipt.agents.station.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.model.Signal;
import ru.mipt.receiver.VideoReceiver;
import ru.mipt.system.Settings;
import ru.mipt.transmitter.AudioTransmitter;
import ru.mipt.transmitter.VideoTransmitter;

import java.util.ArrayList;

public class CommunicationController extends AbstractController
{


	private VideoReceiver videoReceiver;

	private VideoTransmitter videoTransmitter;

	private AudioTransmitter audioTransmitter;

	private ArrayList<Signal> videoSignals;

	private ArrayList<Signal> audioSignals;


	public CommunicationController(AbstractAgent element)
	{
		super(element);
	}


	@Override
	public void initialize()
	{
		super.initialize();

		Double size = getElement().getSize();
		Double videoReceiverRadius = size * Settings.EnergyStorage.VideoReceiver.VIDEO_RECEIVER_RADIUS_MULTIPLIER;

		audioTransmitter = new AudioTransmitter(this);
		videoReceiver = new VideoReceiver(videoReceiverRadius, this);
		videoTransmitter = new VideoTransmitter(this);
	}

	public void handleReceivingVideoSignals()
	{

	}

	public VideoTransmitter getVideoTransmitter()
	{
		return videoTransmitter;
	}
}
