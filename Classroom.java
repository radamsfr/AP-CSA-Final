import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

class Classroom {

  
  ArrayList<Table> classroom = new ArrayList<Table>();
  ArrayList<Student> roster = new ArrayList<Student>();
  ArrayList<Student> copyRoster = new ArrayList<Student>();
  
  
  int classSize = 0;
  private double classAvg = 0;

  
  public void addTable(Table x) {
    classroom.add(x);
  }

  // add student to roster arraylist
  public void addStudent(Student x){
    roster.add(x);
    classSize = roster.size();
  }

  // fill empty seats with null student 0 loudness
  public void fillEmpty(){
    int numSeats = classroom.size()*classroom.get(0).seats;
    while(roster.size()<numSeats){
      addStudent(new Student("", 0));
    }
    updateAverage();
  }

  // populate tables with students from roster
  public void fillTables(){
    int counter = 0;
    for(Table a: classroom){
      for(int i=0;i<a.seats;i++){
        if((a.seats*counter+i)>=roster.size()){
          break;
        }
        a.addStudent(roster.get(a.seats*counter+i), i);
      }
      counter++;
    }
  }

  // randomize roster
  public void randRoster() {
    ArrayList<Student> tempRoster = new ArrayList<Student>();
    for (int i = roster.size(); i > 0; i--) {
      int t = Math.round((int)(Math.random() * i));
      tempRoster.add(roster.get(t));
      roster.remove(t);
    }
    roster = tempRoster;
  }

  // update average loudness of the class
  public void updateAverage() {
    classAvg = 0;
    for (Student s: roster) {
      classAvg += s.loudness;
    }
    classAvg /= classSize;
  }

    // calculate and return error of randomized class (sum of residuals from total average squared) 
  private double loudnessErr = 0;
  public double Error() {
    double error = 0;
    updateAverage();
    for (Table t: classroom) {
      error += Math.pow(t.average() - classAvg , 2);
    }
    return error;
  }

  // save a copy of the roster to copyRoster
  public void saveRoster(){
    copyRoster = (ArrayList)roster.clone();
  }

  // copy best roster to the main roster
  public void chooseBestRoster(){
    roster = (ArrayList)copyRoster.clone();
  }

  // print seating arrangement
  public void printClassroom(){
    System.out.println("\n\nClassroom Layout:");
    for(Table a:classroom){
      a.printTable();
    }
  }
}
