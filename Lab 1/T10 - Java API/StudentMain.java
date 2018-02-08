import ie.dit.comp.student.*;

public class StudentMain {

	public static void main(String args[]) {

        StudentRecord s = new StudentRecord("Best", "Eryk", "C14386641",4);
		System.out.println("Student added: ");
        System.out.println("Name: " + s.getName());
        System.out.println("Surname: " + s.getSurname());
        System.out.println("StudentNo: " + s.getNumber());
        System.out.println("Year:" + s.getCurrentYear());
        
        System.out.println("Changing details...");
        s.setName("Eric");
        s.setSurname("The Best");
        s.setNumber("C14386641");
        s.setCurrentYear(4);
        
        System.out.println("New Details:");
        System.out.println("Name: " + s.getName());
        System.out.println("Surname: " + s.getSurname());
        System.out.println("StudentNo: " + s.getNumber());
        System.out.println("Year:" + s.getCurrentYear());
        
	}
}

