package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductTest extends BaseTest {

    @Test
    public void create() {
        //Given
        Product product = new Product();
        product.setProductName("Apple iPhone");
        product.setDescription("iPhone 12 512GB");

        ProductPrice productPrice1 = new ProductPrice();
        productPrice1.setProduct(product);
        productPrice1.setPriceValue(BigDecimal.valueOf(5990.99));
        productPrice1.setCurrency(Currency.BYN);

        ProductPrice productPrice2 = new ProductPrice();
        productPrice2.setProduct(product);
        productPrice2.setPriceValue(BigDecimal.valueOf(2000.22));
        productPrice2.setCurrency(Currency.EUR);

        List<ProductPrice> prices = new ArrayList<>(2);
        prices.add(productPrice1);
        prices.add(productPrice2);
        product.setProductPrices(prices);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            //do some work
            id = session.save(product);
            session.save(productPrice1);
            session.save(productPrice2);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //Then
        assertNotNull(id);
    }

    @Test
    public void read(){
    //given
            cleanInsert("ProductTest.xml");
            //when
            Session session=factory.openSession();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        assertNotNull(products);
        assertEquals(3, products.size());
        List<ProductPrice> prices=products.stream().filter(product -> "2c931081773acfd101773acfd4180002"
                .equals(product.getProductId()))
                .map(product -> product.getProductPrices())
                .findFirst()
                .orElse(null);
        assertNotNull(prices);
        assertEquals(1, prices.size());
        deleteDataset();
        session.close();
    }
    @Test
    public void delete(){
        //given
        cleanInsert("ProductTest.xml");
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        List<ProductPrice>prices=session.createQuery("from ProductPrice ", ProductPrice.class).list();


        //when
        prices.forEach(session::delete);
        products.forEach(product -> session.delete(product));
        transaction.commit();
        //then
        assertEquals(0,session.createQuery("from Product").list().size());
        session.close();


    }

    @Test
    public void queryProductPrice (){
        //given
        cleanInsert("ProductTest.xml");
        //when
        //old Criteria API
        List priceValue = factory.openSession()
                .createCriteria(ProductPrice.class)
                .add(Restrictions.eq("priceValue", BigDecimal.valueOf(5990.99)))
                .list();

        //new Criteria API in JPA
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProductPrice> query = cb.createQuery(ProductPrice.class);
        Root<ProductPrice> root = query.from(ProductPrice.class);
        query.where(cb.equal(root.get("priceValue"), BigDecimal.valueOf(5990.99)));
        List<ProductPrice> list = session.createQuery(query).list();

        //then
        assertEquals(1,priceValue.size());
        assertEquals(1,list.size());

    }


}