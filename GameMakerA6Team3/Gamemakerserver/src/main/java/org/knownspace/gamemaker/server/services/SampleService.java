package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.entity.Sample;


public interface SampleService {

	Sample findById(Long id);
	List<Sample> findAll();
	Sample save(Sample sample);
	Sample delete(Sample sample);
	
    Sample fromJsonToEntity(String json);
    String toJson(Collection<Sample> collection);

}
