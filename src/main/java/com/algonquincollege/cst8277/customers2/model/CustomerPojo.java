/**
 * @author Jiazhao Zhang
 */
package com.algonquincollege.cst8277.customers2.model;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
*
* Description: model for the Customer object
*/
@Entity
@Table(name = "Customer")
@Access(AccessType.PROPERTY) 
@NamedQueries(
        {
            @NamedQuery(name=ALL_CUSTOMERS_QUERY_NAME, query = "select c from CustomerPojo c"),
            @NamedQuery(name=GET_CUSTOMER_BY_ID_QUERY_NAME, query = "select c from CustomerPojo c WHERE c.id = :id"),
            @NamedQuery(name=UPDATE__QUERY_NAME, query = 
            "UPDATE CustomerPojo c SET c.firstName = :firstname, c.lastName = :lastname, c.email = :email, "
            + "c.phoneNumber = :phoneNumber, c.updatedDate = :updated WHERE c.id = :id")
        }
    )
@EntityListeners(CustomerPojoListener.class)
public class CustomerPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ALL_CUSTOMERS_QUERY_NAME =
        "allCustomers";
    public static final String GET_CUSTOMER_BY_ID_QUERY_NAME = "getCustomerByID";
    public static final String UPDATE__QUERY_NAME =
            "updateCustomer";
//    public static final String UPDATE_CUSTOMER_QUERY_NAME = "updateCustomer";

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected int version;
    protected LocalDateTime created;
    protected LocalDateTime updated;
    
    private boolean editable;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    /**
     * @param id new value for id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the value for firstName
     */
    @Column(name = "FNAME")
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the value for lastName
     */
    @Column(name = "LNAME")
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the value for email
     */
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    /**
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // these methods now used in Assignment 2
    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    @Column(name = "CREATED")
    public LocalDateTime getCreatedDate() {
        return created;
    }
    public void setCreatedDate(LocalDateTime created) {
        this.created = created;
    }
    
    @Column(name = "UPDATED")
    public LocalDateTime getUpdatedDate() {
        return updated;
    }
    public void setUpdatedDate(LocalDateTime updated) {
        this.updated = updated;
    }

    @Transient
    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerPojo)) {
            return false;
        }
        CustomerPojo other = (CustomerPojo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Customer [id=")
            .append(id)
            .append(", ");
        if (firstName != null) {
            builder
                .append("firstName=")
                .append(firstName)
                .append(", ");
        }
        if (lastName != null) {
            builder
                .append("lastName=")
                .append(lastName)
                .append(", ");
        }
        if (phoneNumber != null) {
            builder
                .append("phoneNumber=")
                .append(phoneNumber)
                .append(", ");
        }
        if (email != null) {
            builder
                .append("email=")
                .append(email)
                .append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

}