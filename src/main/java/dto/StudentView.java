package dto;

import java.util.List;
import java.util.stream.Collectors;

import model.Student;

public class StudentView {

	private Integer id;
	private String name;
	private String cnp;
	private String placeOfBirth;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public static StudentView fromStudent(Student student){
		StudentView studentView = new StudentView();
		studentView.setId(student.getId());
		studentView.setName(student.getName());
		studentView.setCnp(student.getCnp());
		studentView.setPlaceOfBirth(student.getPlaceOfBirth());
		return studentView;
	}
	
	public static Student toStudent(StudentView studentView){
		Student student = new Student();
		student.setId(studentView.getId());
		student.setName(studentView.getName());
		student.setCnp(studentView.getCnp());
		student.setPlaceOfBirth(studentView.getPlaceOfBirth());
		return student;
	}
	
	public static List<StudentView> fromStudents(List<Student> students){
		return students.stream()
				.map(p -> fromStudent(p))
				.collect(Collectors.toList());
	}
	
}
