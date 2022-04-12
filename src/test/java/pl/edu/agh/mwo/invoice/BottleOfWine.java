package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.OtherProduct;

import java.math.BigDecimal;

public class BottleOfWine extends OtherProduct {

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price);
    }
}