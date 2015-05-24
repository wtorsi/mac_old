package ru.mipt.controller;

import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.model.Signal;
import ru.mipt.receiver.AudioReceiver;
import ru.mipt.receiver.VideoReceiver;
import ru.mipt.transmitter.AudioTransmitter;
import ru.mipt.transmitter.VideoTransmitter;

import java.util.ArrayList;
import java.util.HashMap;

public class CommunicationController extends AbstractController
{

	private AudioReceiver audioReceiver;

	private VideoReceiver videoReceiver;

	private VideoTransmitter videoTransmitter;

	private AudioTransmitter audioTransmitter;

	private ArrayList<Signal> videoSignals;

	private ArrayList<Signal> audioSignals;

	public CommunicationController(AbstractAgent agent, Double visibleRadius, Double audibleRadius)
	{
		super(agent);

		audioReceiver = new AudioReceiver(audibleRadius, this);
		audioTransmitter = new AudioTransmitter(this);
		videoReceiver = new VideoReceiver(visibleRadius, this);
		videoTransmitter = new VideoTransmitter(this);
	}

	/**
	 * Getters
	 */
	public AudioReceiver getAudioReceiver()
	{
		return audioReceiver;
	}

	public VideoReceiver getVideoReceiver()
	{
		return videoReceiver;
	}

	public VideoTransmitter getVideoTransmitter()
	{
		return videoTransmitter;
	}

	public AudioTransmitter getAudioTransmitter()
	{
		return audioTransmitter;
	}

	public void observeVideoSignals()
	{
		videoSignals = videoReceiver.receive();
	}

	public void observeAudioSignals()
	{
		audioSignals = audioReceiver.receive();
	}

	public boolean hasVideoSignals()
	{
		return videoSignals.size() > 0;
	}

	public ArrayList<Signal> getVideoSignals()
	{
		return videoSignals;
	}

	public boolean hasAudioSignals()
	{
		return audioSignals.size() > 0;
	}

	public ArrayList<Signal> getAudioSignals()
	{
		return audioSignals;
	}


	public ArrayList<Signal> getUniqueSignals()
	{
		ArrayList<Signal> allSignals = new ArrayList<Signal>();
		allSignals.addAll(videoSignals);
		allSignals.addAll(audioSignals);

		HashMap<String, Signal> unique = new HashMap<String, Signal>();
		for (Signal signal : allSignals) {
			if (!unique.containsKey(signal.getId())) {
				unique.put(signal.getId(), signal);
			}
		}

		return new ArrayList<Signal>(unique.values());
	}

}
