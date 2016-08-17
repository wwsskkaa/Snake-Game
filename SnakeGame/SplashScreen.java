

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JWindow {
	private int showtime;
	private static Timer timer;
	private static int counter = 0;
	private static JProgressBar progress = new JProgressBar();

	public SplashScreen(int d) 
	{
		showtime = d;
	}

	public void exhibitSplash() throws Exception {
		JPanel Screen = (JPanel) getContentPane();
		Screen.setBackground(new Color(255, 250, 240));
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();//set to the center of the screen
		setBounds((sc.width-800)/2, (sc.height-600)/2, 800, 600);
		JLabel welcome = new JLabel(new ImageIcon("newlogo.png"));
		EmptyBorder border = new EmptyBorder(15, 0, 0, 0);
		welcome.setBorder(border);

		JPanel instruc = new JPanel(new GridLayout(5, 1));
		instruc.setBackground(new Color(255, 250, 240));
		JLabel intro = new JLabel(
				"This game is created by Shuang Wu, 20640582");
		intro.setHorizontalAlignment(JLabel.CENTER);
		intro.setFont(new Font("Andalus", Font.PLAIN, 20));
		intro.setForeground(new Color(255, 127, 80));

		Image image = ImageIO.read(new File("snakeheadlogo.png"));
		Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel labelpng = new JLabel(new ImageIcon(scaledImage));

		JLabel label1 = new JLabel("   (1)Keyboard shortcuts: P(Pause) S(Resume) R(Restart) ");
		JLabel label2 = new JLabel(
				"   (2)Use Arrow keys to lead the snake to eat fruit and grow longer");
		JLabel label3 = new JLabel(
				"   (3)Prevent the snake from touching its tail or four boundaries.");
		label1.setFont(new Font("Andalus", Font.BOLD, 17));

		label1.setForeground(new Color(255, 127, 80));
		label2.setFont(new Font("Andalus", Font.BOLD, 17));
		label2.setForeground(new Color(255, 127, 80));
		label3.setFont(new Font("Andalus", Font.BOLD, 17));
		label3.setForeground(new Color(255, 127, 80));

		instruc.add(intro);
		instruc.add(labelpng);
		instruc.add(label1);
		instruc.add(label2);
		instruc.add(label3);

		progress.setMaximum(50);
		Screen.add(progress, BorderLayout.SOUTH);
		LoadSplash();
		Screen.add(welcome, BorderLayout.NORTH);
		Screen.add(instruc, BorderLayout.CENTER);
		Screen.setBorder(BorderFactory.createLineBorder(new Color(238, 92, 66), 5));
		setVisible(true);
		try {
			Thread.sleep(showtime);
		} catch (Exception e) {
		}

		setVisible(false);
	}

	public void LoadSplash() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				progress.setValue(++counter);
				if (counter == 600) {
					timer.stop();
					setVisible(false);
					return;
				}
			}
		};
		timer = new Timer(100, al);
		timer.start();
	}

	public void VisiblethenDisappear() throws Exception {
		exhibitSplash();
	}
}