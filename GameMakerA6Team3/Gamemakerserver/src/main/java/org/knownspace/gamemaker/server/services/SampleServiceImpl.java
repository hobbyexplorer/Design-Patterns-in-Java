package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.dao.SampleDao;
import org.knownspace.gamemaker.server.entity.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleDao sampleDao;
	
    @Transactional(readOnly=true)
	@Override
	public Sample findById(Long id) {
		return sampleDao.findById(id);
	}
 
    @Transactional(readOnly=true)
	@Override
	public List<Sample> findAll() {
		return sampleDao.findAll();
	}

    @Transactional
	@Override
	public Sample save(Sample sample) {
		return sampleDao.save(sample);
	}

    @Transactional
	@Override
	public Sample delete(Sample sample) {
		return sampleDao.delete(sample);
	}

    public Sample fromJsonToEntity(String json) {
        return new JSONDeserializer<Sample>().use(null, Sample.class).deserialize(json);
    }

    public String toJson(Collection<Sample> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

}
