package dao;

/**
 *
 * @author Ng Ding Lun
 */
import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CourseDAO {

    private String fileName = "course.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(ListInterface<Course> cList) {
        try ( ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(cList);
            ooStream.close();
            System.out.println("Courses updated to file successfully.");
        } catch (IOException ex) {
            System.err.println("Error while saving courses to file: " + ex.getMessage());
        }
    }

    public ListInterface<Course> retrieveFromFile() {
        ListInterface<Course> cList = new ArrayList<>();
        try ( ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(fileName))) {
            cList = (ListInterface<Course>) oiStream.readObject();
            System.out.println("Courses retrieved from file successfully.");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error while reading courses from file: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found: " + ex.getMessage());
        }
        return cList;
    }

  
}
