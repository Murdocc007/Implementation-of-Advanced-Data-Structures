import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Implements SLEntry :is a driver class 
 * @author Shweta, Radhika, Divyanshu, Aditya
 */
/* Class SkipNode */
class SLEntry {
	
	
	public static void main(String[] args) throws FileNotFoundException{
		int ch;
		SkipList<Integer> s = new SkipList<>();
		s.add(10);
		s.add(21);
		s.add(50);
		System.out.println("Default SkipList created");
		
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("Select your Choice");
			System.out.println("1.Insert");
			System.out.println("2.Contains");
			System.out.println("3.Remove");
			System.out.println("4.Exit");
			ch=scan.nextInt();
			switch(ch){
			case 1:
				System.out.println("Enter the element to be inserted:");
				s.add(scan.nextInt());
				break;
			case 2:
				System.out.println("Enter the element to be found:");
				System.out.println(""+s.contains(scan.nextInt()));
				break;
			case 3:
				System.out.println("Enter the element to be found:");
				System.out.println(""+(s.remove(scan.nextInt())).element);
				break;
			case 4:
				System.out.println("Exit");
				break;
			default: 
				System.out.println("Entered Wrong Choice");
				break;
			}
		}while(ch!=5);  
		scan.close();
		System.exit(0);
	}
}/*
	int element;
	SLEntry prev;
	SLEntry next[];
	
	 
	
    public SLEntry(int x)
    {
        this(x, null, null);
    }
   
    public SLEntry(int x, SLEntry prev, SLEntry next)
    {
        element = x;
        prev = prev;
        next = next;
    }
}*/
/*

//private SkipEntry head;
private SkipEntry tail = null;

//Constructor
public SkipList(int f)
{
    tail = new SkipEntry(f);
    tail.next= tail;
    head = new SkipEntry(f, 0, tail);*/