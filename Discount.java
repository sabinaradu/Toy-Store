public class Discount {
    String name;
    DiscountType discountType;
    double value;

    public static enum DiscountType {
        PERCENTAGE_DISCOUNT, FIXED_DISCOUNT
    }

    public Discount() { }

    public String getName() {
        return name;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }
}

class DiscountNotFoundException extends Exception{
    public DiscountNotFoundException(String eroare) {
    }
}