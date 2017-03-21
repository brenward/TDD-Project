package ie.version1.workshop.tdd.dao;

import java.io.IOException;
import java.util.List;

public interface Dao {
	public List<String> readInData(String fileName) throws IOException;
}
