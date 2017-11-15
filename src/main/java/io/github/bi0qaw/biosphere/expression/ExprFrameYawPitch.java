package io.github.bi0qaw.biosphere.expression;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import io.github.bi0qaw.biosphere.util.Frame;
import io.github.bi0qaw.biosphere.util.VectorMath;

@Name("Frame Yaw and Pitch")
@Description("Yaw and pitch of a frame. Mainly used to rotate or incline reference frames")
@Examples({"set {_frame} to frame from yaw 90 and pitch -45",
			"set {_yaw} to frame yaw of {_frame} #{_yaw} is now equal to 90",
			"add 90 to frame yaw of {_frame}",
			"set {_yaw} to frame yaw of {_frame} #{_yaw} is now equal to 180"})
public class ExprFrameYawPitch extends SimplePropertyExpression<Frame, Number>{

	private int mark;
	private final static String[] type = new String[] {"yaw", "pitch"};


	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		super.init(expressions, i, kleenean, parseResult);
		mark = parseResult.mark;
		return true;
	}



	@Override
	protected String getPropertyName() {
		return "frame " + type[mark];
	}


	@Override
	public Number convert(Frame frame) {
		if (frame != null) {
			switch (mark) {
				case 0:
					return VectorMath.skriptYaw(frame.getYaw());
				case 1:
					return VectorMath.skriptPitch(frame.getPitch());
				default:
					break;
			}
		}
		return null;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public Class<Frame>[] acceptChange(Changer.ChangeMode mode) {
		if ((mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) && getExpr().isSingle() && Changer.ChangerUtils.acceptsChange(getExpr(), Changer.ChangeMode.SET, Frame.class))
			return new Class[] { Number.class };
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Frame f = getExpr().getSingle(e);
		if (f == null){
			return;
		}
		float v = ((Number) delta[0]).floatValue();
		float yaw = f.getYaw();
		float pitch = f.getPitch();
		switch (mode) {
			case REMOVE:
				v = -v;
			case ADD:
				if (mark == 0){
					yaw += v;
				} else if (mark == 1){
					pitch -= v; //negative because skript pitch is upside down and we are adding the value to the non-skript pitch
				}
				yaw = VectorMath.wrapYaw(yaw);
				pitch = VectorMath.wrapPitch(pitch);
				f = f.setFromYawPitch(yaw, pitch);
				getExpr().change(e, new Frame[]{f}, Changer.ChangeMode.SET);
				break;
			case SET:
				if (mark == 0){
					yaw = VectorMath.wrapYaw(v);
					yaw = VectorMath.fromSkriptYaw(yaw);
				} else if (mark == 1){
					pitch = VectorMath.wrapPitch(v);
					pitch = VectorMath.fromSkriptPitch(pitch);
				}
				f = f.setFromYawPitch(yaw, pitch);
				getExpr().change(e, new Frame[]{f}, Changer.ChangeMode.SET);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
				assert false;
		}
	}
}
