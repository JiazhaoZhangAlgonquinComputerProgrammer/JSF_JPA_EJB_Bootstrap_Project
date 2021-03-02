/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author Jiazhao Zhang
 * Student id : 040928137
 *
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "phoneValidator")
public class PhoneValidator implements Validator<String>{

    // North American phoneNumber pattern
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+\\d( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        
        if (value == null || value.trim().equals("")) {
            FacesMessage msg = new FacesMessage("Phone Number should not be empty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        // TODO - use Matcher and Pattern to figure out if the value is a valid phoneNumber
        if (!PHONE_PATTERN.matcher(value).matches()) {
            FacesMessage msg = new FacesMessage("Invalid Phone Number format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}