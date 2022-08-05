package com.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.model.PatientDisasterStatus;
import com.acme.repository.PatientDisasterStatusRepository;
import com.acme.request.model.BatchPatientDisasterStatusInput;
import com.acme.request.model.BatchPatientDisasterStatusOutput;
import com.acme.request.model.GetPatientDisasterStatusInput;
import com.acme.request.model.GetPatientDisasterStatusOutput;
import com.acme.request.model.PutPatientDisasterStatusInput;
import com.acme.request.model.PutPatientDisasterStatusOutput;

@RestController
@RequestMapping("/patientStatus/")
public class PatientDisasterStatusController {

    @Autowired
    private PatientDisasterStatusRepository disasterStatusRepository;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    public PutPatientDisasterStatusOutput createOrUpdatePatientStatus(PutPatientDisasterStatusInput putPatientDisasterStatusInput) {
        return null;
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/get")
    public GetPatientDisasterStatusOutput getPatientStatus(GetPatientDisasterStatusInput getPatientDisasterStatusInput) {
    	GetPatientDisasterStatusOutput response = null;
    	List<PatientDisasterStatus>  returnedStatusList = disasterStatusRepository.findByFacilityAndPatientFacilityId(getPatientDisasterStatusInput.getFacilityNpi().toString(), getPatientDisasterStatusInput.getPatientIdFromFacility());
    	if(returnedStatusList != null && !returnedStatusList.isEmpty()) {
    		PatientDisasterStatus pds = returnedStatusList.get(0);
    		response = new GetPatientDisasterStatusOutput();
    		response.setDate(pds.getDate());
    		response.setDisasterName(pds.getDisaster().getName());
    		if(pds.getPatient()!=null) {
    			if(pds.getPatient().getFacility()!=null) {
    				response.setFacilityLocation(pds.getPatient().getFacility().getFacilityName());
    			}
    			if(pds.getPatient().getId() !=null) {
    				response.setPatientId(pds.getPatient().getId().toString());
    			}
    		}
    		if(pds.getPatientStatus() != null)
    			response.setPatientStatus(pds.getPatientStatus().getStatus());
    		
    	}
    	return response;
    }


    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/batch")
    public BatchPatientDisasterStatusOutput batchUpdateStatus(BatchPatientDisasterStatusInput batchPatientDisasterStatusInput) {
    	//TODO
        return null;
    }
    
  
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{patientId}/list")
    public PatientDisasterStatus listByPatientId(@PathVariable(value = "patientId") Long patientId) {
    	//TODO
        return null;
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}")
    public PatientDisasterStatus getStatusById(@RequestParam(value = "date") String dateMMYY) {
    	//TODO
        return null;
    }



}
