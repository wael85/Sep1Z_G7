package model.data;

public class XMLReaderTest
{
  public static void main(String[] args)
  {
    XMLReaderStudent readerStudent = new XMLReaderStudent();
    System.out.print(readerStudent.getStudent(9,"Last Name"));

    XMLReaderTeacher readerTeacher = new XMLReaderTeacher();
    System.out.println();
    System.out.println(readerTeacher.getTeacher(1,"Last Name"));
  }
}
