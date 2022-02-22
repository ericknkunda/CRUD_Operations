/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students_management_app;

/**
 *
 * @author Uc
 */
public class StudentModel {
    private int StudentNum;

   private String REG_NO;
   private String facult;
   private String yearOfStudy;

    public StudentModel() {
        
    }

    public StudentModel(String REG_NO, String facult, String yearOfStudy) {
        this.REG_NO = REG_NO;
        this.facult = facult;
        this.yearOfStudy = yearOfStudy;
    }
    

    public StudentModel(int stNum,String REG_NO, String facult, String yearOfStudy) {
        this.StudentNum =stNum;
        this.REG_NO = REG_NO;
        this.facult = facult;
        this.yearOfStudy = yearOfStudy;
    }

    public int getStudentNum() {
        return StudentNum;
    }

    public void setStudentNum(int StudentNum) {
        this.StudentNum = StudentNum;
    }
   

    public String getREG_NO() {
        return REG_NO;
    }
    public void setREG_NO(String REG_NO) {
        this.REG_NO = REG_NO;
    }

    public String getFacult() {
        return facult;
    }
     public void setFacult(String facult) {
        this.facult = facult;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }
    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    

   

    
    
}
