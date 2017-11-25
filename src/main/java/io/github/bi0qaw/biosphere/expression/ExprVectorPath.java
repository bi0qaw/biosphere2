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

@Name("Vector Path")
@Description("Creates a path between vectors. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_polygon::*} to polygon with 7 points and radius 3",
			"set {_path::*} to vector path between {_polygon::*} with density 5",
			"show happy villager at player offset by {_path::*}"})
public class ExprVectorPath extends SimpleExpression<Vector>{

	private Expression<Vector> vectors;
	private Expression<Number> density;

	@Override
	protected Vector[] get(Event event) {
		return VectorLib.getPath(vectors.getArray(event), density.getSingle(event).doubleValue());
	}

	@Override
	public boolean isSingle() {
		return vectors.isSingle();
	}

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "vector path between " + vectors.toString(event, b) + " with density " + density.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expressions[0];
		density = (Expression<Number>) expressions[1];
		return true;
	}
}
