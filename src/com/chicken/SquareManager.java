package com.chicken;

import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class SquareManager {

	public Element[][] matrix;

	public TextureRegion textureRegion;

	public int width, height = 64;

	public SquareManager() {
		textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				GameActivity.resourcesAtlas, GameActivity.context, "red.png",
				800, 800);
		matrix = new Element[7][7];
	}

	public void create() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				matrix[i][j] = new Element(width * j, -height * i - height, i,
						j, textureRegion);
				matrix[i][j].create();
			}
		}
	}

	public void update() {
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				matrix[i][j].update();
	}

}
