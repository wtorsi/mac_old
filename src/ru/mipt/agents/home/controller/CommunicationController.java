package ru.mipt.agents.home.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.home.Home;
import ru.mipt.receiver.AudioReceiver;
import ru.mipt.receiver.VideoReceiver;
import ru.mipt.system.Settings;
import ru.mipt.transmitter.AudioTransmitter;
import ru.mipt.transmitter.VideoTransmitter;

public class CommunicationController extends AbstractController
{

	private AudioReceiver audioReceiver;

	private AudioTransmitter audioTransmitter;

	private VideoReceiver videoReceiver;

	private VideoTransmitter videoTransmitter;

	public CommunicationController(Home home)
	{
		super(home);
	}

	@Override
	public Home getElement()
	{
		return (Home) super.getElement();
	}

	@Override
	public void initialize()
	{
		super.initialize();

		Double size = getElement().getSize();
		final Double receiverRadius = size * Settings.Home.Receiver.RECEIVER_RADIUS_MULTIPLIER;

		audioReceiver = new AudioReceiver(receiverRadius, this);
		videoReceiver = new VideoReceiver(receiverRadius, this);


		audioTransmitter = new AudioTransmitter(this){
			@Override
			protected void initSignal()
			{
				super.initSignal();
				signal.getHome().setReceiverRadius(receiverRadius);
			}
		};

		videoTransmitter = new VideoTransmitter(this){
			@Override
			protected void initSignal()
			{
				super.initSignal();
				signal.getHome().setReceiverRadius(receiverRadius);
			}
		};
	}


	public void handleReceivingAudioSignals()
	{
		audioReceiver.receive();
	}

	public void handleReceivingVideoSignals()
	{
		videoReceiver.receive();
	}

	public AudioReceiver getAudioReceiver()
	{
		return audioReceiver;
	}

	public AudioTransmitter getAudioTransmitter()
	{
		return audioTransmitter;
	}

	public VideoReceiver getVideoReceiver()
	{
		return videoReceiver;
	}

	public VideoTransmitter getVideoTransmitter()
	{
		return videoTransmitter;
	}
}
