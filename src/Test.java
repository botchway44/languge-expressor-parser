import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Parser parser = new Parser();
		try {
			Scanner scan  = new Scanner(new File("code.txt"));
			int count = 1;
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
 				parser.parseLine(line, count);
				count++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
