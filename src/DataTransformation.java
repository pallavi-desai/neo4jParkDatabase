import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DataTransformation {

	public void filterData() {
		String line, columnNames;
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("DNR_Camping_Parks_Reservation_Data_2016.csv"));
			columnNames= csvReader.readLine();
			String columns[] = columnNames.split(",");
			
			BufferedWriter wr = new BufferedWriter(new FileWriter("file1.csv"));
			for(String column:columns) {
				wr.write(column+",");
			}
			wr.newLine();
			while((line=csvReader.readLine())!= null) {
				String wordsArray [] = line.split(",");
				if(wordsArray[2].contains("CANADA")) {
					for(String words:wordsArray) {
						wr.write(words+",");
					}
					wr.newLine();
				}
				
			}
			csvReader.close();
			wr.close();
			verticalFragment();
		} catch (Exception e) {
			System.out.println("Unexpected input");
		}
	}
	
	public void verticalFragment() {
		try {
			String line;
			BufferedReader csvReader = new BufferedReader(new FileReader("file1.csv"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("file2.csv"));
			while((line= csvReader.readLine())!= null) {
				String wordsArray[] = line.split(",");
				wr.write(wordsArray[0]+",");
				wr.write(wordsArray[5]+",");
				wr.write(wordsArray[6]+",");
				wr.write(wordsArray[7]+",");
				wr.write(wordsArray[8]+",");
				wr.newLine();
			}
			csvReader.close();
			wr.close();
			replaceValues();
		} catch(Exception e) {
			System.out.println("Unexpected execution while reading file 'file1.csv'");
		}
	}
	
	public void replaceValues() {
		try {
			String line,columnNames;
			BufferedReader csvReader = new BufferedReader(new FileReader("file2.csv"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("file3.csv"));
			columnNames= csvReader.readLine();
			String columns[] = columnNames.split(",");
			for(String column:columns) {
				wr.write(column+",");
			}
			wr.newLine();
			while((line = csvReader.readLine())!= null) {
				String wordsArray[] = line.split(",");
				for(int i=0;i<wordsArray.length-1;i++) {
					wr.write(wordsArray[i]+",");
				}
				if(wordsArray[4].contains("Less than")) {
					wr.write(wordsArray[4].replace("Less than ", "LT")+",");
				} else if(wordsArray[4].contains("Single Tent")) {
					wr.write(wordsArray[4].replace("Single Tent", "ST")+",");
				} else {
					wr.write(wordsArray[4]+",");
				}
				wr.newLine();
			}
			csvReader.close();
			wr.close();
		}catch(Exception e) {
			System.out.println("Unexpected execution while updating the values of Equipment column");
		}
	}
}
