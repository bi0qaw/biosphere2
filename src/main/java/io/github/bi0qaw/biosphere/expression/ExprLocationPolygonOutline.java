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

@Name("Location Polygon Outline")
@Description("Creates a list of locations in the shape of a polygon including corners and edges. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples("show happy villager at polygon outline at player with 6 points, radius 3 and density 5")
public class ExprLocationPolygonOutline extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Number> points;
	private Expression<Number> radius;
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
		locations = (Expression<Location>) expr[0];
		points = (Expression<Number>) expr[1];
		radius = (Expression<Number>) expr[2];
		density = (Expression<Number>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		int p =  points.getSingle(e).intValue();
		double r = radius.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return LocationLib.getPolygonOutline(locations.getArray(e), p, r, d);
	}

}
