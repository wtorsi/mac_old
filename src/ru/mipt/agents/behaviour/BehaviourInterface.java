package ru.mipt.agents.behaviour;

public interface BehaviourInterface
{
	void process();

	boolean isActive();

	void onTick();

	void handleElapsedTimeout();
}
