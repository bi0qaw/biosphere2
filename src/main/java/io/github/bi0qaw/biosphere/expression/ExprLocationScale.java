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
import org.bukkit.util.Vector;

@Name("Location Scale")
@Description("Scales locations from a center by a scaling factor. The direction parameter adds an additional scaling along the x-, y- and z-axis.")
@Examples({"set {_circle::*} to circle at player with radius 1 and density 5",
			"set {_bigcircle::*} to {_circle::*} scaled at player by 2",
			"#Makes the circle 2 times bigger.",
			"set {_ellipse::*} to {_circle::*} scaled at player by 1 in direction vector 1, 1, 2",
			"#Makes the circle 2 times bigger in the z-direction but does not change the x- and y-coordinates. This creates an ellipse.",
			"show happy villager at {_circle::*}",
			"show happy villager at {_bigcircle::*}",
			"show happy villager at {_ellipse::*}"})
public class ExprLocationScale extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Location> center;
	private Expression<Number> factor;
	private Expression<Vector> direction;

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
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult parseResult) {
		locations = (Expression<Location>) expr[0];
		center = (Expression<Location>) expr[1];
		factor = (Expression<Number>) expr[2];
		direction = (Expression<Vector>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		if (direction == null) {
			return locations.toString(arg0, arg1) + " scaled with center " + center.toString(arg0, arg1) + " by " + factor.toString(arg0, arg1);
		}
		else {
			return locations.toString(arg0, arg1) + " scaled with center " + center.toString(arg0, arg1) + " by " + factor.toString(arg0, arg1) + " in direction " + direction.toString(arg0, arg1);
		}
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double f = factor.getSingle(e).doubleValue();
		if (direction == null) {
			return LocationLib.scale(locations.getArray(e), center.getSingle(e), f);
		}
		else {
			return LocationLib.scaleDirectional(locations.getArray(e), center.getSingle(e),  direction.getSingle(e), f);
		}
	}	

}
