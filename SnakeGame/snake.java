

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//frame for snake
public class snake extends JFrame {

	public boolean gameOver = false; // see if game over or not
	public static final int rowNum = 30;
	public static final int colNum = 40;
	public static final int blockSize = 20;
	public int counter = 0;
	public static int[] speed = {360,330,300,270,240,210,180,150,120,90,70};
	public static int fps = 40;
	public static int snakespeed = speed[7];
	static int putsnakespeed=7;
	public int score = 0;
	public Image image,ss;
	public boolean cannotopen=false;
	public Clip music5 = null;
	public AudioInputStream file5 = null;
	SnakeBody littlesnake = new SnakeBody(this);
	Fruit littlefruit = new Fruit();
	PaintThread paintThread = new PaintThread();

	
	public static void main(String[] args) throws Exception {

		SplashScreen splash = new SplashScreen(6000);
		splash.VisiblethenDisappear();
		if (args.length == 1) {
			int putfps = Integer.parseInt(args[0]);
			if (putfps >= 1 && putfps <= 100) {
				fps = putfps;
			}
		} else if (args.length >= 2) {
			int putfps = Integer.parseInt(args[0]);
			putsnakespeed = Integer.parseInt(args[1]);
			if (putfps >= 1 && putfps <= 100) {
				fps = putfps;
			}
			if (putsnakespeed >= 1 && putsnakespeed <= 10) {
				snakespeed = speed[putsnakespeed];
			}
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					snake frame = new snake();
					frame.startGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_R) {// restarting
				paintThread.restartGame();
			} else if (key == KeyEvent.VK_S)// start the game or resume
			{
				paintThread.pausegame = false;
			} else if (key == KeyEvent.VK_P)// pause the game
			{
				paintThread.pausegame = true;
			}

			littlesnake.keyPressed(e);
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		this.score = s;
	}

	public void startGame() {
		try {
			image = ImageIO.read(new File("chopping.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ss = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		//set the background
		setResizable(false);
		//cannot resize
		setIconImage(Toolkit.getDefaultToolkit().getImage("Watermelonz.png"));
		//set the logo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//set it at the screen center
		Canvas canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800, 600));
		add(canvas);
		//add the canvas to the frame
		pack();
		setLocationRelativeTo(null);
		// center the window on the screen
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		KeyMonitor newkeymoni=new KeyMonitor();
		this.addKeyListener(newkeymoni);
		Thread newthread=new Thread(paintThread);
		newthread.start();
	}

	private class Canvas extends JPanel {

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ss, 0, 0, null);
			g.setColor(new Color(210, 180, 140));
			for (int i = 1; i <= rowNum; i++) {
				int ycod= blockSize * i;
				g.drawLine(0, ycod, colNum*blockSize, ycod);
			}
			for (int i = 1; i <= colNum; i++) {
				int bcod= blockSize * i;
				g.drawLine(bcod, 0, bcod, blockSize*rowNum);
			}
			g.setColor(Color.white);
			g.drawString("CURRENT SCORE:" + score+  "  SPEED: "+putsnakespeed+"  FPS: "+fps, 10, 15);
			try {
				music5 = AudioSystem.getClip();
			} catch (Exception e2) {
				cannotopen=true;
				e2.printStackTrace();
			}
			try {
				file5 = AudioSystem.getAudioInputStream(new File("gameover.wav"));
			} catch (Exception e2) {
				cannotopen=true;
				e2.printStackTrace();
			}
			if (gameOver==true) {
				g.setFont(new Font("Impact", Font.BOLD, 150));
				g.setColor(new Color(238, 232, 205));
				g.drawString("GAME OVER", 30, 270);
				g.setFont(new Font("Impact", Font.PLAIN, 20));
				g.drawString("Final Score: " + score, 340, 340);
				g.drawString("Press R to restart", 325, 390);
				
				if (counter == 0) {
					if(cannotopen!=true)
					{
					try {
							music5.open(file5);
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					music5.start();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
						}
					});
					}
				}
				counter++;
			} else {

				try {
					littlefruit.draw(g);// apple
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					littlesnake.draw(g);// snake
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void gameover() throws Exception {
		gameOver = true;
	}

	private class PaintThread implements Runnable {
		private boolean pausegame = false;
		public void restartGame() {
			score = 0;
			pausegame = false;
			littlesnake = new SnakeBody(snake.this);
			gameOver = false;
			counter = 0;

		}
		public void run() {
			TimerTask timerTask = new TimerTask() {
				public void run() {
					if (!pausegame) {
						repaint();
					}
				}
			};
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(timerTask, 0, 1000 / fps);
			TimerTask timerTaskModel = new TimerTask() {
				
				public void run() {
					if (!pausegame) {
						try {
							littlesnake.touchApple(littlefruit);
							littlesnake.moveSnake();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
			Timer timermodel = new Timer(true);
			timermodel.scheduleAtFixedRate(timerTaskModel, 0, snakespeed);
		}

	}

}