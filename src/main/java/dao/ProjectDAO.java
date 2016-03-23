package dao;

import java.util.List;

import model.Project;
import model.Student;

public interface ProjectDAO {

	void addProject(Project project);
	List<Project> getProjects();
	Project getProjectById(Integer projectId);
	Project getProjectByTitle(String projectTitle);
	void updateProject(Project project);
	void deleteProject(Project project);
	
	List<Student> getStudents(Integer projectId);
//	void deleteProjectStudent(Project project, Student student);
}
