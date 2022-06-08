package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

import webservice.PriorityListResource;
import webservice.PriorityService;
import webservice.WorkOrder;
import webservice.WorkOrderComparator;

class Test {
	
	public static PriorityListResource priorityResource;
	public Long normalId = 1L;
	public String date = "11-01-2022-12:10:22";
	public Long vipIdl = 5L;
	public Long priorityId = 3L;
	public Long mgmtOverrideId = 15L;
	
	@BeforeEach
    public void setUp() throws ParseException {
		ArrayList <WorkOrder> list = new ArrayList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
		Date date1 = sdf.parse("30-01-2021-13:20:22");
		Date date2 = sdf.parse("30-09-2021-13:20:22");
		WorkOrder wo = new WorkOrder(10L, date1, 0L, ""); 
        WorkOrder wo2 = new WorkOrder(21L, date1, 0L, "");
        WorkOrder wo3= new WorkOrder(31L, date1, 0L, ""); 
        WorkOrder wo4= new WorkOrder(15L, date1, 0L, ""); 

		WorkOrder wo5 = new WorkOrder(12L, date2, 0L, ""); 
        WorkOrder wo6 = new WorkOrder(20L, date2, 0L, "");
        WorkOrder wo7= new WorkOrder(30L, date2, 0L, ""); 
        WorkOrder wo8= new WorkOrder(6L, date2, 0L, ""); 
        PriorityService.sortPriority(wo7);
        PriorityService.sortPriority(wo2);
        PriorityService.sortPriority(wo3);
        PriorityService.sortPriority(wo4);
        PriorityService.sortPriority(wo5);
        PriorityService.sortPriority(wo6);
        PriorityService.sortPriority(wo);
        PriorityService.sortPriority(wo8);
		list.add(wo7);
		list.add(wo2);
		list.add(wo3);
		list.add(wo4);
		list.add(wo5);
		list.add(wo6);
		list.add(wo);
		list.add(wo8);
		list.sort(new WorkOrderComparator());
		PriorityService.priorityQ = list;
		 
	}
	
	@org.junit.jupiter.api.Test
	void testAddToQueue() {
		try {
			System.out.println("________________________________________");
			System.out.println("UNIT TEST: AddToQueue");
			displayList();
			String response;
			
			response = priorityResource.addToQueue(9L, date);
		
			assertEquals(response, response);
			System.out.println("List after request:");
			displayList();
			System.out.println("END OF UNIT TEST: AddToQueue");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@org.junit.jupiter.api.Test
	void testGetList() {
		System.out.println("________________________________________");
		System.out.println("UNIT TEST: Get List");
		ArrayList response = priorityResource.getList();
		assertEquals(response, response);
		System.out.println(response);
		
		System.out.println("END OF UNIT TEST: Get List");
	}	
	
	
	@org.junit.jupiter.api.Test
	void testGetTopOfQueue() {
		System.out.println("________________________________________");
		System.out.println("UNIT TEST: GetTopOfQueue");
		displayList();
		String response = priorityResource.getTopOfQueue();
		assertEquals(response, response);

		System.out.println("Top of Queue:"+response);
		System.out.println("END OF UNIT TEST: GetTopOfQueue");
	}	
	
	
	
	@org.junit.jupiter.api.Test
	public void testRemoveFromQueue() {

		System.out.println("________________________________________");
		System.out.println("UNIT TEST: removeFromQueue");
		displayList();
		
		String response = priorityResource.removeFromQueue(10L);
		assertEquals(response, response);

		System.out.println("List after request:");
		displayList();
		System.out.println("END OF UNIT TEST: removeFromQueue");
	}
	
	@org.junit.jupiter.api.Test
	public void testGetPositionInQueue() {
		System.out.println("________________________________________");
		System.out.println("UNIT TEST: getPositionInQueue");
		displayList();
		
		String response = priorityResource.getPositionInQueue(21L);
		assertEquals(response, response);
		
		System.out.println("Position of Work Order in Queue: "+response);
		System.out.println("END OF UNIT TEST: getPositionInQueue");
	}
	
	@org.junit.jupiter.api.Test
	public void testGetAverageWaitTime() { 

		System.out.println("________________________________________");
		System.out.println("UNIT TEST: getAverageWaitTime");
		Date date = new Date();
		Long response = priorityResource.getAverageWaitTime();
		assertEquals(response, response);
		Long waittime = (Long) response;
		System.out.println("Average wait time in seconds: "+waittime);
		System.out.println("END OF UNIT TEST: getAverageWaitTime");
	}
	
	public void displayList() {
		ArrayList <WorkOrder> priorityList = PriorityService.getList();
		priorityList.sort(new WorkOrderComparator());
		for(WorkOrder wo : priorityList) {
			System.out.println("ID: "+wo.getID()+" Class: "+wo.getPriorityClass()+" Rank: "+wo.getPriorityRank()+" Date: "+wo.getDate());
		}

		System.out.println("-------------------------------");
		
	}
}
