package es.ull.cc.tsp;


public class Leaf {
	
	private int id;
	private double value;
	private double cost;
	private boolean posibleChildren[];
	
	/**
	 * This is a class that represents the tree leaves.
	 * @param id
	 * @param graphSize
	 */
	Leaf(int id, int graphSize){
		this.id = id;
		this.value = 0.0;
		this.setPosibleChildren(new boolean[graphSize]);
		this.setPosibleChildren(id,true);
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}	

	/**
	 * @return the posibleChildren
	 */
	public boolean[] getPosibleChildren() {
		return posibleChildren;
	}

	/**
	 * @param posibleChildren the posibleChildren to set
	 */
	public void setPosibleChildren(boolean posibleChildren[]) {
		this.posibleChildren = posibleChildren;
	}
	
	/**
	 * @param i the sheet.
	 * @return the boolean value of sheet.
	 */
	public boolean getPosibleChildren(int i){
		return posibleChildren[i];
	}
	
	/**
	 * @param i the sheet to set.
	 * @param visit the state to sheet.
	 */
	public void setPosibleChildren(int i, boolean visit){
		posibleChildren[i] = visit;
	}

	/**
	 * @param fatherSheet
	 */
	public void setAncestor(Leaf fatherSheet) {
		this.posibleChildren = equalsArray(fatherSheet.posibleChildren);
		this.setPosibleChildren(id,true);
	}
	
	private boolean[] equalsArray(boolean[] pChildren) {
		boolean tempArray[] = new boolean[posibleChildren.length];
		for(int i=0; i<posibleChildren.length;i++){
			tempArray[i] = pChildren[i];
		}
		return tempArray;
	}
}
