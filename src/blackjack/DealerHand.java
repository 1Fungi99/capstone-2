package blackjack;

import java.util.*;

// extension to Hand class to not reveal hidden cards
// similar to a casino

public class DealerHand extends Hand {
    private ArrayList<Card> dealerHand = new ArrayList<>();

    // Add card to hand
    public void addToHand(Card card) {
        dealerHand.add(card);
    }

    // Display all cards in hand
    public void displayHand() {
        System.out.println("Dealer's hand: ");
        for (Card t : dealerHand) {
            System.out.println(t.name);
        }
        int total = 0;
        total = totalValue();

        System.out.println("Total hand value: " + total + "\n");
    }

    // display only the first card before the flop
    public void displayHandHidden() {
        System.out.println("Dealer hand: ");
        System.out.println(dealerHand.get(0).name);
        totalValueHidden();
    }

    // sum of all cards in hand
    public int totalValue() {
        int total = 0;
        int aceCount = 0;
        for (Card card : dealerHand) {
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

    // only show value of the one card shown before flop
    public int totalValueHidden() {
        System.out.println("Visible Dealer hand value: " + dealerHand.get(0).value + "\n");
        return dealerHand.get(0).value;
    }

    public int setValue(String name) {
        if (name.contains("Ace")) {
            return 11;
        }
        if (name.contains("King") || name.contains("Queen") || name.contains("Jack")) {
            return 10;
        }

        for (int i = 2; i <= 10; i++) {
            if (name.contains(String.valueOf(i))) {
                return i;
            }
        }
        return 0;
    }
}