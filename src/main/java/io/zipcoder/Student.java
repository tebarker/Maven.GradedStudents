package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String firstName,lastName;
    private ArrayList<Double> studentTestResult;
    public Student(String studentFirstName, String studentLastName){
        firstName=studentFirstName;
        lastName=studentLastName;
        studentTestResult= new ArrayList<>();
    }
    public Student(String studentFirstName, String studentLastName, Double[] examScores){
        firstName=studentFirstName;
        lastName=studentLastName;
        studentTestResult= new ArrayList<>(Arrays.asList(examScores));
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberOfExamsTaken(){
        return studentTestResult.size();
    }

    public String getExamScores(){
        String examScore = "Exam Scores: \n";
        for (int i=0;i<studentTestResult.size();i++){
            examScore+=String.format("\tExam %d -> %.0f %n",i+1,studentTestResult.get(i));
        }
        return examScore;
    }
    public void addExamScore(double score){
        studentTestResult.add(score);
    }
    public void setExamScore(int examNumber, double newScore){
        studentTestResult.remove(examNumber-1);
        studentTestResult.add(examNumber-1,newScore);
    }
    public double getAverageExamScore(){
        double average=0;
        for (int i=0; i<studentTestResult.size();i++){
            average+=studentTestResult.get(i);
        }
        return average/studentTestResult.size();
    }
    public String toString(){
        String result = "Student Name: "+firstName+lastName + "\n";
        result += String.format("> Average Score: %.0f \n", this.getAverageExamScore());
        result += getExamScores();
        return result;
    }

}
