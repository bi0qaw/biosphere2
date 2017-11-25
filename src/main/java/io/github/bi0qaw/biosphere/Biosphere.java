package io.github.bi0qaw.biosphere;

import io.github.bi0qaw.biosphere.expression.*;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import io.github.bi0qaw.biosphere.type.FrameType;
import io.github.bi0qaw.biosphere.util.Frame;

public class Biosphere extends JavaPlugin {

	private static Biosphere plugin;

	public void onEnable(){

		plugin = this;
		Skript.registerAddon(this);

		new FrameType();

		Skript.registerExpression(ExprDegToRad.class, Number.class, ExpressionType.PROPERTY, "%number% [in] rad[ian]");
		Skript.registerExpression(ExprRadToDeg.class, Number.class, ExpressionType.PROPERTY, "%number% [in] deg[ree]");

		Skript.registerExpression(ExprFrameFromEntity.class, Frame.class, ExpressionType.SIMPLE, "frame of %entity%", "%entity%['s] frame");
		Skript.registerExpression(ExprFrameFromYawPitch.class, Frame.class, ExpressionType.SIMPLE, "frame with yaw %number% and pitch %number%");
		Skript.registerExpression(ExprFrameYawPitch.class, Number.class, ExpressionType.PROPERTY, "frame (0¦yaw|1¦pitch) of %frame%");

		Skript.registerExpression(ExprLocationCircle.class, Location.class, ExpressionType.SIMPLE, "circle[s] at %locations%[ with] radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprLocationCube.class, Location.class, ExpressionType.SIMPLE, "cube[s] at %locations% with radius %number%");
		Skript.registerExpression(ExprLocationCubeOutline.class, Location.class, ExpressionType.SIMPLE, "cube[s] outline[s] at %locations% with radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprLocationCylinderLoc.class, Location.class, ExpressionType.SIMPLE, "cylinder coordinate[s] at %locations% with radius %number%(,| and) yaw %number%(,| and) height %number%");
		Skript.registerExpression(ExprLocationHelix.class, Location.class, ExpressionType.SIMPLE, "heli(x|xes|ces) at %locations% with radius %number%(,| and) height %number%(,| and) step[(height|size)] %number%(,| and) density %number%");
		Skript.registerExpression(ExprLocationLine.class, Location.class, ExpressionType.SIMPLE, "line[s] (between|from) %locations% (and|to) %location% with density %number%");
		Skript.registerExpression(ExprLocationLineLoc.class, Location.class, ExpressionType.SIMPLE, "line[ar] (coordinate[s]|position[s]|location[s]) %number% from %locations% to %location%");
		Skript.registerExpression(ExprLocationLinkAll.class, Location.class, ExpressionType.SIMPLE, "%locations% (linked|connected) with density %number%");
		Skript.registerExpression(ExprLocationMidpoint.class, Location.class, ExpressionType.SIMPLE, "[location ]midpoint of %locations%");
		Skript.registerExpression(ExprLocationMove.class, Location.class, ExpressionType.SIMPLE, "%locations% with center %location% (moved|shifted) to %location%", "%locations% with center %location% (moved|shifted) to %location%");
		Skript.registerExpression(ExprLocationOffset.class, Location.class, ExpressionType.SIMPLE, "[location[s]] %locations% offset by %vectors%");
		Skript.registerExpression(ExprLocationPath.class, Location.class, ExpressionType.SIMPLE, "[location ]path between %locations% with density %number%");
		Skript.registerExpression(ExprLocationPolygon.class, Location.class, ExpressionType.SIMPLE, "polygon[s] at %locations% with %integer% (vertex|vertices|vertexes|points)(,| and) radius %number%");
		Skript.registerExpression(ExprLocationPolygonOutline.class, Location.class, ExpressionType.SIMPLE, "polygon[s] outline[s] at %locations% with %integer% (vertex|vertices|vertexes|points)(,| and) radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprLocationReflection.class, Location.class, ExpressionType.SIMPLE, "%locations% (mirrored|reflected) at %location%[ (in|with) direction [of ]%-vector%]");
		Skript.registerExpression(ExprLocationRotate.class, Location.class, ExpressionType.SIMPLE, "%locations% rotated around %vector% at %location% (with angle|by) %number%[ degree[s]]");
		Skript.registerExpression(ExprLocationRotXYZ.class, Location.class, ExpressionType.SIMPLE, "%locations% rotated around (1¦x|2¦y|3¦z)(-| )axis at %location% (with angle|by) %number%[ degree[s]]");
		Skript.registerExpression(ExprLocationScale.class, Location.class, ExpressionType.SIMPLE, "%locations% scaled at %location% by %number%[ (in|with|and) direction [of ]%-vector%]");
		Skript.registerExpression(ExprLocationSphere.class, Location.class, ExpressionType.SIMPLE, "sphere[s] at %locations% with radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprLocationSphereLoc.class, Location.class, ExpressionType.SIMPLE, "spher(e|ic[al]) (coordinate[s]|position[s]|location[s]) at %locations% with radius %number%(,| and) yaw %number%(,| and) pitch %number%");
		Skript.registerExpression(ExprLocationSphereRand.class, Location.class, ExpressionType.SIMPLE, "random sphere[s] at %locations% with radius %number%(,| and) density %number%");

		Skript.registerExpression(ExprVectorCircle.class, Vector.class, ExpressionType.SIMPLE, "[vector ]circle with radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprVectorCube.class, Vector.class, ExpressionType.SIMPLE, "[vector ]cube with radius %number%");
		Skript.registerExpression(ExprVectorCubeOutline.class, Vector.class, ExpressionType.SIMPLE, "[vector ]cube outline with radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprVectorHelix.class, Vector.class, ExpressionType.SIMPLE, "[vector ]helix with radius %number%(,| and) height %number%(,| and) step[(height|size)] %number%(,| and) density %number%");
		Skript.registerExpression(ExprVectorInFrame.class, Vector.class, ExpressionType.SIMPLE, "[vector ]%vectors% in %frame%");
		Skript.registerExpression(ExprVectorLine.class, Vector.class, ExpressionType.SIMPLE, "[vector ]line with length %number%(,| and) density %number%");
		Skript.registerExpression(ExprVectorLineBetweenVectors.class, Vector.class, ExpressionType.SIMPLE, "vector line (between|from) %vector% (and|to) %vector% with density %number%");
		Skript.registerExpression(ExprVectorLinkAll.class, Vector.class, ExpressionType.SIMPLE, "vector[s] %vectors% (linked|connected) with density %number%");
		Skript.registerExpression(ExprVectorMidpoint.class, Vector.class, ExpressionType.SIMPLE, "vector midpoint of %vectors%");
		Skript.registerExpression(ExprVectorMove.class, Vector.class, ExpressionType.SIMPLE, "[vector[s] ]%vectors% moved by %vectors%");
		Skript.registerExpression(ExprVectorOffset.class, Vector.class, ExpressionType.SIMPLE, "vector[s] %vectors% offset by %vectors%");
		Skript.registerExpression(ExprVectorPath.class, Vector.class, ExpressionType.SIMPLE, "vector[s] path between %vectors% with density %number%");
		Skript.registerExpression(ExprVectorPolygon.class, Vector.class, ExpressionType.SIMPLE, "[vector ]polygon with %integer% (vertex|vertices|vertexes|points)(,| and) radius %number%");
		Skript.registerExpression(ExprVectorPolygonOutline.class, Vector.class, ExpressionType.SIMPLE, "[vector ]polygon outline with %integer% (vertex|vertices|vertexes|points)(,| and) radius %number%(,|and) density %number%");
		Skript.registerExpression(ExprVectorReflection.class, Vector.class, ExpressionType.SIMPLE, "vector[s] %vectors% (mirrored|reflected) at %vector%[ (in|with) direction [of ]%-vector%]");
		Skript.registerExpression(ExprVectorRotate.class, Vector.class, ExpressionType.SIMPLE, "[vector[s] ]%vectors% rotated around %vector% (with angle|by) %number%[ degree[s]]");
		Skript.registerExpression(ExprVectorRotateXYZ.class, Vector.class, ExpressionType.SIMPLE, "[vector[s] ]%vectors% rotated around (1¦x|2¦y|3¦z)(-| )axis (with angle|by) %number%[ degree[s]]");
		Skript.registerExpression(ExprVectorScale.class, Vector.class, ExpressionType.SIMPLE, "[vector[s] ]%vectors% scaled by %number%[ (in|with|and) direction [of ]%-vector%]");
		Skript.registerExpression(ExprVectorSphere.class, Vector.class, ExpressionType.SIMPLE, "[vector ]sphere with radius %number%(,| and) density %number%");
		Skript.registerExpression(ExprVectorSphereRand.class, Vector.class, ExpressionType.SIMPLE, "[vector ]random sphere with radius %number%(,| and) density %number%");
	}

	public static Biosphere getPlugin() {
		return plugin;
	}
}
