package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SalePK implements Serializable {
    private Long orderNumber;
    private Long storeId;
    private Long titleId;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalePK salePK = (SalePK) o;

        if (orderNumber != null ? !orderNumber.equals(salePK.orderNumber) : salePK.orderNumber != null) return false;
        if (storeId != null ? !storeId.equals(salePK.storeId) : salePK.storeId != null) return false;
        if (titleId != null ? !titleId.equals(salePK.titleId) : salePK.titleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
        return result;
    }
}
