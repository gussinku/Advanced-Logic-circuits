/**
 * An assignment determines what variables are true and what variables are false
 * This is done by using an array of true variables. The variables not present in 
 * the array are false.
 */

public class Assignment {

	/**
	 * Array of true variables.
	 */

	private String[] trueVariables;

	/**
	 * Constructor for assignment that considers true all variables in the array
	 * 
	 * @param trueVariables
	 *            the name of variables considered true
	 */

	public Assignment(String[] trueVariables) {
		this.trueVariables = trueVariables;
	}

	/**
	 * Determines whether or not a variable is true in this assignment.
	 * 
	 * @param var
	 *            the variable to check
	 * @return true iff {@code var} is true in this assignment
	 */

	public boolean valueOf(Variable var) {
		for (String trueVariable : trueVariables)
			if (var.getName().equals(trueVariable))
				return true;

		return false;
	}
}
