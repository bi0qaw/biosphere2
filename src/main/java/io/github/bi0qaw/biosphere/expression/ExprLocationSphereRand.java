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

@Name("Location Random Sphere")
@Description("Creates a list of locations in the shape of a sphere. The locations are randomly distributed on the sphere surface.")
@Examples("show happy villager at random sphere at player with radius 1 and density 5")
public class ExprLocationSphereRand extends SimpleExpression<Location> {

	private Expression<Location> locations;
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
		radius = (Expression<Number>) expr[1];
		density = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "random sphere at " + locations.toString(arg0, arg1) + " with density " + density.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return LocationLib.getRandomSphere(locations.getArray(e), r, d);
	}
}
