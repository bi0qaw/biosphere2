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

@Name("Location Sphere Coordinate")
@Description("Gives locations in a spherical coordinate system.")
@Examples({"set {_loc} to spherical coordinate at player with radius 3, yaw 90 and pitch -45",
			"show happy villager at {_loc}",
			"#shows a particle at the location 3 in the negative x direction (because of yaw = 90) and a little bit above the player (because of pitch = -45)"})
public class ExprLocationSphereLoc extends SimpleExpression<Location>{

	private Expression<Location> locations;
	private Expression<Number> radius;
	private Expression<Number> yaw;
	private Expression<Number> pitch;

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
		radius = (Expression<Number>) expr[1];
		yaw = (Expression<Number>) expr[2];
		pitch = (Expression<Number>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "sphere coordinate at " + locations.toString(arg0, arg1) + " with radius " + radius.toString(arg0, arg1) + ", yaw " + yaw.toString(arg0, arg1) + " and pitch " + pitch.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		float y = yaw.getSingle(e).floatValue();
		float p = pitch.getSingle(e).floatValue();
		y = VectorMath.fromSkriptYaw(y);
		y = VectorMath.wrapYaw(y + 180);
		p = VectorMath.fromSkriptPitch(p);
		p = VectorMath.wrapPitch(p);
		p = p - 90;
		return LocationLib.getSphericalCoordinates(locations.getArray(e), r, y, p);
	}
}
