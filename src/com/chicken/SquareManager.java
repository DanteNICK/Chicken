package com.chicken;

import java.util.ArrayList;
import java.util.Random;

import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class SquareManager {

	public Element[][] matrix;
	public static Element el_1 = null;
	public static Element el_2 = null;

	public Random randomIndex = new Random();
	public TextureRegion textureRegion;

	public int width = Options.ELEMENT_CELL_SIZE;
	public int height = Options.ELEMENT_CELL_SIZE;

	public SquareManager() {
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

				// matrix[i][j] = new Element(Options.CELL_THICK + width * j,
				// -height * i - height, i, j, textureRegion);
				matrix[i][j] = new Element(randomIndex.nextFloat()
						* Options.CAMERA_HEIGHT, randomIndex.nextFloat()
						* Options.CAMERA_WIDTH, i, j, textureRegion);
				matrix[i][j].create();
				matrix[i][j].index = index;
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

			// Change elements in matrix.
			matrix[el_1.rowNumber][el_1.collNumber] = el_2;
			matrix[el_2.rowNumber][el_2.collNumber] = el_1;

			// coincidence();
			if (coincidenceAlternative()) {
				// Change position of el_1 and el_2. Try to delete this code by
				// adding of setMatrixPosition(row,col)!
				float x = el_1.getX();
				float y = el_1.getY();
				el_1.setPosition(el_2.getX(), el_2.getY());
				el_2.setPosition(x, y);

				// Change row number and count number of el_1 and el_2.
				int r = el_1.rowNumber;
				int c = el_1.collNumber;
				el_1.rowNumber = el_2.rowNumber;
				el_1.collNumber = el_2.collNumber;
				el_2.rowNumber = r;
				el_2.collNumber = c;

			} else {
				// Change elements el_1 and ek_2 in start state if we don't have
				// right groups of elements.
				matrix[el_1.rowNumber][el_1.collNumber] = el_1;
				matrix[el_2.rowNumber][el_2.collNumber] = el_2;
			}

			el_1.setScale(1);
			el_2.setScale(1);

			el_1 = null;
			el_2 = null;

		}
	}

	public boolean movingRezult() {
		Element a = el_1;
		Element b = el_2;
		el_1.rowNumber = b.rowNumber;
		el_1.collNumber = b.collNumber;
		el_2.rowNumber = a.rowNumber;
		el_2.collNumber = a.collNumber;

		boolean movingRezult = false;
		if (coincidence())
			movingRezult = true;
		else {
			movingRezult = false;
			el_1.rowNumber = a.rowNumber;
			el_1.collNumber = a.collNumber;
			el_2.rowNumber = b.rowNumber;
			el_2.collNumber = b.collNumber;
		}
		return movingRezult;
	}

	public boolean coincidence() {
		boolean coincidenceExist = false;
		ArrayList<Element> currentElements = new ArrayList<Element>();
		currentElements.add(matrix[0][0]);
		// search across rows
		for (int row = 0; row < Options.COUNT_OF_ROW; row++)
			for (int col = 0; col < Options.COUNT_OF_COLL; col++) {
				if (currentElements.contains(matrix[row][col]))
					currentElements.add(matrix[row][col]);
				else {
					if (currentElements.size() >= 3) {
						for (int i = currentElements.size(); i > 0; i--) {
							matrix[row][col - i].setRotation(30);
							coincidenceExist = true;
						}
					} else {
						currentElements.clear();
						currentElements.add(matrix[row][col]);
					}
				}
			}
		// search by columns
		for (int col = 0; col < Options.COUNT_OF_COLL; col++)
			for (int row = 0; row < Options.COUNT_OF_ROW; row++) {
				if (currentElements.contains(matrix[row][col]))
					currentElements.add(matrix[row][col]);
				else {
					if (currentElements.size() >= 3) {
						for (int i = currentElements.size(); i > 0; i--) {
							matrix[row - i][col].setRotation(30);
							coincidenceExist = true;
						}
					} else {
						currentElements.clear();
						currentElements.add(matrix[row][col]);
					}
				}

			}

		return coincidenceExist;
	}

	public boolean coincidenceAlternative() {
		// TODO: Delete after verification.
		for (int i = 0; i < Options.COUNT_OF_ROW; i++) {
			for (int j = 0; j < Options.COUNT_OF_COLL; j++) {
				matrix[i][j].setRotation(0);
			}
		}

		boolean isChange = false;

		for (int row = 0; row < Options.COUNT_OF_ROW; row++) {
			for (int coll_start = 0; coll_start < Options.COUNT_OF_COLL; coll_start++) {
				int coll_i = 0;
				for (; coll_start + coll_i + 1 < Options.COUNT_OF_COLL
						&& matrix[row][coll_start + coll_i + 1].index == matrix[row][coll_start].index; coll_i++)
					;
				if (coll_i >= 2) {
					for (int i = 0; i <= coll_i; i++) {
						// TODO: Change for something right.
						matrix[row][coll_start + i].setRotation(30);
					}
					isChange = true;
				}
				coll_start = coll_start + coll_i;
			}
		}

		for (int coll = 0; coll < Options.COUNT_OF_COLL; coll++) {
			for (int row_start = 0; row_start < Options.COUNT_OF_ROW; row_start++) {
				int row_i = 0;
				for (; row_start + row_i + 1 < Options.COUNT_OF_ROW
						&& matrix[row_start + row_i + 1][coll].index == matrix[row_start][coll].index; row_i++)
					;
				if (row_i >= 2) {
					for (int i = 0; i <= row_i; i++) {
						// TODO: Change for something right.
						matrix[row_start + i][coll].setRotation(30);
					}
					isChange = true;
				}
				row_start = row_start + row_i;
			}
		}

		return isChange;
	}
}
