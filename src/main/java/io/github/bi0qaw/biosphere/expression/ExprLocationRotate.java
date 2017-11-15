package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.util.Vector;

@Name("Location Rotate")
@Description("Rotates locations around a rotation center and a rotation axis by an angle.")
@Examples({"set {_point} to location 5 in front of player",
			"set {_point} to {_point} rotated around vector 0, 1, 0 at player with angle 180",
			"show happy villager at {_point}",
			"#shows a particle at the location 5 behind the player",
			"#vector 0, 1, 0 creates a vector along the y-axis, which is used as the rotation axis"})
public class ExprLocationRotate extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Vector> axis;
	private Expression<Location> center;
	private Expression<Number> angle;

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
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult parseResult) {
		locations = (Expression<Location>) expr[0];
		axis = (Expression<Vector>) expr[1];
		center = (Expression<Location>) expr[2];
		angle = (Expression<Number>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return locations.toString(arg0, arg1) + " rotated around " + axis.toString(arg0, arg1) + " at " + center.toString(arg0, arg1) + " with angle " + angle.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		float a = angle.getSingle(e).floatValue();
		a = -a; //Skript uses clockwise and LocationLib anti-clockwise rotation
		Vector ax = axis.getSingle(e).clone().normalize();
		return LocationLib.rotate(locations.getArray(e), center.getSingle(e), ax, a);
	}

}
