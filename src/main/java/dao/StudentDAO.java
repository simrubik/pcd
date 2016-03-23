package dao;

import java.util.List;

import model.Project;
import model.Student;

public interface StudentDAO {
	
	public void addStudent(Student student);
	public List<Student> getStudents();
	public Student getStudentById(Integer studentId);
	public Student getStudentByName(String name);	
	public void updateStudent(Student student);	
	public void deleteStudent(Student student);
	
	List<Project> getProjects(Integer studentId);
}
