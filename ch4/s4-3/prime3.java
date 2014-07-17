/*
ID:
LANG: JAVA
TASK: prime3
*/

import java.io.*;
import java.util.*;

public class prime3 {
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static ArrayList<Integer>[] onetrie = (ArrayList<Integer>[]) new ArrayList[10];
	static ArrayList<Integer>[] twotrie = (ArrayList<Integer>[]) new ArrayList[10];
	static ArrayList<Integer>[] threetrie = (ArrayList<Integer>[]) new ArrayList[10];
	static ArrayList<String> works = new ArrayList<String>();
	static int sum, corner;
	static int[] square = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("prime3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prime3.out")));
        String[] tokens = f.readLine().split(" ");
        sum = Integer.parseInt(tokens[0]);
        corner = Integer.parseInt(tokens[1]);
        // initialize
        for(int i=0; i<10; i++) {
        	onetrie[i] = new ArrayList<Integer>();
        	twotrie[i] = new ArrayList<Integer>();
        	threetrie[i] = new ArrayList<Integer>();
        }        
        for(int i=10001; i<100000; i+=2) {
        	if(isPrime(i) && sumDigits(i)==sum) {
        		primes.add(i);
        		onetrie[i/10000].add(i);
        		twotrie[(i/1000)%10].add(i);
        		threetrie[(i/100)%10].add(i);
        	}
        }  
        /*
        for(int i=0; i<10; i++) {
        	for(int j=0; j<twotrie[i].size(); j++) {
        		System.out.println(twotrie[i].get(j));
        	}
        }*/
        // start testing arrangements
        for(int i=0; i<onetrie[corner].size(); i++) {
        	int p1 = onetrie[corner].get(i);
        	square[0] = p1; // diagonal 1
        	for(int j=0; j<threetrie[(p1/100)%10].size(); j++) {
        		int p2 = threetrie[(p1/100)%10].get(j);
        		if((p2/10000)%2==1) {
        			square[1]=p2; // diagonal 2
        			for(int k=0; k<onetrie[corner].size(); k++) {
        				int p3 = onetrie[corner].get(k);
        				if(p3%10==p2%10 && (p3/10)%10!=0 && (p3/100)%10!=0 && (p3/1000)%10!=0) {
        					square[2]=p3; // row 1
        					for(int l=0; l<onetrie[(p3/1000)%10].size(); l++) {
        						int p4=onetrie[(p3/1000)%10].get(l);
        						if((p4/1000)%10==(p1/1000)%10 && (p4/10)%10==(p2/1000)%10) {
        							square[3]=p4; // col 2
                					for(int m=0; m<onetrie[(p3/10)%10].size(); m++) {
                						int p5=onetrie[(p3/10)%10].get(m);
                						if((p5/1000)%10==(p2/10)%10 && (p5/10)%10==(p1/10)%10) {
                							square[4]=p5; // col 4
                							int botmid = sum - (p2/10000) - p4%10 - p5%10 - p1%10;
                							if(botmid>0 && botmid<10) {
                								int p6 = (p2/10000)*10000 + (p4%10)*1000 + botmid*100 + (p5%10)*10 + (p1%10);
	                							if(isPrime(p6)) {
	                								square[5]=p6; // row 5
	                	        					for(int n=0; n<onetrie[(p3/100)%10].size(); n++) {
	                	        						int p7=onetrie[(p3/100)%10].get(n);
	                	        						if((p7/100)%10==(p1/100)%10 && (p7%10)==(p6/100)%10) {
	                	        							square[6]=p7; // col 3
	                	                					for(int o=0; o<twotrie[(p4/1000)%10].size(); o++) {
	                	                						int p8=twotrie[(p4/1000)%10].get(o);
	                	                						if((p8/100)%10==(p7/1000)%10 && (p8/10)%10==(p5/1000)%10) {
	                	                							square[7]=p8; // row 2
	                        	                					for(int p=0; p<twotrie[(p4/10)%10].size(); p++) {
	                        	                						int p9=twotrie[(p4/10)%10].get(p);
	                        	                						if((p9/100)%10==(p7/10)%10 && (p9/10)%10==(p5/10)%10) {
	                        	                							square[8]=p9; // row 4
	                        	                							int ll = sum - (p3/10000) - (p8/10000) - (p9/10000) - (p6/10000);
	                        	                							int rr = sum - p3%10 - p8%10 - p9%10 - p6%10;
	                        	                							if(ll>0 && ll<10 && rr>0 && rr<10 && rr%2==1) {
		                        	                							int lm = (p4/100)%10;
		                        	                							int mm = (p7/100)%10;
		                        	                							int rm = (p5/100)%10;
		                        	                							if(ll+lm+mm+rm+rr==sum) {
		                        	                								int testm = ll*10000 + lm*1000 + mm*100 + rm*10 + rr; // midrow
		                        	                								int testl = (p3/10000)*10000+(p8/10000)*1000+ll*100+(p9/10000)*10+(p6/10000);
		                        	                								int testr = (p3%10)*10000 + (p8%10)*1000 + rr*100 + (p9%10)*10+p6%10;
		                        	                								if(isPrime(testm) && isPrime(testl) && isPrime(testr)) {
		                        	                									String output = p3+""+p8+""+testm+""+p9+""+p6;
		                        	                									works.add(output);
		                        	                								}
		                        	                							}
	                        	                							}
	                        	                						}
	                        	                					}
	                	                						}
	                	                					}             	   
	                	        						}
	                	        					}            								
	                							}
                							}
                						}
                					}
        						}
        					}        					
        				}
        			}
        		}
        	}
        }
        Collections.sort(works);
        for(int i=0; i<works.size()-1; i++) {
        	String res = works.get(i);
        	String output = res.substring(0,5)+"\n"+res.substring(5,10)+"\n"+res.substring(10,15)+"\n"+res.substring(15,20)+"\n"+res.substring(20,25)+"\n";
        	out.write(output+"\n");
        }
    	String res = works.get(works.size()-1);
    	String output = res.substring(0,5)+"\n"+res.substring(5,10)+"\n"+res.substring(10,15)+"\n"+res.substring(15,20)+"\n"+res.substring(20,25)+"\n";
    	out.write(output);
        out.close();
        System.exit(0);
    }    
    public static boolean isPrime(int n) {
    	if(n==2) return true;
    	for(int i=2; i<Math.sqrt(n)+1; i++) {
    		if(n%i==0) return false;
    	}
    	return true;
    }
    
    public static int sumDigits(int n) {
    	int sum=0;
    	while(n>0) {
    		sum += n%10;
    		n = n/10;
    	}
    	return sum;
    }
}
