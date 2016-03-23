package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import model.Project;
import model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.ProjectService;
import dto.ProjectView;
import dto.StudentView;

@Component
@Path("/projects")
public class ProjectResource {
	
	@Autowired
	 private ProjectService projectService;
	 
	 @GET
	 @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response getProjects(@HeaderParam("Accept") String accepted, @Context UriInfo uriInfo){ 
	  List<ProjectView> projectsView = ProjectView.fromProjects(projectService.getProjects());
	  
	  for(ProjectView p : projectsView){
	   p.addLink(getUriForProjects(uriInfo, p), "self");
	  }
	  
	  if(accepted.equals("application/xml")){
	   //Pentru a returna raspunsul sub forma de XML
	   GenericEntity<List<ProjectView>> entity = new GenericEntity<List<ProjectView>>(projectsView) {};
	   return Response.ok(entity).build();
	  } else {
	   //Pentru a returna raspunsul sub forma de JSON      
	   return Response.status(Status.OK)
	     .entity(projectsView)
	        .build();
	  }
	 }
	 
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response createProject(ProjectView projectview, @Context UriInfo uriInfo){ 
		 Project project = ProjectView.toProject(projectview);
		 projectService.createProject(project);
	  
		 projectview.setId(project.getId());
		 projectview.addLink(getUriForProjectId(uriInfo, projectview), "self");
	  
	  return Response.status(Status.CREATED)
	    .entity(projectview)
	    .build();
	 }
	 
	 @GET
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getProjectById(@PathParam ("id") Integer projectId, @Context UriInfo uriInfo){
	  ProjectView projectView = ProjectView.fromProject( projectService.getProjectById(projectId) ); 
	  
	  projectView.addLink(getUriForProjectId(uriInfo, projectView), "self");
	  projectView.addLink(getUriForProjectTitle(uriInfo, projectView), "self-title");
	  projectView.addLink(getUriForStudents(uriInfo, projectView), "students");
	  
	  return Response.status(Status.OK)
	    .entity(projectView)
	    .build();
	 }
	 
	 @GET
	 @Path("/project/{title}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getProjectByTitle(@PathParam ("title") String projectTitle, @Context UriInfo uriInfo){
	  ProjectView projectView = ProjectView.fromProject( projectService.getProjectByTitle(projectTitle) );
	  projectView.addLink(getUriForProjectTitle(uriInfo, projectView), "self");
	  projectView.addLink(getUriForProjectId(uriInfo, projectView), "self-id");
	  projectView.addLink(getUriForStudents(uriInfo, projectView), "students");
	  
	  return Response.status(Status.OK).entity(projectView).build();
	 }
	 
	 @PUT
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response updateProject(@PathParam ("id") Integer projectId, ProjectView projectView, @Context UriInfo uriInfo){
	  Project project = ProjectView.toProject(projectView);
	  project.setId(projectId);
	  projectService.updateProject(project);
	  
	  ProjectView newProj = ProjectView.fromProject( projectService.getProjectById(projectId) ); 
	  newProj.addLink(getUriForProjectId(uriInfo, newProj), "self");
	  
	  return Response.status(Status.OK).entity(newProj).build();
	 }
	 
	 @DELETE
	 @Path("{id}")
	 public Response removeProject(@PathParam ("id") Integer projectId){
	  Project p = projectService.getProjectById(projectId);
	  projectService.removeProject(p);
	  return Response.status(Status.NO_CONTENT).build();
	 }
	 
	 @GET
	 @Path("/{id}/students")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getStudents(@PathParam ("id") Integer projectId){
	  return Response.status(Status.OK).entity( StudentView.fromStudents( projectService.getStudents(projectId) ) ).build();
	 }
	 
	 @PUT
	 @Path("/{id}/students")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addNewStudent(@PathParam ("id") Integer projectId, Student student){
	  projectService.addStudent(projectId, student.getId()); 
	  
	  return Response.status(Status.OK)
	    .entity( ProjectView.fromProject( projectService.getProjectById(projectId)) )
	    .build();
	 }
	 
	 private String getUriForProjects(UriInfo uriInfo, ProjectView projectView) {
	    return uriInfo.getBaseUriBuilder()
	      .path(ProjectResource.class)
	      .build()
	      .toString();
	   }
	   
	  private String getUriForProjectId(UriInfo uriInfo, ProjectView projectView) {
	   return uriInfo.getBaseUriBuilder()
	     .path(ProjectResource.class)
	     .path(Integer.toString(projectView.getId()))
	     .build()
	     .toString();
	  }
	   
	  private String getUriForProjectTitle(UriInfo uriInfo, ProjectView projectView) {
	   return uriInfo.getBaseUriBuilder()
	     .path(ProjectResource.class)
	     .path("project")
	     .path(projectView.getTitle())
	     .build()
	     .toString();
	  }
	  
	  private String getUriForStudents(UriInfo uriInfo, ProjectView projectView){
	   return uriInfo.getBaseUriBuilder()
	     .path(ProjectResource.class)
	     .path(Integer.toString(projectView.getId()))
	     .path(StudentResource.class)
//	     .path(ProjectResource.class, "getStudents")
//	     .resolveTemplate("id", projectView.getId())
	     .build()
	     .toString();
	  }
}
