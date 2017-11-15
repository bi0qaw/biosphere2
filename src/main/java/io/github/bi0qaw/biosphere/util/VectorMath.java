package io.github.bi0qaw.biosphere.util;

import org.bukkit.util.Vector;

public class VectorMath {

	public static final double PI = Math.PI;
	public static final double DEG_TO_RAD = PI / 180.0;
	public static final double RAD_TO_DEG =  180.0 / PI;

	public static Vector fromSphericalCoordinates(double radius, double yaw, double pitch) {
		double r = Math.abs(radius);
		double p = pitch * DEG_TO_RAD;
		double sinp = Math.sin(p);
		double x = r * sinp * Math.cos(yaw * DEG_TO_RAD);
		double y = r * Math.cos(p);
		double z = r * sinp * Math.sin(yaw * DEG_TO_RAD);
		return new Vector(x, y, z);
	}

	public static Vector fromCylindricalCoordinates(double radius, double yaw, double height) {
		double r = Math.abs(radius);
		double x = r * Math.cos(yaw * DEG_TO_RAD);
		double z = r * Math.sin(yaw * DEG_TO_RAD);
		return new Vector(x, height, z);
	}

	public static Vector fromYawPitch(float yaw, float pitch) {
		double y = Math.sin(pitch * DEG_TO_RAD);
		double div = Math.cos(pitch * DEG_TO_RAD);
		double x = Math.cos(yaw * DEG_TO_RAD);
		double z = Math.sin(yaw * DEG_TO_RAD);
		return new Vector(x,0, z).multiply(div).setY(y);
	}

	public static float getYaw(Vector vector) {
		if (((Double) vector.getX()).equals((double) 0) && ((Double) vector.getZ()).equals((double) 0)){
			return 0;
		}
		return (float) (Math.atan2(vector.getZ(), vector.getX()) * RAD_TO_DEG);
	}

	public static float getPitch(Vector vector) {
		double xy = Math.sqrt(vector.getX() * vector.getX() + vector.getZ() * vector.getZ());
		return (float) (Math.atan(vector.getY() / xy) * RAD_TO_DEG);
	}

	public static Vector setYaw(Vector vector, float yaw) {
		vector = fromYawPitch(yaw, getPitch(vector));
		return vector;
	}

	public static Vector setPitch(Vector vector, float pitch) {
		vector = fromYawPitch(getYaw(vector), pitch);
		return vector;
	}

	public static Vector addMul(Vector v1, Vector v2, double factor) {
		v1.setX(v1.getX() + v2.getX() * factor);
		v1.setY(v1.getY() + v2.getY() * factor);
		v1.setZ(v1.getZ() + v2.getZ() * factor);
		return v1;
	}

	public static Vector rotX(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vy = new Vector(0, cos, -sin);
		Vector vz = new Vector(0, sin, cos);
		Vector clone = vector.clone();
		vector.setY(clone.dot(vy));
		vector.setZ(clone.dot(vz));
		return vector;
	}

	public static Vector rotY(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vx = new Vector(cos, 0, sin);
		Vector vz = new Vector(-sin, 0, cos);
		Vector clone = vector.clone();
		vector.setX(clone.dot(vx));
		vector.setZ(clone.dot(vz));
		return vector;
	}

	public static Vector rotZ(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vx = new Vector(cos, -sin, 0);
		Vector vy = new Vector(sin, cos, 0);
		Vector clone = vector.clone();
		vector.setX(clone.dot(vx));
		vector.setY(clone.dot(vy));
		return vector;
	}

	public static Vector rot(Vector vector, Vector axis, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector a = axis.clone().normalize();
		double ax = a.getX();
		double ay = a.getY();
		double az = a.getZ();
		Vector rotx = new Vector(cos+ax*ax*(1-cos), ax*ay*(1-cos)-az*sin, ax*az*(1-cos)+ay*sin);
		Vector roty = new Vector(ay*ax*(1-cos)+az*sin, cos+ay*ay*(1-cos), ay*az*(1-cos)-ax*sin);
		Vector rotz = new Vector(az*ax*(1-cos)-ay*sin, az*ay*(1-cos)+ax*sin, cos+az*az*(1-cos));
		double x = rotx.dot(vector);
		double y = roty.dot(vector);
		double z = rotz.dot(vector);
		vector.setX(x).setY(y).setZ(z);
		return vector;
	}

	public static float notchYaw(float yaw){
		float y = yaw - 90;
		if (y < -180){
			y += 360;
		}
		return y;
	}

	public static float notchPitch(float pitch){
		return -pitch;
	}

	public static float fromNotchYaw(float notchYaw){
		float y = notchYaw + 90;
		if (y > 180){
			y -= 360;
		}
		return y;
	}

	public static float fromNotchPitch(float notchPitch){
		return -notchPitch;
	}

	public static float skriptYaw(float yaw){
		float y = yaw - 90;
		if (y < 0){
			y += 360;
		}
		return y;
	}

	public static float skriptPitch(float pitch){
		return -pitch;
	}

	public static float fromSkriptYaw(float yaw){
		float y = yaw + 90;
		if (y > 360){
			y -= 360;
		}
		return y;
	}

	public static float fromSkriptPitch(float pitch){
		return -pitch;
	}

	public static float wrapAngleDeg(float angle) {
		angle %= 360f;
		if (angle <= -180) {
			return angle + 360;
		} else if (angle > 180) {
			return angle - 360;
		} else {
			return angle;
		}
	}

	public static float wrap(float a, float min, float max) {
		if (a < min || a > max) {
			return (a - min) - (float) Math.floor((a-min)/(max-min));
		}
		else {
			return a;
		}
	}

	public static float clamp(float a, float min, float max) {
		if (a < min) {
			return min;
		}
		else if (a > max) {
			return max;
		}
		else {
			return a;
		}
	}

	public static float wrapYaw(float yaw) {
		return wrap(yaw, 0, 360);
	}

	public static float wrapPitch(float pitch) {
		return clamp(pitch, -90, 90);
	}

}
