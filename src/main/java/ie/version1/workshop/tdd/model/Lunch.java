package ie.version1.workshop.tdd.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lunch {

	private final Map<Leader, List<Member>> tables = new HashMap<>();
	
	public Lunch(List<Leader> leaders){
		for(Leader leader:leaders){
			tables.put(leader, new ArrayList<Member>());
		}				
	}

	public Map<Leader, List<Member>> getTables() {
		return tables;
	}

}
