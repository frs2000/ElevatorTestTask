package ElevatorTestTask;

public class Main {
    public static void main(String[] args) {
        Elevator elevator = new Elevator ();
        
        Person person1 = new Person(1, 4);
        Person person2 = new Person(3, 2);
        Person person3 = new Person(4, 1); 

        elevator.addPerson(person1);
        elevator.addPerson(person2); 
        elevator.addPerson(person3);
    
        elevator.start();
    }
}
