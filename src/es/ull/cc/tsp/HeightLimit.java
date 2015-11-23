package es.ull.cc.tsp;

import java.util.ArrayList;

public class HeightLimit {
	
	static final double INIT = 1e100;
	
	private Matrix _matrix;
	private ArrayList<Integer> _tour;
	private double _bestValue;
	
	HeightLimit(Matrix matrix){
		_tour = new ArrayList<Integer>();
		_matrix = matrix;
		load();
	}

	public void load() {
		calcHightLimit();
	}

	private void calcHightLimit() {
		NN();
		two_OPT();
	}

	private void NN() {
		boolean visited[] = new boolean[getMatrix().getRows()];
		double near = Double.MAX_VALUE;
		int initNode = (int) (Math.random() * getMatrix().getRows());
		int actual = initNode;
		int tempNum = actual;
		visited[actual]= true;
		
		while(!allVisited(visited)){
			for(int i=0; i<getMatrix().getRows();i++){
				if(i == actual || visited[i]== true){}
				else{
					if(getMatrix().getValue(actual,i) < near){
						near = getMatrix().getValue(actual, i);
						tempNum = i;
					}
				}
			}
			actual = tempNum;
			near = Double.MAX_VALUE;
			_tour.add(actual);
			visited[actual] = true;
		}
		setBestValue(calculateTotalDistance(getTour()));
	}

	private ArrayList<Integer> two_OPTSwap(ArrayList<Integer>tour,int i, int k) {
		ArrayList<Integer> newTour = new ArrayList<Integer>(); 
		for(int j=0; j<i; j++){         // take route[1] to route[i-1] and add them in order to new_route
			newTour.add(tour.get(j));
		}
		for(int j=k; j>=i; j--){        // take route[i] to route[k] and add them in reverse order to new_route
			newTour.add(tour.get(j));
		}
		for(int j=k+1; j< tour.size();j++){  // take route[k+1] to end and add them in order to new_route
			newTour.add(tour.get(j));
		}
		return newTour;
	}
	
	private void two_OPT() {
		boolean improvement = true;
		double tempValue = 0.0;
		ArrayList<Integer> tempTour = _tour;
		while(improvement){
			setBestValue(calculateTotalDistance(getTour()));
			for(int i=0; i < _tour.size()-1;i++){
				for(int k= i+1; k < _tour.size(); k++){
					tempTour = two_OPTSwap(tempTour, i, k);
					tempValue = calculateTotalDistance(tempTour);
					if(tempValue < getBestValue()){
						setBestValue(tempValue);
						_tour = tempTour;
						improvement = true;
						break;
					}
					else{
						improvement = false;
					}
				}
			}
			
		}
		
	}
	
	
	private double calculateTotalDistance(ArrayList<Integer> tour) {
		double result = 0.0;
		for(int i=0;i< tour.size();i++){
			if(i == tour.size()-1){
				result += _matrix.getValue(tour.get(i), tour.get(0));
			}
			else{
				result += _matrix.getValue(tour.get(i), tour.get(i+1));
			}
		}
		return result;
	}

	private boolean allVisited(boolean visited[]) {
		for(int i=0; i < getMatrix().getRows();i++){
			if(visited[i] == false){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param bestValue the bestValue to set
	 */
	private void setBestValue(double bestValue) {
		_bestValue = bestValue;
		
	}

	/**
	 * @return the _matrix
	 */
	public Matrix getMatrix() {
		return _matrix;
	}

	/**
	 * @param _matrix the _matrix to set
	 */
	public void setMatrix(Matrix _matrix) {
		this._matrix = _matrix;
	}

	/**
	 * @return the _tour
	 */
	public ArrayList<Integer> getTour() {
		return _tour;
	}

	/**
	 * @return the bestValue
	 */
	public double getBestValue() {
		return _bestValue;
	}
	
	/**
	* @param clear the memory
	*/
	public void clear(){
		_tour.clear();
		setBestValue(INIT);
	}

}
