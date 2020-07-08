package blackjack;

import java.util.*;

class Deck {
    // Lists for the elements of a card
    private List<String> cards = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace");
    private List<String> faceList = Arrays.asList("Spades", "Clubs", "Hearts", "Diamonds");

    // Possibility of adding more than one deck
    // private int cardsInDeck = 52 * 1; // (#cards in regular deck) * (number of decks)

    private List<Card> deck = new ArrayList<>();

    // Boolean that will not allow the deck to be shuffled more than once.
    // if you think a machine can't shuffle cards... I don't know what to tell you, son.
    private boolean shuffledAlready = false;

    // initialization of a new deck, will be in order of suits => values
    public Deck() {
        for (int i = 0; i < faceList.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                deck.add(new Card(cards.get(j) + " of " + faceList.get(i)));
            }
        }
    }

    // grabs current size of deck
    public void getCardsInDeck() {
        System.out.println("Deck Size: " + deck.size());
    }

    // shuffles new deck
    public void shuffleDeck() {
        if (!shuffledAlready) {
            Collections.shuffle(deck);
            shuffledAlready = true;
            // seeDeck();
        } else {
            System.out.println("Deck was already shuffled");
        }
    }

    // if you want to see and cheat off of the card list... deals from index(0) - index(MAX)
    public void seeDeck() {
        System.out.println("Current Deck: ");
        System.out.println("========================================");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + ". " + deck.get(i).name);
        }
        System.out.println("========================================");
    }

    // General dealing of cards
    public Card dealOne() {
        Card dealtCard = deck.get(0);
        deck.remove(0);
        // System.out.println("Dealt: " + dealtCard.name);
        return dealtCard;
    }

    // Made for Dealer's hidden cards.
    // Probably dont need this.
    public Card dealOneHidden() {
        Card dealtCard = deck.get(0);
        deck.remove(0);
        // System.out.println("Dealt (HIDDEN): " + dealtCard.name);
        return dealtCard;
    }

}