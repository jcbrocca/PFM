package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Platform",
			uniqueConstraints = 
			{
				@UniqueConstraint(columnNames= {"name"})
			}
		)
public class Platform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPlatform;
	private String name;

	@Id
	@Column(name = "idPlatform")
	public int getIdPlatform() {
		return idPlatform;
	}

	public void setIdPlatform(int idPlatform) {
		this.idPlatform = idPlatform;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}