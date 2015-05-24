package ru.mipt.agents.agent.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.manager.HomeManager;
import ru.mipt.agents.agent.manager.LeaderManager;
import ru.mipt.agents.agent.manager.TargetManager;
import ru.mipt.agents.agent.manager.TypeManager;
import ru.mipt.agents.agent.model.Home;
import ru.mipt.agents.agent.model.Leader;
import ru.mipt.agents.agent.model.Target;
import ru.mipt.agents.agent.type.MovingMode;
import ru.mipt.agents.agent.type.State;
import ru.mipt.agents.agent.type.Type;
import ru.mipt.agents.model.Signal;
import ru.mipt.controller.CommunicationController;
import ru.mipt.system.Point;
import ru.mipt.system.Segment;

import java.util.ArrayList;


public class DecisionController extends AbstractController
{

	private TargetManager targetManager;

	private LeaderManager leaderManager;

	private TypeManager typeManager;

	private HomeManager homeManager;

	private State state = State.INITIALIZE;

	private MovingMode mode = MovingMode.DISCOVER_AREA;

	public DecisionController(Agent agent)
	{
		super(agent);
	}

	@Override
	public void initialize()
	{
		super.initialize();

		targetManager = new TargetManager(getElement());
		leaderManager = new LeaderManager(getElement());
		typeManager = new TypeManager(getElement());
		homeManager = new HomeManager(getElement());
	}

	@Override
	public Agent getElement()
	{
		return (Agent) super.getElement();
	}

	public void process()
	{
		switch (state) {
			case INITIALIZE:
				handleInitializeState();
				break;
			case READY:
				handleReadyState();
				break;
			case EXECUTION:
				handleExecutionState();
				break;
		}
	}

	private void handleInitializeState()
	{
		initType();
		initLeader();
		initHome();

		state = State.READY;

	}

	private void handleReadyState()
	{
		state = State.EXECUTION;
	}


	private void handleExecutionState()
	{

		switch (typeManager.getType()) {
			case DISCOVERER:

				removeReachedTargets();
				searchGoals();
				discoverArea();

				break;
			case DEFENDER:
				state = State.THINKING;
//				moveInArea();
				break;

		}

	}

	private void leaveHome()
	{
		Agent element = getElement();

		if (homeManager.hasHome()) {

			Home home = homeManager.getHome();
			Segment exitSegment = new Segment(
					home.getPosition(),
					home.getSize(),
					home.getReceiverRadius());

			Target target;
			if (!targetManager.hasTarget(exitSegment)) {
				target = targetManager.createTarget(exitSegment, Target.Importance.RANDOM);
				targetManager.add(target);
			} else {
				target = targetManager.getTarget(exitSegment);
			}

			if (exitSegment.contains(element.getPosition())) {
				targetManager.removeTarget(target);
				mode = MovingMode.DISCOVER_AREA;
			}
		} else {
			mode = MovingMode.RANDOM_WALKS;
		}
	}

	private void removeReachedTargets()
	{
		ArrayList<Target> targets = targetManager.getTargets();

		if (targets.size() > 0) {
			for (Target target : targets) {
				if (targetManager.isTargetReached(target)) {
					targetManager.remember(target);
				}
			}
			targetManager.removeReachedTargets();
		}
	}

	private void discoverArea()
	{
		Agent element = getElement();
		CommunicationController communicationController = element.getCommunicationController();

		if (homeManager.hasHome()) {
			Home home = homeManager.getHome();

			while (targetManager.canAddTarget(Target.Importance.DISCOVER)) {

				Segment opposite = new Segment(
						home.getPosition(),
						home.getSize()
				).getReverseSegment(
						element.getPosition(),
						communicationController.getVideoReceiver().getReceiveRadius(),
						communicationController.getAudioReceiver().getReceiveRadius()
				);

				Target target = targetManager.createTarget(opposite, Target.Importance.DISCOVER);
				targetManager.add(target);
			}
		} else {
			mode = MovingMode.RANDOM_WALKS;
		}

	}

	private void searchGoals()
	{

		Agent element = getElement();
		CommunicationController communicationController = element.getCommunicationController();

		if (communicationController.hasVideoSignals()) {
			for (Signal signal : communicationController.getVideoSignals()) {
				if (targetManager.getGoalTypes().contains(signal.getType())) {
					if (!targetManager.hasTarget(signal.getPosition())) {
						Target target = new Target(signal.getPosition(), Target.Importance.VISIBLE);
						targetManager.add(target);
					}
				}
			}
		}
//
		if (communicationController.hasAudioSignals()) {
			for (Signal signal : communicationController.getAudioSignals()) {
				if (targetManager.getGoalTypes().contains(signal.getType())) {
					if (!targetManager.hasTarget(signal.getPosition())) {
						Target target = new Target(signal.getPosition(), Target.Importance.AUDIBLE);
						targetManager.add(target);
					}
				}
			}
		}
	}


	private void initType()
	{
		Type type = typeManager.selectType();
		typeManager.setType(type);
	}


	private void initLeader()
	{
		if (!leaderManager.hasLeader()) {
			Leader leader = leaderManager.selectLeader();
			if (leader != null) {
				leaderManager.setLeader(leader);
			}
		}
	}

	private void initHome()
	{
		if (!homeManager.hasHome()) {
			Home home = homeManager.findHome();
			if (home != null) {
				homeManager.setHome(home);
			}
		}
	}


	public Point getDestinationPosition()
	{
		//if has home and storage is full go home
		//else if has visible targets get nearest visible
		//else if has audible go to audible
		//else go to nearest random target
		Target target = targetManager.getNearestTarget();

//		System.out.println(target);
		return target != null ? target.getPosition() : null;
	}

	
	public TargetManager getTargetManager()
	{
		return targetManager;
	}


	public State getState()
	{
		return state;
	}
	
	public TypeManager getTypeManager()
	{
		return typeManager;
	}
	
	public LeaderManager getLeaderManager()
	{
		return leaderManager;
	}
	
	public HomeManager getHomeManager()
	{
		return homeManager;
	}
}
