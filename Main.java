import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String string = scan.next();
        Store store = new Store();
        Product prod = new Product();
        Discount disc = new Discount();
        if (string != null) {
            do {
                if ("listcurrencies".equals(string)) {
                    Arrays.stream(store.currencies).map(c -> c.getName() + " " + c.getParityToEur()).forEach(System.out::println);
                } else if ("getstorecurrency".equals(string)) {
                    System.out.println(store.currency.getName());
                } else if ("addcurrency".equals(string)) {
                    String name_type = scan.next();
                    String symbol = scan.next();
                    String numberA = scan.next();
                    Currency add_currency = new Currency(name_type, symbol, Double.parseDouble(numberA));
                    store.addCurrency(add_currency);

                } else if ("loadcsv".equals(string)) {
                    String filenameL = scan.next();
                    store.products = store.readCSV(filenameL);
                } else if ("setstorecurrency".equals(string)) {
                    String name_typeS = scan.next();
                    Currency[] listCurrency = store.currencies;
                    for (int i = 0; i < listCurrency.length; i++) {
                        Currency c = listCurrency[i];
                        if (c.getName().equals(name_typeS))
                            store.changeCurrency(c);
                    }
                } else if ("updateparity".equals(string)) {
                    String name_typeU = scan.next();
                    String numberU = scan.next();
                    Arrays.stream(store.currencies).filter(c -> c.getName().equals(name_typeU)).forEach(c -> c.updateParity(Double.parseDouble(numberU)));
                } else if ("listproducts".equals(string)) {
                    Arrays.stream(store.products).map(p -> p.getUniqueld() + "," + p.getName() + "," + p.getManufacturer() + "," + p.getPrice() + "," + p.getQuantity()).forEach(System.out::println);
                } else if ("showproduct".equals(string)) {
                    String uniq_id = scan.next();
                    Product[] products = store.products;
                    for (int i = 0, productsLength = products.length; i < productsLength; i++) {
                        Product p = products[i];
                        if (!p.getUniqueld().equals(uniq_id)) {
                            continue;
                        }
                        System.out.println(p.getUniqueld() + "," + p.getName() + "," + p.getManufacturer() + "," + p.getPrice() + "," + p.getQuantity());
                        break;
                    }
                } else if ("listmanufacturers".equals(string)) {
                    Arrays.stream(store.manufacturers).map(Manufacturer::getName).forEach(System.out::println);
                } else if ("listproductsbymanufacturarer".equals(string)) {
                    String manufacturer = scan.next();
                    Arrays.stream(store.products).filter(p -> p.getManufacturer().getName().equals(manufacturer)).map(p -> p.getUniqueld() + "," + p.getName() + "," + p.getManufacturer() + "," + p.getPrice() + "," + p.getQuantity()).forEach(System.out::println);
                } else if ("listdiscounts".equals(string)) {
                    Arrays.stream(store.discounts).map(d -> d.getName() + "," + d.getDiscountType() + "," + d.getValue()).forEach(System.out::println);
                } else if ("addiscount".equals(string)) {
                    String discountType_Add = scan.next();
                    String value_add = scan.next();
                    String name_add = scan.nextLine();
                    name_add = name_add.substring(1);
                    disc.name = name_add;
                    disc.discountType = Discount.DiscountType.valueOf(discountType_Add);
                    disc.value = Double.parseDouble(value_add);
                    Arrays.stream(store.discounts).forEach(d -> ArrayUtils.add(store.discounts, disc));
                } else if ("applydiscount".equals(string)) {
                    String discountType_apply = scan.next();
                    String value_apply = scan.next();
                    disc.discountType = Discount.DiscountType.valueOf(discountType_apply);
                    disc.value = Double.parseDouble(value_apply);
                    Product[] products = store.products;
                    for (int i = 0, productsLength = products.length; i < productsLength; i++) {
                        Product p = products[i];
                        store.applyDiscount(disc);
                    }
                } else if ("calculatetotal".equals(string)) {
                    String uniqueId = scan.nextLine();
                    String[] uniqueId_list = uniqueId.split(" ");
                    uniqueId_list = (String[]) ArrayUtils.remove(uniqueId_list, 0);
                    Arrays.stream(uniqueId_list).forEach(i -> {
                        prod.uniqueld = i;
                        store.products = (Product[]) ArrayUtils.add(store.products, prod);
                    });
                    store.calculateTotal(store.products);
                } else if ("exit".equals(string)) {
                    System.exit(0);
                } else if ("quit".equals(string)) {
                    System.exit(0);
                } else {
                    System.out.println("Command not found. Please try again");
                }
                string = scan.next();
            } while (string != null);
        }
    }
}