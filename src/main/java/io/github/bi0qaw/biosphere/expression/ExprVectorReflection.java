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

@Name("Vector Reflection")
@Description("Mirrors vectors in another vector. The direction parameter applies an additional scaling for each axis.")
@Examples({"set {_point} to vector 1, 2, 3 mirrored at vector 0, 0, 0",
		"#{_point} is now equal to the vector -1, -2, -3"})
public class ExprVectorReflection extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Vector> center;
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
		center = (Expression<Vector>) expr[1];
		direction = (Expression<Vector>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return vectors.toString() + " mirrored at " + center.toString() + " in direction " + direction.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		Vector[] clones = VectorLib.clone(vectors.getArray(e));
		if (direction == null) {
			return VectorLib.pointReflection(clones, center.getSingle(e));
		}
		else {
			return VectorLib.reflection(clones, center.getSingle(e), direction.getSingle(e));
		}
	}
}
