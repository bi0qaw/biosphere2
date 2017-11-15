package io.github.bi0qaw.biosphere.util;

import org.bukkit.util.Vector;

public class Frame implements Cloneable {

	private static Vector tempv1 = new Vector();

	private Vector u;
	private Vector v;
	private Vector w;

	public Frame() {
		this.u = new Vector(1, 0, 0);
		this.v = new Vector(0, 1, 0);
		this.w = new Vector(0, 0, 1);
	}

	public Frame setFromFrame(Frame f) {
		this.u = f.getU().clone();
		this.v = f.getV().clone();
		this.w = f.getW().clone();
		return this;
	}

	public Frame setFromYawPitch(float yaw, float pitch ) {
		this.u = VectorMath.fromYawPitch(yaw, pitch);
		this.w = VectorMath.fromYawPitch(yaw + 90, 0);
		this.v = w.clone().crossProduct(u);
		return this;
	}

	public Vector getU() {
		return u;
	}

	public Vector getV() {
		return v;
	}

	public Vector getW() {
		return w;
	}

	public float getYaw() {
		return VectorMath.wrapAngleDeg(VectorMath.getYaw(this.getW()) - 90);
	}

	public Frame setYaw(float yaw) {
		this.setFromYawPitch(yaw, this.getPitch());
		return this;
	}

	public float getPitch() {
		return VectorMath.getPitch(this.getU());
	}

	public Frame setPitch(float pitch) {
		this.setFromYawPitch(this.getYaw(), pitch);
		return this;
	}

	public Vector transform(Vector v) {
		tempv1.setX(v.getX()).setY(v.getY()).setZ(v.getZ());
		v.zero();
		VectorMath.addMul(v, this.getU(), tempv1.getX());
		VectorMath.addMul(v, this.getV(), tempv1.getY());
		VectorMath.addMul(v, this.getW(), tempv1.getZ());
		return v;
	}

	public Frame clone() {
		return new Frame().setFromFrame(this);
	}
}
