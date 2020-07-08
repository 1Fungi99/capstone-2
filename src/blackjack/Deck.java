package blackjack;

import java.util.*;

class Deck {
    private List<String> cards = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace");
    private List<String> faceList = Arrays.asList("Spades", "Clubs", "Hearts", "Diamonds");
    // private int cardsInDeck = 52 * 1; // (#cards in regular deck) * (number of decks)
    private List<Card> deck = new ArrayList<>();
    private boolean shuffledAlready = false;

    public Deck() {
        for (int i = 0; i < faceList.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                deck.add(new Card(cards.get(j) + " of " + faceList.get(i)));
            }
        }
    }

    public void getCardsInDeck() {
        System.out.println("Deck Size: " + deck.size());
    }

    public void shuffleDeck() {
        if (!shuffledAlready) {
            Collections.shuffle(deck);
            shuffledAlready = true;
            // seeDeck();
        } else {
            System.out.println("Deck was already shuffled");
        }
    }

    public void seeDeck() {
        System.out.println("Current Deck: ");
        System.out.println("========================================");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + ". " + deck.get(i).name);
        }
        System.out.println("========================================");
    }

    // General deal hands
    public Card dealOne() {
        Card dealtCard = deck.get(0);
        deck.remove(0);
        // System.out.println("Dealt: " + dealtCard.name);
        return dealtCard;
    }

    // Made for Dealer's hidden cards.
    public Card dealOneHidden() {
        Card dealtCard = deck.get(0);
        deck.remove(0);
        // System.out.println("Dealt (HIDDEN): " + dealtCard.name);
        return dealtCard;
    }

}