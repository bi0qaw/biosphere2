package io.github.bi0qaw.biosphere.type;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import io.github.bi0qaw.biosphere.util.Frame;
import io.github.bi0qaw.biosphere.util.VectorMath;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameType {
	public FrameType(){}

	static {
		Classes.registerClass(new ClassInfo<Frame>(Frame.class, "frame")
				.user("frames?")
				.name("Frame")
				.description("Reference frame that can be used to get vectors relative to some direction.")
				.usage(new String[]{"frame of %entity%", "frame with yaw %number% and pitch %number%"})
				.examples(new String[]{"set {_frame} to frame of player", "set {_circle::*} to circle with radius 1 and density 5",
										"show happy villager at player's head offset by {_circle::*} in frame of player",
										"#shows a particle circle around the player's head with the yaw and pitch of the player"})
				.defaultExpression(new EventValueExpression<Frame>(Frame.class))
				.parser(new Parser<Frame>() {

					private final Pattern parsePattern = Pattern.compile("frame:(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)"
							, Pattern.CASE_INSENSITIVE);

					@Override
					public Frame parse(String s, ParseContext parseContext) {
						final Matcher m = parsePattern.matcher(s);
						if (m.matches()) {
							float yaw = VectorMath.fromSkriptYaw(Float.parseFloat(m.group(1)));
							float pitch = VectorMath.fromSkriptPitch(Float.parseFloat(m.group(3)));
							return new Frame().setFromYawPitch(yaw, pitch);
						}
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return (context == ParseContext.COMMAND || context == ParseContext.EVENT || context == ParseContext.SCRIPT);
					}

					@Override
					public String toString(Frame f, int i) {
						float yaw = VectorMath.skriptYaw(f.getYaw());
						float pitch = VectorMath.skriptPitch(f.getPitch());
						return "frame:yaw: " + Skript.toString(yaw) + ", pitch: " + Skript.toString(pitch);
					}

					@Override
					public String toVariableNameString(Frame f) {
						float yaw = VectorMath.skriptYaw(f.getYaw());
						float pitch = VectorMath.skriptPitch(f.getPitch());
						return "frame:" + Skript.toString(yaw) + "," + Skript.toString(pitch);
					}

					@Override
					public String getVariableNamePattern() {
						return "frame:(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)";
					}

					@Override
					public String getDebugMessage(Frame f) {
						float yaw = VectorMath.skriptYaw(f.getYaw());
						float pitch = VectorMath.skriptPitch(f.getPitch());
						return "frame:yaw=" + Skript.toString(yaw) + ",pitch=" + Skript.toString(pitch);
					}
				})
				.serializer(new Serializer<Frame>() {
					@Override
					public Fields serialize(Frame f) throws NotSerializableException {
						final Fields fields = new Fields();
						float yaw = VectorMath.skriptYaw(f.getYaw());
						float pitch = VectorMath.skriptPitch(f.getPitch());
						fields.putPrimitive("yaw", yaw);
						fields.putPrimitive("pitch", pitch);
						return fields;
					}

					@Override
					public void deserialize(Frame f, Fields fields) throws StreamCorruptedException, NotSerializableException {
						assert false;
					}

					@Override
					protected Frame deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
						float yaw = VectorMath.fromSkriptYaw(f.getPrimitive("yaw", float.class));
						float pitch = VectorMath.fromSkriptPitch(f.getPrimitive("pitch", float.class));
						return new Frame().setFromYawPitch(yaw, pitch);
					}

					@Override
					public boolean mustSyncDeserialization() {
						return false;
					}

					@Override
					protected boolean canBeInstantiated() {
						return false;
					}
				})
		);
	}
}
