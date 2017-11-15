package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import io.github.bi0qaw.biosphere.util.TrigLib;
import io.github.bi0qaw.biosphere.util.VectorMath;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.util.Vector;

@Name("Location Line Coordinate")
@Description("Gives a location on the line between two locations. The number parameter describes how far the point is from the first location. A value of 0 corresponds the the start (first location). A value of 1 corresponds to the end (location 2). 0.5 is the midpoint between the two locations.")
@Examples({"show happy villager at line coordinate 0.2 between player and location 10 in front of player",
			"#shows a particle 2 blocks in front of the player"})

public class ExprLocationLineLoc extends SimpleExpression<Location>	{

	private Expression<Number> point;
	private Expression<Location> location1;
	private Expression<Location> location2;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return location1.isSingle();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		point = (Expression<Number>) expr[0];
		location1 = (Expression<Location>) expr[1];
		location2 = (Expression<Location>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "line coordinate " + point.toString(arg0, arg1) + " from " + location1.toString(arg0, arg1) + " to " + location2.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double p = point.getSingle(e).doubleValue();
		return LocationLib.getLineCoordinate(location1.getArray(e), location2.getSingle(e), p);
	}
}
