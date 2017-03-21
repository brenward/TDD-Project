package ie.version1.workshop.tdd.model;

import java.util.List;

public class Leader extends Person{
	
	public Leader(String name){
		this.name = name;
	}
	
	@Override
	public boolean canAttendLunch(List<Member> table) {
		for(Member member:table){
			if(peopleEatenWith.contains(member)){
				return false;
			}
		}
		return true;
	}
	
	public boolean areSufficientViableMembersAvailable(List<Member> remainingMembers, int requiredMembers) {
		int suitableMembers = 0;
		for(Member member:remainingMembers){
			if(!getPeopleEatenWith().contains(member)){
				suitableMembers++;
			}
		}
		return suitableMembers >= requiredMembers;
	}
}
