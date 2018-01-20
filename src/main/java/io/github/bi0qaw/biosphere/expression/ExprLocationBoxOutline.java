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


@Name("Location Box Outline")
@Description("Creates a list of vectors in the shape of a box including corners and edges. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_boxoutline::*} to box outline between player and location 5 right, 5 in front and 5 above player with density 5",
		"show happy villager at {_boxoutline::*}"})
public class ExprLocationBoxOutline extends SimpleExpression<Location> {

	private Expression<Location> location1;
	private Expression<Location> location2;
	private Expression<Number> density;

	@Override
	protected Location[] get(Event event) {
		return LocationLib.getBoxOutline(location1.getSingle(event), location2.getSingle(event), density.getSingle(event).doubleValue());
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
		return "box outline between " + location1.toString(event, b) + " and " + location2.toString(event, b) + " with denstiy " + density.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		location1 = (Expression<Location>) expressions[0];
		location2 = (Expression<Location>) expressions[1];
		density = (Expression<Number>) expressions[2];
		return true;
	}
}
