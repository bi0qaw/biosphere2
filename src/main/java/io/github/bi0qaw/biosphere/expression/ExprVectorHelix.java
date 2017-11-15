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

@Name("Vector Helix")
@Description("Creates a list of vectors in the shape of a helix. The step parameter determines how many blocks the helix will go upwards in one rotation. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_helix::*} to helix with radius 2, height 3, step 1 and density 5",
		"show happy villager at player's head offset by {_helix::*}"})
public class ExprVectorHelix extends SimpleExpression<Vector>{
	private Expression<Number> radius;
	private Expression<Number> height;
	private Expression<Number> step;
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
		height = (Expression<Number>) expr[1];
		step = (Expression<Number>) expr[2];
		density = (Expression<Number>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "helix with radius " + radius.toString(arg0, arg1) + ", height " + height.toString(arg0, arg1) + ", step " + step.toString(arg0, arg1) + " and density " + density.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		double h = height.getSingle(e).doubleValue();
		double s = step.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return VectorLib.getHelix(r, h, s, d);
	}
}
