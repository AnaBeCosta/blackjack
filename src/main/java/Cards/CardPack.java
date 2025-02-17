package main.java.Cards;


import java.util.Stack;

/**
 * Represents a pack of 52 playing main.java.Cards. Hearts, Diamonds, Clubs and Spades;
 * Ace, 2 - 10, Jack, Queen and King.
 *
 * @author David Winter
 */
public class CardPack extends Stack<Card> {
	public static final int CARDS_IN_PACK = 52;

	private static final long serialVersionUID = 1;
	/**
	 * Default constructor that will create a new Card Pack. Also will assign each
	 * card with a number value.
	 */
	public CardPack() {
		super();

		final String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

		int cardCode = 1;
		int numberCycles = 14;

		for (String suit : suits) {
			for (int i = 1; i < numberCycles; i++) {
				addCard(new Card(new Face(i), new Suit(suit), cardCode));
				cardCode++;
			}
		}
	}

	private void addCard(Card card) {
		this.push(card);
	}
}