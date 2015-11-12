package es.ull.cc.tsp;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Parser {
	
	private String xmlName;
	private String xmlSource;
	private String xmlDescription;
	private double xmlDoublePrecision;
	private int xmlIgnoreDigit;
	private int numVertex;
	
	Parser(String route, Matrix theMatrix){
		File xmlFile = new File("files/"+ route);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();

			//xmlName = doc.getDocumentElement().getNodeName();
			xmlName = doc.getElementsByTagName("name").item(0).getTextContent();
			xmlSource = doc.getElementsByTagName("source").item(0).getTextContent();
			xmlDescription = doc.getElementsByTagName("description").item(0).getTextContent();
			xmlDoublePrecision = Double.valueOf(doc.getElementsByTagName("doublePrecision").item(0).getTextContent()).doubleValue();
			xmlIgnoreDigit = Integer.parseInt(doc.getElementsByTagName("ignoredDigits").item(0).getTextContent());
			
			NodeList vList = doc.getElementsByTagName("vertex");
			numVertex = vList.getLength();
			theMatrix.resize(numVertex);
			
			for(int i=0; i< vList.getLength();i++){
				Element tempNode = (Element) vList.item(i);
				
				if(tempNode.getNodeType() == Node.ELEMENT_NODE){
					NodeList eList = tempNode.getElementsByTagName("edge");
					for(int j=0;j<eList.getLength();j++){
						Element auxElem = (Element) eList.item(j);
						int coord_j = Integer.parseInt(auxElem.getTextContent());
						double auxCost = Double.valueOf(auxElem.getAttribute("cost")).doubleValue();
						theMatrix.setValue(i, coord_j, auxCost);
					}
				}
			}
			
			theMatrix.printMatrix();
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getNumVertex(){
		return numVertex;
	}
	
	public String getXmlName() {
		return xmlName;
	}

	public String getXmlSource() {
		return xmlSource;
	}

	public String getXmlDescription() {
		return xmlDescription;
	}

	public double getXmlDoublePrecision() {
		return xmlDoublePrecision;
	}

	public int getXmlIgnoreDigit() {
		return xmlIgnoreDigit;
	}


}
