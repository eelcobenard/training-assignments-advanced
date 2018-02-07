package com.jme3.texture;

import java.io.IOException;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.texture.Texture.WrapAxis;
import com.jme3.texture.Texture.WrapMode;

public abstract class AbstractTexture3D extends Texture {

	private WrapMode wrapS = WrapMode.EdgeClamp;
	private WrapMode wrapT = WrapMode.EdgeClamp;
	private WrapMode wrapR = WrapMode.EdgeClamp;

	@Override
	public Texture createSimpleClone(Texture rVal) {
		rVal.setWrap(WrapAxis.S, wrapS);
		rVal.setWrap(WrapAxis.T, wrapT);
		rVal.setWrap(WrapAxis.R, wrapR);
		return super.createSimpleClone(rVal);
	}

	/**
	 * <code>setWrap</code> sets the wrap mode of this texture for a particular
	 * axis.
	 *
	 * @param axis
	 *            the texture axis to define a wrapmode on.
	 * @param mode
	 *            the wrap mode for the given axis of the texture.
	 * @throws IllegalArgumentException
	 *             if axis or mode are null
	 */
	public void setWrap(WrapAxis axis, WrapMode mode) {
		if (mode == null) {
			throw new IllegalArgumentException("mode can not be null.");
		} else if (axis == null) {
			throw new IllegalArgumentException("axis can not be null.");
		}
		switch (axis) {
		case S:
			this.wrapS = mode;
			break;
		case T:
			this.wrapT = mode;
			break;
		case R:
			this.wrapR = mode;
			break;
		}
	}

	/**
	 * <code>setWrap</code> sets the wrap mode of this texture for all axis.
	 *
	 * @param mode
	 *            the wrap mode for the given axis of the texture.
	 * @throws IllegalArgumentException
	 *             if mode is null
	 */
	public void setWrap(WrapMode mode) {
		if (mode == null) {
			throw new IllegalArgumentException("mode can not be null.");
		}
		this.wrapS = mode;
		this.wrapT = mode;
		this.wrapR = mode;
	}

	/**
	 * <code>getWrap</code> returns the wrap mode for a given coordinate axis on
	 * this texture.
	 *
	 * @param axis
	 *            the axis to return for
	 * @return the wrap mode of the texture.
	 * @throws IllegalArgumentException
	 *             if axis is null
	 */
	public WrapMode getWrap(WrapAxis axis) {
		switch (axis) {
		case S:
			return wrapS;
		case T:
			return wrapT;
		case R:
			return wrapR;
		}
		throw new IllegalArgumentException("invalid WrapAxis: " + axis);
	}

	public WrapMode getWrapS() {
		return wrapS;
	}

	public void setWrapS(WrapMode wrapS) {
		this.wrapS = wrapS;
	}

	public WrapMode getWrapT() {
		return wrapT;
	}

	public void setWrapT(WrapMode wrapT) {
		this.wrapT = wrapT;
	}

	public WrapMode getWrapR() {
		return wrapR;
	}

	public void setWrapR(WrapMode wrapR) {
		this.wrapR = wrapR;
	}

	@Override
	public void write(JmeExporter e) throws IOException {
		super.write(e);
		OutputCapsule capsule = e.getCapsule(this);
		capsule.write(this.wrapS, "wrapS", WrapMode.EdgeClamp);
		capsule.write(this.wrapT, "wrapT", WrapMode.EdgeClamp);
		capsule.write(this.wrapR, "wrapR", WrapMode.EdgeClamp);
	}

	@Override
	public void read(JmeImporter e) throws IOException {
		super.read(e);
		InputCapsule capsule = e.getCapsule(this);
		wrapS = capsule.readEnum("wrapS", WrapMode.class, WrapMode.EdgeClamp);
		wrapT = capsule.readEnum("wrapT", WrapMode.class, WrapMode.EdgeClamp);
		wrapR = capsule.readEnum("wrapR", WrapMode.class, WrapMode.EdgeClamp);
	}

	@Override
	public abstract Type getType();

	@Override
	public abstract Texture createSimpleClone();

}
