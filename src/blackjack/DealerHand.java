package blackjack;

import java.util.*;

// extension to Hand class to not reveal hidden cards
// similar to a casino

public class DealerHand extends Hand {
    private ArrayList<Card> dealerHand = new ArrayList<>();

    public void addToHand(Card card) {
        dealerHand.add(card);
    }

    public void displayHand() {
        System.out.println("Dealer's hand: ");
        for (Card t : dealerHand) {
            System.out.println(t.name);
        }
        int total = 0;
        total = totalValue();

        System.out.println("Total hand value: " + total + "\n");
    }

    public void displayHandHidden() {
        System.out.println("Dealer hand: ");
        System.out.println(dealerHand.get(0).name);
        totalValueHidden();
    }

    public int totalValue() {
        int total = 0;
        for (Card card : dealerHand) {
            total = total + card.value;
        }
        return total;
    }

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