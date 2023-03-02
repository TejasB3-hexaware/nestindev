package com.springpostlast.controller;

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

import com.springpostlast.entity.Youtube;
import com.springpostlast.service.YoutubeService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class YoutubeControllerTest {
	@Mock
	private YoutubeService service;

	@InjectMocks
	private YoutubeController controller;
	
	private List<Youtube> prepareYoutubeRecords(){
		List<Youtube> youtubeList = new ArrayList<Youtube>();
		Youtube youtube1 = new Youtube(345345L, "RHZfR",100);
		Youtube youtube2 = new Youtube(345344L, "iQ3tg",79);
		youtubeList.add(youtube1);
		youtubeList.add(youtube2);
		return youtubeList;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareYoutubeRecords());
		List<Youtube> youtubeList = prepareYoutubeRecords();
		List<Youtube> youtubeListFromController =  controller.fetchAll();
		for(int i=0; i<youtubeList.size();i++) {
			Assertions.assertEquals(youtubeList.get(i).getId(), youtubeListFromController.get(i).getId());
            Assertions.assertEquals(youtubeList.get(i).getName(), youtubeListFromController.get(i).getName());
            Assertions.assertEquals(youtubeList.get(i).getAge(), youtubeListFromController.get(i).getAge());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareYoutubeRecords());
		List<Youtube> youtubeList = null; //Intentionally made null to fail the test.
		List<Youtube> youtubeListFromController =  controller.fetchAll();
		Assertions.assertNotEquals(youtubeList, youtubeListFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(345344L))
            .thenReturn(prepareYoutubeRecords().get(0));

        Youtube youtubeById = prepareYoutubeRecords().get(0);
        Youtube youtubeByIdFromController = controller.fetchById(345344L);
        
        Assertions.assertEquals(youtubeById.getId(), youtubeByIdFromController.getId());
        Assertions.assertEquals(youtubeById.getName(), youtubeByIdFromController.getName());
        Assertions.assertEquals(youtubeById.getAge(), youtubeByIdFromController.getAge());
		 
	 }

	 @Test public void fetchByIdFailure() { 
		Mockito
	        .when(controller.fetchById(345344L))
            .thenReturn(prepareYoutubeRecords().get(0));

        Youtube youtubeById = prepareYoutubeRecords().get(1);
        Youtube youtubeByIdFromController = controller.fetchById(345344L);
        
        Assertions.assertNotEquals(youtubeById.getId(), youtubeByIdFromController.getId());
        Assertions.assertNotEquals(youtubeById.getName(), youtubeByIdFromController.getName());
        Assertions.assertNotEquals(youtubeById.getAge(), youtubeByIdFromController.getAge());
		 
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(345345L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Youtube youtubeToBeCreated 			= prepareYoutubeRecords().get(0);
		Youtube youtubeReturned = prepareYoutubeRecords().get(0);
		youtubeReturned.setId(345348L); //Changed the ID.
		
		Mockito
			.when(controller.create(youtubeToBeCreated))
            .thenReturn(youtubeReturned);
		
		Youtube youtubeFromController  = controller.create(youtubeToBeCreated);
		Assertions.assertNotEquals(youtubeToBeCreated.getId(), youtubeFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal.
        Assertions.assertEquals(youtubeToBeCreated.getName(), youtubeFromController.getName());
        Assertions.assertEquals(youtubeToBeCreated.getAge(), youtubeFromController.getAge());
	}
	
	@Test
	public void createFailure() {
		Youtube youtubeToBeCreated = prepareYoutubeRecords().get(0);
		Youtube youtubeReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(youtubeToBeCreated))
            .thenReturn(youtubeReturned);
		
		Youtube youtubeFromController  = controller.create(youtubeToBeCreated);
		Assertions.assertNull(youtubeFromController);
	}
}
