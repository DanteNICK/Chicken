package com.chicken;

import java.util.Random;

import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class SquareManager1 {

	public Element[][] matrix;
	public static Element el_1 = null;
	public static Element el_2 = null;

	public Random randomIndex = new Random();
	public TextureRegion textureRegion;

	public int width = Options.ELEMENT_CELL_SIZE;
	public int height = Options.ELEMENT_CELL_SIZE;

	public SquareManager1() {
		/*
		 * textureRegion =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(
		 * GameActivity.resourcesAtlas, GameActivity.context, "red.png", 0, 0);
		 * textureRegion =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(
		 * GameActivity.resourcesAtlas, GameActivity.context, "green.png", 0,
		 * 65); textureRegion =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(
		 * GameActivity.resourcesAtlas, GameActivity.context, "blue.png", 0,
		 * 130); textureRegion =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(
		 * GameActivity.resourcesAtlas, GameActivity.context, "yellow.png", 0,
		 * 195);
		 */

		matrix = new Element[Options.COUNT_OF_ROW][Options.COUNT_OF_COLL];
	}

	public void create() {
		for (int i = 0; i < Options.COUNT_OF_ROW; i++) {
			for (int j = 0; j < Options.COUNT_OF_COLL; j++) {

				int index = randomIndex.nextInt(Options.ELEMENTS_COUNT);

				textureRegion = BitmapTextureAtlasTextureRegionFactory
						.createFromAsset(GameActivity.resourcesAtlas,
								GameActivity.context, "element" + index
										+ ".png", 0, Options.ELEMENT_SIZE
										* index);

				matrix[i][j] = new Element(Options.CELL_THICK + width * j,
						-height * i - height, i, j, textureRegion);
				matrix[i][j].create();
			}
		}
	}

	public void update() {
		for (int i = 0; i < Options.COUNT_OF_ROW; i++) {
			for (int j = 0; j < Options.COUNT_OF_COLL; j++) {
				matrix[i][j].update();
			}
		}
		if (el_1 != null && el_2 != null) {

			float x = el_1.getX();
			float y = el_1.getY();
			el_1.setPosition(el_2.getX(), el_2.getY());
			el_2.setPosition(x, y);

			int r = el_1.rowNumber;
			int c = el_1.collNumber;

			el_1.rowNumber = el_2.rowNumber;
			el_1.collNumber = el_2.collNumber;

			el_2.rowNumber = r;
			el_2.collNumber = c;

			matrix[el_1.rowNumber][el_1.collNumber] = el_1;
			matrix[el_2.rowNumber][el_2.collNumber] = el_2;

			el_1 = null;
			el_2 = null;

		}
	}

}
