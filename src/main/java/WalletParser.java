import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

class WalletParser {

    Wallet wallet = null;
    Position position = null;
    String pathToFile = null;

    private int id;
    private String name = null;
    private double value;
    private double volatility;
    private double eigenvalue;
    private double[] eigenvector = null;


    WalletParser(String fileName) {

        pathToFile = "src/main/resources/" + fileName;
        wallet = new Wallet();
    }

    public Wallet parseWallet() {
        try {

            File fXmlFile = new File(pathToFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList positions = doc.getElementsByTagName("position");
            int numOfPosInWallet = positions.getLength();

            for (int i = 0; i < numOfPosInWallet; i++) {

                if (positions.item(i).getNodeType() == Node.ELEMENT_NODE) {

                    Element position = (Element) positions.item(i);
                    id = Integer.parseInt(position.getAttribute("id"));
                    name = position.getElementsByTagName("name").item(0).getTextContent();


                    String sValue = position.getElementsByTagName("value").item(0).getTextContent();
                    value = Double.parseDouble(sValue);

                    String sVolatility = position.getElementsByTagName("volatility").item(0).getTextContent();
                    volatility = Double.parseDouble(sVolatility);

                    String sEigenvalue = position.getElementsByTagName("eigenvalue").item(0).getTextContent();
                    eigenvalue = Double.parseDouble(sEigenvalue);

                    NodeList vecElems = position.getElementsByTagName("elem");
                    eigenvector = new double[vecElems.getLength()];

                    for (int j = 0; j < vecElems.getLength(); j++) {

                        if (vecElems.item(j).getNodeType() == Node.ELEMENT_NODE) {

                            Element vecElem = (Element) vecElems.item(j);
                            String sElemValue = vecElem.getTextContent();
                            eigenvector[j] = Double.parseDouble(sElemValue);
                        }
                    }


                }


                position = new Position(id, name, value, volatility, eigenvalue, eigenvector);
                wallet.addPosition(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return wallet;

    }

}
