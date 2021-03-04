package it.academy.model;

import lombok.Data;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_VISITOR_COUNT")
@Data
@OptimisticLocking
public class VisitorCount {

    @Id
    private Integer id;

    @Column
    private Integer count;
}
