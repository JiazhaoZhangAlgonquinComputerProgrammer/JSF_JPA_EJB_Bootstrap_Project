/**
 * @author Jiazhao Zhang
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements javax.faces.validator.Validator<String>{

    // really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        System.out.println("email validator");
        if (value == null || value.trim().equals("")) {
            FacesMessage msg = new FacesMessage("Email should not be empty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
		// TODO - use Matcher and Pattern to figure out if the value is a valid email
        if(!EMAIL_PATTERN.matcher(value).matches()) {
            FacesMessage msg = new FacesMessage("Invalid Email format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }


}