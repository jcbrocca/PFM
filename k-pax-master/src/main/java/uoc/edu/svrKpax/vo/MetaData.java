package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@XmlRootElement
@Entity
@Table(name = "MetaData")
public class MetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMetadata;
	private String keyMeta;
	private String valueMeta;

	private Game game;

	@Id
	@Column(name = "idMetadata")
	public int getIdMetadata() {
		return idMetadata;
	}

	public void setIdMetadata(int idMetadata) {
		this.idMetadata = idMetadata;
	}

	@Column(name = "keyMeta")
	public String getKeyMeta() {
		return keyMeta;
	}

	public void setKeyMeta(String keyMeta) {
		this.keyMeta = keyMeta;
	}
	
	@Column(name = "valueMeta")
	public String getValueMeta() {
		return valueMeta;
	}

	public void setValueMeta(String valueMeta) {
		this.valueMeta = valueMeta;
	}
	
	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGame")
	@JsonIgnore
	public Game getGame() {
	    return this.game;
	}

	public void setGame(Game game) {
	    this.game = game;
	}
}