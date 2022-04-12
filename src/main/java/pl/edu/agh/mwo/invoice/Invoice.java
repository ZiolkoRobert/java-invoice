package pl.edu.agh.mwo.invoice;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private final Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        Integer originalQuantityOfProduct;
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            originalQuantityOfProduct = products.get(product);
            products.put(product, quantity + originalQuantityOfProduct);
        } else {
            products.put(product, quantity);
        }
    }

    public int getTotalProductQuantity(Product product) {
        return products.get(product);
    }


    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public void printProducts() {
        for (Product p : products.keySet()) {
            System.out.println(p.getName() + " ; " + p.getPrice() + " ; " + products.get(p));
        }
    }

    public int numberOfLines() {
        return products.size();
    }

    public BigDecimal getGrossTotalWithExciseTax() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTaxWithExciseTax().multiply(quantity));
        }
        return totalGross;
    }
}