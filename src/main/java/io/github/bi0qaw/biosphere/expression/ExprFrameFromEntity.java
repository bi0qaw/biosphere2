package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.biosphere.util.VectorMath;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import io.github.bi0qaw.biosphere.util.Frame;

@Name("Frame of Entity")
@Description("Gives the reference frame of an entity. The reference frame is used to get locations with respect to the entities orientation.")
@Examples({"set {_frame} to frame of player",
			"show happy villager at player's head offset by vector 1, 2, 3 in {_frame}",
			"#shows a happy villager particle at the location 1 in front, 2 above and 3 blocks to the right of the player's head",
			"set {_circle::*} to circle with radius 1 and density 5",
			"show happy villager at player's head offset by {_circle::*} in {_frame}",
			"#shows a halo around the player's head that follows the movement of the head"})
public class ExprFrameFromEntity extends SimpleExpression<Frame> {

	private Expression<Entity> entity;

	@Override
	protected Frame[] get(Event event) {
		Entity e = entity.getSingle(event);
		float yaw = e.getLocation().getYaw();
		float pitch = e.getLocation().getPitch();
		yaw = VectorMath.fromNotchYaw(yaw);
		pitch = VectorMath.fromNotchPitch(pitch);
		return new Frame[] {new Frame().setFromYawPitch(yaw, pitch)};
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Frame> getReturnType() {
		return Frame.class;
	}

	@Override
	public String toString(Event event, boolean b) {
		return "frame of " + entity.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		entity = (Expression<Entity>) expressions[0];
		return true;
	}
}
