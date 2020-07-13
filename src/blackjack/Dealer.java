package blackjack;

import java.util.*;

public class Dealer {

    public static void main(String[] args) {
        boolean again = true;
        Scanner s = new Scanner(System.in);
        Bet bet = new Bet(0);

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to the table!\n");

        System.out.println("Starting amount of money: (Minimum $15)");
        System.out.print("$");

        int betAmount = s.nextInt();
        boolean checker = bet.startingBet(betAmount);

        while (!checker) {
            System.out.println("\nStarting amount of money: (Minimum $15)");
            System.out.print("$");
            betAmount = s.nextInt();
            checker = bet.startingBet(betAmount);
        }

        do {
            Hand playerHand = new Hand();
            DealerHand dealerHand = new DealerHand();

            boolean game = newGame(playerHand, dealerHand, s, bet);

            if (game) {
                again = true;
            } else {
                again = false;
            }
        } while (again && bet.getBetAmount() > 5);
        if (bet.getBetAmount() < 5) {
            System.out.println("\nYou've been asked to leave the table.");
            System.out.println("You walked away with $" + bet.getBetAmount());
            System.out.println("Sad day :(");
        }
        System.out.println("\nThank you for playing!");
        s.close();

    }

    public static boolean newGame(Hand playerHand, DealerHand dealerHand, Scanner s, Bet bet) {

        System.out.println("Your Money: $" + bet.getBetAmount());

        System.out.println("\nHow much would you like to bet?");
        System.out.println("(Minimum $5)");
        System.out.print("$");
        boolean checker = false;
        int newBet = s.nextInt();

        // error handling
        while (!checker) {
            if (newBet < 5) {
                System.out.println("Invalid Response: Must be greater than 5.");
                System.out.println("\nHow much would you like to bet?");
                System.out.println("(Minimum $5)");
                System.out.print("$");
                newBet = s.nextInt();
            } else if (newBet > bet.getBetAmount()) {
                System.out.println("Invalid Response: You dont have enough money... ");
                System.out.println("Your current amount: $" + bet.getBetAmount());
                System.out.println("\nHow much would you like to bet?");
                System.out.println("(Minimum $5)");
                System.out.print("$");
                newBet = s.nextInt();
            } else {
                checker = true;
            }
        }
        System.out.println("\nNew Game Started \n");

        System.out.println("How many decks would you like to play with?");
        System.out.print("Number of decks: ");

        int numOfDecksResponse = s.nextInt();

        // error handling
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

        boolean win = finalResults(playerTotal, dealerTotal);
        bet.result(win, newBet);

        return playAgain(s, bet);
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
    public static boolean finalResults(int player, int dealer) {
        if ((player > dealer && player <= 21 && dealer <= 21) || (player > dealer && player <= 21 && dealer > 21)
                || (player < dealer && player <= 21 && dealer > 21)) {
            System.out.println("YOU WIN!");
            return true;
        } else {
            System.out.println("YOU LOSE!");
            return false;
        }
    }

    public static boolean playAgain(Scanner s, Bet bet) {
        System.out.println("\nPlay Again?");
        System.out.println("( 1 ) Yes");
        System.out.println("( 2 ) No");
        System.out.print("Selection: ");
        int response = s.nextInt();
        switch (response) {
            case 1:
                return true;
            case 2:
                System.out.println("\nYou walked away with $" + bet.getBetAmount() + "!");
                return false;
            default:
                System.out.println("Invalid argument... Exiting game... Thank you for playing.");
                return false;
        }
    }

    // bust function
    public static void bust() {
        System.out.println("\nBust...GG");
        System.out.println("Proceed with dealer\n");
    }
}