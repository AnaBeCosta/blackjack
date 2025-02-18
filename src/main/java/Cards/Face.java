package Cards;

/**
 * Class to represent the face value of a Card.
 *
 * @author David Winter
 */
public class Face {
	/**
	 * String representation of the face value.
	 */
	private String name;

	/**
	 * Integer representation of the face value.
	 */
	private int value;

	private final int HIGH_ACE = 11;
	private final int LOW_ACE = 1;

	/**
	 * Conversion constructor that creates a Face object based on an Integer code.
	 *
	 * @param face The Integer code that represents a face value.
	 */

	public Face(int face) {
		String[] names = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		if (face >= 1 && face <= 13) {
			setName(names[face - 1]);
			setValue(values[face - 1]);
		}
	}


	/**
	 * Mutator method that sets the String representation of the face value.
	 *
	 * @param name The String representation of the face value.
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor method that returns the name of a face value.
	 *
	 * @return The name of the face value.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Mutator method that sets the Integer value of a face value.
	 *
	 * @param value The Integer value of the face value.
	 */
	private void setValue(int value) {
		this.value = value;
	}

	/**
	 * Accessor method that returns the Integer value of the face value.
	 *
	 * @return The Integer value of the face value.
	 */
	public int getValue() {
		return this.value;
	}

	public boolean isAce() {
		return name.equals("Ace");
	}

	public boolean isLowAce() {
		return name.equals("Ace") && getValue() == LOW_ACE;
	}

	public void switchAce() {
		if (isAce() && getValue() == HIGH_ACE) {
			setValue(LOW_ACE);
		}
	}

	/**
	 * String representation of the face value.
	 *
	 * @return The String representation of the face value.
	 */
	public String toString() {
		return getName();
	}
}