package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.biosphere.util.LocationLib;
import org.bukkit.Location;
import org.bukkit.event.Event;

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
