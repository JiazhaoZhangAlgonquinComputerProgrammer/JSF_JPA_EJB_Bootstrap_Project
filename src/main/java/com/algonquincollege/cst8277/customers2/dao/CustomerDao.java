/*****************************************************************c******************o*******v******id********
 * File: CustomerDao.java
 * Course materials (20F) CST 8277
 *
 * @author Jiazhao Zhang
 * Student id : 040928137
 *
 */
package com.algonquincollege.cst8277.customers2.dao;

import java.util.List;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: API for the database C-R-U-D operations
 */
public interface CustomerDao {

    // C
    public CustomerPojo createCustomer(CustomerPojo customer);
    // R
    public CustomerPojo readCustomerById(int customerId);
    public List<CustomerPojo> readAllCustomers();
    // U
    public CustomerPojo updateCustomer(CustomerPojo customer);
    // D
    public void deleteCustomerById(int customerId);

}