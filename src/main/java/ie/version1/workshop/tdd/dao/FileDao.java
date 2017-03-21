package ie.version1.workshop.tdd.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileDao implements Dao{

	@Override
	public List<String> readInData(String fileName) throws IOException {
		List<String> dataReadIn = new ArrayList<>();
		Path fileToReadIn = Paths.get(fileName);
		
		try(InputStream inputStream = Files.newInputStream(fileToReadIn);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			String line = bufferedReader.readLine();
			while(line != null){
				dataReadIn.add(line);
				line = bufferedReader.readLine();
			}			
		}
		
		return dataReadIn;
	}

}
