package com.shareBuddy.excp;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ErrorResponse 
{
    public ErrorResponse(String message, List<String> details) {
        super();
        this.setMessage(message);
        this.setDetails(details);
    }
 
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	//General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
 
    //Getter and setters
}