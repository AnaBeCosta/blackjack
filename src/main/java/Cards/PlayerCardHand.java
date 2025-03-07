package Cards;

import Cards.Card;
import Cards.CardHand;


public class PlayerCardHand extends CardHand {
	public PlayerCardHand() {
		super();
	}

	public boolean add(Card card) {
		if (isBust() || hasBlackJack()) {
			return false;
		}

		boolean cardAdded = super.add(card);

		if (isBust()) {
			adJustAces();
		}

		return cardAdded;
	}

	private void adJustAces() {
		for (Card eachCard : this) {
			eachCard.getFace().switchAce();
			if (!isBust()) {
				break;
			}
		}
	}

	private boolean hasBlackJack() {
		return false;
	}
}