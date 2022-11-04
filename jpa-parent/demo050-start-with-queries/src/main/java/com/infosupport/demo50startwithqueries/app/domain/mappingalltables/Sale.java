package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@IdClass(SalePK.class)
public class Sale {
    private Long orderNumber;
    private Long storeId;
    private Long titleId;
    private Date orderDate;
    private String paymentTerms;
    private BigDecimal quantity;
    private Store store;
    private Title title;

    @Id
    @Column(name = "ord_num", nullable = false)
    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Id
    @Column(name = "stor_id", nullable = false)
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Id
    @Column(name = "title_id", nullable = false)
    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    @Basic
    @Column(name = "ord_date", nullable = true)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "payterms", nullable = true, length = 255)
    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    @Basic
    @Column(name = "qty", nullable = true, precision = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (orderNumber != null ? !orderNumber.equals(sale.orderNumber) : sale.orderNumber != null) return false;
        if (storeId != null ? !storeId.equals(sale.storeId) : sale.storeId != null) return false;
        if (titleId != null ? !titleId.equals(sale.titleId) : sale.titleId != null) return false;
        if (orderDate != null ? !orderDate.equals(sale.orderDate) : sale.orderDate != null) return false;
        if (paymentTerms != null ? !paymentTerms.equals(sale.paymentTerms) : sale.paymentTerms != null) return false;
        if (quantity != null ? !quantity.equals(sale.quantity) : sale.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (paymentTerms != null ? paymentTerms.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "stor_id", referencedColumnName = "stor_id", nullable = false)
    @MapsId("stor_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false)
    @MapsId("title_id")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
