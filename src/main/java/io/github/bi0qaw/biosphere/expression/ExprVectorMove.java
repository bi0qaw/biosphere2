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

@Name("Vector Offset")
@Description("Offset vectors by other vectors.")
@Examples({"set {_circle::*} to circle with radius 1 and density 5",
			"set {_offset::*} to {_circle::*} moved by vector 1, 2, 3",
			"#Moves the circle from the origin (vector 0, 0, 0) to the position at 1, 2, 3"})
public class ExprVectorMove extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Vector> offset;

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
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
		vectors = (Expression<Vector>) expr[0];
		offset = (Expression<Vector>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return vectors.toString() + " moved by " + offset.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		Vector[] o = offset.getAll(e);
		Vector totalOffset = new Vector();
		for (Vector v: o) {
			totalOffset.add(v);
		}
		return VectorLib.move(VectorLib.clone(vectors.getArray(e)), totalOffset);
	}
}
