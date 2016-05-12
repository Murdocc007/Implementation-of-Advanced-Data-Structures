import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class implements Trie data structure for dictionary
 * @author Shweta , Radhika, Aditya, Divyanshu
 *
 */
public class TrieST {
	private static TrieEntry root;
	int wordsCount;
	int count=0;
	
	/**
	 * Constructor for TrieST
	 */
	public TrieST(){
		root = new TrieEntry();
		wordsCount=0;
	}
		
	/**	
	 * Add a word to the dictionary
	 * @param word
	 */
	public void add(String word){
//		TrieEntry temp= root;
//		char[] charList = word.toLowerCase().toCharArray();
		char[] charList = word.toCharArray();
		
		if(!containsCheck(root.next,charList)){
			addAomefunc(root.next, charList);
		}else {
			System.out.println("Word ("+ word +" ) already present in the dictionary.");
		}
	}

	/**Helper function for add
	 * @param entry
	 * @param charList
	 */
	private void addAomefunc(TrieEntry[] entry, char[] charList) {
		for(int i = 0; i < charList.length; i++ ){
			if(entry[((int) charList[i])-97] == null){
				if(i != charList.length-1){
					entry[((int) charList[i])-97] = new TrieEntry(charList[i], false);
				}else {
					entry[((int) charList[i])-97] = new TrieEntry(charList[i], true);
					wordsCount++;
				}		
			}
			else{
				if(i == charList.length-1){
					entry[((int) charList[i])-97].IsWordEnd= true;
				}
			}
			entry = entry[((int) charList[i])-97].next;
		}
	}
	
	/**
	 * Remove a word from the dictionary
	 * @param word
	 */
	void remove(String word){
		TrieEntry[] entry = root.next;
		char[] charList = word.toCharArray() ;
		for(int i =0; i < charList.length; i++ ){
			if(entry[(int)charList[i]-97] != null){
				if(i == charList.length-1 && entry[(int)charList[i]-97].IsWordEnd == true){
					entry[(int)charList[i]-97].IsWordEnd = false;
				}
				entry = entry[(int)charList[i]-97].next;
			}
		}
	}
	
	/**Open a file with comma separated data and import all words from it
	 * @param file
	 * @throws IOException
	 */
	void importFromFile(String file) throws IOException{
		   try {
			FileReader fileRead= new FileReader(file);
			BufferedReader 	bufferedReader =  new BufferedReader(fileRead);
			String fileLine= null;
//			fileLine = bufferedReader.readLine().toLowerCase();

			while((fileLine = bufferedReader.readLine())!= null){
//				fileLine.toLowerCase();
				String[] s = fileLine.split(",");
				for(int i = 0; i < s.length; i++){
					add(s[i].toLowerCase());
				}
			}
			bufferedReader.close();			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found. Program exited.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Contains: search for the string s 
	 * @param s
	 * @return boolean 
	 */
	protected boolean contains(String s){
		return containsCheck(root.next,s.toCharArray());
	}
	
	/**
	 * Helper function for contains, Search for a word in the dictionary
	 * @param entry
	 * @param charList
	 * @return
	 */
	private boolean containsCheck(TrieEntry[] entry, char[] charList) {
		boolean status= false;  
		for(int i =0; i < charList.length; i++ ){
			if(entry[(int)charList[i]-97] != null){
				if(i == charList.length-1 && entry[(int)charList[i]-97].IsWordEnd == true){
					status = true;
				}
				entry = entry[(int)charList[i]-97].next;
			}
			
		}
		return status;
	}
	
	
	/**
	* function finds the number of words that start with the given prefix
	*@param  word (prefix given by user)
	*/
	int prefix(String word) {
		TrieEntry[] entry = root.next;
		word = word.toLowerCase();
		char[] charList = word.toCharArray() ;
		for(int i =0; i < charList.length; i++ ){
			if(entry[(int)charList[i]-97] != null){
				if(i == charList.length-1 && entry[(int)charList[i]-97].IsWordEnd == true){
					count++;
				}
				entry = entry[(int)charList[i]-97].next;
				
			}
			else{
				return 0;
			}
		}
		countChildEntry(entry);
		System.out.println(count);
		return count;		
	}
	
	/**helper function which will find the branching nodes
	 * @param entry
	 */
	private void countChildEntry(TrieEntry[] entry) {
		for(int i = 0; i < entry.length-1 ; i++){
			if(entry[i]!= null){
				if(entry[i].IsWordEnd)
					count++;
				countChildEntry(entry[i].next);
			}
		}		
	}
	
	/**Class defines the trie datastructure
	 *@author shweta
	 *
	 */
	public static class TrieEntry{
		boolean IsWordEnd;
		private  TrieEntry[] next;
		
		/**
		 * Constructor for TrieEntry
		 */
		TrieEntry(){
			IsWordEnd= false;
		    next = new TrieEntry[26];		    
		 }
		
	   /**
	    * Constructor for child node.
	    */
	   TrieEntry(char c)
	   {
	      this();
	   }
	   
	   /**
	    * Constructor for child node.
	    */
	   TrieEntry(char c, boolean IsWordEnd)
	   {
	      this();
	      this.IsWordEnd = IsWordEnd;
	   }
	}
}
