package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import io.github.bi0qaw.biosphere.util.TrigLib;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Location Polygon")
@Description("Creates a list of locations in the shape of a polygon (only the corners!). If you want a polygon with edges use polygon outline.")
@Examples({"set {_polygon::*} to polygon at player with 3 points and radius 3",
			"show happy villager at {_polygon::*}",
			"#shows particles in the shape of a triangle at the player"})
public class ExprLocationPolygon extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Number> points;
	private Expression<Number> radius;

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
		locations = (Expression<Location>) expr[0];
		points = (Expression<Number>) expr[1];
		radius = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "polygon at " + locations.toString(arg0, arg1) + " with " + points.toString(arg0, arg1) + " points and radius " + radius.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		int p = points.getSingle(e).intValue();
		double r = radius.getSingle(e).doubleValue();
		return LocationLib.getPolygon(locations.getArray(e), p, r);
	}
}
