import java.awt.Color;
import java.awt.Graphics;


public class PowerUpBlock2 extends GridSquare {
	public static int WIDTH = 28;
	public static int HEIGHT = 10;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	private Color color;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public PowerUpBlock2(int courtWidth, int courtHeight, int x, int y) {
		super(INIT_VEL_X, INIT_VEL_Y, x, y);
	}
	
	

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos_x, pos_y, width, height);
	}
 


}
