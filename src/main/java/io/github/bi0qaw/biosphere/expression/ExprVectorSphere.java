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

@Name("Vector Sphere")
@Description("Creates a list of vectors in the shape of a sphere. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_sphere::*} to sphere with radius 1 and density 5",
			"show happy villager at player offset by {_sphere::*}"})
public class ExprVectorSphere extends SimpleExpression<Vector> {

	private Expression<Number> radius;
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
		radius = (Expression<Number>) expr[0];
		density = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "sphere with radius " + radius.toString() + " and density " + density.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return VectorLib.getSphere(r, d);
	}
}
