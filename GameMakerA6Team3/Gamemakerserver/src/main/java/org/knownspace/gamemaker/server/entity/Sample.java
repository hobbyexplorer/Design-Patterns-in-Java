package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import flexjson.JSONSerializer;

@Entity
@Table(name="BI_SMPL_T") 
public class Sample implements Serializable {
 
	private static final long serialVersionUID = 2013770474039352852L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long id;

    @Column(name="NM")
	private String name;

    @Column(name="VAL")
    private String value;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="sample")
    private List<SampleItem> items;
    
	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Sample() {
		items = new ArrayList<SampleItem>();
	}
	
    public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

    public List<SampleItem> getItems() {
		return items;
	}

	public void setItems(List<SampleItem> items) {
		this.items = items;
	}

}
