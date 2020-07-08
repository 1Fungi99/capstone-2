package blackjack;

import java.util.*;

// the hand of the player(s)

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private boolean dealt = false;

    // Add cards to hand
    public void addToHand(Card card) {
        hand.add(card);
        int total = 0;
        for (Card t : hand) {
            total = total + t.value;
        }
    }

    // Display current hand of player
    public void displayHand() {
        System.out.println("Your hand: ");
        for (Card t : hand) {
            System.out.println(t.name);
        }
        int total = 0;
        total = totalValue();

        //TEST: BLACKJACK
        // total = 21;

        System.out.println("Total hand value: " + total);
        checkHandValue(total);
    }

    // get total value of current hand
    private int totalValue() {
        int total = 0;
        for (Card card : hand) {
            total = total + card.value;
        }
        return total;
    }

    // Checks for blackjacks
    private void checkHandValue(int total) {
        if (!dealt && total == 21) {
            System.out.println("BLACKJACK");
        }
    }
}