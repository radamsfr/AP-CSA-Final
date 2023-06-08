import java.util.Scanner;
import java.util.ArrayList;

/*
------------------------------------
White Tower Project: Seat Sorter

Purpose: To arrange students in a classroom with given table count and seats per table with optimal student loudness distribution across the classroom.

------------------------------------
*/

class Main {
  public static void main(String[] args) {

    Scanner scanman = new Scanner(System.in);
    //Initiates new classroom object
    Classroom class1 = new Classroom();

    
    System.out.println("Table Arrangments: \n");

    // # of tables in classroom
    System.out.print("How many tables in the classroom?: ");
    int numTables = scanman.nextInt();

    // # of seats per table
    System.out.print("How many seats per table?: ");
    int numSeats = scanman.nextInt();

    // create # of table w # of seats 
    for(int i=0;i<numTables;i++){
      class1.addTable(new Table(numSeats));
    }

    // prints total # of seats
    int totalSeats = numSeats*numTables;
    System.out.println("Total number of seats: " + totalSeats);

    // # of students in the class (must be less than total # of seats)
    System.out.print("Enter # of students in the class: ");
    int classSize = scanman.nextInt();
    while(totalSeats<classSize){
      System.out.println("Class size exceeds number of seats:");
      classSize = scanman.nextInt();
    }

    // Initializes student name and loudness for each student
    System.out.println("Enter each student in class:\n\n");
    for(int i=0;i<classSize;i++){
      System.out.println("Name of student" + (i+1) + ": ");
      String student = scanman.next();
      System.out.println("How loud is this student(0-10): ");
      double loudness = scanman.nextDouble();
      class1.addStudent(new Student(student, loudness));
    }

    
    double error1 = 0;
    double error2 = 0;

    // Fill empty seats with null students
    class1.fillEmpty();
    
    // Randomize roster
    class1.randRoster();

    // Populate table with students
    class1.fillTables();

    // Calculate error of randomized class (sum of residuals from total average squared)
    error1 = class1.Error();
    

    // Randomize roster student count squared (to optimize)
    for(int i=0;i<class1.classSize*class1.classSize;i++) {
      class1.randRoster();
      class1.fillTables();
      error2 = class1.Error();

      // Save best seating arrangement (smallest error)
      if(error2<error1){
        class1.saveRoster();
        error1=error2;
      }
    }

    // prints best seating arrangement
    class1.chooseBestRoster();
    class1.fillTables();
    class1.printClassroom();
    //System.out.println(class1.Error());
    
  }
}
