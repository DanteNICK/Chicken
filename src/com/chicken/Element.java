package com.chicken;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Element extends Sprite {

	public int rowNumber, collNumber;

	public Element(final float x, final float y, final int rowNumber,
			final int collNumber, TextureRegion textureRegion) {

		super(x, y, textureRegion.deepCopy());
		this.rowNumber = rowNumber;
		this.collNumber = collNumber;
	}

	public void create() {
		GameActivity.scene.attachChild(this);

	}

	public void update() {

		if (800 - 68 * (rowNumber+1) > getY())
			setPosition(getX(), getY() + 4);

	}
}
