import java.awt.Color;
import java.awt.Graphics;


public class Square2 extends GameObj {
    public static int WIDTH = 60;
    public static int HEIGHT = 10;
	public static final int INIT_X = 20;
	public static final int INIT_Y = 100;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Square2(int courtWidth, int courtHeight, int width, int height) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, width, height, courtWidth,
				courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(pos_x, pos_y, width, height);
	}
 

}
