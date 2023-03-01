package com.springmsmarch.controller;

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

import com.springmsmarch.controller.TeacherController;
import com.springmsmarch.entity.Teacher;
import com.springmsmarch.service.TeacherService;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {
	@Mock
	private TeacherService service;

	@InjectMocks
	private TeacherController controller;
	
	private List<Teacher> prepareTeacherRecords(){
		List<Teacher> teacher = new ArrayList<Teacher>();
		Teacher teacher1 = new Teacher(1L, "XTUZA",27);
		Teacher teacher2 = new Teacher(2L, "FnlzD",46);
		teacher.add(teacher1);
		teacher.add(teacher2);
		return teacher;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareTeacherRecords());
		List<Teacher> teacher = prepareTeacherRecords();
		List<Teacher> teacherFromController =  controller.fetchAll();
		for(int i=0; i<teacher.size();i++) {
			Assertions.assertEquals(teacher.get(i).getId(), teacherFromController.get(i).getId());
            Assertions.assertEquals(teacher.get(i).getName(), teacherFromController.get(i).getName());
            Assertions.assertEquals(teacher.get(i).getAge(), teacherFromController.get(i).getAge());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareTeacherRecords());
		List<Teacher> teacher = null; //Intentionally made null to fail the test.
		List<Teacher> teacherFromController =  controller.fetchAll();
		Assertions.assertNotEquals(teacher, teacherFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareTeacherRecords().get(0));
			Teacher teacherById = prepareTeacherRecords().get(0);
			Teacher teacherByIdFromController = controller.fetchById(1L);
			
			Assertions.assertEquals(teacherById.getId(), teacherByIdFromController.getId());
			
		     
			Assertions.assertEquals(teacherById.getName(), teacherByIdFromController.getName());
			Assertions.assertEquals(teacherById.getAge(), teacherByIdFromController.getAge());
	 }

	 @Test public void fetchByIdFailure() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareTeacherRecords().get(0));
			Teacher teacherById = prepareTeacherRecords().get(1);
			Teacher teacherByIdFromController = controller.fetchById(1L);
			
			Assertions.assertNotEquals(teacherById.getId(), teacherByIdFromController.getId());

        Assertions.assertNotEquals(teacherById.getName(), teacherByIdFromController.getName());
        Assertions.assertNotEquals(teacherById.getAge(), teacherByIdFromController.getAge());
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(1L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Teacher teacherToBeCreated = prepareTeacherRecords().get(0);
		Teacher teacherReturned = prepareTeacherRecords().get(0);
		teacherReturned.setId(7L); //Changed the ID.
		
		Mockito
			.when(controller.create(teacherToBeCreated)).thenReturn(teacherReturned);
		
		Teacher teacherFromController  = controller.create(teacherToBeCreated);
		Assertions.assertNotEquals(teacherToBeCreated.getId(), teacherFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal. 
		
        Assertions.assertEquals(teacherToBeCreated.getName(), teacherFromController.getName());
        Assertions.assertEquals(teacherToBeCreated.getAge(), teacherFromController.getAge());
	}
	
	@Test
	public void createFailure() {
		Teacher teacherToBeCreated = prepareTeacherRecords().get(0);
		Teacher teacherReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(teacherToBeCreated)).thenReturn(teacherReturned);
		
			Teacher teacherFromController  = controller.create(teacherToBeCreated);
		Assertions.assertNull(teacherFromController);
	}
	
	/*
	 * @Test public void update() { ResponseEntity<Object> responseObject = null;
	 * 
	 * Mockito.when(controller.update(teacherToBeUpdated,
	 * "")).thenReturn(responseObject); }
	 */	
}