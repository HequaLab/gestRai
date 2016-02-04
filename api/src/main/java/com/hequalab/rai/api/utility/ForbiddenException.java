package com.hequalab.rai.api.utility;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Thrown to return a 400 Bad Request response with a list of error messages in
 * the body.
 */
public class ForbiddenException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private String errors;

    public ForbiddenException(String errors) {
	super(Response.status(Status.FORBIDDEN)
		.type(MediaType.APPLICATION_XHTML_XML)
		.entity(new GenericEntity<String>(errors) {
		}).build());
	this.setErrors(errors);
    }

    public String getErrors() {
	return errors;
    }

    public void setErrors(String errors) {
	this.errors = errors;
    }

}