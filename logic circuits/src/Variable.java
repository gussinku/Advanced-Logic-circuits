/**
 * This is a subclass of Circuit implementing Variable in a Circuit
 * 
 */

public class Variable extends Circuit {

	// Private instance variable,
	// name, a String representing a variable

	private String name;

	// Constructor with parameters

	public Variable(String name) {
		this.name = name;
	}

	/**
	 * String representation of a variable of a circuit
	 */

	public String toString() {
		return this.getName();
	}

	/**
	 * Returns the name of the variable
	 */

	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns true if the variable is true in the assignment
	 */

	public boolean isTrueIn(Assignment assignment) {
		return assignment.valueOf(this);
	}

	/**
	 * Returns the array of free Variables in this Variable. 
	 */

	public Variable[] freeVariables() {
        return CircuitMain.freeVariables(this);
	}
}