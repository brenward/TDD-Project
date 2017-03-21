package ie.version1.workshop.tdd.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.version1.workshop.tdd.model.Leader;
import ie.version1.workshop.tdd.service.LeaderService;

public class LeaderServiceTest {
	LeaderService objectUnderTest;
	
	@Before
	public void setup(){
		objectUnderTest = new LeaderService();
	}
	
	@Test
	public void createLeaders_NotNull() throws IOException{
		List<Leader> leaders = objectUnderTest.createLeaders("leaders.txt");
		assertNotNull(leaders);
	}
	
	@Test
	public void createLeaders_NotEmpty() throws IOException{
		List<Leader> leaders = objectUnderTest.createLeaders("leaders.txt");
		assertTrue(leaders.size() > 0);
	}
	
	@Test
	public void creatLeaders_leaderNamesNotNull() throws IOException{
		List<Leader> leaders = objectUnderTest.createLeaders("leaders.txt");		
		assertNotNull(leaders.get(0).getName());
	}
}
