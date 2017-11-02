/**
 * This is a subclass of Circuit implementing the disjunction of a left Circuit
 * and a right Circuit
 * 
 */

public class Or extends Circuit {

	// Private instance variables,
	// left, the left Circuit
	// right, the right Circuit

	private Circuit left, right;

	// Constructor with parameters

	public Or(Circuit left, Circuit right) {
		this.left = left;
        this.right = right;
	}

	/**
	 * String representation of the disjunction of a left circuit and a right
	 * circuit
	 */

	public String toString() {
		return "("+ left.toString() + " \u22C1 " + right.toString() + ")";
	}

	/**
	 * Returns true if the disjunction of the left circuit and the right circuit
	 * is true in the assignment
	 */

	public boolean isTrueIn(Assignment assignment) {
		return left.isTrueIn(assignment) || right.isTrueIn(assignment);
	}

	/**
	 * Returns the array of free Variables in the disjunction of a left circuit
	 * and a right circuit. The order of variables is not important, however, a
	 * variable should appear exactly once in the array (no repetitions)
	 */

	public Variable[] freeVariables() {
        return CircuitMain.freeVariables(this);
	}

}