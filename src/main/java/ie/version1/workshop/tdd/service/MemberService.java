package ie.version1.workshop.tdd.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ie.version1.workshop.tdd.dao.Dao;
import ie.version1.workshop.tdd.dao.FileDao;
import ie.version1.workshop.tdd.model.Member;

public class MemberService {
	private Dao dao;
	
	public MemberService(){
		dao = new FileDao();
	}
	
	public List<Member> createMembers(String fileName) throws IOException{
		List<Member> members = new ArrayList<>();
		
		List<String> nameList = dao.readInData(fileName);
		for(String name: nameList){
			Member member = new Member(name);
			members.add(member);
		}
		
		return members;		
	}
}
