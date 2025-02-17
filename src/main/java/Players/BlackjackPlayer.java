package main.java.Players;

import java.io.Serializable;

/**
 * Class that represents a general main.java.main.java.balckjack.Blackjack player. Including the Dealer. The
 * dealer though plays to certain rules, and because of this, will need to
 * override some methods in the Dealer class.
 *
 * @author David Winter
 */
public class BlackjackPlayer implements Serializable {
	private static final long serialVersionUID = 1L;  // Added serialVersionUID

	/**
	 * Name of main.java.main.java.balckjack.Blackjack player.
	 */
	private String name;

	/**
	 * Age of main.java.main.java.balckjack.Blackjack player.
	 */
	private int age;

	/**
	 * Gender of main.java.main.java.balckjack.Blackjack player.
	 */
	private String gender;

	public BlackjackPlayer() {
		// Default constructor
	}

	/**
	 * Conversion constructor that creates a new player.
	 *
	 * @param name   The name of the player.
	 * @param age    The age of the player.
	 * @param gender The player's gender.
	 */
	public BlackjackPlayer(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	/**
	 * Mutator method that sets the name of the player.
	 *
	 * @param name The player's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor method that returns the player's name.
	 *
	 * @return The player's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Mutator method that sets the player's age.
	 *
	 * @param age The player's age.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Accessor method that returns the player's age.
	 *
	 * @return Player's age.
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Mutator method that sets the player's gender.
	 *
	 * @param gender The player's gender.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Accessor method that returns the player's gender.
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * String representation of the player.
	 *
	 * @return The player's name.
	 */
	@Override
	public String toString() {
		return getName();
	}
}
