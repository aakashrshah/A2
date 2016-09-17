import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class P2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		// Read file for data set
		File file = new File("bin/data.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		List<Integer> lst = new ArrayList<Integer>();
		String numb = null;
		
		while( (numb = br.readLine()) != null){
			lst.add(Integer.valueOf(numb));
		}
		
		
		System.out.println(lst);
		lst = quicksort(lst,0,(lst.size()-1));
		System.out.println(lst);
	}

	private static List<Integer> quicksort(List<Integer> lst, int beg, int end) {
		List<Integer> stack = new ArrayList<Integer>();
		stack.add(beg);
		stack.add(end);
		while(!stack.isEmpty()){			
			end = stack.remove(stack.size() - 1 );
			beg = stack.remove(stack.size() - 1 );
			
			int splitArray = iterativeSort(lst,beg,end);
			
			if (splitArray - 1 > beg){
				stack.add(beg);
				stack.add(splitArray - 1);
			}
			
			if(splitArray + 1 < end){
				stack.add(splitArray + 1);
				stack.add(end);
			}
			
//			System.out.println(stack);
		}
		return lst;	
	}


	private static int iterativeSort(List<Integer> lst, int beg, int end) {
		int pivot = beg;
		boolean tr = false;	
		
		while(!tr){
			
			// Change index for same numbers at different position
			// Ignore same indexes and same numbers.
			if(lst.get(beg).equals(lst.get(pivot)) && (beg != pivot)){
				beg++;
			}
			
			// When Pivot is at end.
			while(lst.get(beg) < lst.get(pivot)){
				beg++;
			} 
			
			// Change index for same numbers at different position
			// Ignore same indexes and same numbers.
			if(lst.get(end).equals(lst.get(pivot)) && (end != pivot)){
				end--;
			}
			
			// When Pivot is at beginning.
			while(lst.get(pivot) < lst.get(end)){
				end--;
			}
			
			if(beg >= end){
				tr = true;
			}
			else{
				// Swap
				int temp = lst.get(beg);
				lst.set(beg , lst.get(end));
				lst.set(end, temp);
				if(pivot == end){
					pivot = beg;
				}
				else{
					pivot = end;
				}
//				System.out.println(lst);
			}
		}
//		System.out.println(lst);
		return pivot;
	}
}
