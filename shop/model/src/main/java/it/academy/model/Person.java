package it.academy.model;


import org.hibernate.annotations.GenericGenerator;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String personId;

    private String name;

    private String secondName;

    private Date dateOfBirth;

    @ElementCollection
    @OrderColumn(name="C_ORDER")
    private String[] comment;


    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "SHOP_USER_ID")
    private ShopUser shopUser;

}

enum Status{
    NEW, UPDATED, DELETED
}
