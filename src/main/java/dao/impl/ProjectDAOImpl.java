package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Project;
import model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjectDAO;

@Repository("projectDaoImpl")
@Transactional
public class ProjectDAOImpl implements ProjectDAO{

	@PersistenceContext
	private EntityManager em;
	
	public void addProject(Project project){
		em.persist(project);
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjects(){
		Query q = em.createQuery("SELECT p FROM Project p");
		return	q.getResultList();
	}

	public Project getProjectById(Integer projectId){
		Query q = em.createQuery("SELECT p FROM Project p WHERE p.id = :project_id");
        q.setParameter("project_id", projectId);

        Project project = null;
        if(q.getResultList().size() == 0){
        	return null;
		} 
        
        project = (Project) q.getSingleResult();
        return project;
	}

	public Project getProjectByTitle(String projectTitle) {
		try {
			Query q = em.createQuery("SELECT p FROM Project p WHERE p.title = :title");
	        q.setParameter("title", projectTitle);
	        
	        return (Project) q.getSingleResult();
		} catch (NoResultException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> getStudents(Integer projectId) {
		Query q = em.createQuery("SELECT s FROM Student s "
				+ "JOIN s.projects p "
				+ "WHERE :projectId = p.id");
		q.setParameter("projectId", projectId);
		return q.getResultList();
	}

	public void updateProject(Project project) {
		em.merge(project);
	}

	public void deleteProject(Project project) {
		Project p = em.find(Project.class, project.getId());
		em.remove(p);
	}
}
