package blackjack;

import java.util.*;

// the hand of the player(s)

public class Hand extends HandOfCards {
    private ArrayList<Card> hand = new ArrayList<>();
    private boolean dealt = false;

    // Add cards to hand
    @Override
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

        System.out.println("Total hand value: " + total + "\n");
        initialBlackjack(total);
    }

    // get total value of current hand
    public int totalValue() {
        int total = 0;
        int aceCount = 0;
        for (Card card : hand) {
            total = total + card.value;
            if (card.name.contains("Ace")) {
                aceCount++;
            }
        }
        if (total > 21 && aceCount != 0) {
            total = total - (aceCount * 10);
        }
        return total;
    }

    // Checks for blackjacks
    private void initialBlackjack(int total) {
        if (!dealt && total == 21) {
            System.out.println("BLACKJACK");
        }
    }

    // checks to make sure that the player has not busted
    // true means less than 21, still valid play
    // false means more than 21, no longer valid play
    public boolean checkHandValue() {
        if (totalValue() <= 21) {
            return true;
        } else {
            return false;
        }
    }

}