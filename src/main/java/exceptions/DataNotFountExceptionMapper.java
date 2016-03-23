package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dto.ErrorMessage;

@Provider
public class DataNotFountExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(ex.getMessage(), 404)).build();
	}

}
