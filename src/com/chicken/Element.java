package com.chicken;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.modifier.IModifier;

public class Element extends Sprite {

	public int rowNumber, collNumber;
	public int index;

	private boolean isMove = false;

	public Element(final float x, final float y, final int rowNumber,
			final int collNumber, TextureRegion textureRegion) {

		super(x, y, textureRegion.deepCopy());
		this.rowNumber = rowNumber;
		this.collNumber = collNumber;
	}

	public void create() {
		GameActivity.scene.registerTouchArea(this);
		GameActivity.scene.attachChild(this);
	}

	public void update() {

		// if (Options.CAMERA_HEIGHT - Options.ELEMENT_CELL_SIZE * (rowNumber +
		// 1) > getY())
		// setPosition(getX(), getY() + 4);

		if (!isMove) {
			float rightX = Options.ELEMENT_CELL_SIZE * collNumber
					+ Options.CELL_THICK;
			float rightY = Options.CAMERA_HEIGHT - Options.ELEMENT_CELL_SIZE
					* rowNumber - Options.ELEMENT_CELL_SIZE
					+ Options.CELL_THICK;
			// - (Options.ELEMENT_CELL_SIZE / 2) * (collNumber % 2);
			if (getX() != rightX || getY() != rightY) {
				isMove = true;
				MoveModifier moveModifier = new MoveModifier(5f, getX(),
						rightX, getY(), rightY, new IEntityModifierListener() {
							@Override
							public void onModifierFinished(
									IModifier<IEntity> arg0, IEntity arg1) {
								Element.this.isMove = false;
							}

							@Override
							public void onModifierStarted(
									IModifier<IEntity> arg0, IEntity arg1) {
							}
						});
				registerEntityModifier(moveModifier);
			}
		}
	}

	/*
	 * @Override public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
	 * final float pTouchAreaLocalX, final float pTouchAreaLocalY) { if
	 * (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) { ScaleModifier
	 * modifier = new ScaleModifier(0.5f, 1f, 0.2f);
	 * modifier.setRemoveWhenFinished(true); modifier.addModifierListener(new
	 * IModifierListener<IEntity>() {
	 * 
	 * @Override public void onModifierFinished(IModifier<IEntity> arg0, IEntity
	 * arg1) { //arg1.setScale(1f); }
	 * 
	 * @Override public void onModifierStarted(IModifier<IEntity> arg0, IEntity
	 * arg1) {
	 * 
	 * }
	 * 
	 * }); registerEntityModifier(modifier); }
	 * 
	 * if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) { ScaleModifier
	 * modifier = new ScaleModifier(0.5f, 0.2f, 1f);
	 * modifier.setRemoveWhenFinished(true); registerEntityModifier(modifier); }
	 * return true; } }
	 */
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			// ScaleModifier modifier = new ScaleModifier(0.5f, 1f, 0.7f);
			// modifier.setRemoveWhenFinished(true);
			// registerEntityModifier(modifier);

			if (SquareManager.el_1 == null) {
				SquareManager.el_1 = Element.this;
				SquareManager.el_1.setScale(0.7f);
			} else {
				if (SquareManager.el_1 == this) {
					SquareManager.el_1 = null;
				} else if (Math.abs(SquareManager.el_1.rowNumber - rowNumber)
						+ Math.abs(SquareManager.el_1.collNumber - collNumber) == 1) {
					SquareManager.el_2 = Element.this;
					SquareManager.el_2.setScale(0.7f);
				}
			}

		}

		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY);

	}
}