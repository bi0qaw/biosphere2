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

@Name("Vector Line")
@Description("Creates a list of vectors in the shape of a line. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_line::*} to line with length 1 and density 5",
			"show happy villager at player offset by {_line::*}",
			"#shows a particle line that goes from the player to the location 1 block in the x-direction from the player"})
public class ExprVectorLine extends SimpleExpression<Vector> {

	private Expression<Number> length;
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
		length = (Expression<Number>) expr[0];
		density = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "line with length " + length.toString(arg0, arg1) + " and density " + density.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		double l = length.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return VectorLib.getLine(l, d);
	}
}
