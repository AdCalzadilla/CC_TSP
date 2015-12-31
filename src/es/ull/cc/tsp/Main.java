package es.ull.cc.tsp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void menuInit(){
		System.out.println(" =.== TSP problem ==.= ");
		System.out.println(" ===================== ");
		System.out.print(" Introduce nombre (fichero): ");
	}
	
	public static void subMenu2(){
		System.out.println(" -.-- TSP problem --.- ");
		System.out.println(" --------------------- ");
		System.out.print(" - Número de pruebas: ");
	}
	
	public static void menu(){
		System.out.println(" =.== TSP problem ==.= ");
		System.out.println(" ===================== ");
		String[] myOptinos = {" 1.- Imprimir matriz de distancia"," 2.- NN y 2-OPT", " 3.- Árbol de ramificación", " 4- Salir"," -- Opción: "};
		for(int i=0; i< myOptinos.length-1;i++){
			System.out.println(myOptinos[i]);
		}
		System.out.print(myOptinos[myOptinos.length-1]);
	}
	
	public static void run(){
		Scanner sc = new Scanner(System.in);
		String rute;
		int choice;
		int numProof;
		double bestValue = Double.MAX_VALUE;
		ArrayList<Integer>tour = new ArrayList<Integer>();
		
		menuInit();
		rute = sc.nextLine();
		Matrix matrix1 = new Matrix();
		Parser theXmlParse = new Parser(rute,matrix1);
		
		menu();
		choice = sc.nextInt();
		
		while(choice != 4){
			switch(choice){
			case 1:
				matrix1.printMatrix();
				break;
			case 2:
				subMenu2();
				numProof = sc.nextInt();
				HeightLimit prueba1 = new HeightLimit(matrix1);
				for(int i=0; i < numProof; i++){
					System.out.println(i+" - Best value: "+prueba1.getBestValue());
					prueba1.printTour();
					System.out.println("\n");
					if(bestValue > prueba1.getBestValue()){
						bestValue = prueba1.getBestValue();
						tour = prueba1.getTour();
					}
					prueba1.clear();
					prueba1.load();
				}
				break;
			case 3:
				if(tour.isEmpty()){
					HeightLimit prueba2 = new HeightLimit(matrix1);
					TreeBB myTree = new TreeBB(matrix1, prueba2.getTour(),prueba2.getBestValue(),prueba2.getTour().get(0));
					myTree.printFinalTour();
				}
				else{
					TreeBB myTree = new TreeBB(matrix1, tour,bestValue,tour.get(0));
					myTree.printFinalTour();
				}
				break;
			}
			menu();
			choice = sc.nextInt();
		}
		sc.close();
	}

	public static void main(String[] args) {
		run();
	}

}
