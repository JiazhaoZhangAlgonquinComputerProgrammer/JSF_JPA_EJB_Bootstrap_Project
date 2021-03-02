/**
 *  @author Jiazhao Zhang
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.customers2.dao.CustomerDao;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: Responsible for collection of Customer Pojo's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */
@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UICONSTS_BUNDLE_EXPR = "#{uiconsts}";
    public static final String CUSTOMER_MISSING_REFRESH_BUNDLE_MSG = "refresh";
    public static final String CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG = "outOfDate";

    @Inject
    protected FacesContext facesContext;

    @Inject
    protected ServletContext sc;

    @Inject
    protected CustomerDao customerDao;

    @Inject
    @ManagedProperty(UICONSTS_BUNDLE_EXPR)
    protected ResourceBundle uiconsts;
    
    protected List<CustomerPojo> customers;
    protected boolean adding;

    public void loadCustomers() {
        logMsg("loadCustomers");
        customers = customerDao.readAllCustomers();
    }

    public List<CustomerPojo> getCustomers() {
        return this.customers;
    }
    public void setCustomers(List<CustomerPojo> customers) {
        this.customers = customers;
    }

    public boolean isAdding() {
        return adding;
    }
    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    /**
     * Toggles the add customer mode which determines whether the addCustomer form is rendered
     */
    public void toggleAdding() {
        System.out.println("toggleAdding() called");
        this.adding = !this.adding;
    }

    /**
     * set customer to be editable
     * @param cust
     * @return
     */
    public String editCustomer(CustomerPojo cust) {
        logMsg("editCustomer");
        cust.setEditable(true);
        return null; //current page
    }
    
    /**
     * Update a customer
     * @param customerWithEdits
     * @return
     */
    public String updateCustomer(CustomerPojo customerWithEdits) {
        logMsg("updateCustomer");
        CustomerPojo customerToBeUpdated = customerDao.readCustomerById(customerWithEdits.getId());
        if (customerToBeUpdated == null) {
            // someone else deleted it
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                uiconsts.getString(CUSTOMER_MISSING_REFRESH_BUNDLE_MSG), null));
        }
        else {
            customerToBeUpdated = customerDao.updateCustomer(customerWithEdits);
            if (customerToBeUpdated == null) {
                //OptimisticLockException 
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    uiconsts.getString(CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG), null));
            }
            else {
                customerToBeUpdated.setEditable(false);
                int idx = customers.indexOf(customerWithEdits);
                customers.remove(idx);
                customers.add(idx, customerToBeUpdated);
            }
        }
        return null; //current page
    }
    
    /**
     * set a customer not editable
     * @param cust
     * @return
     */
    public String cancelUpdate(CustomerPojo cust) {
        logMsg("cancelUpdate");
        cust.setEditable(false);
        return null; //current page
    }
    
    /**
     * delete a customer
     * @param custId
     */
    public void deleteCustomer(int custId) {
        logMsg("deleteCustomer: " + custId);
        CustomerPojo customerToBeRemoved = customerDao.readCustomerById(custId);
        if (customerToBeRemoved != null) {
            customerDao.deleteCustomerById(custId);
            customers.remove(customerToBeRemoved);
        }
    }
    
    /**
     * add a new customer
     * @param theNewCustomer
     */
    public void addNewCustomer(CustomerPojo theNewCustomer) {
        logMsg("adding new Customer");
        CustomerPojo newCust = customerDao.createCustomer(theNewCustomer);
        customers.add(newCust);
    }
    
    /**
     * refresh customer form
     * @return
     */
    public String refreshCustomerForm() {
        logMsg("refreshCustomerForm");
        Iterator<FacesMessage> facesMessageIterator = facesContext.getMessages();
        while (facesMessageIterator.hasNext()) {
            facesMessageIterator.remove();
        }
        return "index.xhtml?faces-redirect=true";
    }

    protected void logMsg(String msg) {
        sc.log(msg);
    }
}