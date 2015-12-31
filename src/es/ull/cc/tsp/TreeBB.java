package es.ull.cc.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TreeBB {
	
	private static double INFINITE = Double.MAX_VALUE;
	
	private Matrix _mt;
	private double _levelHeight;
	private int _initNode;
	private Leaf _e_Node;
	private ArrayList<Leaf>_LNVtree;
	private ArrayList<Integer>_posibleTour;
	private ArrayList<Integer>_actualTour;
	
	/**
	 * This is a class that represents the Branch and Bound tree.
	 * @param mt
	 * @param tour
	 * @param levelHeight
	 * @param initNode
	 */
	public TreeBB(Matrix mt, ArrayList<Integer> tour, double levelHeight, int initNode){
		this._mt = mt;
		this._levelHeight = levelHeight;
		this._initNode = initNode;
		this._e_Node = createLeaf(initNode, 0.0);
		_actualTour = tour;
		_posibleTour = new ArrayList<Integer>();
		_LNVtree = new ArrayList<Leaf>();
		_LNVtree.add(_e_Node);
		RyP();
	}

	private Leaf createLeaf(int id, double costPath) {
		Leaf tempLeaf = new Leaf(id, _mt.getRows());
		tempLeaf.setCost(costPath);
		return tempLeaf;
	}
	
	private Leaf createLeaf(int id, Leaf father){
		double cost = 0.0;
		Leaf tempLeaf = new Leaf(id, _mt.getRows());
		cost = father.getCost() + _mt.getValue(id, father.getId());
		tempLeaf.setCost(cost);
		tempLeaf.setAncestor(father);
		return tempLeaf;
	}
	
	private void RyP(){
		while(!_LNVtree.isEmpty()){
			_e_Node = _LNVtree.get(0);  // Se selecciona el nodo más prometedor.
			_posibleTour.add(_e_Node.getId());
			deleteLeaf();              // Lo eliminamos de la LNV.
			extendLeaf(_e_Node);       // Se extiende el nodo, se estima y se poda.
			sort();                     // Se ordena
			if(_LNVtree.get(0).getValue() >= _levelHeight){
				break;
			}
		}
	}

	private void extendLeaf(Leaf e_node){
		Leaf tempLeaf;
		for(int i=0; i< e_node.getPosibleChildren().length;i++ ){
			tempLeaf = e_node;
			if(!e_node.getPosibleChildren(i)){
				tempLeaf = createLeaf(i, tempLeaf);
				estimation(tempLeaf);
				if(!discard(tempLeaf)){
					if(allVisited(tempLeaf.getPosibleChildren())){
						posibleSolution(tempLeaf);
					}
					else{
						_LNVtree.add(tempLeaf);
					}
				}
			}
		}
	}

	private void posibleSolution(Leaf tempLeaf) {
		if(_levelHeight > tempLeaf.getValue()){
			_levelHeight = tempLeaf.getValue();
			_posibleTour.add(tempLeaf.getId());
			_actualTour = _posibleTour;
		}
		else{
			_posibleTour.removeAll(_posibleTour);
		}
	}

	private void estimation(Leaf temp) {       // Mal hecha recalcular.
		int next_leaf;
		Leaf partialLeaf = temp;
		double value = temp.getCost();
		boolean tempArray[] = equalsArray(temp.getPosibleChildren());
		while(!allVisited(tempArray)){
			next_leaf = minimEdge(partialLeaf, tempArray);
			tempArray[next_leaf] = true;
			value += _mt.getValue(partialLeaf.getId(), next_leaf);
			partialLeaf = new Leaf(next_leaf, temp.getPosibleChildren().length);
			partialLeaf.setPosibleChildren(tempArray);
		}
		value += _mt.getValue(_initNode, partialLeaf.getId());
		temp.setValue(value);
	}
	
	private boolean[] equalsArray(boolean[] pChildren) {
		boolean tempArray[] = new boolean[_mt.getRows()];
		for(int i=0; i<_mt.getRows();i++){
			tempArray[i] = pChildren[i];
		}
		return tempArray;
	}

	private boolean discard(Leaf temp){
		if(temp.getValue() >= _levelHeight){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void sort(){
		Collections.sort(_LNVtree, new Comparator<Leaf>(){
			@Override
			public int compare(Leaf s1, Leaf s2) {
				return new Double(s1.getValue()).compareTo(new Double(s2.getValue()));
			}
			
		});
	}
	
	private int minimEdge(Leaf actual, boolean[] tempVisited){
		int tempMinimum = -1;
		double tempValue = INFINITE;
		for(int i=0; i< _mt.getRows();i++){
			if(!actual.getPosibleChildren(i) && !tempVisited[i]){
				if(tempValue > _mt.getValue(actual.getId(), i)){
					tempValue = _mt.getValue(actual.getId(), i);
					tempMinimum = i;
				}
			}
		}
		return tempMinimum;

	}

	private boolean allVisited(boolean arrayVisit[]) {
		for(int i=0; i< _mt.getRows();i++){
			if(arrayVisit[i]==false){
				return false;
			}
		}
		return true;
	}
	
	private void deleteLeaf() {
		_LNVtree.remove(0);	
	}
	
	/**
	 * Print the LNV list.
	 */
	public void printLNV() {
		for(Leaf s : _LNVtree){
			System.out.print(s.getId()+" ");
		}
		System.out.println("\n");		
	}
	
	/**
	 * Print the tour.
	 */
	public void printFinalTour() {
		System.out.print(" - Recorrido árbol: ");
		for(int i : _actualTour){
			System.out.print(i+" ");
		}
		System.out.println("\n - valor: "+ _levelHeight);
	}
	
}
