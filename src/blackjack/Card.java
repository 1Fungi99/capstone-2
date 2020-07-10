package blackjack;

// Card class that holds the information of the current card.
// Simply holds name of card and the associated values of the card

public class Card {
    String name;
    int value;
    String shortValue;
    String shortName;

    public Card(String name, String shortValue, String shortName) {
        this.name = name;
        this.shortValue = shortValue;
        this.shortName = shortName;
        this.value = setValue(name);
        // this.shortName = setShortName(name);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getShortValue() {
        return shortValue;
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