package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.ProjectDAO;
import dao.StudentDAO;
import dto.ProjectView;
import dto.StudentView;

@Component
@Path("/students")
public class StudentResource {

	@Autowired
	private StudentDAO studentDao;
	@Autowired
	private ProjectDAO projectDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudents(){
		return Response.status(Status.OK).entity( StudentView.fromStudents(studentDao.getStudents()) ).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createStudent(StudentView studentView){	
		studentDao.addStudent( StudentView.toStudent(studentView) );
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentById(@PathParam ("id") Integer studentId){		
		return Response.status(Status.OK).entity( StudentView.fromStudent(studentDao.getStudentById(studentId)) ).build();
	}
	
	@GET
	@Path("/student/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentByName(@PathParam ("name") String studentName){
		return Response.status(Status.OK).entity( StudentView.fromStudent(studentDao.getStudentByName(studentName)) ).build();
	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(@PathParam ("id") Integer studentId, StudentView studentView){
		Student student = StudentView.toStudent(studentView);
		student.setId(studentId);
		studentDao.updateStudent(student);
		return Response.status(Status.OK).entity( StudentView.fromStudent(studentDao.getStudentById(student.getId())) ).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removeStudent(@PathParam ("id") Integer studentId){
		Student s = studentDao.getStudentById(studentId);
		studentDao.deleteStudent(s);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/{id}/projects")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@PathParam ("id") Integer studentId){
		return Response.status(Status.OK).entity( ProjectView.fromProjects(studentDao.getProjects(studentId)) ).build();
	}
}
