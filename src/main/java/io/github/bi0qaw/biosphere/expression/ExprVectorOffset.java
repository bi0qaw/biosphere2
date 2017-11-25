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


@Name("Vector Offset")
@Description("Adds each offset to all vectors")
@Examples({"#creates a 8 circles on the vertices of a cube and displays them with particles around the player",
			"set {_cube::*} to cube with radius 3",
			"set {_circle::*} to circle with radius 2 and density 5",
			"set {_cube-circles::*} to vectors {_cube::*} offset by {_circle::*}",
			"show happy villager at player offset by {_cube-circles::*}"})
public class ExprVectorOffset extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Vector> offsets;

	@Override
	protected Vector[] get(Event event) {
		return VectorLib.offset(vectors.getArray(event), offsets.getArray(event));
	}

	@Override
	public boolean isSingle() {
		return vectors.isSingle() && offsets.isSingle();
	}

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "vector " + vectors.toString(event, b) + " offset by " + offsets.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expressions[0];
		offsets = (Expression<Vector>) expressions[1];
		return true;
	}
}
