/*
ID:
LANG: JAVA
TASK: shuttle
*/

import java.io.*;

public class shuttle {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("shuttle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
        
        int N = Integer.parseInt(f.readLine());
        StringBuilder sb = new StringBuilder();
        int[] wpos = new int[N];
        int[] bpos = new int[N];
        
        // initialize positions of black and white stones
        for(int i=0; i<N; i++) {
        	wpos[i] = i+1;
        	bpos[i] = N+2+i;
        }
        
        int num=0;
        
        // Step 1
        for(int i=1; i<=N; i++) {
        	if(i%2==1) { // move white stones
        		for(int j=N-1; j>=N+1-i; j--) { // hops
        			sb.append(wpos[j]+" ");
        			wpos[j]+=2;
        			if(++num%20==0) {
        				sb.deleteCharAt(sb.length()-1);
        				sb.append("\n");
        			}
        		}
        		sb.append(wpos[N-i] + " ");
        		wpos[N-i]++; // move stone one step
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        	}
        	else { // move black stones
        		for(int j=0; j<i-1; j++) { // hops
        			sb.append(bpos[j]+" ");
        			bpos[j]-=2;
        			if(++num%20==0) {
        				sb.deleteCharAt(sb.length()-1);
        				sb.append("\n");
        			}
        		}
        		sb.append(bpos[i-1]+" ");
        		bpos[i-1]--;
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        	}
        }
        
        // Step 2
        if(N%2==0) { // WBWBWB...WB_
        	for(int i=N-1; i>=0; i--) {
        		sb.append(wpos[i]+" ");
        		wpos[i]+=2;
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        	}
        }
        else { // _WBWBWB..WB
        	for(int i=0; i<N; i++) {
        		sb.append(bpos[i]+" ");
        		bpos[i]-=2;
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        	}   	
        }

        // Step 3
        for(int i=N; i>=1; i--) {
        	if(i%2==1) { // move white stones right
        		sb.append(wpos[i-1]+" ");
        		wpos[i-1]++;
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        		for(int j=i-2; j>=0; j--) { // hops
        			sb.append(wpos[j]+" ");
        			wpos[j]+=2;
        			if(++num%20==0) {
        				sb.deleteCharAt(sb.length()-1);
        				sb.append("\n");
        			}
        		}
        	}
        	else { // move black stones left
        		sb.append(bpos[N-i]+" "); // move black stone one step
        		bpos[N-i]--;
    			if(++num%20==0) {
    				sb.deleteCharAt(sb.length()-1);
    				sb.append("\n");
    			}
        		for(int j=N-i+1; j<N; j++) { // hops
        			sb.append(bpos[j]+" ");
        			bpos[j]-=2;
        			if(++num%20==0) {
        				sb.deleteCharAt(sb.length()-1);
        				sb.append("\n");
        			}
        		}
        	}
        }
        
        sb.setCharAt(sb.length()-1, '\n');
        
        //System.out.println(output.toString());
        out.write(sb.toString());
        out.close();
        System.exit(0);
    }
}
