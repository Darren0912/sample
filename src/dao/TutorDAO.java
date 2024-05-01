package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Tutor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ng Ding Lun
 */
public class TutorDAO {

    private String fileName = "tutor.dat"; // For security and maintainability, should not have filename hardcoded here.

    

    public void saveToFile(ListInterface<Tutor> tList) {
        try ( ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(tList);
            ooStream.close();
            System.out.println("Tutors updated to file successfully.");
        } catch (IOException ex) {
            System.err.println("Error while saving courses to file: " + ex.getMessage());
        }
    }

    public ListInterface<Tutor> retrieveFromFile() {
       
        ListInterface<Tutor> tList = new ArrayList<>();
        try ( ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(fileName))) {
            tList = (ListInterface<Tutor>) oiStream.readObject();
            System.out.println("Tutors retrieved from file successfully.");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error while reading courses from file: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found: " + ex.getMessage());
        }
        return tList;
    }
}
