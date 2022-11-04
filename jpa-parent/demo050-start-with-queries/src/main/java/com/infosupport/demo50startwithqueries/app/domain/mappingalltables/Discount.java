package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Discount {
    private Long id;
    private BigDecimal discount;
    private String discountType;
    private BigDecimal highQuantityy;
    private BigDecimal lowQuantity;
    private Store store;

    @Id
    @Column(name = "discount_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 2)
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "discounttype", nullable = true, length = 40)
    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Basic
    @Column(name = "highqty", nullable = true, precision = 2)
    public BigDecimal getHighQuantityy() {
        return highQuantityy;
    }

    public void setHighQuantityy(BigDecimal highQuantityy) {
        this.highQuantityy = highQuantityy;
    }

    @Basic
    @Column(name = "lowqty", nullable = true, precision = 2)
    public BigDecimal getLowQuantity() {
        return lowQuantity;
    }

    public void setLowQuantity(BigDecimal lowQuantity) {
        this.lowQuantity = lowQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount1 = (Discount) o;

        if (id != null ? !id.equals(discount1.id) : discount1.id != null) return false;
        if (discount != null ? !discount.equals(discount1.discount) : discount1.discount != null) return false;
        if (discountType != null ? !discountType.equals(discount1.discountType) : discount1.discountType != null)
            return false;
        if (highQuantityy != null ? !highQuantityy.equals(discount1.highQuantityy) : discount1.highQuantityy != null)
            return false;
        if (lowQuantity != null ? !lowQuantity.equals(discount1.lowQuantity) : discount1.lowQuantity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (discountType != null ? discountType.hashCode() : 0);
        result = 31 * result + (highQuantityy != null ? highQuantityy.hashCode() : 0);
        result = 31 * result + (lowQuantity != null ? lowQuantity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "stor_id", referencedColumnName = "stor_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
