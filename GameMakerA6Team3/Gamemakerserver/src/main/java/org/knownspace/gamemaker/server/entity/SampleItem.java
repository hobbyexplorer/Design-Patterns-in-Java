package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="BI_SMPL_ITM_T")
public class SampleItem implements Serializable {
 
	private static final long serialVersionUID = -8421434817028837443L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long id;

    @Column(name="SMPL_ID", insertable=false, updatable=false)
    private Long sampleId;

    @Column(name="NM")
	private String name;

    @Column(name="VAL")
    private String value;

	@ManyToOne
	@JoinColumn(name="SMPL_ID")
	private Sample sample;
    
    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

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

	public Long getSampleId() {
		return sampleId;
	}

	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

}
