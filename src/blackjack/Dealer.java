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

        // initial dealing of cards
        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOne());
        playerHand.addToHand(deck.dealOne());
        dealerHand.addToHand(deck.dealOne());

        dealerHand.displayHandHidden();
        playerHand.displayHand();

        System.out.println("Your hand total is: " + playerHand.totalValue());

        int playerTotal = hitOptions(s, playerHand, deck);
        int dealerTotal = dealerHit(dealerHand, deck);
        System.out.println("Results: \n");
        System.out.println("Player Total: " + playerTotal);
        System.out.println("Dealer Total: " + dealerTotal);
        finalResults(playerTotal, dealerTotal);
    }

    // console syntax input
    public static int hitOptions(Scanner s, Hand pointedHand, Deck deck) {
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
                    System.out.println("\nDealer's Turn\n");
                    hitBool = true;
                    break;
                default:
                    System.out.println("Invalid Argument: " + hitResponse + "\n");
                    hitBool = false;
                    break;
            }
        }
        return pointedHand.totalValue();
    }

    // Dealer's hit function
    public static int dealerHit(DealerHand dealerHand, Deck deck) {
        boolean isValid = true;
        dealerHand.displayHand();
        do {
            dealerHand.addToHand(deck.dealOne());
            dealerHand.displayHand();
            if (dealerHand.totalValue() <= 17) {
                isValid = true;
            } else if (dealerHand.totalValue() == 21) {
                System.out.println("Blackjack");
                isValid = false;
            } else {
                System.out.println("BUST\n");
                isValid = false;
            }
        } while (isValid == true);
        return dealerHand.totalValue();
    }

    // outputs final result, if the player on or if the dealer has a greater value
    // if ties occur, dealer wins due to casino realism
    public static void finalResults(int player, int dealer) {
        if ((player > dealer && player <= 21 && dealer <= 21) || (player > dealer && player <= 21 && dealer > 21)
                || (player < dealer && player <= 21 && dealer > 21)) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("YOU LOSE!");
        }
    }

    // bust function
    public static void bust() {
        System.out.println("Bust...GG");
        System.out.println("Proceed with dealer");
    }
}