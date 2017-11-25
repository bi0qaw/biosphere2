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

@Name("Vector Rotate XYZ")
@Description("Rotates vectors around a rotation center and rotation axis by an angle.")
@Examples({"set {_point} to vector 1, 2, 3",
		"set {_point} to {_point} rotated around y-axis at player with angle 180",
		"#{_point} is now equal to the vector -1, 2, -3"})
public class ExprVectorRotateXYZ extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Number> angle;
	private int axis;
	private static final String[] axisStr = new String[] {"x", "y", "z"};

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
		angle = (Expression<Number>) expr[1];
		axis = parseResult.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return vectors.toString(e, arg1) + " rotated around " + axisStr[axis] + "-axis with angle " + angle.toString(e, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		float a = angle.getSingle(e).floatValue();
		a = -a; //Skript uses clockwise and VectorMath anti-clockwise rotation
		Vector[] clones = VectorLib.clone(vectors.getArray(e));
		if (axis == 1) {
			return VectorLib.rotateX(clones, a);
		}
		else if (axis == 2) {
			return VectorLib.rotateY(clones, a);
		}
		else {
			return VectorLib.rotateZ(clones, a);
		}
	}

}
