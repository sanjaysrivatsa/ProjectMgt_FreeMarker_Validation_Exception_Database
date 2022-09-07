package com.ProjectManagement.ProjectManagement.Controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProjectManagement.ProjectManagement.Model.Project;
import com.ProjectManagement.ProjectManagement.dao.ProjectsDAO;
import com.ProjectManagement.ProjectManagement.exceptions.EntryAlreadyExists;
import com.ProjectManagement.ProjectManagement.exceptions.ErrorHandling;

@RestController
//@Controller
@RequestMapping("/Project") //Defines  a context
//With ControllerAdvice, we are implementing a routing logic.
//If any exception takes place in any part of the application, the control will
//be routed to this controller.
//This controller is the exception handler.
@ControllerAdvice
public class ProjectController extends ResponseEntityExceptionHandler{

	@Autowired
	private ProjectsDAO  repo; //Good practice is to make Autowiring as private
	Logger  logger=Logger.getLogger("customlogger");
	@GetMapping (value="/listProjects", produces="application/json")
	//All end-point implementations must be non private by convention
	//And for none of them return types should be void.
	public ModelAndView getProjects()
	{
		logger.info("Returned list of projects");
		//Use ModelAndView  class to perform binding between the model and the view
		//The model elements are bound to the view.
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("listProjects");
		
		List   projectsList=repo.findAll();
		mv.addObject("projects", projectsList);
		return mv;
	}
	
	@GetMapping("/addNewProject")
	public ModelAndView addNewProject()
	{
		//It will integrate the Model and the View
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addProject"); //Refers to addProject.ftlh
		Date  now=new java.util.Date();
		mv.addObject("Today", now.toString()); //Model
		
		return mv;
	}
	
	@PostMapping(value="/addProject", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces="application/json")
	public ModelAndView addProject(@Valid Project p)
	{
		//@Valid causes validation to be done at the time addProject() method is called.
//Validations : projectname -min length to be 5 characters. Less than 5 it should not accept and prints invalid project name
//start-date - It should be later than 01-Jan-1994		
//end-date - should be lesser than 31-Dec-2030
		//If validation succeeds, it calls addProject() otherwise it doesn't call.
		repo.save(p);
		logger.info("Added project");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addedProject");
		mv.addObject("project", p);
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete() {
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("deleteProject");
		List   projectsList=repo.findAll();
		mv.addObject("projects", projectsList);
		return mv;
	}

	@PostMapping(value="/deleteProject")
	public ModelAndView deleteProject(@RequestParam("projectId") String projectId)
	{
		int  prId=Integer.parseInt(projectId); //String to number conversion. For ex: "100" will
		//become 100
		repo.deleteById(prId);
		logger.info("Project deleted...");

		ModelAndView  mv=new ModelAndView();
		mv.setViewName("deletedProject");
		mv.addObject("pName", prId);
		//To keep track of Request-Response pair, ServletUriComponentsBuilder is used
		return mv;
	}
	//This also can handle any other exception other than the validations failure.
	//This method works like catch block of try-catch combination.
	@ExceptionHandler(EntryAlreadyExists.class)
	public ResponseEntity<Object> handleUserNotFoundException(EntryAlreadyExists ent, WebRequest request)
	{
		List <String> details = new ArrayList();
		details.add(ent.getLocalizedMessage());
		ErrorHandling errh= new ErrorHandling("projectId already exists", details);
		//Exception messages are formatted in JSON format because in the REST endpoint, 
		//format is application/json
		return new ResponseEntity(errh, HttpStatus.CONFLICT);
	}
	
	
	//This exception handler is executed when the validations fail.
	//Here we give the report of all the failed validations.
	protected ResponseEntity<Object> handleMethodArgumentNotValid
	(MethodArgumentNotValidException m, HttpHeaders headers, HttpStatus status, WebRequest webreq)
	{
		System.out.println("Exception : " + m.getMessage());
		System.out.println("Total failed validations : " + m.getErrorCount());
		return super.handleMethodArgumentNotValid(m, headers, status, webreq);
	}
}
