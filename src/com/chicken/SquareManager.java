package com.chicken;

public class SquareManager {

	public Element[][] matrix;

	public int width, height = 64;

	public SquareManager() {
		matrix = new Element[7][7];
	}

	public void create() {
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				matrix[i][j] = new Element(width * j, -height * i - height);
	}

}
