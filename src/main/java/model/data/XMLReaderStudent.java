
package model.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLReaderStudent
{

  private ArrayList<Object> studentArrayObject;
  private ArrayList<Object> students;

  private ArrayList<Object> teacherArrayObject;
  private ArrayList<Object> teachers;

  private ArrayList<Object> courseArrayObject;
  private ArrayList<Object> courses;

  //private ArrayList<Courses> coursesArrayList;
  //private ArrayList<Teachers> teachersArrayList;

  private String firstNameStudent;
  private String lastNameStudent;
  private String semester;
  private String claSS;

  private String firstNameTeacher;
  private String lastNameTeacher;

  public XMLReaderStudent()
  {

    students = new ArrayList<Object>();

    try
    {
      File studentsXML = new File("src/Data/students.xml");

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
      Document doc = dBuilder.parse(studentsXML);


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

      NodeList nList = doc.getElementsByTagName("Student");

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

          firstNameStudent = eElement.getElementsByTagName("FirstName").item(0).getTextContent();

          lastNameStudent = eElement.getElementsByTagName("LastName").item(0).getTextContent();

          semester = eElement.getElementsByTagName("Semester").item(0).getTextContent();

          claSS = eElement.getElementsByTagName("Class").item(0).getTextContent();

        }

        studentArrayObject = new ArrayList<Object>();
        studentArrayObject.add(firstNameStudent);
        studentArrayObject.add(lastNameStudent);
        studentArrayObject.add(semester);
        studentArrayObject.add(claSS);
        //studentArrayObject.addAll(Arrays.asList(firstName, lastName, semester, claSS));

        students.add(studentArrayObject);

      }
      System.out.println();
      System.out.println(students);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }


  public Object getStudent(int whichStudent, String value)
  {
    ArrayList<Object> chosenStudent = (ArrayList<Object>) students.get(whichStudent);

    switch (value)
    {
      case "First Name":
        return chosenStudent.get(0);
      case "Last Name":
        return chosenStudent.get(1);
      case "Semester":
        return chosenStudent.get(2);
      case "Class":
        return chosenStudent.get(3);
    }

    return null;
  }



}