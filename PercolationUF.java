
public class PercolationUF implements IPercolate{
	private final int VTOP;
	private final int VBOTTOM;
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;

	public PercolationUF(int size, IUnionFind finder) {
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		myGrid = new boolean[size][size];
		finder.initialize(size*size + 2);
		myFinder = finder;
		
	}
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		int val = row* myGrid.length + col;
		if(! isOpen(row,col)) {
			myGrid[row][col] = true;
			myOpenCount++;
		}
		if(row == 0) {
			myFinder.union(val, VTOP);
		}
		if(row == myGrid.length - 1) {
			myFinder.union(val, VBOTTOM);
		}
		if(inBounds(row+1,col) && isOpen(row+1,col)) {
			int val2 = (row + 1) * myGrid.length + col;
			myFinder.union(val, val2);
		}
		if(inBounds(row-1,col) && isOpen(row-1,col)) {
			int val3 = (row - 1) * myGrid.length + col;
			myFinder.union(val, val3);
		}
		if(inBounds(row,col+1) && isOpen(row,col+1)) {
			int val4 = (row) * myGrid.length + col + 1;
			myFinder.union(val, val4);
		}
		if(inBounds(row,col-1) && isOpen(row,col-1)) {
			int val5 = (row) * myGrid.length + col-1;
			myFinder.union(val, val5);
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		int val = row*myGrid.length + col;
		return myFinder.connected(VTOP, val);
	}

	@Override
	public boolean percolates() {
		if(myFinder.connected(VTOP, VBOTTOM)) {
			return true;
		}
		return false;
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
