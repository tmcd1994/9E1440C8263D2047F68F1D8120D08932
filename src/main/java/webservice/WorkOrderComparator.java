package webservice;

import java.util.Comparator;

public class WorkOrderComparator implements Comparator<WorkOrder> {

	@Override
	public int compare(WorkOrder wo1, WorkOrder wo2) {
		//check if work order is management override
		if (wo1.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride) || wo2.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride)) {
			if(wo1.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride) && !wo2.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride)) {
				return -1;
			}
			else if(wo2.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride) && !wo1.getPriorityClass().equalsIgnoreCase(PriorityService.mgmtOverride)) {
				return 1;
				
			} else {
				if (wo1.getPriorityRank() == wo2.getPriorityRank()) {
					return 0;
				} else if(wo1.getPriorityRank() > wo2.getPriorityRank()) {
					return -1;
				}
				else {
					return 1;
				}
			}
		} else {
			if (wo1.getPriorityRank() == wo2.getPriorityRank()) {
				return 0;
			}
			else if(wo1.getPriorityRank() > wo2.getPriorityRank()) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
}
