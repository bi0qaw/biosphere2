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

@Name("Vector Line Between Vectors")
@Description("Creates a list of vectors in the shape of a line between the two given vectors. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_line::*} to vector line between vector 1, 2, 3 and vector 3, 2, 1",
		"show happy villager at player offset by {_line::*}"})
public class ExprVectorLineBetweenVectors extends SimpleExpression<Vector> {

	private Expression<Vector> vector1;
	private Expression<Vector> vector2;
	private Expression<Number> density;

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
		return "vector line between " + vector1.toString(event, b) + " and " + vector2.toString(event, b) + " with density " + density.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector1 = (Expression<Vector>) expressions[0];
		vector2 = (Expression<Vector>) expressions[1];
		density = (Expression<Number>) expressions[2];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		double d = density.getSingle(event).doubleValue();
		return VectorLib.getLine(vector1.getSingle(event), vector2.getSingle(event), d);
	}

}
