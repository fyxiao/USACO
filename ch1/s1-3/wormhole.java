/*
ID: 
LANG: JAVA
TASK: wormhole
*/

/* 1/28/15 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class wormhole {
	private static class WH {
		private int num, x;
		public WH(int n, int xcoord) {
			num = n;
			x = xcoord;
		}
		public int getNum() {
			return num;
		}
		public int getX() {
			return x;
		}
	}
	
	private static int COUNT = 0;
    private static HashMap<Integer, ArrayList<WH>> hm = new HashMap<Integer, ArrayList<WH>>();
    private static int[] xs; // wormhole i has x coordinate xs[i]
    private static int[] ys; // wormhole i has y coordinate ys[i]
	
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        
        // construct the level sets of wormholes
        int N = Integer.parseInt(f.readLine());
        xs = new int[N];
        ys = new int[N];
        for (int i=0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	xs[i] = x;
        	ys[i] = y;
        	if (hm.containsKey(y)) {
        		hm.get(y).add(new WH(i, x));
        	} else {
        		ArrayList<WH> levelSet = new ArrayList<WH>();
        		levelSet.add(new WH(i, x));
        		hm.put(y, levelSet);
        	}
        }
        
        for (int k : hm.keySet()) {
        	Collections.sort(hm.get(k), new Comparator<WH>() {
        		public int compare(WH wh1, WH wh2) {
        			return wh1.getX() - wh2.getX();
        		}
        	});
        	System.out.println("level set for " + k + ": ");
        	for (int j = 0; j < hm.get(k).size(); j++)
        		System.out.print(hm.get(k).get(j).getNum() + " ");
        	System.out.println();
        }
        
        // iterate through all possible pairings (wormhole i is paired with p[i])
        ArrayList<Integer> unpaired = new ArrayList<Integer>();
        for (int i=0; i < N; i++)
        	unpaired.add(i);
        int[] pairing = new int[N];
        generatePairings(unpaired, pairing);
        
        out.write(COUNT + "\n");
        out.close();
        f.close();
        System.exit(0);
    }
    
    public static boolean cycleExists(int[] pairing) {
    	//boolean[] checked = new boolean[pairing.length];
    	for (int i = 0; i < pairing.length; i++) {
    		// start at wormhole i
    		// check if we can enter wormhole i, if it's at (x, y), need
    		// (x-1, y) to be empty
    		int startx = xs[i];
    		int starty = ys[i];
    		boolean pass = true;
    		if (hm.get(starty) != null) {
    			for (int j = 0; j < hm.get(starty).size(); j++) {
    				if (hm.get(starty).get(j).getX() == startx) {
    					pass = false;
    					break;
    				}
    			}
    		}
    		if (pass) continue;
    		
    		// enter wormhole i, see if there's a loop
    		boolean[] visited = new boolean[pairing.length];
    		int cur = i;
    		visited[cur] = true;
    		cur = pairing[cur];
    		while(true) {
    			// travel along the level set
    			ArrayList<WH> levelSet = hm.get(ys[cur]);
    			int j = 0;
    			for (; j < levelSet.size() && cur != levelSet.get(j).getNum(); j++)
    				;
    			if (j == levelSet.size()-1) {
    				break;
    			} else { // travel east to the next wormhole in the level set
    				cur = levelSet.get(j+1).getNum();
    				if (visited[cur]) return true;
    				visited[cur] = true;
    				cur = pairing[cur];
    			}
    		}
    	}
    	return false;
    }
    
    public static void generatePairings(ArrayList<Integer> unpaired, int[] pairing) {
    	if (unpaired.isEmpty()) {
    		if (cycleExists(pairing))
    			COUNT++;
    		return;
    	}
    	
    	int fixed = unpaired.remove(0);
    	int remaining = unpaired.size();
    	for (int i = 0; i < remaining; i++) {
    		int partner = unpaired.remove(0);
    		pairing[fixed] = partner;
    		pairing[partner] = fixed;
    		
    		// recursive call
    		ArrayList<Integer> copy = new ArrayList<Integer>();
    		for (int j=0; j < unpaired.size(); j++)
    			copy.add(unpaired.get(j));
    		generatePairings(copy, pairing);
    		
    		// unwind recursion
    		unpaired.add(partner);
    	}
    	
    }
}
