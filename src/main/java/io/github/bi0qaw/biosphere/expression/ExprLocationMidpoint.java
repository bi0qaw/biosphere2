package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.biosphere.util.LocationLib;
import io.github.bi0qaw.biosphere.util.TrigLib;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Location Midpoint")
@Description("Gives the midpoint between locations.")
@Examples({"show happy villager at midpoint between player and location 5 in front of player",
			"#shows a particle at the location 2.5 in front of the player"})
public class ExprLocationMidpoint extends SimpleExpression<Location>{

	private Expression<Location> locations;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		locations = (Expression<Location>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "midpoint of " + locations.toString(arg0, arg1);
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		return new Location[] {LocationLib.midpoint(locations.getArray(e))};
	}

}
