
import static dit948.SimpleIO.*;
import static dit948.Random.*;

public  class Circuit {


	/**
	 * Creates a random circuit of a given depth.
	 *
	 * @param depth the maximal depth, at least 1
	 * @param variables the variables used in the circuit
	 * @return a random circuit
	 */

	public static Circuit mkRandom(int depth, String[] variables) {
		if (depth == 1)
			return chooseOneFrom(variables);
		else {
			switch (randomInt(4)) {
			case 0:
				return new And(mkRandom(depth - 1, variables), mkRandom(depth - 1, variables));
			case 1:
				return new Or(mkRandom(depth - 1, variables), mkRandom(depth - 1, variables));
			case 2:
				return new Not(mkRandom(depth - 1, variables));
			default:
				return chooseOneFrom(variables);
			}
		}
	}

	/**
	 * Chooses a random variable from the formal parameter 
	 *
	 * @param variables names of to choose from
	 * @return the chosen variable
	 */

	private static Variable chooseOneFrom(String[] variables) {
		return new Variable(variables[randomInt(variables.length)]);
	}

	/**
	 * Determine whether this circuit is true in this assignment
	 *
	 * @param assignment the assignment
	 * @return false
	 */

	public  boolean isTrueIn(Assignment assignment){
		return false;
	}

	/**
	 * Returns the free variables in this circuit.
	 *
	 * @return empty array of variables
	 */

	public  Variable[] freeVariables(){
		return new Variable[] {};
	}

	/**
	 * Returns true iff other is logically equivalent to this circuit.
	 */

	public boolean equals(Object other) {
		if (other instanceof Circuit) {
			Circuit otherAsCircuit = (Circuit) other;

			Variable[] fv = new And(this, otherAsCircuit).freeVariables();

			return agreeOnAllPossibleAssignments(this, otherAsCircuit, fv, 0,
					new String[0]);
		}

		return false;
	}

	private boolean agreeOnAllPossibleAssignments(Circuit circuit1,
			Circuit circuit2, Variable[] vars, int pos, String[] choice) {
		if (pos == vars.length) {
			Assignment assignment = new Assignment(choice);
			return circuit1.isTrueIn(assignment) == circuit2
					.isTrueIn(assignment);
		} else {
			return agreeOnAllPossibleAssignments(circuit1, circuit2, vars,
					pos + 1, expand(choice, vars[pos].getName()))
					&& agreeOnAllPossibleAssignments(circuit1, circuit2, vars,
							pos + 1, choice);
		}
	}

	private String[] expand(String[] names, String name) {
		String[] res = new String[names.length + 1];
		System.arraycopy(names, 0, res, 0, names.length);
		res[names.length] = name;

		return res;
	}


	public  String toString(){
		return null;
	}
}