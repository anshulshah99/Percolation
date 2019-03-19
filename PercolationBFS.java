import java.util.*;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int size) {
		super(size);
	}
	@Override
	protected void dfs(int row, int col) {
		if(! inBounds(row,col)) return;
		if(isFull(row,col) || !isOpen(row,col)) return;
		int[] rowDelta = {-1,1,0,0};
		int[] colDelta = {0,0,-1,1};
		Queue<Integer> q = new LinkedList<>();
		myGrid[row][col] = FULL;
		q.add(row*myGrid.length + col);
		while(q.size() != 0){
			int location = q.remove();
			int rows = location/myGrid.length;
			int cols = location%myGrid.length;
			for(int k = 0; k < rowDelta.length; k++) {
				row = rows + rowDelta[k];
				col = cols + colDelta[k];
				if(inBounds(row,col) && myGrid[row][col] == OPEN && myGrid[row][col] != FULL) {
					myGrid[row][col] = FULL;
					q.add((row)*myGrid.length + col);
				}
			}
			
			

		}
	}
}
