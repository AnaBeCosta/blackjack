package Cards;


public class Suit {

	private String name;

	public Suit(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return getName();
	}
}
