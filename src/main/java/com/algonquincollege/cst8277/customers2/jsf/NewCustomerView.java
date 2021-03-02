/**
 * @author Jiazhao Zhang
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

@Named("newCustomer")
@ViewScoped
public class NewCustomerView implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

    @Inject
    @ManagedProperty("#{customerController}")
    protected CustomerController employeeController;

    public NewCustomerView() {
    }

    /**
     * @return  firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName  firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return  lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param LastName  LastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addCustomer() {
        System.out.println("addCustomer()"+ this.firstName+" "+this.lastName+" "+this.email+" "+this.phoneNumber);
        if (allNotNullOrEmpty(firstName, lastName, phoneNumber,email)) {
            CustomerPojo theNewCustomer = new CustomerPojo();
            theNewCustomer.setFirstName(getFirstName());
            theNewCustomer.setLastName(getLastName());
            // TODO - additional fields
            theNewCustomer.setPhoneNumber(getPhoneNumber());
            theNewCustomer.setEmail(getEmail());

            // this Managed Bean does not know how to 'do' anything, ask controller
            employeeController.addNewCustomer(theNewCustomer);
            System.out.println(theNewCustomer.toString());
            //clean up
            employeeController.toggleAdding();
            setFirstName(null);
            setLastName(null);
            // TODO clean up additional fields
            this.setPhoneNumber(null);
            this.setEmail(null);
        }
    }
    
    static boolean allNotNullOrEmpty(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
            if (val instanceof String) {
                String str = (String)val;
                if (str.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}