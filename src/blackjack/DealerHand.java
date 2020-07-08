package blackjack;

import java.util.*;

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

    private void totalValue() {
        System.out.println("Visible Dealer hand value: " + dealerHand.get(0).value);
    }
}