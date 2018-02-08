package ie.dit.comp.student;

/**
	Student Record where a current year can be specified.
	Extends Student and implements Tellable (since this is implemented at a higher level)
	@author Eryk Szlachekta
*/
public class StudentRecord extends Student {

	private int currentYear;

	/** 
		Default constructor, sets the balance to 0, and the name to an empty string
	*/
	public StudentRecord() {
		this("", ""); // call the third constructor
	}
	
	/**
		Constructor to read in balance and name
		@param surname The surname of the student
		@param name The name of the student
	*/
	public StudentRecord(String surname, String name) {
		this(surname, name, "", 1); // call the third constructor
	}
	
	/**
		Constructor to read in balance, name and credit limit
		@param surname The surname of the student
		@param name The name of the student
		@param studentNo The student number
        @param cyear The current year of the student
	*/
	public StudentRecord(String surname, String name, String studentNo, int cyear) {
		super(name, surname, studentNo);
		this.currentYear = cyear;
	}
	
	/**
		Returns the current year
		@return Year which the student attends
	*/
	public int getCurrentYear() {
		return currentYear;
	}

	/**
		Sets current year
		@param amt Sets current year that student attends
	*/
	public void setCurrentYear(int amt) {
		currentYear = amt;
	}
    
    /**
        Sets the student no
        @param sno Sets the student number.
    */
    public void setNumber(String sno){
        this.studentNo = sno;
    }
    
    /**
        Sets the name
        @param sname Sets the student name
     */
    public void setName(String sname){
        this.name = sname;
    }
    
    /**
        @return Student Number
     */
    public String getNumber(){
        return studentNo;
    }
}
