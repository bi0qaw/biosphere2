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


@Name("Vector Box")
@Description("Creates a list of vectors in the shape of a box (only the corners!).")
@Examples({"set {_box::*} to vector box between vector -2.5, -2.5, -2.5 and vector 2.5, 2.5, 2.5",
		"show happy villager at player's head offset by {_box::*}"})
public class ExprVectorBox extends SimpleExpression<Vector>{

	private Expression<Vector> vector1;
	private Expression<Vector> vector2;

	@Override
	protected Vector[] get(Event event) {
		return VectorLib.getBox(vector1.getSingle(event), vector2.getSingle(event));
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
		return "vector box between " + vector1.toString(event, b) + " and " + vector2.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector1 = (Expression<Vector>) expressions[0];
		vector2 = (Expression<Vector>) expressions[1];
		return true;
	}
}
