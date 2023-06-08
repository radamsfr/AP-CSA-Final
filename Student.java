class Student {
  String name;
  double loudness;

  //constructor
  public Student(String n, double l){
    name = n;
    loudness = l;   
  }

  // print student name and loudness
  public void printStudent(){
    System.out.println(this.name + ": " + this.loudness);
  }
}
  
