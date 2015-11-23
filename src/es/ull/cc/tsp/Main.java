package es.ull.cc.tsp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix matrix1 = new Matrix();
		Parser theXmlParse = new Parser("burma14.xml",matrix1);
		HeightLimit prueba1 = new HeightLimit(matrix1);
		for(int i=0; i < 25; i++){
			System.out.println(i+": - Best value: "+prueba1.getBestValue());
			prueba1.clear();
			prueba1.load();
		}

	}

}
