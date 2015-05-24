package ru.mipt.agents.agent.manager;

import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.model.Target;
import ru.mipt.agents.station.Station;
import ru.mipt.system.Point;
import ru.mipt.system.Segment;
import ru.mipt.system.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TargetManager extends AbstractManager
{

	private ArrayList<String> goalTypes = new ArrayList<String>(Arrays.asList(
			Station.class.getName()
	));

	private ArrayList<Target> targets = new ArrayList<Target>();

	private ArrayList<Target> reachedTargets = new ArrayList<Target>();

	public TargetManager(Agent agent)
	{
		super(agent);
	}

	public Target createTarget(Target.Importance importance)
	{

		Segment segment = new Segment(
				element.getPosition(),
				element.getCommunicationController()
						.getVideoReceiver()
						.getReceiveRadius(),
				element.getCommunicationController()
						.getAudioReceiver()
						.getReceiveRadius());

		return createTarget(segment, importance);

	}


	public Target createTarget(Segment segment, Target.Importance importance)
	{
		return new Target(segment.getRandomPoint(), importance);
	}


	public boolean isTargetReached(Target target)
	{
		Point targetPosition = target.getPosition();

		return element.getPosition().subtract(targetPosition).magnitude()
				<= element.getCommunicationController().getVideoReceiver().getReceiveRadius();
	}


	public TargetManager remember(Target target)
	{
		target.setReached(true);
		reachedTargets.add(target);

		return this;
	}


	public TargetManager add(Target target)
	{
		targets.add(target);
//		System.out.println("targets: " + targets.size());

		return this;
	}


	public ArrayList<Target> getTargets()
	{
		return targets;
	}

	public Target getNearestTarget()
	{
		Target nearestTarget = null;
		if (targets.size() > 0) {

			Point center = element.getPosition();

			Double minMagnitude = Double.MAX_VALUE,
					magnitude;

			for (Target target : targets) {
				magnitude = center.subtract(target.getPosition()).magnitude();
				if (magnitude < minMagnitude) {
					minMagnitude = magnitude;
					nearestTarget = target;
				}
			}

//			System.out.println("nearest " + nearestTarget);
		}

		return nearestTarget;
	}


	public Target getTarget(Segment segment)
	{

		for (Target target : targets) {
			if (segment.contains(target.getPosition())) {
				return target;
			}
		}

		return null;
	}

	public Target getTarget(Point center, Double minDistance, Double maxDistance)
	{
		for (Target target : targets) {
			Double distance = target.getPosition().subtract(center).magnitude();
			if (distance <= maxDistance && distance >= minDistance) {
				return target;
			}

		}
		return null;
	}


	public Target getTarget(Point center, Double maxDistance)
	{
		return getTarget(center, 0.0, element.getCommunicationController().getVideoReceiver().getReceiveRadius());
	}


	public boolean hasTarget(Point center, Double minDistance, Double maxDistance)
	{

		for (Target target : targets) {
			Double distance = target.getPosition().subtract(center).magnitude();
			if (distance <= maxDistance && distance >= minDistance) {
				return true;
			}

		}
		return false;
	}


	public boolean hasTarget(Point center, Double maxDistance)
	{
		return hasTarget(center, 0.0, element.getCommunicationController().getVideoReceiver().getReceiveRadius());
	}

	public boolean hasTarget(Point center)
	{
		return hasTarget(center, element.getCommunicationController().getVideoReceiver().getReceiveRadius());
	}


	public boolean hasTarget(Segment segment)
	{
		for (Target target : targets) {
			if (segment.contains(target.getPosition())) {
				return true;
			}

		}
		return false;
	}

	public void removeReachedTargets()
	{
		Iterator<Target> iterator = targets.iterator();
		while (iterator.hasNext()) {

			Target target = iterator.next();

			if (target.isReached()) {
				iterator.remove();
			}
		}
	}

	public void removeTarget(Target target)
	{
		targets.remove(target);
	}


	public ArrayList<Target> getReachedTargets()
	{
		return reachedTargets;
	}


	public ArrayList<String> getGoalTypes()
	{
		return goalTypes;
	}


	public boolean canAddTarget(Target.Importance importance)
	{
		return getTargets(importance).size() < Settings.Agent.MAX_TARGETS;
	}


	public ArrayList<Target> getTargets(Target.Importance importance)
	{

		ArrayList<Target> list = new ArrayList<Target>();
		for (Target target : targets) {
			if (target.getImportance() == importance) {
				list.add(target);
			}
		}

		return list;
	}
}
