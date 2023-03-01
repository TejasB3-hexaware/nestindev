package com.sppringmslast.controller;

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

import com.sppringmslast.controller.NewsController;
import com.sppringmslast.entity.News;
import com.sppringmslast.service.NewsService;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class NewsControllerTest {
	@Mock
	private NewsService service;

	@InjectMocks
	private NewsController controller;
	
	private List<News> prepareNewsRecords(){
		List<News> news = new ArrayList<News>();
		News news1 = new News(1L, "NOHcf",63);
		News news2 = new News(2L, "jcQsr",65);
		news.add(news1);
		news.add(news2);
		return news;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareNewsRecords());
		List<News> news = prepareNewsRecords();
		List<News> newsFromController =  controller.fetchAll();
		for(int i=0; i<news.size();i++) {
			Assertions.assertEquals(news.get(i).getId(), newsFromController.get(i).getId());
            Assertions.assertEquals(news.get(i).getName(), newsFromController.get(i).getName());
            Assertions.assertEquals(news.get(i).getAge(), newsFromController.get(i).getAge());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareNewsRecords());
		List<News> news = null; //Intentionally made null to fail the test.
		List<News> newsFromController =  controller.fetchAll();
		Assertions.assertNotEquals(news, newsFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareNewsRecords().get(0));
			News newsById = prepareNewsRecords().get(0);
			News newsByIdFromController = controller.fetchById(1L);
			
			Assertions.assertEquals(newsById.getId(), newsByIdFromController.getId());
			
		     
			Assertions.assertEquals(newsById.getName(), newsByIdFromController.getName());
			Assertions.assertEquals(newsById.getAge(), newsByIdFromController.getAge());
	 }

	 @Test public void fetchByIdFailure() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareNewsRecords().get(0));
			News newsById = prepareNewsRecords().get(1);
			News newsByIdFromController = controller.fetchById(1L);
			
			Assertions.assertNotEquals(newsById.getId(), newsByIdFromController.getId());

        Assertions.assertNotEquals(newsById.getName(), newsByIdFromController.getName());
        Assertions.assertNotEquals(newsById.getAge(), newsByIdFromController.getAge());
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(1L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		News newsToBeCreated = prepareNewsRecords().get(0);
		News newsReturned = prepareNewsRecords().get(0);
		newsReturned.setId(7L); //Changed the ID.
		
		Mockito
			.when(controller.create(newsToBeCreated)).thenReturn(newsReturned);
		
		News newsFromController  = controller.create(newsToBeCreated);
		Assertions.assertNotEquals(newsToBeCreated.getId(), newsFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal. 
		
        Assertions.assertEquals(newsToBeCreated.getName(), newsFromController.getName());
        Assertions.assertEquals(newsToBeCreated.getAge(), newsFromController.getAge());
	}
	
	@Test
	public void createFailure() {
		News newsToBeCreated = prepareNewsRecords().get(0);
		News newsReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(newsToBeCreated)).thenReturn(newsReturned);
		
			News newsFromController  = controller.create(newsToBeCreated);
		Assertions.assertNull(newsFromController);
	}
	
	/*
	 * @Test public void update() { ResponseEntity<Object> responseObject = null;
	 * 
	 * Mockito.when(controller.update(newsToBeUpdated,
	 * "")).thenReturn(responseObject); }
	 */	
}