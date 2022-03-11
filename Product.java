public class Product {
    String uniqueld;
    String name;
    Manufacturer manufacturer;
    double price;
    int quantity;
    Discount discount;

    public Product() {
    }

    public Product(productBuilder pBuilder) {
        this.uniqueld = pBuilder.uniqueId;
        this.name = pBuilder.name;
        this.manufacturer = pBuilder.manufacturer;
        this.price = pBuilder.price;
        this.quantity = pBuilder.quantity;
        this.discount = pBuilder.discount;
    }

    public String getUniqueld() {
        return uniqueld;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    static class productBuilder {
        String uniqueId;
        String name;
        Manufacturer manufacturer;
        double price;
        int quantity;
        Discount discount;

        public productBuilder() {
        }

        public productBuilder setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public productBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public productBuilder setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public productBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public productBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public productBuilder setDiscount(Discount discount) {
            this.discount = discount;
            return this;
        }
    }
}

class DuplicateProductException extends Exception {
    public DuplicateProductException(String eroare) {
    }
}

class NegativePriceException extends Exception {
    public NegativePriceException(String eroare) {
    }
}