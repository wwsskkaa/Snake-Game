

import java.awt.*;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

public class Fruit {

	int row, column;
	int blocksize = snake.blockSize;
	private static Random randomplace = new Random();
	private String[] files = { "apple.png", "watermelon.png", "Grapefruit.png", "kiwi.png", "pear.png", "cherry2.png" };
	private String filename = "watermelon.png";

	public Fruit() {
		this.row=randomplace.nextInt(snake.rowNum - 10) + 5;
		this.column=randomplace.nextInt(snake.colNum - 10) + 5;
	}

	public Fruit(int r, int c)// constructor
	{
		this.row = r;
		this.column = c;
	}

	public void setRow(int r) {// set method for the row
		this.row = r;
	}

	public void setCol(int c) {// set method for the column
		this.column = c;
	}

	public int getRow() {// get method for the row
		return row;
	}

	public int getCol() {// get method for the column
		return column;
	}

	public Rectangle getRect() {
		int xindex = column * blocksize;
		int yindex = row * blocksize;
		Rectangle a = new Rectangle(xindex, yindex, blocksize, blocksize);
		return a;
	}

	public void setFilename(String f) {
		filename = f;
	}

	public void nextFruit() {
		setFilename(randomFruit());
		setRow(randomplace.nextInt(snake.rowNum - 10) + 5);
		setCol(randomplace.nextInt(snake.colNum - 10) + 5);
	}

	public String randomFruit() {
		filename = files[randomplace.nextInt(6)];
		return filename;
	}

	public void draw(Graphics g) throws Exception {

		Image i = ImageIO.read(new File(filename));
		Image s = i.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		g.drawImage(s, blocksize * column, blocksize * row, null);
	}

}
