package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entity.Standard;
import com.example.demo.exception.StandardException;
import com.example.demo.service.StandardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/higherAuthority/standard")
public class StandardController {
	
	@Autowired
	StandardService standardService;
	
	@PostMapping
	public ResponseEntity<Standard> addStandard(@RequestBody Standard std) {
		Standard newStandard = null;
		try {
			newStandard = standardService.addStandard(std);
			return new ResponseEntity<>(newStandard, HttpStatus.CREATED);
		} catch (StandardException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Standard> getStandardById(@PathVariable Long id) {
		Standard std = null;
		try {
			std = standardService.getStandardById(id);
			return new ResponseEntity<>(std, HttpStatus.OK);
		} catch (StandardException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Standard>> getAllStandards() {
		List<Standard> stdList = null;
		try {
			stdList = standardService.getAllStandards();
			return new ResponseEntity<>(stdList, HttpStatus.OK);
		} catch (StandardException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Standard> updateStandard(@PathVariable Long id, @RequestBody Standard standard){
		try {
			Standard e= standardService.getStandardById(id);
			e.setStdName(standard.getStdName());
			return new ResponseEntity<>(standardService.updateStandard(e),HttpStatus.OK);
		}catch(StandardException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
