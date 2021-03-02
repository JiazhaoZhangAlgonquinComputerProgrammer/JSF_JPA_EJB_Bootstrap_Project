package com.algonquincollege.cst8277.customers2.EJB;
/**
 * 
 * @author Jiazhao Zhang
 *
 */
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

@Stateless
public class CustomerService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * persistence unit name
     */
    public static final String CUSTOMER_PU = "acmeCustomers-PU";

    @PersistenceContext(name = CUSTOMER_PU)
    protected EntityManager em;
    
    /**
     * get all customers
     * @return all customers
     */
    public List<CustomerPojo> getAllCustomers() {
        TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(CustomerPojo.ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
        return allCustomersQuery.getResultList();
    }
    
    /**
     * add new customer to database
     * @param customer
     * @return
     */
    @Transactional
    public CustomerPojo insertNewCustomer(CustomerPojo customer) {
        em.persist(customer);
        return customer;
    }
    
    /**
     * get a customer by id
     * @param customerId
     * @return
     */
    public CustomerPojo getCustomerById(int customerId) {
        return em.find(CustomerPojo.class, customerId);
    }
    
    /**
     * get a customer by id
     * @param customerId
     * @return
     */
    public CustomerPojo getCustomerByIdWithNameQuery(int customerId) {
        TypedQuery<CustomerPojo> query = em.createQuery(CustomerPojo.GET_CUSTOMER_BY_ID_QUERY_NAME,CustomerPojo.class);
        query.setParameter("id", customerId);
        CustomerPojo customer = (CustomerPojo)query.getSingleResult();
        return customer;
    }
    
    /**
     * update a customer
     * @param customerWithUpdates
     * @return
     */
    @Transactional
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        return em.merge(customerWithUpdates);
    }
    
    public CustomerPojo updateCustomerWithNameQuery(CustomerPojo customerWithUpdates) {
        TypedQuery<CustomerPojo> query = em.createQuery(CustomerPojo.UPDATE__QUERY_NAME,CustomerPojo.class);
        query.setParameter("id", customerWithUpdates.getId());
        query.setParameter("firstname", customerWithUpdates.getFirstName());
        query.setParameter("lastname", customerWithUpdates.getLastName());
        query.setParameter("email", customerWithUpdates.getEmail());
        query.setParameter("phoneNumber", customerWithUpdates.getPhoneNumber());
        query.setParameter("updated", LocalDateTime.now());
        if(query.executeUpdate()>0) {
            return customerWithUpdates;
        }else {
            return null;
        }
    }
    
    /**
     * delete a customer
     * @param customerId
     */
    @Transactional
    public void deleteCustomer(int customerId) {
        CustomerPojo customer = getCustomerById(customerId);
        if (customer != null) {
            em.refresh(customer);
            em.remove(customer);
        }
    }
    
}
