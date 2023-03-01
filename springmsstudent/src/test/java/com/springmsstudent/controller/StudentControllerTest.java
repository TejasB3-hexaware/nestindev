package com.springmsstudent.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;

import com.springmsstudent.controller.StudentController;
import com.springmsstudent.entity.Student;
import com.springmsstudent.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	@Mock
	private StudentService service;

	@InjectMocks
	private StudentController controller;
	
	private List<Student> prepareStudentRecords(){
		List<Student> student = new ArrayList<Student>();
		Student student1 = new Student(1L, "Qe7L9",40);
		Student student2 = new Student(2L, "uWhUv",75);
		student.add(student1);
		student.add(student2);
		return student;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareStudentRecords());
		List<Student> student = prepareStudentRecords();
		List<Student> studentFromController =  controller.fetchAll();
		for(int i=0; i<student.size();i++) {
			Assertions.assertEquals(student.get(i).getId(), studentFromController.get(i).getId());
            Assertions.assertEquals(student.get(i).getName(), studentFromController.get(i).getName());
            Assertions.assertEquals(student.get(i).getAge(), studentFromController.get(i).getAge());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareStudentRecords());
		List<Student> student = null; //Intentionally made null to fail the test.
		List<Student> studentFromController =  controller.fetchAll();
		Assertions.assertNotEquals(student, studentFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareStudentRecords().get(0));
			Student studentById = prepareStudentRecords().get(0);
			Student studentByIdFromController = controller.fetchById(1L);
			
			Assertions.assertEquals(studentById.getId(), studentByIdFromController.getId());
			
		     
			Assertions.assertEquals(studentById.getName(), studentByIdFromController.getName());
			Assertions.assertEquals(studentById.getAge(), studentByIdFromController.getAge());
	 }

	 @Test public void fetchByIdFailure() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareStudentRecords().get(0));
			Student studentById = prepareStudentRecords().get(1);
			Student studentByIdFromController = controller.fetchById(1L);
			
			Assertions.assertNotEquals(studentById.getId(), studentByIdFromController.getId());

        Assertions.assertNotEquals(studentById.getName(), studentByIdFromController.getName());
        Assertions.assertNotEquals(studentById.getAge(), studentByIdFromController.getAge());
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(1L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Student studentToBeCreated = prepareStudentRecords().get(0);
		Student studentReturned = prepareStudentRecords().get(0);
		studentReturned.setId(7L); //Changed the ID.
		
		Mockito
			.when(controller.create(studentToBeCreated)).thenReturn(studentReturned);
		
		Student studentFromController  = controller.create(studentToBeCreated);
		Assertions.assertNotEquals(studentToBeCreated.getId(), studentFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal. 
		
        Assertions.assertEquals(studentToBeCreated.getName(), studentFromController.getName());
        Assertions.assertEquals(studentToBeCreated.getAge(), studentFromController.getAge());
	}
	
	@Test
	public void createFailure() {
		Student studentToBeCreated = prepareStudentRecords().get(0);
		Student studentReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(studentToBeCreated)).thenReturn(studentReturned);
		
			Student studentFromController  = controller.create(studentToBeCreated);
		Assertions.assertNull(studentFromController);
	}
	
	/*
	 * @Test public void update() { ResponseEntity<Object> responseObject = null;
	 * 
	 * Mockito.when(controller.update(studentToBeUpdated,
	 * "")).thenReturn(responseObject); }
	 */	
}