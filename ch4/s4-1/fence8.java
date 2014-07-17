/*
ID: 
LANG: JAVA
TASK: fence8
*/

import java.io.*;
import java.util.*;

public class fence8 {
    public static int n, r, boardlen;
    public static int[] boardlens, boards, rails, crlens, wastes;
    public static int[] railcnts=new int[128+1];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fence8.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence8.out")));
        // initialize
        n=Integer.parseInt(f.readLine());
        boards=new int[n];
        boardlens=new int[n];
        boardlen=0;
        for(int i=0; i<n; i++) {
            boardlens[i]=Integer.parseInt(f.readLine());
            boardlen+=boardlens[i];
        }
        r=Integer.parseInt(f.readLine());
        rails=new int[r];
        crlens=new int[r];
        wastes=new int[r];
        for(int i=0; i<r; i++) {
            rails[i]=Integer.parseInt(f.readLine());
            railcnts[rails[i]]++;
        }
        // method relies on arrays being sorted
        Arrays.sort(boardlens);
        Arrays.sort(rails);
        // calculate
        for(int i=0; i<r; i++) {
            if(i==0) { crlens[i]=rails[i]; }
            else { crlens[i]=crlens[i-1]+rails[i]; }
            wastes[i]=boardlen-crlens[i];
        }
        // best=greatest number of rails such that sum(rail lengths)<=sum(board lengths)
        boolean boardover=false;
        int best = 0;
        for(int i=0; i<r; i++) {
            if(crlens[i]>boardlen) {
                best=i;
                boardover=true;
                break;
            }
        }
        if(!boardover) { best=r; }
        // find worst
        int worst = 0;
        int rtracker = 0;
        for(int i=0; i<n; i++) {
        	int boardlen = boardlens[i];
        	if(rtracker>=r || boardlen < rails[rtracker]) {
        		break;
        	}
        	while(rtracker<r && boardlen >=rails[rtracker]) {
        		boardlen -= rails[rtracker];
        		rtracker++;
        		worst++;
        	}
        }
        // run binary search on [lower, upper): lower is known to work, upper don't know
        int lower=worst;
        int upper=best;
        while(upper>lower) {
        	// reset boards array
        	boards=new int[n];
        	for(int i=0; i<n; i++) {
        		boards[i]=boardlens[i];
        	}
        	int test=Math.max((lower+upper)/2, lower+1);
        	boolean result=search(0, test-1, test, 0);
        	//System.out.println("testing " + lower + " " + upper);
        	if(!result) {
        		System.out.println(test + " didn't work");
        		upper=test-1;
        		System.out.println("About to test " + lower + " " + upper);
        	}
        	else {
        		System.out.println(test + " did work");
        		lower=test;
        		System.out.println("About to test " + lower + " " + upper);
        	}
        }

        //System.out.println(upper);
        out.write(lower+"\n");
        out.close();
        System.exit(0);
    }

    // recursive method that tries to cut rail numr [0, k-1] from somewhere
    public static boolean search(int lboard, int numr, int k, int waste) {
    	if(waste>wastes[k-1]) { return false; }
    	else { // cut rails[numr] from board i
    		boolean pos=false;
    		int maxBoard = maxBoard();
    		int prevRem = -1;
    		int prevBoard = -1;
    		for(int i=n-1; i>=0; i--) {
    			if(maxBoard < rails[numr]) {
    				return false;
    			}
    			if(prevBoard!=-1 && boardlens[i]==boardlens[prevBoard]) {
    				return false;
    			}
    			if(boards[i]>=rails[numr]) {
    				if(numr==0) { return true; } // can cut all rails
    				/* if previous rail cut attempt left same remains and didn't work
    				 * then no need to repeat
    				*/
    				else if(boards[i]-rails[numr]!=prevRem) {
    					boards[i]-=rails[numr];
    					prevRem = boards[i];
    					prevBoard = i;
    					if(boards[i]<rails[0]) { waste+=boards[i]; } // wasted board space
    					pos=search(i, numr-1, k, waste);
    					if(boards[i]<rails[0]) { waste-=boards[i]; } // undo
    					boards[i]+=rails[numr]; // undo
    				}
    			}
    			if(pos) { return true; }
    		}
    		return pos; // failed
    	}
    }
    
    // maximum board length left
    public static int maxBoard() {
    	int max = 0;
    	for(int i=0; i<boards.length; i++) {
    		max = Math.max(max, boards[i]);
    	}
    	return max;
    }

}
