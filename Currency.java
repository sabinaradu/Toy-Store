public class Currency {
    String name;
    String symbol;
    double parityToEur;

    public Currency() { }

    void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }

    public String getName() {
        return name;
    }

    public double getParityToEur() {
        return parityToEur;
    }

    public Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }
}
class DuplicateCurrencyException extends Exception {
    public DuplicateCurrencyException(String eroare) {
    }
}

class CurrencyNotFoundException extends Exception{
    public CurrencyNotFoundException(String eroare) {
    }
}