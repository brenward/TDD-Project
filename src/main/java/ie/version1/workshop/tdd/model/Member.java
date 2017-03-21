package ie.version1.workshop.tdd.model;

import java.util.List;

public class Member extends Person{
	public Member(String name){
		this.name = name;
	}

	@Override
	public boolean canAttendLunch(List<Member> table) {
		int count = 0;
		for(Member member:table){
			if(peopleEatenWith.contains(member)){
				count++;
			}
		}
		return count < 3;
	}
}
