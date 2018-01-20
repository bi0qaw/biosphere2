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

@Name("Vector Polygon Outline")
@Description("Creates a list of vectors in the shape of a polygon including corners and edges. The density value controls the amount of points. A higher number increases the amount of points. A density of 1 corresponds to 1 point per block.")
@Examples({"set {_polyoutline::*} to polygon outline with 6 points, radius 3 and density 5",
			"show happy villager at player offset by {_polygonoutline::*}",
			"#shows particles in the shape of a hexagon (6 points) at the player"})
public class ExprVectorPolygonOutline extends SimpleExpression<Vector> {

	private Expression<Number> vertices;
	private Expression<Number> radius;
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
		vertices = (Expression<Number>) expr[0];
		radius = (Expression<Number>) expr[1];
		density = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "polygon outline with " + vertices.toString(arg0, arg1) + " vertices, radius " + radius.toString(arg0, arg1) + " and density " + density.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Vector[] get(Event e) {
		int v = vertices.getSingle(e).intValue();
		double r = radius.getSingle(e).doubleValue();
		double d = density.getSingle(e).doubleValue();
		return VectorLib.getPolygonOutline(v, r, d);
	}

}
