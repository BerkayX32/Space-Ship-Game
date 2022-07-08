package fighter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreTable {
	File f1 = new File("ScoreTable.txt");
	int point;
	String alias;
	
	public ScoreTable(int score, String userName) {
		this.point = score;
		this.alias = userName;
	}

	// save scores
	public void writeScore() throws IOException{
		try {
			//check score file if exist; if not create one
			createFileIfNotExist();
			
			//write to file 
			FileWriter fileWritter = new FileWriter(f1.getName(),true);
			BufferedWriter bw = new BufferedWriter(fileWritter);
			bw.write("Score: "+point + " Name: " + alias+"\r\n");
			bw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void createFileIfNotExist () {
		if(!f1.exists()) {
			try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}	

