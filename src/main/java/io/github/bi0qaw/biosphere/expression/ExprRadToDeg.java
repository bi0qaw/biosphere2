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

@Name("Radian to Degree")
@Description("Converts radian to degree")
@Examples({"set {_num} to 3.14159 in degree",
			"#{_num} is now equal to 180"})
public class ExprRadToDeg extends SimpleExpression<Number>{

	private Expression<Number> radian;

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
		radian = (Expression<Number>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return radian.toString(e, arg1) + " in degree";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		return new Double[]{ radian.getSingle(e).doubleValue() * 180 / Math.PI};
	}

}
