
package model.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLReaderTeacher
{

  private ArrayList<Object> teacherArrayObject;
  private ArrayList<Object> teachers;

  //private ArrayList<Courses> coursesArrayList;
  //private ArrayList<Teachers> teachersArrayList;

  private String firstName;
  private String lastName;

  public XMLReaderTeacher()
  {

    teachers = new ArrayList<Object>();

    try
    {
      File teachersXML = new File("src/Data/teachers.xml");
      //DocumentBuilderFactory will allow us to make Document Builder

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

      /*
        Defines the API to obtain DOM Document instances from an XML document.
         Using this class, an application programmer can obtain a Document from XML.
        An instance of this class can be obtained from the DocumentBuilderFactory.newDocumentBuilder() method.
         Once an instance of this class is obtained, XML can be parsed from a variety of input sources.
        These input sources are InputStreams, Files, URLs, and SAX InputSources.
      * */
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

      //The Document interface represents the entire HTML or XML document
      Document doc = dBuilder.parse(teachersXML);

      //This is a convenience attribute that allows direct access to the child node that is the document element of the document.
      doc.getDocumentElement().normalize();

      //could be represented like this in a denormalized node:

      // Element foo
      //Text node: ""
      //Text node: "Hello "
      //Text node: "wor"
      // Text node: "ld"

      // When normalized, the node will look like this

      // Element foo
      // Text node: "Hello world"

      NodeList nList = doc.getElementsByTagName("Teacher");

      for (int temp = 0; temp < nList.getLength(); temp++)
      {
        //item(int index)
        //Returns the indexth item in the collection.
        //method of interface NodeList
        Node nNode = nList.item(temp); //first student, second student, third student, ...
        //System.out.println("\nCurrent Element :" + nNode.getNodeName());

        //	ELEMENT_NODE
        //The node is an Element.
        // An Element node like <p> or <div>.
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {

          Element eElement = (Element) nNode;

          firstName = eElement.getElementsByTagName("FirstName").item(0).getTextContent();

          lastName = eElement.getElementsByTagName("LastName").item(0).getTextContent();


        }

        teacherArrayObject = new ArrayList<Object>();
        teacherArrayObject.add(firstName);
        teacherArrayObject.add(lastName);
        //studentArrayObject.addAll(Arrays.asList(firstName, lastName, semester, claSS));

        teachers.add(teacherArrayObject);
      }
      System.out.println();
      System.out.println(teachers);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public Object getTeacher(int whichTeacher, String value)
  {
    ArrayList<Object> chosenTeacher = (ArrayList<Object>) teachers.get(whichTeacher);

    switch (value)
    {
      case "First Name":
        return chosenTeacher.get(0);
      case "Last Name":
        return chosenTeacher.get(1);
    }

    return null;
  }

}