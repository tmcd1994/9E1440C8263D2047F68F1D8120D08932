package webservice;

import java.io.Serializable;
import java.util.Date;

public class WorkOrder implements Serializable {
	
	public Long id;
	public Long priorityRank;
	public Date date; 
	public String priorityClass;
	
	public WorkOrder(Long id, Date date, Long priorityRank, String priorityClass){
		this.id = id;
		this.date = date;
		this.priorityClass = priorityClass;
		this.priorityRank = priorityRank;
	}

	
	public void setID(Long id) {
		this.id = id; 
	}
	
	public Long getID() {
		return id; 
	}
	
	public void setDate(Date date) {
		this.id = id; 
	}
	
	public Date getDate() {
		return date; 
	}
	
	public void setPriorityRank(Long priorityRank) {
		this.priorityRank = priorityRank; 
	}
	
	public Long getPriorityRank() {
		return priorityRank; 
	}
	
	public void setPriorityClass(String priorityClass) {
		this.priorityClass = priorityClass; 
	}
	
	public String getPriorityClass() {
		return priorityClass; 
	}
}
