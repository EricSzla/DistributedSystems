package ie.dit.comp.student;

/**
	Abstract base class for a Student. Implements the Tellable interface which prints out details about the object.
	@author Eryk Szlachetka
*/
public abstract class Student {

	/** The name of the student */
	protected String name;

	/** The surname of the student */
	protected String surname;
    
    /** The student number */
    protected String studentNo;

	/** 
		Default constructor, sets the everything to empty
	*/
	public Student() {
		this("","",""); // call the other constructor
	}
	
	/**
		Constructor to read in balance and name
		@param surname The student's surname
		@param name The name of the account 
        @param no Student number
	*/
	public Student(String name, String surname, String no) {
		this.name = name; // 'this' keyword represents the varaible attached to the object,
								// used to distinguish from the parameter passed in which has local scope
		this.surname = surname;
        this.studentNo = no;
	}
	
	/**
		Returns the balance in the account
		@return Name of the student.
	*/
	public String getName() {
		return name;
	}
    
    /**
        Returns surname
        @return Surname of the student.
    */
    
    public String getSurname(){
        return surname;
    }

	/**
		Abstract method, takes an int parameter, will be overridden by sub classes
		@param sno Student Number to be recorded
	*/
	public abstract void setNumber(String sno);

	/**
		Abstract method, takes an int parameter, will be overridden by sub classes
		@param sname Name to be recorded
	*/
	public abstract void setName(String sname);

    /**
        @return true if success
    */
    public abstract String getNumber();
    
    public void setSurname(String namee){
        this.surname = namee;
    }
}
