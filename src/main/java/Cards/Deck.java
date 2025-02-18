package Cards;

import java.io.Serializable;
import java.util.Collections;
import java.util.Stack;

/**
 * Represents a shuffled Deck of playing main.java.Cards.
 *
 * @author David Winter
 */
public class Deck extends Stack<Card> implements Serializable {
	private final int serialVersionUID = 1;
	/**
	 * The number of Card Packs used for this Deck.
	 */
	private int numberOfPacks;

	/**
	 * Default constructor that will create a Deck from 1 Card Pack.
	 */
	public Deck() {
		super();
		setNumberOfPacks(1);
		shuffleDeck();
	}

	/**
	 * Conversion constructor that will create a Deck from the specified number of
	 * Card Packs.
	 *
	 * @param numberOfPacks The number of Card Packs to use.
	 */
	public Deck(int numberOfPacks) {
		super();

		setNumberOfPacks(numberOfPacks);

		for (int i = 0; i < numberOfPacks; i++) {
			shuffleDeck();
		}
	}

	/**
	 * Mutator method that sets the number of Card Packs used for this deck.
	 *
	 * @param number The number of Card Packs used for this deck.
	 */
	private void setNumberOfPacks(int number) {
		this.numberOfPacks = number;
	}

	/**
	 * Accessor method that returns the number of Card Packs used for this deck.
	 *
	 * @return The number of Card Packs used for this deck.
	 */
	public int getNumberOfPacks() {
		return this.numberOfPacks;
	}

	/**
	 * Shuffles the Deck of cards.
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}

	/**
	 * Only the dealer should deal the card from the Deck.
	 */
	public Card deal() {
		if (this.empty()) {
			System.out.println("Run out of cards. New Deck.");
			for (int i = 0; i < numberOfPacks; i++) {
				this.addAll(new CardPack());
			}

			shuffle();
		}

		return this.pop();
	}
	
	public void returnCard(Card card) {
		this.push(card);
	}

	private void shuffleDeck() {
		this.addAll(new CardPack());
		shuffle();
	}
}