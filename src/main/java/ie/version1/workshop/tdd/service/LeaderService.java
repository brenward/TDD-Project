package ie.version1.workshop.tdd.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ie.version1.workshop.tdd.dao.Dao;
import ie.version1.workshop.tdd.dao.FileDao;
import ie.version1.workshop.tdd.model.Leader;

public class LeaderService {
private Dao dao;
	
	public LeaderService(){
		dao = new FileDao();
	}
	
	public List<Leader> createLeaders(String fileName) throws IOException{
		List<Leader> leaders = new ArrayList<>();
		
		List<String> nameList = dao.readInData(fileName);
		for(String name: nameList){
			Leader leader = new Leader(name);
			leaders.add(leader);
		}
		
		return leaders;		
	}
}
