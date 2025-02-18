package blackjack;

import main.java.AppWindow;

import javax.swing.*;

public class Blackjack {
	public static void main(String[] args) {
		setSystemLookAndFeel();
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		SwingUtilities.invokeLater(AppWindow::new);
	}

	private static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
