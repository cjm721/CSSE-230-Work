package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.swing.JComponent;

/**
 * This component renders a chart of the trials of random graph generation.
 * 
 * @author Curt Clifton
 */
public class ChartComponent extends JComponent {

	private static final double DOT_SIZE = 3;
	private static final float AVG_WIDTH = 3;
	private static final Color AXIS_COLOR = Color.BLACK;
	private static final Color DOT_COLOR = Color.BLUE;
	private static final Color AVG_COLOR = Color.RED;
	private static final int BORDER = 80;

	private final int[][] results;
	private final int numExpr;
	private final int numEdges;
	private final int size;
	private final double[] averages;

	/**
	 * Constructs a chart plotter for the given trial data
	 * 
	 * @param size
	 *            the size of the test graph
	 * 
	 * @param results
	 *            Must be a non-empty rectangular matrix. Each row represents
	 *            the results of one experimental run. Within each row, column c
	 *            gives the size of the largest connected component after adding
	 *            c random edges to the graph.
	 */
	public ChartComponent(int size, int[][] results) {
		this.size = size;
		this.results = results;
		this.numExpr = results.length;
		this.numEdges = results[0].length;
		this.averages = new double[this.numEdges];
		for (int edge = 0; edge < this.numEdges; edge++) {
			long sum = 0;
			for (int expr = 0; expr < this.numExpr; expr++) {
				sum += results[expr][edge];
			}
			this.averages[edge] = ((double) sum) / this.numExpr;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		final double compWidth = this.getWidth();
		final double compHeight = this.getHeight();
		double sx = (compWidth - 2 * BORDER) / this.numEdges;
		double sy = (compHeight - 2 * BORDER) / this.size;

		// Draws axes
		g2.setColor(AXIS_COLOR);
		g2.drawLine(BORDER, (int) compHeight - BORDER,
				(int) compWidth - BORDER, (int) compHeight - BORDER);
		g2.drawLine(BORDER, (int) compHeight - BORDER, BORDER, BORDER);
		int edgeStep = this.numEdges / 10;
		for (int edge = 0; edge <= this.numEdges; edge += edgeStep) {
			g2.drawString(Integer.toString(edge),
					(int) (BORDER + edge * sx) - 5,
					(int) (compHeight - 2 * BORDER / 3));
		}
		int nStep = this.size / 10;
		for (int n = 0; n <= this.size; n += nStep) {
			g2.drawString(Integer.toString(n), 2 * BORDER / 3,
					(int) (compHeight - BORDER - n * sy));
		}

		// Labels axes, jumping through hoops necessary to draw text sideways
		AffineTransform saveAT = g2.getTransform();
		g2.transform(AffineTransform.getQuadrantRotateInstance(-1));
		g2.drawString("Size of largest connected component",
				(int) (-compHeight / 2) - 100, (int) (BORDER / 2.5));
		g2.setTransform(saveAT);
		g2.drawString("Number of edges added", (int) (compWidth / 2) - 60,
				(int) (compHeight - BORDER / 3));

		// Plots points and generates line showing the averages
		g2.setColor(DOT_COLOR);
		Ellipse2D dot = new Ellipse2D.Double(0, 0, 3, 3);
		Path2D averageLine = new Path2D.Double();
		averageLine.moveTo(BORDER, compHeight - BORDER - this.averages[0] * sy);
		for (int edge = 0; edge < this.numEdges; edge++) {
			averageLine.lineTo(BORDER + edge * sx, compHeight - BORDER
					- this.averages[edge] * sy);
			for (int expr = 0; expr < this.numExpr; expr++) {
				final int lcc = this.results[expr][edge];
				final double x = BORDER + edge * sx;
				final double y = compHeight - BORDER - lcc * sy;
				dot.setFrameFromCenter(x, y, x - DOT_SIZE, y - DOT_SIZE);
				g2.draw(dot);
			}
		}
		g2.setColor(AVG_COLOR);
		g2.setStroke(new BasicStroke(AVG_WIDTH));
		g2.draw(averageLine);
	}

}
