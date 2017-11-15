package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import io.github.bi0qaw.biosphere.util.VectorLib;

import javax.annotation.Nullable;

@Name("Vector Rotate")
@Description("Rotates vectors around a rotation center and a rotation axis by an angle.")
@Examples({"set {_point} to vector 1, 2, 3",
		"set {_point} to {_point} rotated around vector 0, 1, 0 at player with angle 180",
		"#{_point} is now equal to the vector -1, 2, -3",
		"#vector 0, 1, 0 creates a vector along the y-axis, which is used as the rotation axis"})
public class ExprVectorRotate extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Vector> axis;
	private Expression<Number> angle;

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
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expr[0];
		axis = (Expression<Vector>) expr[1];
		angle = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return vectors.toString(arg0, arg1) + " rotated around " + axis.toString(arg0, arg1) + " with angle " + angle.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		float a = angle.getSingle(e).floatValue();
		a = -a; //Skript uses clockwise and VectorMath anti-clockwise rotation
		Vector ax = axis.getSingle(e).clone().normalize();
		return VectorLib.rotate(vectors.getArray(e).clone(), ax, a);
	}

}
