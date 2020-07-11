package blackjack;

import java.util.*;

class Deck {
    // Lists for the elements of a card
    private final List<String> cards = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
            "King", "Ace");
    private final List<String> faceList = Arrays.asList("Spades", "Clubs", "Hearts", "Diamonds");

    private final List<String> cardsShort = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
            "A");
    private final List<String> faceShort = Arrays.asList("S", "C", "H", "D");

    // Adding more than one deck
    // private int cardsInDeck = 52 * 1; // (#cards in regular deck) * (number of decks)
    private final int numOfDecks;

    private final List<Card> deck = new ArrayList<>();

    // Boolean that will not allow the deck to be shuffled more after the for loop.
    // if you think a machine can't shuffle cards... I don't know what to tell you, son.
    private boolean shuffledAlready = false;

    // initialization of a new deck, will be in order of suits => values
    public Deck(final int numOfDecks) {
        this.numOfDecks = numOfDecks;
        for (int a = 0; a < numOfDecks; a++) {
            for (int i = 0; i < faceList.size(); i++) {
                for (int j = 0; j < cards.size(); j++) {
                    deck.add(new Card(cards.get(j) + " of " + faceList.get(i), cardsShort.get(j), faceShort.get(i)));
                }
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
            final int numOfShuffles = (int) (Math.random() * 5 + 1);
            for (int i = 0; i < numOfShuffles; i++) {
                Shuffle task = new Shuffle(shuffledAlready, deck);
                task.run();
            }
            System.out.println("Deck shuffled " + numOfShuffles + " times!\n");
        } else {
            System.out.println("Deck was already shuffled");
        }
    }

    // if you want to see and cheat off of the card list... deals from index(0) - index(MAX)
    private void seeDeck() {
        System.out.println("Current Deck: ");
        System.out.println("========================================");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + ". " + deck.get(i).name);
        }
        System.out.println("========================================");
    }

    // General dealing of cards
    public Card dealOne() {
        final Card dealtCard = deck.get(0);
        deck.remove(0);
        return dealtCard;
    }

    // Made for Dealer's hidden cards.
    // Probably dont need this.
    public Card dealOneHidden() {
        final Card dealtCard = deck.get(0);
        deck.remove(0);
        return dealtCard;
    }
}

class Shuffle implements Runnable {
    private boolean shuffledAlready;
    private List<Card> deck;

    public Shuffle(boolean shuffledAlready, List<Card> deck) {
        this.shuffledAlready = shuffledAlready;
        this.deck = deck;
    }

    public void run() {
        try {
            System.out.println("Shuffling...");
            Collections.shuffle(deck);
            shuffledAlready = true;
            Thread.sleep(1000);
            // seeDeck();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}