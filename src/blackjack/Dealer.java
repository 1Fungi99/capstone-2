package blackjack;

public class Dealer {
    public static void main(String[] args) {
        Hand playerHand = new Hand();
        DealerHand dealerHand = new DealerHand();

        newGame(playerHand, dealerHand);
    }

    public static void newGame(Hand playerHand, DealerHand dealerHand) {
        System.out.println("New Game Started");
        Deck deck = new Deck();
        deck.shuffleDeck();

        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOne());
        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOneHidden());

        playerHand.displayHand();
        dealerHand.displayHand();
    }

    public static void hit() {
    }

}