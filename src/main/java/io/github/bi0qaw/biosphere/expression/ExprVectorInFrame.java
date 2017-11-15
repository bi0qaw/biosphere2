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
import io.github.bi0qaw.biosphere.util.Frame;

@Name("Vector in Frame")
@Description("Converts a vector in a reference frame to the standard Minecraft coordinates.")
@Examples({"set {_vector} to vector 5, 0, 0 in frame of player",
			"#Gives a vector that points 5 blocks in the player's direction",
			"set {_circle::*} to circle with radius 1 and density 5 in frame of player",
			"#Gives a list of vectors in the shape of a circle around the player's head with the correct yaw and pitch"})
public class ExprVectorInFrame extends SimpleExpression<Vector> {

	private Expression<Vector> vectors;
	private Expression<Frame> frame;

	@Override
	protected Vector[] get(Event event) {
		Frame f = frame.getSingle(event);
		Vector[] v = vectors.getArray(event);
		Vector[] transformed = new Vector[v.length];
		int i = 0;
		for (Vector vec: v) {
			transformed[i] = f.transform(vec.clone());
			i++;
		}
		return transformed;
	}

	@Override
	public boolean isSingle() {
		return vectors.isSingle();
	}

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return vectors.toString(event, b) + " in " + frame.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expressions[0];
		frame = (Expression<Frame>) expressions[1];
		return true;
	}
}
