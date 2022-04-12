package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.OtherProduct;

import java.math.BigDecimal;

public class FuelCanister extends OtherProduct {

    public static boolean isFuel;

    public FuelCanister(String name, BigDecimal price, Boolean isFuel) {
        super(name, price);
        FuelCanister.isFuel = isFuel;

    }
}
