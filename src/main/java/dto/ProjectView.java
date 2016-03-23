package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Project;

public class ProjectView {

	private Integer id;
	private String title;
	private String description;
	private String year;
	
	private List<Link> links = new ArrayList<Link>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public static ProjectView fromProject(Project project){
		ProjectView projectView = new ProjectView();
		projectView.setId(project.getId());
		projectView.setTitle(project.getTitle());
		projectView.setDescription(project.getDescription());
		projectView.setYear(project.getYear());
//		projectView.getLinks().put("self", "projects/");
//		projectView.getLinks().put("students", "projects/" + project.getId() + "/students");
		return projectView;
	}
	
	public static Project toProject(ProjectView projectView){
		Project project = new Project();
		project.setId(projectView.getId());
		project.setTitle(projectView.getTitle());
		project.setDescription(projectView.getDescription());
		project.setYear(projectView.getYear());
		return project;
	}
	
	public static List<ProjectView> fromProjects(List<Project> projects){
		return projects.stream()
				.map(p -> fromProject(p))
				.collect(Collectors.toList());
	}
	
	public void addLink(String url, String rel){
		Link link = new Link();
		link.setUrl(url);
		link.setRel(rel);
		links.add(link);
	}
}
