package blackjack;

public class Card {
    String name;
    int value;

    public Card(String name) {
        this.name = name;
        this.value = setValue(name);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int setValue(String name) {
        if (name.contains("Ace")) {
            return 11;
        }
        if (name.contains("King") || name.contains("Queen") || name.contains("Jack")) {
            return 10;
        }

        for (int i = 2; i <= 10; i++) {
            if (name.contains(String.valueOf(i))) {
                return i;
            }
        }
        return 0;
    }
}