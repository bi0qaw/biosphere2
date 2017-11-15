package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import io.github.bi0qaw.biosphere.util.VectorLib;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

@Name("Location Line")
@Description("Creates a list of locations in the shape of a line. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples("show happy villager at line between player's head and location 5 in front of player's head with density 5")
public class ExprLocationLine extends SimpleExpression<Location>{
	private Expression<Location> location1;
	private Expression<Location> location2;
	private Expression<Number> density;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		location1 = (Expression<Location>) expr[0];
		location2 = (Expression<Location>) expr[1];
		density = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "line from " + location1.toString(arg0, arg1) + " to " + location2.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double d = density.getSingle(e).doubleValue();
		return LocationLib.getLine(location1.getArray(e), location2.getSingle(e), d);
	}

}
