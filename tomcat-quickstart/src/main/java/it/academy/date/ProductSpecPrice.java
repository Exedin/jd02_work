package it.academy.date;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ProductSpecPrice implements Serializable {

    private long id;
    private float productSpecPrice;
    private Date priceStartDate;
    private Date priceEndDate;
    private int productSpecId;

    public ProductSpecPrice() {
    }

    @Override
    public String toString() {
        return "ProductSpecPrice{" +
                "id=" + id +
                ", productSpecPrice=" + productSpecPrice +
                ", priceStartDate=" + priceStartDate +
                ", priceEndDate=" + priceEndDate +
                ", productSpecId=" + productSpecId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecPrice that = (ProductSpecPrice) o;
        return id == that.id && Float.compare(that.productSpecPrice, productSpecPrice) == 0 && productSpecId == that.productSpecId && Objects.equals(priceStartDate, that.priceStartDate) && Objects.equals(priceEndDate, that.priceEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productSpecPrice, priceStartDate, priceEndDate, productSpecId);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductSpecPrice(float productSpecPrice) {
        this.productSpecPrice = productSpecPrice;
    }

    public void setPriceStartDate(Date priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public void setPriceEndDate(Date priceEndDate) {
        this.priceEndDate = priceEndDate;
    }

    public void setProductSpecId(int productSpecId) {
        this.productSpecId = productSpecId;
    }

    public long getId() {
        return id;
    }

    public float getProductSpecPrice() {
        return productSpecPrice;
    }

    public Date getPriceStartDate() {
        return priceStartDate;
    }

    public Date getPriceEndDate() {
        return priceEndDate;
    }

    public int getProductSpecId() {
        return productSpecId;
    }
}
