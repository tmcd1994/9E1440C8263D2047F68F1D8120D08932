package webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PriorityList")
public class PriorityListResource {
	public static String ID_OUT_OF_RANGE = "ID is out of range";
	public static String ID_IN_USE = "ID is already in use";
	public static Long max = 9223372036854775807L;
	public static Integer min = 1;
	
	@PutMapping()
	@RequestMapping("/addToQueue")
	public static String addToQueue(Long id, String date) throws ParseException {
		try {
			if(!validateID(id).isEmpty()) { 
				return validateID(id); 
			} else {	
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
				Date d = sdf.parse(date);
				WorkOrder workOrder = new WorkOrder(id, d, 0L, "");
				PriorityService.sortPriority(workOrder);
				return "Work Order: "+id+" has been added to the queue";
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("/getTopOfQueue")
	public static String getTopOfQueue() {
		WorkOrder workOrder;
		try {
			workOrder = PriorityService.getTopOfQueue();
			PriorityService.removeFromQueue(workOrder.getID());
			return "Highest ranked ID: "+workOrder.getID()+". Date entered queue: "+workOrder.getDate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("/getList")
	public static ArrayList getList() {
		try {
			ArrayList <WorkOrder> priorityList;
			priorityList = PriorityService.getList();
			return priorityList;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@DeleteMapping()
	@RequestMapping("/removeFromQueue")
	public static String removeFromQueue(Long id) {
		try {
			if(validateWorkOrderExist(id) == false) {
				return "Work Order: "+id+" does not exist. Please enter a valid ID";
			} else {	
				PriorityService.removeFromQueue(id);
				return "Work Order: "+id+" has been removed from queue";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	@GetMapping("/getPositionInQueue")
	public static String getPositionInQueue(Long id) {
		try {
			if(validateWorkOrderExist(id) == false) {
				return "Work Order: "+id+" does not exist. Please enter a valid ID";
			} else {
				Integer arrayPlace = PriorityService.getItemPositionInQueue(id);
				Integer placeInQueue = arrayPlace + 1; //First index is zero therefore top of que is 0 and not 1.
														//Adding 1 so place number '1' is not confused as next in line
				return "Position in Queue: "+placeInQueue.toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("/getAverageWaitTime")
	public static Long getAverageWaitTime() { 
		try {
			Date date = new Date();
			Long waittime = PriorityService.calculateAverageWaitTime(date);
			return waittime; //returns average wait time in seconds
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	//Checks ID is within min/max range. Also makes sure ID is not in use
	public static String validateID(Long id) {
		if (id < min || id > max) {
			return ID_OUT_OF_RANGE;
		}
		
		ArrayList <WorkOrder> list = PriorityService.priorityQ;
		for (WorkOrder wo : list) {
			if(wo.getID() == id) {
				return ID_IN_USE;
			}
		}
		return "";
	}
	
	//checks ID is inside list before continuing with web service
	public static Boolean validateWorkOrderExist(Long id) {
	
		ArrayList <WorkOrder> list = PriorityService.priorityQ;
		for (WorkOrder wo : list) {
			if(wo.getID() == id) {
				return true;
			}
		}
		return false;
	}

}
