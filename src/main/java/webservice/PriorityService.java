package webservice;

import java.util.ArrayList;
import java.util.Date;

public class PriorityService {
	public static String mgmtOverride = "ManagementOverride";
	public static String vip = "VIP";
	public static String priority = "Priority";
	public static String normal = "Normal";
	public static ArrayList<WorkOrder> priorityQ = new ArrayList();
	
	
	//assigns a work order a class based on the ID
	public static void sortPriority(WorkOrder workOrder) {
		Long id = workOrder.getID();
		if(id % 5 == 0 && id % 3 == 0) {
			workOrder.setPriorityClass(mgmtOverride);
		}else if (id % 3 == 0) {
			workOrder.setPriorityClass(priority);
		} else if (id % 5 == 0) {
			workOrder.setPriorityClass(vip);
		} else {
			workOrder.setPriorityClass(normal);
		}
		giveClassRank(workOrder);
		
	}
	
	//calculates position in queue
	public static void giveClassRank(WorkOrder wo) {
		Date currentDate = new Date();
		long calculatePlace = 0;
		Date timeOfJoiningQue = wo.getDate(); 
		long secondInQueue = calculateTimeInQueue(currentDate, timeOfJoiningQue);
		if(wo.getPriorityClass().equals(priority)) {
			calculatePlace = (long)Math.max(3, secondInQueue * Math.log(secondInQueue));
			wo.setPriorityRank(calculatePlace);
			priorityQ.add(wo);
		} else if(wo.getPriorityClass().equals(vip)) {
			calculatePlace = (long) Math.max(4, (2 * secondInQueue) * Math.log(secondInQueue));
			wo.setPriorityRank(calculatePlace);
			priorityQ.add(wo);
		} else if(wo.getPriorityClass().equals(mgmtOverride)) {
			calculatePlace = secondInQueue; 
			wo.setPriorityRank(calculatePlace);
			priorityQ.add(wo);
		} else {
			calculatePlace = secondInQueue;
			wo.setPriorityRank(calculatePlace);
			priorityQ.add(wo);
		}
		priorityQ.sort(new WorkOrderComparator());
	}
	
	public static WorkOrder getTopOfQueue() {
		priorityQ.sort(new WorkOrderComparator());
		WorkOrder workOrder = priorityQ.get(0);
		return workOrder;
			
	}
	
	public static ArrayList getList() {
		priorityQ.sort(new WorkOrderComparator());
		return priorityQ;
			
	}
	
	public static void removeFromQueue(Long id) {
		for(int i = 0; i < priorityQ.size(); i ++) {
			if(priorityQ.get(i).getID()==id) {
				priorityQ.remove(i);
			}
		}
	}
	
	public static Integer getItemPositionInQueue(Long id) {	
		Integer position = null;
		for (WorkOrder wo : priorityQ) {
			if(wo.getID() == id) {
				return priorityQ.indexOf(wo);
			}
		}
		return position;
	}
	
	public static Long calculateAverageWaitTime(Date currentDate) {
		Long totalWaitTime = 0L;
		Long averageWaitTime = 0L;
		Integer countWO = 0;
		for (WorkOrder wo : priorityQ) {
			Date timeOfJoiningQue = wo.getDate();
			Long waitTime = calculateTimeInQueue(currentDate, timeOfJoiningQue);
			totalWaitTime = waitTime + totalWaitTime;
			countWO = countWO + 1;
		}
		averageWaitTime = totalWaitTime/countWO;
		return averageWaitTime;
	}
	
	
	public static Long calculateTimeInQueue(Date currentDate, Date joinedQueue) {	
		long seconds = (currentDate.getTime()-joinedQueue.getTime())/1000;
		return seconds;
	}
	
}


