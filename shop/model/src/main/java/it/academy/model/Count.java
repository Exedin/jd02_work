package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "T_VISITOR_COUNT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Count {

    @Id
    @GeneratedValue
    @Column (columnDefinition = "int default 1")
    private Integer id;

    @Column (columnDefinition = "int default 1")
    private int count;

}


