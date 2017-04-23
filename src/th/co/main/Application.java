package th.co.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Application {
	
	public static void main(String[] args) throws IOException {

		//set target directory that keep file
		String targetDir = "D:/test/";
		//set destination path
		String outputPath = "D:/output/";
		//set dictionary path
		String dictionaryPath = "D:/test/dictionary.txt";
		File dir = new File(targetDir);
		File[] allFileInDir = dir.listFiles();
		//read dictionary
		HashMap<String, String> replaceWord  = readDict(dictionaryPath);
		//start process each file
		for(File file : allFileInDir){
			if(file.isFile()){
				System.out.println(file.getName() + " is File start process");
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				FileWriter writer = new FileWriter(new File(outputPath + file.getName()));
				BufferedWriter bw = new BufferedWriter(writer);
				String line;
				//read each file
				while((line = br.readLine()) != null){
					System.out.println("old line : " + line);
					//each key word in dictionary
					for(String key : replaceWord.keySet()){
						//replace text
						line = line.replace(key, replaceWord.get(key));
					}
					//write processed line to file
					bw.write(line);
					bw.newLine();
					bw.flush();
					System.out.println("new line : " + line);
				}
				bw.close();
			}else{
				System.out.println(file.getName() + " is directory not process");
			}
		}

	}
	
	public static HashMap<String, String> readDict(String dictPath) throws IOException{
		HashMap<String, String> dictionary = new HashMap<String, String>();
		File file = new File(dictPath);
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String line;
		while((line = br.readLine()) != null){
			String[] s;
			s = line.split("=");
			//expect format oldValue=newValue
			dictionary.put(s[0], s[1]);
		}
		return dictionary;	
	}
	
}
