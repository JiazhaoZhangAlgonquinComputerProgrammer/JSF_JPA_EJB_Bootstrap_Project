/**
 * @author Jiazhao Zhang
 */
package com.algonquincollege.cst8277.customers2.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.EJB.CustomerService;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
* Description: Implements the C-R-U-D API for the database
*/
@Named
@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public static final String CUSTOMER_PU = "acmeCustomers-PU";

    @PersistenceContext
    protected EntityManager em;
    
    protected ServletContext sc;
    
    @EJB
    protected CustomerService customerService;

    @Inject
    public CustomerDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    protected void logMsg(String msg) {
        sc.log(msg);
    }
    

    // delegate all C-R-U-D operations to EntityManager

    @Override
    @Transactional
    public List<CustomerPojo> readAllCustomers() {
//        logMsg("reading all customers");
//        TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(CustomerPojo.ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
//        return allCustomersQuery.getResultList();
          return customerService.getAllCustomers();
    }

    @Override
    @Transactional
    public CustomerPojo createCustomer(CustomerPojo customer) {
        logMsg("creating an customer");
//        em.something(customer);
//        em.persist(customer);
//        return customer;
        return customerService.insertNewCustomer(customer);
    }

    @Override
    public CustomerPojo readCustomerById(int customerId) {
        logMsg("read a specific customer");
 
//        return em.find(CustomerPojo.class, customerId);
        return customerService.getCustomerById(customerId);

    }

    @Override
    @Transactional
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        logMsg("updating a specific customer");
//        TypedQuery<CustomerPojo> query = em.createNamedQuery(CustomerPojo.UPDATE_CUSTOMER_QUERY_NAME, CustomerPojo.class);
////        CustomerPojo mergedCustomerPojo = em.something(customerWithUpdates);
//        query.setParameter(1, customerWithUpdates.getFirstName());
//        query.setParameter(2, customerWithUpdates.getLastName());
//        query.setParameter(3, customerWithUpdates.getEmail());
//        query.setParameter(4, customerWithUpdates.getPhoneNumber());
//        query.setParameter(5, customerWithUpdates.getId());
//        int returnValue = query.executeUpdate();
//        if(returnValue>0) {
//            return customerWithUpdates;
//        }else {
//            return null;
//        }
//        return query.executeUpdate();
//            return em.merge(customerWithUpdates);
          return customerService.updateCustomer(customerWithUpdates);
//        return customerService.updateCustomerWithNameQuery(customerWithUpdates);
    }

    @Override
    @Transactional
    public void deleteCustomerById(int customerId) {
        logMsg("deleting a specific customer");
//        CustomerPojo customer = readCustomerById(customerId);
//        if (customer != null) {
//            em.refresh(customer);
//            em.remove(customer);
//        }
        customerService.deleteCustomer(customerId);
    }

}