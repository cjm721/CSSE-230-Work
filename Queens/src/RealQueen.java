/**
 * RealQueen is the class that does most of the work in this object-oriented
 * solution of the non-attacking chess queens problem (writen by Claude
 * Anderson, adapted from an algorithm by Timothy Budd). A queen is permanently
 * stationed in a column, but may choose its own row and negotiate with its
 * neighbors (it only has the "address" of its neighbor immediately to the left)
 * to find a peaceable, non-attacking arrangement. The description of the Queen
 * interface includes a few more details.
 */
public class RealQueen implements Queen {
	private final Queen neighbor; // next queen to the left.

	private final int column; // What's my (permanent) column?

	private int currentRow; // where am I now? (1-based index, top of board = 1)

	private final int size; // number of rows/columns on board

	/**
	 * Constructs a queen for the given column, on a board of the given size,
	 * and tells it about its neighbor to the left.
	 * 
	 * @param neighbor
	 * @param column
	 * @param size
	 */
	RealQueen(Queen neighbor, int column, int size) {
		this.neighbor = neighbor;
		this.column = column;
		this.size = size;
		this.currentRow = 0; // by default. The other methods will change this to a valid row (1 to n).  
	}

	public boolean findFirst() {
		this.currentRow = 1;
		if (this.neighbor.findFirst())
			return testOrAdvance();
		else
			return false;
	}

	// If this is a legal row for me, say so. If not, try the next row.
	private boolean testOrAdvance() {
		if (this.neighbor.canAttack(this.currentRow, this.column))
			return findNext();
		return true;
	}

	public boolean findNext() {
		this.currentRow++;
		
		if( !(this.currentRow > this.size) ){
			return this.testOrAdvance();
		}else if(!this.neighbor.findNext()){
			return false;
		}
		this.currentRow = 1;
		return this.testOrAdvance();
	}

	public boolean canAttack(int row, int col) {
		if(this.currentRow == row || this.column == col){
			return true;
		}else {
			if(Math.abs(row - this.currentRow) == Math.abs(col - this.column))
				return true;
			return this.neighbor.canAttack(row, col);
		}
		
	}

	@Override
	public String toString() {
		return this.currentRow + " " + this.neighbor.toString();
	}
}
