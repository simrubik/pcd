package service;

import java.util.List;

import javax.ws.rs.PathParam;

import model.Project;
import model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjectDAO;
import dao.StudentDAO;
import exceptions.DataNotFoundException;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectDAO projectDao;
	@Autowired
	private StudentDAO studentDao;
	
	public List<Project> getProjects(){		
		return projectDao.getProjects();
	}
	
	public void createProject(Project project){	
		projectDao.addProject(project);
	}
	
	public Project getProjectById(@PathParam ("id") Integer projectId){
		Project project = projectDao.getProjectById(projectId);
		
		if(project == null){
			throw new DataNotFoundException("Project with id " + projectId + " not found.");
		}
		return project;
	}
	
	public Project getProjectByTitle(String projectTitle){
		return projectDao.getProjectByTitle(projectTitle);
	}
	
	public void updateProject(Project project){
		projectDao.updateProject(project);
	}
	
	public void removeProject(Project project){
		projectDao.deleteProject(project);
	}
	
	public List<Student> getStudents(Integer projectId){
		return projectDao.getStudents(projectId);
	}
	
	public void addStudent(Integer projectId, Integer studentId){
		Student s = studentDao.getStudentById(studentId);
		Project p = projectDao.getProjectById(projectId);
		p.addStudent(s);
		projectDao.updateProject(p);
	}
	
	
}
