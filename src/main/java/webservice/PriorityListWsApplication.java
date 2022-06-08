package webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriorityListWsApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(PriorityListWsApplication.class, args);
		
		//Populating the priority queue for test purposes
		
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
        PriorityService.sortPriority(wo); //sortPriority will give these work orders their 'class' i.e. VIP
        PriorityService.sortPriority(wo2); //sortPriority calls giveClassRank which populates the work order's rank
        PriorityService.sortPriority(wo3);
        PriorityService.sortPriority(wo4);
        PriorityService.sortPriority(wo5);
        PriorityService.sortPriority(wo6);
        PriorityService.sortPriority(wo7);
        PriorityService.sortPriority(wo8);
		list.add(wo);
		list.add(wo2);
		list.add(wo3);
		list.add(wo4);
		list.add(wo5);
		list.add(wo6);
		list.add(wo7);
		list.add(wo8);
		list.sort(new WorkOrderComparator());
		PriorityService.priorityQ = list;
	}
}