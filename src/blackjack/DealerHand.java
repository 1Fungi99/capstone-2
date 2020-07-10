package blackjack;

import java.util.*;

// extension to Hand class to not reveal hidden cards
// similar to a casino

public class DealerHand extends Hand {
    private ArrayList<Card> dealerHand = new ArrayList<>();

    @Override
    public void addToHand(Card card) {
        dealerHand.add(card);
    }

    public void displayHand() {
        System.out.println("Dealer hand: ");
        System.out.println(dealerHand.get(0).name);
        totalValue();
    }

    public int totalValue() {
        System.out.println("Visible Dealer hand value: " + dealerHand.get(0).value + "\n");
        return dealerHand.get(0).value;
    }
}