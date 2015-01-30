package graphs;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class drives the experiment.
 * 
 * @author Curt Clifton
 */
public class Experiment {
	private static final int SIZE = 200;
	private static final int NUMBER_OF_EXPR = 10;
	private static final Dimension WINDOW_SIZE = new Dimension(800, 600);

	/**
	 * Starts the experiment.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		runPrintingExperiment();
		runChartingExperiment();
	}

	/**
	 * Exercises some RandomGraph methods by printing results.
	 */
	private static void runPrintingExperiment() {
		// Some test code that prints graphs
		RandomGraph g = new RandomGraph(6);
		final int edgeCount = 6;
		System.out.println(g);
		System.out.println("largest connected component size: "
				+ g.largestConnectedComponentSize());
		for (int i = 0; i < edgeCount; i++) {
			g.addRandomEdge();
			System.out.println("--------------------");
			System.out.println(g);
			System.out.println("largest connected component size: "
					+ g.largestConnectedComponentSize());
		}
	}

	/**
	 * Runs several experiments, collecting data on the size of the largest
	 * connected component vs. the number of edges added. Generates a chart of
	 * the results.
	 */
	private static void runChartingExperiment() {
		// Runs NUMBER_OF_EXPR experiments, collecting data to be plotted
		System.out.println("Running experiments");
		final int edgesToAdd = Math.min(SIZE * 3, (SIZE - 1) * SIZE / 2);
		final int[][] results = new int[NUMBER_OF_EXPR][edgesToAdd];
		for (int exprNum = 0; exprNum < NUMBER_OF_EXPR; exprNum++) {
			results[exprNum] = new int[edgesToAdd];
			RandomGraph gr = new RandomGraph(SIZE);
			for (int edgeNum = 0; edgeNum < edgesToAdd; edgeNum++) {
				gr.addRandomEdge();
				results[exprNum][edgeNum] = gr.largestConnectedComponentSize();
			}
		}

		// Draws the chart
		JFrame frame = new JFrame();
		frame.setTitle("Graph chart");
		frame.setSize(WINDOW_SIZE);

		frame.add(new ChartComponent(SIZE, results));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
