package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import io.github.bi0qaw.biosphere.util.VectorLib;

import javax.annotation.Nullable;

@Name("Vector Midpoint")
@Description("Gives the midpoint between vectors.")
@Examples({"set {_circle::*} to circle with radius 1 and density 5",
		"set {_midpoint} to vector midpoint of {_circle::*}",
		"#{_midpoint} is a vector pointing to the center of the circle"})
public class ExprVectorMidpoint extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
		vectors = (Expression<Vector>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "vector midpoint of " + vectors.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		return new Vector[] {VectorLib.midpoint(vectors.getArray(e))};
	}
}
