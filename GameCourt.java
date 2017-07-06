/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

import java.util.TreeSet;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private GridColor gridcolor;
	private Square square1; // the paddle, keyboard control
	private Square2 square2; // the paddle, keyboard control
	private Ball ball; // the ball, bounces
	private PowerUpBlock power;
	private PowerUpBlock2 power2;
	private PowerUpBlock3 power3;
	private boolean reverse;

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)
	// Game constants
	public static final int COURT_WIDTH = 330;
	public static final int COURT_HEIGHT = 270;
	public static final int SQUARE_VELOCITY = 8;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	private ArrayList<GridSquare> bricks;
	private int lives;
	private int score;
	private String ScoreString;
	private int level;
	private String LevelString;

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bricks = new ArrayList<GridSquare>(18);
		lives = 3;
		score = 0;
		level = 1;
		ScoreString = " Score = " + score;
		LevelString = " Level = " + Integer.toString(level);

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					square1.v_x = -SQUARE_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					square1.v_x = SQUARE_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_S)
					square2.v_y = SQUARE_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_W)
					square2.v_y = -SQUARE_VELOCITY;
			}

			public void keyReleased(KeyEvent e) {
				square1.v_x = 0;
				square1.v_y = 0;
				square2.v_x = 0;
				square2.v_y = 0;
			}
		});

		this.status = status;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		reverse = false;
		bricks = new ArrayList<GridSquare>();
		lives = 3;
		score = 0;
		level = 1;
		LevelString = " Level = " + Integer.toString(level);
		square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 60, 10);
		square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 60);
		gridcolor = new GridColor(COURT_WIDTH, COURT_HEIGHT);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		for (int x = 8; x < 10; x++) {
			for (int y = 3; y < 6; y++) {
				if (x == 8 && y == 5) {
					power = new PowerUpBlock(COURT_WIDTH, COURT_HEIGHT, 29 * x,
							13 * y);
					bricks.add(power);
				} else if (x == 8 && y == 3) {
					power2 = new PowerUpBlock2(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y);
					bricks.add(power2);
				} else if (x == 9 && y == 5) {
					power3 = new PowerUpBlock3(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y);
					bricks.add(power3);
				} else {
					bricks.add(new GridSquare(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y));
				}

			}
		}

		playing = true;
		ScoreString = " Score = " + score;
		status.setText("Lives = 3" + ScoreString + LevelString);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	public void level2() {
		reverse = false;
		bricks = new ArrayList<GridSquare>();
		square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 60, 10);
		square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 60);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		for (int x = 3; x < 9; x++) {
			for (int y = 3; y < 9; y++) {
				if (x == 8 && y == 8) {
					power2 = new PowerUpBlock2(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y);
					bricks.add(power2);
				} else if (x == 8 && y == 5) {
					power = new PowerUpBlock(COURT_WIDTH, COURT_HEIGHT, 29 * x,
							13 * y);
					bricks.add(power);
				} else if (x < y) {

				} else {
					bricks.add(new GridSquare(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y));
				}

			}
		}

		ScoreString = " Score = " + score;
		status.setText("Lives = 3" + ScoreString + LevelString);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	public void level3() {
		reverse = false;
		bricks = new ArrayList<GridSquare>();
		square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 60, 10);
		square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 60);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		for (int x = 4; x < 11; x++) {
			for (int y = 3; y < 10; y++) {
				if (x == 9 && y == 8) {
					power = new PowerUpBlock(COURT_WIDTH, COURT_HEIGHT, 29 * x,
							13 * y);
					bricks.add(power);
				} else if (x % 3 == 0 || y % 3 == 0) {

				} else {
					bricks.add(new GridSquare(COURT_WIDTH, COURT_HEIGHT,
							29 * x, 13 * y));
				}
			}
		}

		ScoreString = " Score = " + score;
		status.setText("Lives = 3" + ScoreString + LevelString);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			if (bricks.isEmpty()) {
				level = level + 1;
				LevelString = " Level = " + Integer.toString(level);
				if (level == 4) {
					status.setText("You win, for now.");
					playing = false;
				} else if (level == 3) {
					level3();
				} else {
					level2();
				}
			}
			// advance the square and snitch in their
			// current direction.
			if (reverse == false) {
				// Move normally
				square1.move2();
				square2.move3();
			} else {
				// Powerup active, move in reverse
				square1.move4();
				square2.move5();
			}
			ball.move();

			// Ball hits bottom wall
			if (ball.hitWall() == Direction.DOWN) {
				lives = lives - 1;
				status.setText("Lives = " + Integer.toString(lives)
						+ ScoreString + LevelString);
				ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
				square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 60, 10);
				square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 60);
				if (lives == 0) {
					playing = false;
					status.setText("GAME OVER");
				}
			}
			// Ball hits Left wall
			if (ball.hitWall() == Direction.LEFT) {
				lives = lives - 1;
				status.setText("Lives = " + Integer.toString(lives)
						+ ScoreString + LevelString);
				ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
				square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 60, 10);
				square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 60);
				if (lives == 0) {
					playing = false;
					status.setText("GAME OVER");
				}
			}
			ball.bounce(ball.hitWall());
			// When the ball hits a brick
			for (int i = 0; i < bricks.size(); i++) {
				// Ball hits red powerup
				if (bricks.get(i) == power && bricks.get(i).intersects(ball)) {
					square1 = new Square(COURT_WIDTH, COURT_HEIGHT, 100, 10);
					square2 = new Square2(COURT_WIDTH, COURT_HEIGHT, 10, 100);
					ball.bounce(ball.hitObj(bricks.get(i)));
					bricks.remove(i);
					score = score + 100;
					ScoreString = "Score = " + score;
					status.setText("Lives = " + Integer.toString(lives)
							+ ScoreString + LevelString);
				}
				// Ball hits blue powerup
				else if (bricks.get(i) == power2
						&& bricks.get(i).intersects(ball)) {
					reverse = true;
					ball.bounce(ball.hitObj(bricks.get(i)));
					bricks.remove(i);
					score = score + 100;
					ScoreString = "Score = " + score;
					status.setText("Lives = " + Integer.toString(lives)
							+ ScoreString + LevelString);
				}
				// Ball hits Green powerup
				else if (bricks.get(i) == power3
						&& bricks.get(i).intersects(ball)) {
					ball.bounce(ball.hitObj(bricks.get(i)));
					bricks.remove(i);
					score = score + 500;
					ScoreString = "Score = " + score;
					status.setText("Lives = " + Integer.toString(lives)
							+ ScoreString + LevelString);
				} else if (bricks.get(i).intersects(ball)) {
					ball.bounce(ball.hitObj(bricks.get(i)));
					bricks.remove(bricks.get(i));
					score = score + 100;
					ScoreString = "Score = " + score;
					status.setText("Lives = " + Integer.toString(lives)
							+ ScoreString + LevelString);
				}
			}
			// check for the game end conditions
			if (square1.intersects(ball)) {
				ball.bounce(Direction.DOWN);
			}
			if (square2.intersects(ball)) {
				ball.bounce(Direction.LEFT);
			}

			// update the display
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gridcolor.draw(g);
		square1.draw(g);
		square2.draw(g);
		ball.draw(g);
		// Draw the bricks with the list.
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).draw(g);
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
