package tech.wenisch.ipfix.generator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tech.wenisch.ipfix.generator.datastructures.IPFIXGeneratorJobHistoryEntry;
import tech.wenisch.ipfix.generator.service.IPFIXGeneratorService;
import tech.wenisch.ipfix.generator.threads.IPFIXGeneratorJob;

@RestController
public class APIController {
	@Autowired
	private IPFIXGeneratorService ipfixGeneratorService;

	@GetMapping("/api/jobs")
	public List<IPFIXGeneratorJob> getJobs() 
	{
		return  new ArrayList<IPFIXGeneratorJob>(ipfixGeneratorService.getJobsList());
	}
	
	@GetMapping("/api/jobs/{id}/history")
	public List<IPFIXGeneratorJobHistoryEntry> jobDetails(@PathVariable("id") String jobId, HttpServletRequest request, Model model)
	{ 
		return ipfixGeneratorService.getJobById(jobId).getHistory();

	}
}