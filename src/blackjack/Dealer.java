package blackjack;

import java.util.*;

public class Dealer {

    public static void main(String[] args) {
        Hand playerHand = new Hand();
        DealerHand dealerHand = new DealerHand();

        newGame(playerHand, dealerHand);
    }

    public static void newGame(Hand playerHand, DealerHand dealerHand) {
        Scanner s = new Scanner(System.in);

        System.out.println("\nNew Game Started \n");
        System.out.println("How many decks would you like to play with?");
        System.out.print("Number of decks: ");

        int numOfDecksResponse = s.nextInt();

        while (numOfDecksResponse <= 0 || numOfDecksResponse > 10) {
            if (numOfDecksResponse <= 0) {
                System.out.println("Invalid Response:\nREASON: Must be greater than 0.");
                System.out.println("Please input a valid number of decks... *Dealer glares at you*");
                System.out.print("# of decks: ");
                numOfDecksResponse = s.nextInt();
            } else if (numOfDecksResponse > 10) {
                System.out.println("Invalid Response:\nREASON: Must be less than 10.");
                System.out.println("Please input a valid number of decks... *Dealer glares at you*");
                System.out.print("# of decks: ");
                numOfDecksResponse = s.nextInt();
            }
        }

        Deck deck = new Deck(numOfDecksResponse);

        deck.shuffleDeck();

        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOne());
        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOneHidden());

        dealerHand.displayHand();
        playerHand.displayHand();

        System.out.println("Your hand total is: " + playerHand.totalValue());

        // hitQuestion(deck, playerHand, s);
        hitOptions(s, playerHand, deck);
    }

    public static void hitOptions(Scanner s, Hand pointedHand, Deck deck) {
        boolean hitBool = false;
        while (!hitBool) {
            System.out.println("Hit?\n( 1 ) Hit\n( 2 ) Stay");
            System.out.print("Selection: ");
            int hitResponse = s.nextInt();
            switch (hitResponse) {
                case 1:
                    pointedHand.addToHand(deck.dealOne());
                    pointedHand.displayHand();
                    if (pointedHand.checkHandValue()) {
                        hitBool = false;
                    } else {
                        hitBool = true;
                        bust();
                    }
                    break;
                case 2:
                    System.out.println("Proceed to dealer...");
                    hitBool = true;
                    break;
                default:
                    System.out.println("Invalid Argument: " + hitResponse + "\n");
                    hitBool = false;
                    break;
            }
        }
    }

    public static void bust() {
        System.out.println("Bust...GG");
        System.out.println("Proceed with dealer");
    }
}