package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entity.StandardSubjectDO;
import com.example.demo.entity.StandardSubjects;
import com.example.demo.exception.StandardSubjectException;
import com.example.demo.repository.StandardSubjectsRepository;
import com.example.demo.service.StandardSubjectsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/higherAuthority/standardSubjects")
public class StandardSubjectsController {

	@Autowired
	StandardSubjectsRepository stdSubRepo;

	@Autowired
	StandardSubjectsService stdSubService;

	@PostMapping()
	public String assignSubjectsToStandard(@RequestBody StandardSubjectDO stdSubDO) throws StandardSubjectException {
		int flag = 0;
		List<StandardSubjects> subTeacherList = null;

		try {
			flag = 0;
			if (flag == 0) {
				subTeacherList = stdSubService.findAllByStandardId(stdSubDO.getStd_id());
				System.out.println(subTeacherList.size());
				System.out.println(subTeacherList);
				if (subTeacherList.size() != 0) {
					for (int i = 0; i < subTeacherList.size(); i++) {
						if (subTeacherList.get(i).getSubject().getSubId() == stdSubDO.getSub_id()
								&& (subTeacherList.get(i).getStandard().getStdId() == stdSubDO.getStd_id())) {
							flag = 1;// same subject is assigned to selected standard
							break;
						}
					}
				}
			}

			if (flag == 1) {
				return ("Failed!! Same subject is assigned to selected standard");
			} else {
				StandardSubjects status;
				status = stdSubService.assignSubjectsToStandard(stdSubDO);

				if (status != null) {
					return "Successfully assigned";
				} else {
					return "Unable to assign subject to standard";
				}
			}
		} catch (StandardSubjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<StandardSubjects>> getAllRows() {
		List<StandardSubjects> subList = null;
		try {
			subList = stdSubService.getAllRows();
			return new ResponseEntity<>(subList, HttpStatus.OK);
		} catch (StandardSubjectException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	// localhost:8080/higherAuthority/standardSubjects/teacher/2
	@GetMapping("/teacher/{id}")
	public ResponseEntity<List<StandardSubjects>> getAllRowsByTeacherId(@PathVariable Long id) {
		List<StandardSubjects> subList = null;
		try {
			subList = stdSubService.findAllByTeacherId(id);
			return new ResponseEntity<>(subList, HttpStatus.OK);
		} catch (StandardSubjectException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteRow(@PathVariable Long id){
		try {
			Map<String,Boolean> statusMap=
					stdSubService.deleteById(id);
			return new ResponseEntity<>(statusMap,HttpStatus.OK);
		}catch(StandardSubjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

}
