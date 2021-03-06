package it.academy.date;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ProductSpec implements Serializable {

    private int id;
    private String productName;
    private String productDetails;
    private Date productDate;

    @Override
    public String toString() {
        return "ProductSpec{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDetails='" + productDetails + '\'' +
                ", productDate=" + productDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpec that = (ProductSpec) o;
        return id == that.id && Objects.equals(productName, that.productName) && Objects.equals(productDetails, that.productDetails) && Objects.equals(productDate, that.productDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productDetails, productDate);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public Date getProductDate() {
        return productDate;
    }

    public ProductSpec() {
    }
}
