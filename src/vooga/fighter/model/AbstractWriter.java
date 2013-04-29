package vooga.fighter.model;

import java.util.List; 

import org.w3c.dom.Element;

import util.XMLTool;

/**
 * Abstract class for an xml filewriter. This class uses an XMLTool to write to files,
 * and gives flexibility in how xml data is written to files 
 * (write to file function must be implemented in subclasses).
 * Also contains a utility function for writing xml lines with attributes.
 * @author matthewparides
 *
 */
public abstract class AbstractWriter {
	
	private String myFilePath;
	private XMLTool myXMLWriter;
	
	
	/**
	 * Constructor
	 * @param filePath
	 */
	public AbstractWriter(String filePath, XMLTool tool) {
		myFilePath = filePath;
		myXMLWriter = tool;
	}
	
	/**
	 * writes data to a predetermined filepath (usually myFilePath)
	 */
	public abstract void writeData();
	
	/**
	 * writes data to the file at location filepath
	 * @param filepath
	 */
	public abstract void writeData(String filepath);
	/**
	 * @return the myFilePath
	 */
	
	public String getFilePath() {
		return myFilePath;
	}

	/**
	 * @param myFilePath the myFilePath to set
	 */
	public void setFilePath(String filePath) {
		myFilePath = filePath;
	}
	
	/**
	 * @return the myXMLTool
	 */
	public XMLTool getXMLWriter() {
		return myXMLWriter;
	}

	/**
	 * @param myXMLTool the myXMLTool to set
	 */
	public void setXMLWriter(XMLTool xmltool) {
		myXMLWriter = xmltool;
	}

	/**
	 * convenience method for those less familiar with Elements (we use a lot of attributes in our xmls)
	 * writes out and returns an Element Object with given attributes.
	 * Attribute names and values match up by index. Index 0 will be the rightmost entry in the returned
	 * element.
	 * @param name
	 * @param attribNames
	 * @param attribValues
	 * @return Element object with attributes and values from input lists. 
	 * the first index of the lists will be the rightmost attribute, and additional attributes will be added
	 * to the left of the most recent entry.
	 * 
	 * format - <name attribName[2]="attribValues[2]" attribName[1]="attribValues[1]" attribName[0]="attribValues[0]" />
	 */
	public Element makeElementWithAttributes(String name, List<String> attribNames, List<String> attribValues) {
		Element elem = myXMLWriter.makeElement(name);
		for(int i = 0; i < attribNames.size(); i++) {
			elem.setAttribute(attribNames.get(i), attribValues.get(i));
		}
		return elem;
		
	}
	
	/**
	 * writes this writer's information to the file at filepath
	 * @param filepath the file to write information to
	 */
	protected abstract void writeToFile(String filepath);
}
