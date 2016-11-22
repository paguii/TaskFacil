package taskfacil.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Task")
public class Task {


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int owner;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String local;
	@Column
	@Type(type="date")
	private Date date;


	public Task() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setLocal(String local){
		this.local = local;
	}

	public String getLocal(){
		return local;
	}
}
