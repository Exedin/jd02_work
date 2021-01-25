package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@Data
@Entity
public class ProductPrice {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String priceId;

    private BigDecimal priceValue;

    private Currency currency;
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;
}

enum Currency{
    BYN, EUR, USD
}
