package es.ull.cc.tsp;

public class Matrix {
	
	private int rows;
	private int cols;
	private double[][] _matrix;
	
	public Matrix(){
		setRows(0);
		setCols(0);
		_matrix = new double[getRows()][getCols()];
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return the _matrix
	 */
	public double[][] get_matrix() {
		return _matrix;
	}

	/**
	 *  _matrix is doing again
	 */
	public void resize() {
		setRows(0);
		setCols(0);
		_matrix = new double[getRows()][getCols()];
	}
	
	/**
	 *  @param _matrix is doing again with new values
	 */
	public void resize(int numRow){
		setRows(numRow);
		setCols(numRow);
		_matrix = new double[getRows()][getCols()];
	}
	
	/**
	 * Print the matrix
	 */
	public void printMatrix(){
		for(int i=0; i<getRows();i++){
			for(int j=0; j<getCols();j++){
				System.out.print(_matrix[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
	
	public void setValue(int row, int col, double val){
		_matrix[row][col] = val;
	}
}
