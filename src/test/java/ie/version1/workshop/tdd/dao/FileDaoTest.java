package ie.version1.workshop.tdd.dao;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.version1.workshop.tdd.dao.FileDao;

public class FileDaoTest {
	
	FileDao objectUnderTest;
	
	@Before
	public void setup(){
		objectUnderTest = new FileDao();
	}
	
	@Test
	public void readInData_NotNull(){
		List<String> data = null;
		try {
			data = objectUnderTest.readInData("leaders.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(data);
	}
	
	@Test(expected=NoSuchFileException.class)
	public void readInData_FileNotFound() throws IOException{
		objectUnderTest.readInData("test.txt");
	}
	
	@Test
	public void readInData_ListNotEmpty(){
		List<String> data = null;
		try {
			data = objectUnderTest.readInData("leaders.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(!data.isEmpty());
	}

}
