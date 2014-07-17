/*
ID: 
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;

public class contact {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("contact.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        StringBuffer noise = new StringBuffer();

        int a, b, n;
        StringTokenizer st = new StringTokenizer(f.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        while(f.ready()) {
            noise.append(f.readLine());
        }

        int[][] count = new int[4096][12];
        int length = noise.length();

        // move tracker through sequence
        for(int i=0; i<length-a+1; i++) {
            // look at each char sequence of length j
            for(int j=a; j<=b; j++) {
                // check if char at (i+j)th index is in range
                if(i+j-1 < length) {
                    int output = Integer.parseInt(noise.substring(i, i+j), 2);
                    count[output][j-1]++;
                }
            }

        }

        // output the n highest frequencies
        ArrayList<Integer> seen = new ArrayList<Integer>();
        for(int i=0; i<n; i++) {
            int maxfreq = 0;
            ArrayList<String> patterns = new ArrayList<String>();
            // go through array
            for(int k=a; k<=b; k++) {
                for(int j=0; j<4096; j++) {
                    if(count[j][k-1]==maxfreq) {
                        patterns.add(j + "/" + k);
                    }
                    else if(count[j][k-1]>maxfreq && !seen.contains(count[j][k-1])) {
                        maxfreq = count[j][k-1];
                        patterns.clear();
                        patterns.add(j + "/" + k);
                    }
                }
            }
            if(maxfreq==0) {
                break;
            }
            else {
                seen.add(maxfreq);
                System.out.println(maxfreq);
                out.write(maxfreq + "\n");
                for(int m=0; m<patterns.size(); m++) {
                    StringTokenizer st2 = new StringTokenizer(patterns.get(m), "/");
                    int pattern = Integer.parseInt(st2.nextToken());
                    int len = Integer.parseInt(st2.nextToken());
                    String output = Integer.toBinaryString(pattern);
                    // pad with zeroes if necessary
                    if(output.length()<len) {
                    	int oldlen = output.length();
                        for(int p=0; p<len-oldlen; p++) {
                            output = "0" + output;
                        }
                    }
                    if((m+1)%6==0 || m == patterns.size()-1) {
                        System.out.println(output);
                        out.write(output + "\n");
                    }
                    else {
                        System.out.print(output + " ");
                        out.write(output + " ");
                    }
                }
            }
        }


        out.close();
        System.exit(0);
    }

}