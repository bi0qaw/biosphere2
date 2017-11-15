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

@Name("Location Helix")
@Description("Creates a list of locations in the shape of a helix. The step parameter determines how many blocks the helix will go upwards in one rotation. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"show happy villager at helix at player's head with radius 2, height 3, step 1 and density 5",
			"#shows a helix at the player's head with a height of 3 blocks that makes a total of 3 rotations"})
public class ExprLocationHelix extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Number> radius;
	private Expression<Number> height;
	private Expression<Number> step;
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
		height = (Expression<Number>) expr[2];
		step = (Expression<Number>) expr[3];
		density = (Expression<Number>) expr[4];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "helix at " + locations.toString(arg0, arg1) + " with radius " + radius.toString(arg0, arg1) + ", height " + height.toString(arg0, arg1) + ", step " + step.toString(arg0, arg1) + " and density " + density.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		double h = height.getSingle(e).doubleValue();
		double s = step.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return LocationLib.getHelix(locations.getArray(e), r, h, s, d);
	}
}
