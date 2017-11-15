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

@Name("Vector Cube")
@Description("Creates a list of vectors in the shape of a cube (only the corners!). To get a list of locations including the cube edges use cube outline.")
@Examples({"set {_cube::*} to cube with radius 1",
			"show happy villager at player's head offset by {_cube::*}"})
public class ExprVectorCube extends SimpleExpression<Vector>{

	private Expression<Number> radius;

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
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "cube with radius " + radius.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		double r = radius.getSingle(e).doubleValue();
		return VectorLib.getCube(r);
	}
}
