package blackjack;

import java.util.*;

class Deck {
    private List<String> cards = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace");
    private List<String> faceList = Arrays.asList("Spades", "Clubs", "Hearts", "Diamonds");
    private int cardsInDeck = 52 * 1; // (#cards in regular deck) * (number of decks)
    private List<Card> deck = new ArrayList<>();

    public Deck() {
        for (int i = 0; i < faceList.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                deck.add(new Card(cards.get(j) + " of " + faceList.get(i)));
            }
        }
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
        seeDeck();
    }

    public void seeDeck() {
        System.out.println("Current Deck: ");
        System.out.println("========================================");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + ". " + deck.get(i).name);
        }
        System.out.println("========================================");
    }

    // public void dealTwo() {
    //     String cardOne = dealOne();
    //     String cardTwo = dealOne();

    //     System.out.println("Card 1: " + cardOne);
    //     System.out.println("Card 2: " + cardTwo);
    // }

    // private String dealOne() {
    //     int cardsRange = 13;
    //     int cardsIndex = (int) (Math.random() * cardsRange);
    //     String cardValue = cards.get(cardsIndex);

    //     int faceRange = 3;
    //     int faceIndex = (int) (Math.random() * faceRange);
    //     String faceValue = faceList.get(faceIndex);

    //     // System.out.println("cardIndex: " + cardsIndex);
    //     // System.out.println("card value: " + cards.get(cardsIndex));
    //     // System.out.println("faceIndex: " + faceIndex);
    //     // System.out.println("face value: " + faceList.get(faceIndex));

    //     return cardValue + " of " + faceValue;

    // }
}