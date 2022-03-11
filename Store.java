import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang.ArrayUtils;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Store {
    String name;
    Currency currency;
    Product[] products;
    Manufacturer[] manufacturers;
    Currency[] currencies;
    Discount[] discounts;

    public Store() {
        Currency cur = new Currency("EUR", "€", 1.0);
        this.currency = cur;
        this.currencies = (Currency[]) ArrayUtils.add(currencies, cur);
    }

    Product[] readCSV(String filename) throws IOException, CsvValidationException, Exception {

        FileReader filereader = new FileReader(filename);
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;
        if ((nextRecord = csvReader.readNext()) == null) {
            return products;
        }
        String uniqId = nextRecord[0];
        String name_product = nextRecord[1];
        Manufacturer manufacturer = new Manufacturer(nextRecord[2]);
        this.addManufacturer(manufacturer);
        double price = Double.parseDouble(nextRecord[3].substring(1));
        String[] nr_available = nextRecord[4].split("\\u00a0");
        int nr_products = Integer.parseInt(nr_available[0]);
        Product.productBuilder productBuilder = new Product.productBuilder();
        productBuilder.setUniqueId(uniqId);
        productBuilder.setName(name_product);
        productBuilder.setManufacturer(manufacturer);
        productBuilder.setPrice(price);
        productBuilder.setQuantity(nr_products);

        Product product = new Product(productBuilder);
        this.addProduct(product);
        if (null != (nextRecord = csvReader.readNext())) {
            uniqId = nextRecord[0];
            name_product = nextRecord[1];
            manufacturer = new Manufacturer(nextRecord[2]);
            this.addManufacturer(manufacturer);
            price = Double.parseDouble(nextRecord[3].substring(1));
            nr_available = nextRecord[4].split("\\u00a0");
            nr_products = Integer.parseInt(nr_available[0]);
            productBuilder = new Product.productBuilder();
            productBuilder.setUniqueId(uniqId);
            productBuilder.setName(name_product);
            productBuilder.setManufacturer(manufacturer);
            productBuilder.setPrice(price);
            productBuilder.setQuantity(nr_products);

            product = new Product(productBuilder);
            this.addProduct(product);
            if (null != (nextRecord = csvReader.readNext())) {
                do {
                    uniqId = nextRecord[0];
                    name_product = nextRecord[1];
                    manufacturer = new Manufacturer(nextRecord[2]);
                    this.addManufacturer(manufacturer);
                    price = Double.parseDouble(nextRecord[3].substring(1));
                    nr_available = nextRecord[4].split("\\u00a0");
                    nr_products = Integer.parseInt(nr_available[0]);
                    productBuilder = new Product.productBuilder();
                    productBuilder.setUniqueId(uniqId);
                    productBuilder.setName(name_product);
                    productBuilder.setManufacturer(manufacturer);
                    productBuilder.setPrice(price);
                    productBuilder.setQuantity(nr_products);

                    product = new Product(productBuilder);
                    this.addProduct(product);
                } while (null != (nextRecord = csvReader.readNext()));
            }
        }
        return products;
    }

    void addCurrency(Currency currency) throws DuplicateCurrencyException {
        int err = 0;
        int i = 0, listCurrencyLength = currencies.length;
        while (i < listCurrencyLength) {
            Currency c = currencies[i];
            if (c.getName().equals(currency.getName()))
                err = 1;
            i++;
        }
        if(err == 1)
            throw new DuplicateCurrencyException("Error found");
        currencies = (Currency[]) ArrayUtils.add(currencies, currency);
    }

    void addProduct(Product product) throws DuplicateProductException {
        int err = 0;
        for (int i = 0, productsLength = products.length; i < productsLength; i++) {
            Product p = products[i];
            if (p.getName().equals(product.getName()))
                err = 1;
        }
        if(err == 0)
            throw new DuplicateProductException("Error found");
        ArrayUtils.add(products, product);
    }

    void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        int err = 0;
        for (int i = 0, manufacturersLength = manufacturers.length; i < manufacturersLength; i++) {
            Manufacturer m = manufacturers[i];
            if (m.getName().equals(manufacturer.getName()))
                err = 1;
        }
        if(err == 0)
            throw new DuplicateManufacturerException("Error found");
        ArrayUtils.add(manufacturers, manufacturer);
    }

    Currency createCurrency(String name, String symbol, double parityToEur) {
        return new Currency(name, symbol, parityToEur);
    }

    void changeCurrency(Currency currency) throws CurrencyNotFoundException {
        int err = 0;
        for (int i = 0, currenciesLength = currencies.length; i < currenciesLength; i++) {
            Currency c = currencies[i];
            if (c.getName().equals(currency.getName()))
                err = 1;
        }
        if(err == 0)
            throw new CurrencyNotFoundException("Error found");
        this.currency = currency;
        for (int i = 0, productsLength = products.length; i < productsLength; i++) {
            Product product = products[i];
            product.price = (product.price * this.currency.parityToEur) / currency.parityToEur;
        }
    }

    Discount createDiscount(Discount.DiscountType discountType, String name, double value) {
        return new Discount(name, discountType, value);
    }

    void applyDiscount(Discount discount) throws DiscountNotFoundException, NegativePriceException {
        int err = 0;
        for (int i = 0, discountsLength = discounts.length; i < discountsLength; i++) {
            Discount c = discounts[i];
            if (c.getName().equals(discount.getName()))
                err = 1;
        }
        if(err == 0)
            throw new DiscountNotFoundException("Error found");
        err = 1;
        for (int i = 0, productsLength = products.length; i < productsLength; i++) {
            Product product = products[i];
            product.price = discount.discountType != Discount.DiscountType.FIXED_DISCOUNT ? product.price - product.price * discount.value / 100 : product.price - discount.value;
            if (product.price < 0)
                err = 0;
        }
        if(err == 0 )
            throw new NegativePriceException("Error found");
    }

    Product[] getProductsByManufacturer(Manufacturer manufacturer) {
        Product[] productList = new Product[0];
        for (int i = 0, productsLength = products.length; i < productsLength; i++) {
            Product product = products[i];
            if (product.manufacturer.getName().equals(manufacturer.getName()))
                ArrayUtils.add(productList, product);
        }
        return productList;
    }

    double calculateTotal(Product[] products) {
        return Arrays.stream(products).mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
    }

    public class DuplicateCurrencyException extends Exception {
        public DuplicateCurrencyException(String eroare) {
        }
    }

    private class DuplicateProductException extends Exception {
        public DuplicateProductException(String eroare) {
        }
    }

    private class DuplicateManufacturerException extends Exception {
        public DuplicateManufacturerException(String eroare) {
        }
    }

    public class CurrencyNotFoundException extends Exception {
        public CurrencyNotFoundException(String eroare) {
        }
    }

    public class DiscountNotFoundException extends Exception {
        public DiscountNotFoundException(String eroare) {
        }
    }

    public class NegativePriceException extends Exception {
        public NegativePriceException(String eroare) {
        }
    }
}