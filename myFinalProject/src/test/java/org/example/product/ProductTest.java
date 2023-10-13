package org.example.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
class ProductTest {
    @Test
    void createdProperly() {
        Product product = new Product("brand", "model") {
            //dummy extension of product
        };


        LocalDate now = LocalDate.now();
        LocalDate expectdExpirationDate = now.plus(2, ChronoUnit.YEARS);

        assertThat(product.getDateOfMade()).isEqualTo(now);
        assertThat(product.getExpirationDate()).isEqualTo(expectdExpirationDate);
        assertThat(product.isWorking()).isTrue();
    }
}