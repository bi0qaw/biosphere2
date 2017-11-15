package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import io.github.bi0qaw.biosphere.util.VectorMath;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Location Cylinder Coordinate")
@Description("Gives locations in a cylindrical coordinate system.")
@Examples({"set {_loc} to cylinder coordinate at player with radius 5, yaw 90 and height 5",
			"#gives the location 5 blocks in the negative x-direction and 5 blocks above the player"})
public class ExprLocationCylinderLoc extends SimpleExpression<Location>{
	private Expression<Location> locations;
	private Expression<Number> radius;
	private Expression<Number> yaw;
	private Expression<Number> height;
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
		yaw = (Expression<Number>) expr[2];
		height = (Expression<Number>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "cylinder coordinate at " + locations.toString(arg0, arg1) + " with radius " + radius.toString(arg0, arg1) + ", yaw " + yaw.toString(arg0, arg1) + " and height " + height.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		float y = yaw.getSingle(e).floatValue();
		double h = height.getSingle(e).doubleValue();
		y = VectorMath.fromSkriptYaw(y);
		y = VectorMath.wrapYaw((y));
		return LocationLib.getCylindricalCoordinate(locations.getArray(e), r, y, h);
	}

}
