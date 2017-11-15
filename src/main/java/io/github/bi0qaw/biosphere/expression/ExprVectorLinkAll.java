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

@Name("Vector Link All")
@Description("Creates a list of vectors linking multiple vectors with lines. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_circle::*} to circle with radius 5 and density 0.2",
		"set {_linked::*} to {_circle::*} linked with density 2",
		"#connects all points on the circle with lines",
		"show happy villager at player offset by {_linked::*}"})
public class ExprVectorLinkAll extends SimpleExpression<Vector>{

	private Expression<Vector> vectors;
	private Expression<Number> density;

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
		vectors = (Expression<Vector>) expr[0];
		density = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "all vectors " + vectors.toString() + " linked with density " + density.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		return VectorLib.linkAll(vectors.getArray(e), density.getSingle(e).doubleValue());
	}
}
