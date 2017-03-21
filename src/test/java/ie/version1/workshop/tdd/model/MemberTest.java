package ie.version1.workshop.tdd.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.version1.workshop.tdd.model.Member;

public class MemberTest {
	Member objectUnderTest;
	
	@Before
	public void setup(){
		objectUnderTest = new Member("Bob");
	}
	
	@Test
	public void canAttendLunch_NoConflicts(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("Larry"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Harry"));
		
		assertTrue(objectUnderTest.canAttendLunch(table));
	}
	
	@Test
	public void canAttendLunch_OneConflict(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("John"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Harry"));
		
		assertTrue(objectUnderTest.canAttendLunch(table));
	}
	
	@Test
	public void canAttendLunch_TwoConflict(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("John"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Kate"));
		
		assertTrue(objectUnderTest.canAttendLunch(table));
	}
	
	@Test
	public void canAttendLunch_ThreeConflict(){
		List<Member> table = new ArrayList<>();
		table.add(new Member("John"));
		table.add(new Member("Amy"));
		table.add(new Member("Shane"));
		table.add(new Member("Kate"));
		
		objectUnderTest.getPeopleEatenWith().add(new Member("John"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Kate"));
		objectUnderTest.getPeopleEatenWith().add(new Member("Shane"));
		
		assertFalse(objectUnderTest.canAttendLunch(table));
	}
}
