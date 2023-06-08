public class Table{
  int seats;
  int totalLoudness;
  Student[] students;
  
  public Table(int seats){
    this.seats = seats;
    students = new Student[seats];
    totalLoudness = 0;
  }

  // add student to table
  public void addStudent(Student a, int i) {
    students[i]=a;
  }
  
  // average loudness of the table
  public double average(){
    double avg = 0;
    for(Student i:students){
      avg += i.loudness;
    }
    return avg/seats;
  }

  // print students in table
  public void printTable(){
    System.out.println("----------------------");
    for(Student a:students){
      a.printStudent();
    }
  }

}
