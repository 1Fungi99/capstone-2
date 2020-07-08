package blackjack;

import java.util.*;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private boolean dealt = false;

    public void addToHand(Card card) {
        hand.add(card);
        int total = 0;
        for (Card t : hand) {
            total = total + t.value;
        }
    }

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

    private int totalValue() {
        int total = 0;
        for (Card card : hand) {
            total = total + card.value;
        }
        return total;
    }

    private void checkHandValue(int total) {
        if (!dealt && total == 21) {
            System.out.println("BLACKJACK");
        }
    }
}