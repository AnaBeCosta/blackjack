package main.java;

import main.java.Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerDialog extends JDialog implements ActionListener {
	String name;
	int age;
	String gender;

	Player player;

	String[] genders = { "Male", "Female" };

	JTextField playerName = new JTextField();
	JTextField playerAge = new JTextField();
	JComboBox<String> playerGender = new JComboBox<>(genders);

	public PlayerDialog(Frame owner, String title, boolean modal, Player player) {
		super(owner, title, modal);
		initialize();

		this.player = player;

		JPanel playerDetailsPanel = new JPanel(new SpringLayout());

		JLabel nameLabel = new JLabel("Name", SwingConstants.TRAILING);
		playerDetailsPanel.add(nameLabel);
		nameLabel.setLabelFor(playerName);
		playerDetailsPanel.add(playerName);

		JLabel ageLabel = new JLabel("Age", SwingConstants.TRAILING);
		playerDetailsPanel.add(ageLabel);
		ageLabel.setLabelFor(playerAge);
		playerDetailsPanel.add(playerAge);

		JLabel genderLabel = new JLabel("Gender", SwingConstants.TRAILING);
		playerDetailsPanel.add(genderLabel);
		genderLabel.setLabelFor(playerGender);
		playerDetailsPanel.add(playerGender);

		SpringUtilities.makeCompactGrid(playerDetailsPanel, 3, 2, 5, 5, 5, 5);

		inicializarComponentes();

		JButton updateButton = new JButton("Update Details");
		JButton cancelButton = new JButton("Cancel");

		JPanel playerUpdatePanel = new JPanel();
		playerUpdatePanel.add(updateButton);
		playerUpdatePanel.add(cancelButton);
		inicializarComponentes();

		updateButton.addActionListener(this);
		cancelButton.addActionListener(this);

		playerName.setText(player.getName());
		playerAge.setText(Integer.toString(player.getAge()));
		playerGender.setSelectedItem(player.getGender());

	}

	private void initialize() {
		setSize(300, 200);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent evt) {
		String act = evt.getActionCommand();

		if (act.equals("Update Details")) {
			updateDetails();
		} else if (act.equals("Cancel")) {
			setVisible(false);
			dispose();
		} else {
			return;
		}
	}

	private void updateDetails() {
		boolean validName = true;
		if (playerName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a Player name", "Error", JOptionPane.ERROR_MESSAGE);
			validName = false;
		} else {
			player.setName(playerName.getText());
		}

		boolean validAge = true;
		try {
			age = Integer.parseInt(playerAge.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter an age - numbers only", "Error",
					JOptionPane.ERROR_MESSAGE);
			validAge = false;
		}

		if (validAge) {
			player.setAge(age);
		}

		player.setGender((String) playerGender.getSelectedItem());

		if (validAge && validName) {
			setVisible(false);
			dispose();
		}
	}

	public Player getPlayer() {
		return player;
	}

	private void inicializarComponentes() {
		JPanel playerDetailsPanel = new JPanel();
		this.add(playerDetailsPanel, BorderLayout.NORTH);
	}
}