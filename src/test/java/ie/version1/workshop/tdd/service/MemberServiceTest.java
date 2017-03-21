package ie.version1.workshop.tdd.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.version1.workshop.tdd.model.Member;
import ie.version1.workshop.tdd.service.MemberService;

public class MemberServiceTest {
	MemberService objectUnderTest;
	
	@Before
	public void setup(){
		objectUnderTest = new MemberService();
	}
	
	@Test
	public void createMembers_NotNull() throws IOException{
		List<Member> members = objectUnderTest.createMembers("members.txt");
		assertNotNull(members);
	}
	
	@Test
	public void createMembers_NotEmpty() throws IOException{
		List<Member> members = objectUnderTest.createMembers("members.txt");
		assertTrue(members.size() > 0);
	}
	
	@Test
	public void creatMembers_MemberNamesNotNull() throws IOException{
		List<Member> members = objectUnderTest.createMembers("members.txt");		
		assertNotNull(members.get(0).getName());
	}
}
