package com.springpostgresql.controller;

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
import org.mockito.junit.jupiter.MockitoExtension;

import com.springpostgresql.entity.Teacher;
import com.springpostgresql.service.TeacherService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {
	@Mock
	private TeacherService service;

	@InjectMocks
	private TeacherController controller;
	
	private List<Teacher> prepareTeacherRecords(){
		List<Teacher> teacherList = new ArrayList<Teacher>();
		Teacher teacher1 = new Teacher(345345L, "2ezq9",86);
		Teacher teacher2 = new Teacher(345344L, "MTT71",69);
		teacherList.add(teacher1);
		teacherList.add(teacher2);
		return teacherList;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareTeacherRecords());
		List<Teacher> teacherList = prepareTeacherRecords();
		List<Teacher> teacherListFromController =  controller.fetchAll();
		for(int i=0; i<teacherList.size();i++) {
			Assertions.assertEquals(teacherList.get(i).getId(), teacherListFromController.get(i).getId());
            Assertions.assertEquals(teacherList.get(i).getName(), teacherListFromController.get(i).getName());
            Assertions.assertEquals(teacherList.get(i).getAge(), teacherListFromController.get(i).getAge());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareTeacherRecords());
		List<Teacher> teacherList = null; //Intentionally made null to fail the test.
		List<Teacher> teacherListFromController =  controller.fetchAll();
		Assertions.assertNotEquals(teacherList, teacherListFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(345344L))
            .thenReturn(prepareTeacherRecords().get(0));

        Teacher teacherById = prepareTeacherRecords().get(0);
        Teacher teacherByIdFromController = controller.fetchById(345344L);
        
        Assertions.assertEquals(teacherById.getId(), teacherByIdFromController.getId());
        Assertions.assertEquals(teacherById.getName(), teacherByIdFromController.getName());
        Assertions.assertEquals(teacherById.getAge(), teacherByIdFromController.getAge());
		 
	 }

	 @Test public void fetchByIdFailure() { 
		Mockito
	        .when(controller.fetchById(345344L))
            .thenReturn(prepareTeacherRecords().get(0));

        Teacher teacherById = prepareTeacherRecords().get(1);
        Teacher teacherByIdFromController = controller.fetchById(345344L);
        
        Assertions.assertNotEquals(teacherById.getId(), teacherByIdFromController.getId());
        Assertions.assertNotEquals(teacherById.getName(), teacherByIdFromController.getName());
        Assertions.assertNotEquals(teacherById.getAge(), teacherByIdFromController.getAge());
		 
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(345345L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Teacher teacherToBeCreated 			= prepareTeacherRecords().get(0);
		Teacher teacherReturned = prepareTeacherRecords().get(0);
		teacherReturned.setId(345348L); //Changed the ID.
		
		Mockito
			.when(controller.create(teacherToBeCreated))
            .thenReturn(teacherReturned);
		
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
			.when(controller.create(teacherToBeCreated))
            .thenReturn(teacherReturned);
		
		Teacher teacherFromController  = controller.create(teacherToBeCreated);
		Assertions.assertNull(teacherFromController);
	}
}
