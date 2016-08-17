

import java.awt.event.KeyEvent;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class SnakeBody {
	private enum Direction 
	{
		LEFT, RIGHT, UP, DOWN
	}
	private snake y;
	private Bodypart head = null;
	private Bodypart tail = null;
	private Bodypart n = new Bodypart(Direction.RIGHT,10,10);
	private int size = 0;
	public Clip music1;
	public Image ii, ss;

	public SnakeBody(snake y1) {
		y=y1;
		size=1;
		head=n;
		tail=n;
		try {
			ii = ImageIO.read(new File("skin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ss=ii.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	}

	private class Bodypart {
		Bodypart prev = null;
		Bodypart next = null;
		int row = 0;
		int col = 0;
		Direction direct = Direction.LEFT;

		Bodypart(Direction d,int r, int c) {
			direct = d;
			row = r;
			col = c;
		}

		void draw(Graphics g) {
			int xcoord = col * snake.blockSize;
			int ycoord = row * snake.blockSize;
			g.drawImage(ss, xcoord, ycoord, null);

		}
	}

	public void keyPressed(KeyEvent e) {
		Direction currentdirection = head.direct;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (currentdirection != Direction.RIGHT) {
				head.direct = Direction.LEFT;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (currentdirection != Direction.LEFT) {
				head.direct = Direction.RIGHT;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (currentdirection != Direction.DOWN) {
				head.direct = Direction.UP;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (currentdirection != Direction.UP) {
				head.direct = Direction.DOWN;
			}
		} else {// do nothing;
		}
	}

	public void creatingHead(int step) {
		Bodypart newhead = null;
		Direction currentheaddirection = head.direct;
		int r = head.row;
		int c = head.col;
		if(currentheaddirection == Direction.UP)
		{
			r -= step;
		}
		else if(currentheaddirection == Direction.DOWN)
		{
			r += step;
		}
		else if(currentheaddirection == Direction.LEFT)
		{
			c -= step;
		}
		else if (currentheaddirection == Direction.RIGHT)
		{
			c += step;
		}
		newhead = new Bodypart(currentheaddirection,r, c);
		newhead.next = head;// update the head
		head.prev = newhead;
		head = newhead;
		size++;
	}

	public void moveSnake() throws Exception{
		move();
	}

	public void draw(Graphics g) throws Exception {
		if (size !=0) {
			Bodypart n = head;
			while (n != null) {
				n.draw(g);
				n = n.next;
			}
		}
	}

	public void move() throws Exception {
		creatingHead(1);
		RemovingTail();
		Loseornot();
	}

	public void Loseornot() throws Exception {
		if (head.row < 0 || head.row >= (snake.rowNum) || head.col < 0 || head.col >= (snake.colNum)) {
			y.gameover();
		} else {
			Bodypart n = head.next;
			while(n!=null) {
				if (head.col==n.col&&head.row==n.row) {// head touch tail
					y.gameover();
				}
				n = n.next;
			}
		}
	}

	public void RemovingTail() {
		if (size!=0) {
			tail=tail.prev;
			tail.next = null;
		}
	}

	public void touchApple(Fruit fruit) throws Exception {
		music1 = AudioSystem.getClip();
		AudioInputStream file = AudioSystem.getAudioInputStream(new File("power.wav"));
		Rectangle fruitcurrent=fruit.getRect();
		Rectangle newblock = new Rectangle(snake.blockSize * head.col, snake.blockSize * head.row,
				snake.blockSize, snake.blockSize);
		if (newblock.intersects(fruitcurrent)) {
			music1.open(file);
			music1.start();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				}
			});
			int newscore = 1 + y.getScore();
			y.setScore(newscore);
			fruit.nextFruit();
			creatingHead(1);
		}
	}


}
