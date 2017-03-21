package ie.version1.workshop.tdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ie.version1.workshop.tdd.model.Leader;
import ie.version1.workshop.tdd.model.Lunch;
import ie.version1.workshop.tdd.model.Member;
import ie.version1.workshop.tdd.service.LeaderService;
import ie.version1.workshop.tdd.service.MemberService;

public class LunchAlgorithm {
	private static final int NUMBER_OF_TABLES = 5;
	private static final int NUMBER_OF_LUNCHES = 5;
	
	LeaderService leaderService;
	MemberService memberService;
	
	List<Member> lunchAttendees;
	int[] tableSizes;
	
	public LunchAlgorithm(){
		leaderService = new LeaderService();
		memberService = new MemberService();
	}

	public List<Lunch> getLunches() throws IOException {
		List<Lunch> lunchList = new ArrayList<Lunch>();
		List<Leader> leaders = leaderService.createLeaders("leaders.txt");
		for(int i=0;i<NUMBER_OF_LUNCHES;i++){
			Lunch lunch = new Lunch(leaders);
			lunchList.add(lunch);
		}

		lunchAttendees = memberService.createMembers("members.txt");
		setTableSizes();
		for(Lunch lunch: lunchList){
			boolean success = false;
			while(!success){
				success = assignMembersToTables(lunch);
			}
			
			if(success){
				updateLeaders(lunch);
			}
		}		
		
		return lunchList;
	}
	
	public List<Member> getLunchAttendees(){
		return lunchAttendees;
	}
	
	public int[] getTableSizes(){
		return tableSizes;
	}
	
	private boolean assignMembersToTables(Lunch lunch){		
		int tableCount = 0;
		List<Member> unassignedAttendees = new ArrayList<>(lunchAttendees);
			
		for(Map.Entry<Leader, List<Member>> table:lunch.getTables().entrySet()){
			table.getValue().clear();
			List<Member> attendees = assignMembersToTable(table.getKey(),unassignedAttendees,tableSizes[tableCount]);
			if(attendees.isEmpty()){
				return false;
			}else{
				lunch.getTables().get(table.getKey()).addAll(attendees);
				for(Member member:attendees){
					if(unassignedAttendees.contains(member)){
						unassignedAttendees.remove(member);
					}
				}
			}
			tableCount++;
		}
		return true;
	}
	
	public void setTableSizes(){
		tableSizes = new int[NUMBER_OF_TABLES];
		int numberOfAttendees = lunchAttendees.size();
		int remainder = numberOfAttendees%NUMBER_OF_TABLES;
		for(int i=0;i<tableSizes.length;i++){
			tableSizes[i] = numberOfAttendees / NUMBER_OF_TABLES;
			if(remainder > 0){
				tableSizes[i]+=1;
				remainder--;
			}
		}
	}
	
	public List<Member> assignMembersToTable(Leader leader, List<Member> availableMembers,int tableSize){
		List<Member> table = new ArrayList<>();
		Random random = new Random();
		if(availableMembers.size() >= tableSize && leader.areSufficientViableMembersAvailable(availableMembers, tableSize)){
			List<Member> uniqueMembers = getMembersUniqueToLeader(availableMembers,leader.getPeopleEatenWith());
			for(int i=0;i<tableSize;i++){
				Member member = uniqueMembers.get(
						random.nextInt(uniqueMembers.size()));
				table.add(member);
				uniqueMembers.remove(member);
			}
		}
		return table;
	}
	
	public List<Member> getMembersUniqueToLeader(List<Member> availableMembers,List<Member> leadersPastMembers){
		List<Member> uniqueMembers = new ArrayList<>(availableMembers);
		for(Member member:leadersPastMembers){
			if(uniqueMembers.contains(member)){
				uniqueMembers.remove(member);
			}
		}		
		return uniqueMembers;
	}
	
	public void updateLeaders(Lunch lunch){
		for(Map.Entry<Leader, List<Member>> table:lunch.getTables().entrySet()){
			table.getKey().getPeopleEatenWith().addAll(table.getValue());
		}
	}
	


}
