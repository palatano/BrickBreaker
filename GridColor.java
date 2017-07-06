import java.awt.Color;
import java.awt.Graphics;


public class GridColor extends GameObj {
	public static int WIDTH = 330;
	public static int HEIGHT = 270;
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
	public GridColor(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, WIDTH, HEIGHT, courtWidth,
				courtHeight);
		
	}
	
	

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos_x, pos_y, width, height);
	}
 

}
