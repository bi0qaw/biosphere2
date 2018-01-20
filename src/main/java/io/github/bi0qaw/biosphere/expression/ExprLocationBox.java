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


@Name("Location Box")
@Description("Creates a list of locations in the shape of a box (only the corners!).")
@Examples({"set {_box::*} to box between player and location 5 right, 5 in front and 5 above player",
		"show happy villager at {_box::*}"})
public class ExprLocationBox extends SimpleExpression<Location> {

	private Expression<Location> location1;
	private Expression<Location> location2;

	@Override
	protected Location[] get(Event event) {
		return LocationLib.getBox(location1.getSingle(event), location2.getSingle(event));
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "box between " + location1.toString(event, b) + " and " + location2.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		location1 = (Expression<Location>) expressions[0];
		location2 = (Expression<Location>) expressions[1];
		return true;
	}
}
