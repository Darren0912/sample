/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Ng Ding Lun
 */
public class TutorialGroup implements Serializable,Comparable<TutorialGroup> {

    private String tutorialGroupID;
    private int numStudent;
   

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorialGroupID, int numStudent) {
        this.tutorialGroupID = tutorialGroupID;
        this.numStudent = numStudent;
    }
  


    public String getTutorialGroupID() {
        return tutorialGroupID;
    }

    public void setTutorialGroupID(String tutorialroupID) {
        this.tutorialGroupID = tutorialroupID;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    @Override
    public String toString() {
        return "TutorialGroup{" + "tutorialGroupID=" + tutorialGroupID + ", numStudent=" + numStudent + '}';
    }

    @Override
    public int compareTo(TutorialGroup o) {
        return this.tutorialGroupID.compareTo(o.tutorialGroupID);
    }

}
