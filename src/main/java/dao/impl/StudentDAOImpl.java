package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Project;
import model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.StudentDAO;

@Repository("studentDaoImpl")
@Transactional
public class StudentDAOImpl implements StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public void addStudent(Student student) {
		em.persist(student);
	}

	public List<Student> getStudents() {
		return em.createQuery("SELECT s FROM Student s", Student.class)
				.getResultList();
	}

	public Student getStudentById(Integer id) {
		try {
			return em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class)
				.setParameter("id", id)
				.getSingleResult();
	} catch (NoResultException e) {
		e.printStackTrace();
	}
	return null;
	}

	public Student getStudentByName(String name) {
		try {
			return em.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateStudent(Student student) {
		em.merge(student);
	}

	public void deleteStudent(Student student) {
		Student s = em.find(Student.class, student.getId());
		em.remove(s);
	}

	public List<Project> getProjects(Integer studentId) {
		return em.createQuery("SELECT p FROM Project p "
				+ "JOIN p.students s "
				+ "WHERE :studentId = s.id", Project.class)
				.setParameter("studentId", studentId).getResultList();
	}
}
