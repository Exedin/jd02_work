package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name= "T_SHOP_USER")
public class ShopUser {
    @Id
    @Column (name = "SHOP_USER_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String shopUserId;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="USER_PASSWORD")
    private String password;

}
