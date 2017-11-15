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

@Name("Location Rotate XYZ")
@Description("Rotates locations around a rotation center and rotation axis by an angle.")
@Examples({"set {_point} to location 5 in front of player",
			"set {_point} to {_point} rotated around y-axis at player with angle 180"})
public class ExprLocationRotXYZ extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Location> center;
	private Expression<Number> angle;
	private int axis;
	private String[] axisStr = new String[] {"x", "y", "z"};

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
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, SkriptParser.ParseResult parseResult) {
		locations = (Expression<Location>) expr[0];
		center = (Expression<Location>) expr[1];
		angle = (Expression<Number>) expr[2];
		axis = parseResult.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return locations.toString(e, arg1) + " rotated around " + axisStr[axis] + "-axis at " + center.toString(e, arg1) + " with angle " + angle.toString(e, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		float a = angle.getSingle(e).floatValue();
		a = -a; //Skript uses clockwise and LocationLib anti-clockwise rotation
		if (axis == 1) {
			return LocationLib.rotateX(locations.getArray(e), center.getSingle(e), a);
		}
		else if (axis == 2) {
			return LocationLib.rotateY(locations.getArray(e), center.getSingle(e), a);
		}
		else {
			return LocationLib.rotateZ(locations.getArray(e), center.getSingle(e), a);
		}
	}

}
