package it.academy.dao;

import it.academy.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    private static List<Product> products=List.of(
//            Product.builder()
//                    .productId("1")
//                    .productName("Apple iPhone 12")
//                    .description("256 GB white")
//                    .build(),
//            Product.builder()
//                    .productId("2")
//                    .productName("Nokia 3310")
//                    .description("2G gray")
//                    .build(),
//            Product.builder()
//                    .productId("3")
//                    .productName("Samsung Galaxy 10")
//                    .description("512 GB black")
//                    .build()
//        );
    @Override
    public List<Product> findAllProducts() {
        Session session = sessionFactory.openSession();
        List<Product>productList = session.createQuery("from Product ", Product.class).list();

        return productList;
    }

    @Override
    public Product read(String id) {
         return sessionFactory.openSession().get(Product.class, id);
//        return products.stream().filter(product -> product.getProductId().equals(id))
//                .findFirst()
//                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String id = (String) session.save(product);
        transaction.commit();
        return id;

//
//        int maxId=products.stream()
//                .max((o1, o2) -> Integer.valueOf(o1.getProductId())-Integer.valueOf(o2.getProductId()))
//                .map(product1 -> Integer.valueOf(product1.getProductId()))
//                .get();
//        String productId = String.valueOf(++maxId);
//        product.setProductId(productId);
//        products= new ArrayList<>(products);
//        products.add(product);
//        return productId;

    }
}
