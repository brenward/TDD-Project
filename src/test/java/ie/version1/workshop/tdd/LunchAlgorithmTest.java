package ie.version1.workshop.tdd;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ie.version1.workshop.tdd.LunchAlgorithm;
import ie.version1.workshop.tdd.model.Leader;
import ie.version1.workshop.tdd.model.Lunch;
import ie.version1.workshop.tdd.model.Member;

public class LunchAlgorithmTest {

	LunchAlgorithm objectUnderTest = new LunchAlgorithm();

	@Test
	public void getLunches_listNotNull() throws IOException {

		assertNotNull(objectUnderTest.getLunches());
	}

	@Test
	public void getLunches_listNotEmpty() throws IOException {
		assertFalse(objectUnderTest.getLunches().isEmpty());
	}

	@Test
	public void getLunches_tableNotEmpty() throws IOException {

		List<Lunch> lunchList = objectUnderTest.getLunches();

		assertFalse(lunchList.isEmpty());
		assertNotNull(lunchList.get(0));
	}

	@Test
	public void getLunches_hasFiveLeaders() throws IOException {
		List<Lunch> lunchList = objectUnderTest.getLunches();

		assertEquals(5, lunchList.get(0).getTables().size());
	}
	
	@Test
	public void getLunches_AttendeesListNotEmpty() throws IOException{
		objectUnderTest.getLunches();
		assertNotNull(objectUnderTest.getLunchAttendees());
		assertFalse(objectUnderTest.getLunchAttendees().isEmpty());
	}
	
	@Test
	public void getLunches_LunchesTablesHaveAttendees() throws IOException{
		List<Lunch> lunchList = objectUnderTest.getLunches();
		
		for(Lunch lunch:lunchList){
			for(List<Member> members:lunch.getTables().values()){
				assertFalse(members.isEmpty());
			}
		}
	}
	
	@Test
	public void getLunches_AllAttendeesAssigned() throws IOException{
		List<Lunch> lunchList = objectUnderTest.getLunches();
		Set<Member> allMembers = new HashSet<>();
		Lunch lunch = lunchList.get(0);
		for(List<Member> members:lunch.getTables().values()){
			allMembers.addAll(members);
		}
		
		assertEquals(allMembers.size(),objectUnderTest.getLunchAttendees().size());
	}
	
	@Test
	public void getLunches_SetTableSizesNotNull() throws IOException{
		objectUnderTest.getLunches();
		assertNotNull(objectUnderTest.getTableSizes());		
	}
	
	@Test
	public void getLunches_SetTableSizesPopulated() throws IOException{
		objectUnderTest.getLunches();
		assertTrue(objectUnderTest.getTableSizes()[0] > 0);
	}
	
	@Test
	public void getLunches_TotalTableSizesEqualNumberOfAttendees() throws IOException{
		objectUnderTest.getLunches();
		int totalTableSize = 0;
		for(int tableSize:objectUnderTest.getTableSizes()){
			totalTableSize += tableSize;
		}
		
		assertEquals(totalTableSize,objectUnderTest.getLunchAttendees().size());
	}
	
	@Test
	public void getLunches_ReturnsFiveLunches() throws IOException{
		List<Lunch> lunchList = objectUnderTest.getLunches();
		assertEquals(5, lunchList.size());
	}
	
	@Test
	public void getLunches_LeadersHaveUniqueMembers() throws IOException{
		List<Lunch> lunchList = objectUnderTest.getLunches();
		Map<Leader,List<Member>> leaderCombinedMembers = new HashMap<>();
		
		for(Lunch lunch:lunchList){
			for(Map.Entry<Leader,List<Member>> table:lunch.getTables().entrySet()){
				List<Member> membersLeaderIsHosting = new ArrayList<Member>();
				for(Member member:table.getValue()){
					membersLeaderIsHosting.add(member);
				}
				
				if(leaderCombinedMembers.containsKey(table.getKey())){
					leaderCombinedMembers.get(table.getKey()).addAll(membersLeaderIsHosting);
				}else{
					leaderCombinedMembers.put(table.getKey(), membersLeaderIsHosting);
				}
			}
		}
		
		for(List<Member> combinedMembers:leaderCombinedMembers.values()){
			Set<Member> uniqueMembers = new HashSet<>(combinedMembers);
			assertEquals(combinedMembers.size(),uniqueMembers.size());
		}		
	}
	
	@Test
	public void assignMembersToTable_SufficientAvailableMembersUniqueToLeader(){
		Leader leader = new Leader("Bob");
		leader.getPeopleEatenWith().add(new Member("Larry"));
		
		List<Member> availableMembers = createMemberList();
		
		List<Member> attendees = objectUnderTest.assignMembersToTable(leader,availableMembers,6);
		assertEquals(6,attendees.size());
	}
	
	@Test
	public void assignMembersToTable_InSufficientAvailableMembersUniqueToLeader(){
		Leader leader = new Leader("Bob");
		leader.getPeopleEatenWith().add(new Member("Larry"));
		
		List<Member> availableMembers = createMemberList();
		
		List<Member> attendees = objectUnderTest.assignMembersToTable(leader,availableMembers,7);
		assertTrue(attendees.isEmpty());
	}	
	
	@Test
	public void assignMembersToTable_NotNull(){
		Leader leader = new Leader("Bob");
		List<Member> availableMembers = new ArrayList<>();
		
		List<Member> attendees = objectUnderTest.assignMembersToTable(leader,availableMembers,0);
		assertNotNull(attendees);
	}
	
	@Test
	public void getMembersUniqueToLeader_NotNull(){
		List<Member> availableMembers = new ArrayList<>();
		List<Member> leadersPreviousMembers = new ArrayList<>();
		List<Member> attendees = objectUnderTest.getMembersUniqueToLeader(availableMembers,leadersPreviousMembers);
		assertNotNull(attendees);
	}
	
	@Test
	public void getMembersUniqueToLeader_AllRemoved(){
		List<Member> availableMembers = createMemberList();
		List<Member> leadersPreviousMembers = createMemberList();
		List<Member> attendees = objectUnderTest.getMembersUniqueToLeader(availableMembers,leadersPreviousMembers);
		assertTrue(attendees.isEmpty());
	}
	
	@Test
	public void getMembersUniqueToLeader_DuplicatesRemoved(){
		List<Member> availableMembers = createMemberList();
		List<Member> leadersPreviousMembers = new ArrayList<>();
		leadersPreviousMembers.add(new Member("Larry"));
		leadersPreviousMembers.add(new Member("John"));
		leadersPreviousMembers.add(new Member("Harry"));
		
		List<Member> attendees = objectUnderTest.getMembersUniqueToLeader(availableMembers,leadersPreviousMembers);
		assertEquals(5,attendees.size());
	}
	
	private List<Member> createMemberList(){
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Larry"));
		memberList.add(new Member("Harry"));
		memberList.add(new Member("Barry"));
		memberList.add(new Member("Gary"));
		memberList.add(new Member("James"));
		memberList.add(new Member("Joseph"));
		memberList.add(new Member("Shane"));
		
		return memberList;
	}

	/*
	 * input list of members and lieutenants output lists
	 * 
	 * asssertions:
	 */

}
