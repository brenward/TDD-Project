package ie.version1.workshop.tdd.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.version1.workshop.tdd.model.Leader;
import ie.version1.workshop.tdd.model.Lunch;
import ie.version1.workshop.tdd.model.Member;

public class LeaderTest {
	Leader objectUnderTest;
	
	@Before
	public void setup(){
		objectUnderTest = new Leader("Bob");
	}
	
	@Test
	public void canAttendLunch_NoConflicts(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("Larry"));
		
		assertTrue(objectUnderTest.canAttendLunch(table));
	}
	
	@Test
	public void canAttendLunch_Conflicts(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("John"));
		
		assertFalse(objectUnderTest.canAttendLunch(table));
	}
	
	@Test
	public void areSufficientViableMembersAvailable_EmptyList(){
		objectUnderTest.getPeopleEatenWith().add(new Member("Larry"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Harry"));
		
		List<Member> members = new ArrayList<>();

		assertFalse(objectUnderTest.areSufficientViableMembersAvailable(members,1));
	}
	
	@Test
	public void areSufficientViableMembersAvailable_ViableAvailable(){
		objectUnderTest.getPeopleEatenWith().add(new Member("Larry"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Harry"));
		
		List<Member> members = new ArrayList<>();
		members.add(new Member("Larry"));
		members.add(new Member("Harry"));
		members.add(new Member("Barry"));
		
		assertTrue(objectUnderTest.areSufficientViableMembersAvailable(members,1));
	}
	
	@Test
	public void areSufficientViableMembersAvailable_NoViableAvailable(){
		objectUnderTest.getPeopleEatenWith().add(new Member("Larry"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Harry"));
		
		List<Member> members = new ArrayList<>();
		members.add(new Member("Larry"));
		members.add(new Member("Harry"));
		
		assertFalse(objectUnderTest.areSufficientViableMembersAvailable(members,1));
	}

}
