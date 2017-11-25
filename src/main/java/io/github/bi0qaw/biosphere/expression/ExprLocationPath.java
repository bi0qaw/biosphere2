package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.biosphere.util.LocationLib;
import org.bukkit.Location;
import org.bukkit.event.Event;


@Name("Location Path")
@Description("Creates a path between the locations. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_polygon::*} to polygon at player with 7 points and radius 3",
			"set {_path::*} to path between {_polygon::*} with density 5",
			"show happy villager at {_path::*}"})
public class ExprLocationPath extends SimpleExpression<Location> {

	private Expression<Location> locations;
	private Expression<Number> density;

	@Override
	protected Location[] get(Event event) {
		return LocationLib.getPath(locations.getArray(event), density.getSingle(event).doubleValue());
	}

	@Override
	public boolean isSingle() {
		return locations.isSingle();
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "path between" + locations.toString(event, b) +  "with density " + density.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		locations = (Expression<Location>) expressions[0];
		density = (Expression<Number>) expressions[1];
		return true;
	}
}
