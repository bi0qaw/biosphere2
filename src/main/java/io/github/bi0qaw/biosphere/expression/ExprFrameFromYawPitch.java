package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import io.github.bi0qaw.biosphere.util.Frame;
import io.github.bi0qaw.biosphere.util.VectorMath;

@Name("Frame from Yaw and Pitch")
@Description("Creates a reference frame from a yaw and a pitch.")
@Examples({"set {_frame} to frame with yaw 90 and pitch -45",
			"set {_circle::*} to circle with radius 1 and density 5 in {_frame}",
			"show happy villager at player's head offset by {_circle::*}",
			"#shows a particle circle at the player's head with a yaw of 90 and pitch of -45"})
public class ExprFrameFromYawPitch extends SimpleExpression<Frame> {

	Expression<Number> yaw;
	Expression<Number> pitch;

	@Override
	protected Frame[] get(Event event) {
		float y = yaw.getSingle(event).floatValue();
		float p = pitch.getSingle(event).floatValue();
		y = VectorMath.fromSkriptYaw(y);
		p = VectorMath.fromSkriptPitch(p);
		y = VectorMath.wrapYaw(y);
		p = VectorMath.wrapPitch(p);
		return new Frame[] {new Frame().setFromYawPitch(y, p)};
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
		return "frame with yaw " + yaw.toString(event, b) + " and pitch " + pitch.toString(event, b);
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		yaw = (Expression<Number>) expressions[0];
		pitch = (Expression<Number>) expressions[1];
		return true;
	}
}
