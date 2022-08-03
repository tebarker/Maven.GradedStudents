package io.zipcoder;

import java.util.*;

public class Classroom {

    private Student [] classroom;
    public Classroom (){
        this(30);
    }
    public Classroom(int maxNumberOfStudent){
        classroom=new Student[maxNumberOfStudent];
    }
    public Classroom (Student[] studentsList){
        classroom=studentsList;
    }
    public Student[] getStudents(){
        return classroom;
    }
    public double getAverageExamScore(){
        double average=0;
        for (int i=0;i<classroom.length;i++){
            average+=classroom[i].getAverageExamScore();
        }
        return average/classroom.length;
    }
    public void addStudent(Student student){
        for (int i=0;i<classroom.length;i++){
            if (classroom[i] == null){
                classroom[i] = student;
                break;
            }
        }
    }
    public void removeStudent(String firstName, String lastName) {
        for (int i=0;i<classroom.length;i++){
            if (classroom[i].getLastName()==lastName){
                if (classroom[i].getFirstName() == firstName){
                    if (i==classroom.length-1)
                        classroom[i]=null;
                    else {
                        classroom[i] = classroom[i + 1];
                        classroom[i + 1] = null;
                    }
                }
            }
            if (((i != classroom.length-1) && (classroom[i]==null))
                    && (classroom[i+1] != null)){
                classroom[i] = classroom[i + 1];
                classroom[i + 1] = null;
            }
        }
    }
    public Student[] getStudentsByScore(){
        List <Student> studentSortedList = new ArrayList<>(Arrays.asList(classroom));
        studentSortedList.removeAll(Collections.singleton(null));
        Comparator<Student> comparator =
                Comparator.comparingDouble((Student s) -> -s.getAverageExamScore() )
                        .thenComparing(s -> s.getLastName())
                        .thenComparing(s -> s.getFirstName());
        Collections.sort(studentSortedList,comparator);
        return studentSortedList.toArray(new Student[studentSortedList.size()]);
    }
    public Map<Student, String> getGradeBook(){
        Double percentile;
        Student[] classroomSorted = this.getStudentsByScore();
        Map<Student,String> gradeBook = new HashMap<>();
        int count= getStudentsByScore().length;
        for (int i=0;i<count;i++){
            percentile = ((count-i-1.0)/(count))*100;
            if (percentile >= 90)
                gradeBook.put(classroomSorted[i],"A");
            else if (percentile <= 89 && percentile >= 71)
                gradeBook.put(classroomSorted[i],"B");
            else if (percentile <= 70 && percentile >= 50)
                gradeBook.put(classroomSorted[i],"C");
            else if (percentile <= 49 && percentile >= 11)
                gradeBook.put(classroomSorted[i],"D");
            else
                gradeBook.put(classroomSorted[i], "F");
        }
        return gradeBook;
    }
}
