package org.knownspace.gamemaker.server.dao;

import java.util.List;

import org.knownspace.gamemaker.server.entity.Sample;


public interface SampleDao {

	Sample findById(Long id);
	List<Sample> findAll();
	Sample save(Sample sample);
	Sample delete(Sample sample);
}
