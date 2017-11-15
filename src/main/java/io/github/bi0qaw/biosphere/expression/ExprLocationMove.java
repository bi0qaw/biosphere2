package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.util.Vector;

@Name("Location Move")
@Description("Moves a list of locations from one location to another.")
@Examples({"set {_circle::*} to circle at player with radius 1 and density 5",
			"set {_moved::*} to {_circle::*} with center player moved to location 5 in front of player",
			"show happy villager at {_moved::*}"})
public class ExprLocationMove extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Location> center;
	private Expression<Location> point;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return locations.isSingle();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		locations = (Expression<Location>) expr[0];
		center = (Expression<Location>) expr[1];
		point = (Expression<Location>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "move " + locations.toString(arg0, arg1) + " with center " + center.toString(arg0, arg1) + " to " + locations.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		Vector delta = point.getSingle(e).toVector().subtract(center.getSingle(e).toVector());
		return LocationLib.offset(locations.getArray(e), delta);
	}

}
