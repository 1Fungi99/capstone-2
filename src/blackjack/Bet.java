package blackjack;

import interfaces.*;

public class Bet {
    private int betAmount;
    MathOperation addBet = (int a, int b) -> a + b;
    MathOperation subtractBet = (int a, int b) -> a - b;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public boolean startingBet(int amount) {
        if (amount >= 15) {
            betAmount = addBet.operation(betAmount, amount);
            return true;
        } else {
            System.out.println("Invalid Response: Less than 15.");
            return false;
        }
    }

    public void result(boolean win, int amount) {
        if (win) {
            winBet(amount);
        } else {
            loseBet(amount);
        }
    }

    private void winBet(int amount) {
        betAmount = addBet.operation(betAmount, amount);
    }

    private void loseBet(int amount) {
        betAmount = subtractBet.operation(betAmount, amount);
    }

}
