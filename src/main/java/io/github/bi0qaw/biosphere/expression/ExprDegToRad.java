package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Degree to Radian")
@Description("Converts degree to radian")
@Examples({"#returns PI", "set {_num} to 180 in radian" })
public class ExprDegToRad extends SimpleExpression<Number>{

	private Expression<Number> degree;

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		degree = (Expression<Number>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return degree.toString(e, arg1) + " in radian";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		return new Double[]{ degree.getSingle(e).doubleValue() * Math.PI / 180.0};
	}
	
}
