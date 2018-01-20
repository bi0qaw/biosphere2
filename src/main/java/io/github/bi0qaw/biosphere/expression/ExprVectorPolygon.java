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

@Name("Vector Polygon")
@Description("Creates a list of vectors in the shape of a polygon (only the corners!). If you want a polygon with edges use polygon outline.")
@Examples({"set {_polygon::*} to polygon with 3 points and radius 3",
		"show happy villager at player offset by {_polygon::*}",
		"#shows particles in the shape of a triangle at the player"})
public class ExprVectorPolygon extends SimpleExpression<Vector>{

	private Expression<Number> vertices;
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
		vertices = (Expression<Number>) expr[0];
		radius = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "vector polygon with " + vertices.toString() + " vertices and radius " + radius.toString();
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		int p = vertices.getSingle(e).intValue();
		double r = radius.getSingle(e).doubleValue();
		return VectorLib.getPolygon(p, r);
	}
}
