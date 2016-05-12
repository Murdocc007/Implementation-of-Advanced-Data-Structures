import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for the Trie implementation
 * @author Shweta , Radhika, Aditya, Divyanshu
 *
 */
public class SP0str extends TrieST{

	public static void main(String[] args) throws IOException  {
		Scanner scan;
		int ch;
		TrieST NewDictionary = new TrieST(); 
		scan = new Scanner(System.in);
		do{
			System.out.println("Select your Choice");
			System.out.println("1.Insert a new word from console");
			System.out.println("2.Contains: Check if the word exists in dictionary");
			System.out.println("3.Remove the word from dictionary");
			System.out.println("4.Import words from a file");
			System.out.println("5.Check number of occurences for a prefix");
			System.out.println("6.Exit");
			ch=scan.nextInt();
			switch(ch){
			case 1:
				System.out.println("Enter the Words to be inserted:");
				NewDictionary.add(scan.next().toLowerCase());
				break;
			case 2:
				System.out.println("Enter the word to be found:");
				System.out.println(""+NewDictionary.contains(scan.next().toLowerCase()));
				break;
			case 3:
				System.out.println("Enter the word to be removed:");
				NewDictionary.remove(scan.next().toLowerCase());
				System.out.println("Word removed.");
				break;
			case 4:
				System.out.println("Enter the file path that needs to be imported into the dictionary:");
				NewDictionary.importFromFile(scan.next());
				System.out.println("Import complete.");
				break;
			case 5:
				System.out.println("Enter the prefix to be searched in the dictionary:");
//				String word= scan.next().toLowerCase();
				String word= scan.next();
				System.out.println("Number of occurences:"+ NewDictionary.prefix(word));
				break;
			case 6:
				System.out.println("Exit");
				break;
			default: 
				System.out.println("Entered Wrong Choice");
				break;
			}
		}while(ch!=6);  
		scan.close();
		System.exit(0);
	}
}