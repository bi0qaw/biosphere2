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
import org.bukkit.util.Vector;

@Name("Location Offset")
@Description("Calculates the offset of locations from vectors.")
@Examples({"set {_circle::*} to circle with radius 1 and density 5",
			"#creates a list of vectors in the shape of a circle",
			"set {_offset::*} to player's head offset by {_circle::*}",
			"#gives a list of locations around the player's head in the shape of a circle",
			"show happy villager at {_offset::*}"})
public class ExprLocationOffset extends SimpleExpression<Location> {

	private Expression<Location> locations;
	private Expression<Vector> vectors;

	@Override
	public boolean isSingle() {
		return locations.isSingle() && vectors.isSingle();
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return locations.toString() + " offset by " + vectors.toString();
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		locations = (Expression<Location>) expressions[0];
		vectors = (Expression<Vector>) expressions[1];
		return true;
	}

	@Override
	protected Location[] get(Event event) {
		return LocationLib.offset(locations.getArray(event), vectors.getAll(event));
	}
}
