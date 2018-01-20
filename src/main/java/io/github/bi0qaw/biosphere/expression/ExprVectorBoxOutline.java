package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.biosphere.util.VectorLib;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;


@Name("Vector Box Outline")
@Description("Creates a list of vectors in the shape of a box including corners and edges. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_boxoutline::*} to vector box outline between vector -2.5, -2.5, -2.5 and vector 2.5, 2.5, 2.5 with density 5",
		"show happy villager at player's head offset by {_boxoutline::*}"})
public class ExprVectorBoxOutline extends SimpleExpression<Vector>{

	private Expression<Vector> vector1;
	private Expression<Vector> vector2;
	private Expression<Number> density;

	@Override
	protected Vector[] get(Event event) {
		return VectorLib.getBoxOutline(vector1.getSingle(event), vector2.getSingle(event), density.getSingle(event).doubleValue());
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "vector box outline between " + vector1.toString(event, b) + " and " + vector2.toString(event, b) + " with density " + density.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector1 = (Expression<Vector>) expressions[0];
		vector2 = (Expression<Vector>) expressions[1];
		density = (Expression<Number>) expressions[2];
		return true;
	}
}
