/*****************************************************************c******************o*******v******id********
 * File: CustomerPojoListener.java
 * Course materials (20F) CST 8277
 *
 * @author Jiazhao Zhang
 * Student id : 040928137
 *
 */
package com.algonquincollege.cst8277.customers2.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CustomerPojoListener {

    @PrePersist
    public void setCreatedOnDate(CustomerPojo cust) {
        LocalDateTime now = LocalDateTime.now();
        cust.setCreatedDate(now);
        //might as well call setUpdatedDate as well
        cust.setUpdatedDate(now);
    }

    @PreUpdate
    public void setUpdatedDate(CustomerPojo cust) {
        cust.setUpdatedDate(LocalDateTime.now());
    }

}