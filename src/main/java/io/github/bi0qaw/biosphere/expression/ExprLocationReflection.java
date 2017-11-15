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

@Name("Location Reflection")
@Description("Mirrors locations in another location. The direction parameter applies an additional scaling for each axis.")
@Examples({"set {_point} to location 5 in front of player mirrored at player",
			"show happy villager at {_point}",
			"#shows a particle 5 blocks behind the player"})
public class ExprLocationReflection extends SimpleExpression<Location>{
	private Expression<Location> locations;
	private Expression<Location> point;
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
		point = (Expression<Location>) expr[1];
		direction = (Expression<Vector>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		if (direction == null) {
			return locations.toString(e, arg1) + " reflected at " + point.toString(e, arg1);
		}
		else {
			return locations.toString(e, arg1) + " reflected at " + point.toString(e, arg1) + " in direction " + direction.toString(e, arg1);
		}
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		if(direction == null) {
			return LocationLib.pointReflection(locations.getArray(e), point.getSingle(e));
		}
		else {
			return LocationLib.reflection(locations.getArray(e), point.getSingle(e), direction.getSingle(e));
		}
	}

}
