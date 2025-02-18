package main.java;

import Cards.PlayerCardHand;
import Cards.Card;
import Cards.CardPack;
import Cards.DealerCardHand;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameTable extends JPanel {
    private DealerCardHand dealer;
    private ArrayList<PlayerCardHand> players;
    private boolean showAllDealerCards;
    private boolean gameOver;

    private final int CARD_INCREMENT = 20;
    private final int INITIAL_CARD_POSITION = 100;
    private final int DEALER_POSITION = 50;
    private final int CARD_IMAGE_WIDTH = 71;
    private final int CARD_IMAGE_HEIGHT = 96;
    private final int NAME_SPACE = 10;

    private Font handTotalFont;
    private Font playerNameFont;

    private String dealerName;
    private ArrayList<String> playerNames;

    private Color playerNameColor = Color.WHITE;

    // Use transient to mark non-serializable images
    private transient Image[] cardImages = new Image[CardPack.CARDS_IN_PACK + 1];
    private transient Image backgroundImg;

    private int cardX = INITIAL_CARD_POSITION;
    private Timer timer;
    private boolean animating = false;
    private int animationStep = 5;
    private int cardIndex = 0;

    public GameTable() {
        super();
        initializePanel();
        loadCardImages();
        setupTimer();
    }

    private void initializePanel() {
        this.setBackground(Color.GREEN); // Avoid calling in constructor directly.
        this.setOpaque(false); // Avoid calling in constructor directly.
        handTotalFont = new Font("Serif", Font.PLAIN, 96);
        playerNameFont = new Font("Serif", Font.ITALIC, 20);
        showAllDealerCards = true;
        gameOver = false;

        players = new ArrayList<>();
        playerNames = new ArrayList<>();

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    private void loadCardImages() {

        for (int i = 0; i < CardPack.CARDS_IN_PACK; i++) {
            String cardName = "/card_images/" + (i + 1) + ".png";
            URL urlImg = getClass().getResource(cardName);
            if (urlImg == null) {
                System.err.println("Imagem não encontrada: " + cardName);
                continue;
            }
            cardImages[i] = Toolkit.getDefaultToolkit().getImage(urlImg);
        }

        String backCard = "/card_images/red_back.png";
        URL backCardURL = getClass().getResource(backCard);
        if (backCardURL != null) {
            cardImages[CardPack.CARDS_IN_PACK] = Toolkit.getDefaultToolkit().getImage(backCardURL);
        }

        MediaTracker imageTracker = new MediaTracker(this);
        for (int i = 0; i < CardPack.CARDS_IN_PACK + 1; i++) {
            if (cardImages[i] != null) {
                imageTracker.addImage(cardImages[i], i);
            }
        }

        try {
            imageTracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setupTimer() {
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardX += animationStep;
                if (cardX >= INITIAL_CARD_POSITION + CARD_INCREMENT * cardIndex) {
                    animating = false;
                    timer.stop();
                }
                repaint();
            }
        });
    }

    private void handleMouseClick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (!gameOver && y >= getPlayerPositionY(0) && y <= getPlayerPositionY(0) + CARD_IMAGE_HEIGHT) {
            int index = (x - INITIAL_CARD_POSITION) / CARD_INCREMENT;
            if (index >= 0 && index < players.get(0).size()) {
                Card clickedCard = players.get(0).get(index);
                JOptionPane.showMessageDialog(null, "You clicked on: " + clickedCard);
            }
        }
    }

    private int getPlayerPositionY(int playerIndex) {
        return getHeight() - 150;
    }

    public void setHands(DealerCardHand dealer, ArrayList<PlayerCardHand> players) {
        this.dealer = dealer;
        this.players = players;
        this.cardIndex = 0;
        this.cardX = INITIAL_CARD_POSITION;
        animating = true;
        timer.start();
    }

    public void setShowAllDealerCards(boolean showAllDealerCards) {
        this.showAllDealerCards = showAllDealerCards;
    }

    public void setNames(String dealerName, ArrayList<String> playerNames) {
        this.dealerName = dealerName;
        this.playerNames = playerNames;
    }

    public void setPlayerNameColor(Color color) {
        this.playerNameColor = color;
        repaint();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        repaint();
    }

    public void startCardAnimation(int index) {
        cardIndex = index;
        cardX = INITIAL_CARD_POSITION;
        animating = true;
        timer.start();
    }

    public void update(DealerCardHand dealer, ArrayList<PlayerCardHand> players, boolean showAllDealerCards) {
        setHands(dealer, players);
        setShowAllDealerCards(showAllDealerCards);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackgroundImage(g);
        drawPlayerNamesAndScores(g);
        if (gameOver) {
            return;
        }

        g.setFont(handTotalFont);
        drawDealerCards(g);
        drawPlayerCards(g);
    }

    private void drawBackgroundImage(Graphics g) {
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void drawPlayerNamesAndScores(Graphics g) {
        g.setColor(playerNameColor);
        g.setFont(playerNameFont);

        g.drawString(dealerName, getWidth() / 2 - dealerName.length() * 5, DEALER_POSITION - NAME_SPACE);

        if (playerNames.size() > 0) {
            drawPlayerNameAndScore(g, playerNames.get(0), 75, getPlayerPositionY(0), 0);
            if (playerNames.size() > 1) {
                drawPlayerNameAndScore(g, playerNames.get(1), getWidth() - 175, getPlayerPositionY(0), 1);
            }
        }
    }

    private void drawPlayerNameAndScore(Graphics g, String playerName, int playerX, int playerY, int playerIndex) {
        String[] nameParts = playerName.split(" ");
        StringBuilder line = new StringBuilder();
        int lineHeight = g.getFontMetrics().getHeight();
        int linesCount = calculateLinesCount(nameParts);

        int yPosition = playerY - NAME_SPACE - (linesCount - 1) * lineHeight;
        int wordCount = 0;

        for (int i = 0; i < nameParts.length; i++) {
            if (wordCount < 2) {
                if (line.length() > 0) {
                    line.append(" ");
                }
                line.append(nameParts[i]);
                wordCount++;
            } else {
                g.drawString(line.toString(), playerX, yPosition);
                yPosition += lineHeight;
                line.setLength(0);
                line.append(nameParts[i]);
                wordCount = 1;
            }
        }

        if (line.length() > 0) {
            g.drawString(line.toString(), playerX, yPosition);
        }

        g.drawString(Integer.toString(players.get(playerIndex).getTotal()), playerX + 20, playerY + CARD_IMAGE_HEIGHT + 20);
    }

    private int calculateLinesCount(String[] nameParts) {
        int linesCount = 0;
        int wordCount = 0;

        for (String part : nameParts) {
            if (wordCount < 2) {
                wordCount++;
            } else {
                linesCount++;
                wordCount = 1;
            }
        }

        if (wordCount > 0) {
            linesCount++;
        }

        return linesCount;
    }

    private void drawDealerCards(Graphics g) {
        int dealerStartX = (getWidth() - (CARD_IMAGE_WIDTH + CARD_INCREMENT) * dealer.size()) / 2;
        if (showAllDealerCards) {
            for (Card aCard : dealer) {
                g.drawImage(cardImages[aCard.getCode() - 1], dealerStartX, DEALER_POSITION, this);
                dealerStartX += CARD_INCREMENT;
            }
            g.drawString(Integer.toString(dealer.getTotal()), dealerStartX + CARD_IMAGE_WIDTH + CARD_INCREMENT, DEALER_POSITION + CARD_IMAGE_HEIGHT);
        } else {
            for (Card aCard : dealer) {
                g.drawImage(cardImages[CardPack.CARDS_IN_PACK], dealerStartX, DEALER_POSITION, this);
                dealerStartX += CARD_INCREMENT;
            }
            try {
                Card topCard = dealer.lastElement();
                dealerStartX -= CARD_INCREMENT;
                g.drawImage(cardImages[topCard.getCode() - 1], dealerStartX, DEALER_POSITION, this);
                g.drawString("?", dealerStartX + CARD_IMAGE_WIDTH + CARD_INCREMENT, DEALER_POSITION + CARD_IMAGE_HEIGHT);
            } catch (Exception e) {
                System.out.println("No cards have been dealt yet.");
            }
        }
    }

    private void drawPlayerCards(Graphics g) {
        int playerStartX = 75;
        for (Card aCard : players.get(0)) {
            g.drawImage(cardImages[aCard.getCode() - 1], playerStartX, getPlayerPositionY(0), this);
            playerStartX += CARD_INCREMENT;
        }

        if (players.size() > 1) {
            playerStartX = getWidth() - 175;
            for (Card aCard : players.get(1)) {
                g.drawImage(cardImages[aCard.getCode() - 1], playerStartX, getPlayerPositionY(0), this);
                playerStartX += CARD_INCREMENT;
            }
        }
    }
}
