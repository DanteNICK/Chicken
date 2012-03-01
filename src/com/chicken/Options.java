package com.chicken;

public class Options {
	public static final int CAMERA_WIDTH = 480;
	public static final int CAMERA_HEIGHT = 800;

	public static final int ELEMENT_SIZE = 64;
	public static final int CELL_THICK = 4;
	public static final int ELEMENT_CELL_SIZE = ELEMENT_SIZE + CELL_THICK;

	public static final int ELEMENTS_COUNT = 4;
	

	public static final int COUNT_OF_ROW = Math
			.min(CAMERA_WIDTH, CAMERA_HEIGHT) / ELEMENT_SIZE;
	public static final int COUNT_OF_COLL = COUNT_OF_ROW;

}
