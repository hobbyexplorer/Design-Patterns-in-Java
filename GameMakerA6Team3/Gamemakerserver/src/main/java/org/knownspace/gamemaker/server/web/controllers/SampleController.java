package org.knownspace.gamemaker.server.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.knownspace.gamemaker.server.entity.Sample;
import org.knownspace.gamemaker.server.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/sample")
public class SampleController {

	// Not thread safe, but ok for this simple example
	private static int counter = 1;
	
	@Autowired
	private SampleService sampleService;
	
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
    	return "index";
    }

    @RequestMapping(value = "bootstrap", method = RequestMethod.GET)
    public String bootstrap() {
		Sample s1 = new Sample();
		s1.setName("s" + counter);
		s1.setValue("s" + counter + "-val");
		sampleService.save(s1);
		counter++;
		
		Sample s2 = new Sample();
		s2.setName("s" + counter);
		s2.setValue("s" + counter + "-val");
		sampleService.save(s2);
		counter++;

		return "redirect:all";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String retriveAllSamples(Model uiModel, HttpServletRequest request) {
    	List<Sample> samples = sampleService.findAll();
    	uiModel.addAttribute("samples", samples);
		return "data";
    }

    @RequestMapping(value = "{sampleId}", method = RequestMethod.GET)
    public String retriveById(@PathVariable("sampleId") Long sampleId, Model uiModel, HttpServletRequest request) {
    	Sample sample = sampleService.findById(sampleId);
    	List<Sample> samples = new ArrayList<Sample>();
    	if (sample != null) {
    		samples.add(sample);
    	}
    	uiModel.addAttribute("samples", samples);
		return "data";
    }

    @RequestMapping(value = "{sampleId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object get(@PathVariable("sampleId") Long sampleId) {
    	Sample sample = sampleService.findById(sampleId);
        if (sample == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return sample.toJson();
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public String getListJson(HttpServletRequest request) {
    	List<Sample> samples = sampleService.findAll();
    	return sampleService.toJson(samples);
    }
	
}
