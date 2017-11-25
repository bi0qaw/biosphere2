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

@Name("Vector Scale")
@Description("Scales vectors by a scaling factor and an optional direction. The direction parameter adds an additional scaling along the x-, y- and z-axis.")
@Examples({"set {_circle::*} to circle with radius 1 and density 5",
		"set {_bigcircle::*} to {_circle::*} scaled by 2",
		"#Makes the circle 2 times bigger.",
		"set {_ellipse::*} to {_circle::*} scaled by 1 in direction vector 1, 1, 2",
		"#Makes the circle 2 times bigger in the z-direction but does not change the x- and y-coordinates. This creates an ellipse.",
		"show happy villager at player offset by {_circle::*}",
		"show happy villager at player offset by {_bigcircle::*}",
		"show happy villager at player offset by {_ellipse::*}"})

public class ExprVectorScale extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Number> factor;
	private Expression<Vector> direction;

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public boolean isSingle() {
		return vectors.isSingle();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expr[0];
		factor = (Expression<Number>) expr[1];
		direction = (Expression<Vector>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return vectors.toString() + " scaled by " + factor.toString() + " in direction " + direction.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		double f = factor.getSingle(e).doubleValue();
		if (direction == null) {
			return VectorLib.scale(VectorLib.clone(vectors.getArray(e).clone()), f);
		}
		else {
			return VectorLib.scaleDirectional(VectorLib.clone(vectors.getArray(e)), direction.getSingle(e), f);
		}
	}
}
