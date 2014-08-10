/*
ID: 
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.StringTokenizer;

public class milk2 
{
	private static class Event implements Comparable<Event>
	{
		public int time, delta;
		public Event(int t, int d) 
		{
			time = t;
			delta = d;
		}

		public int compareTo(Event e) 
		{
			int time2 = e.time;
			if (time != time2)
				return time - time2;
			else {
				if (delta < 0)
					return 1; // break ties in favor of +1's
				else
					return -1;
			}
		}
	}
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("milk2.out")));
        
        int N = Integer.parseInt(f.readLine());
        Event[] events = new Event[2*N];
        
        for (int i=0; i<N; i++) {
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	events[2*i] = new Event(Integer.parseInt(st.nextToken()), 1);
        	events[2*i+1] = new Event(Integer.parseInt(st.nextToken()), -1);
        }
        Arrays.sort(events);
        
        int busy = 0, idle = 0, cows = 0, busyStart = events[0].time, 
        		idleStart = busyStart;
        
        for (int i=0; i<2*N; i++) {
        	Event e = events[i];
        	cows += e.delta;
        	if (cows == 1 && e.delta==1) {
        		busyStart = e.time;
        		idle = Math.max(idle, busyStart - idleStart);
        	} else if (cows == 0 && e.delta == -1) {
        		idleStart = e.time;
        		busy = Math.max(busy, idleStart - busyStart);
        	}
        }
        
        out.write(busy + " " + idle + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
	
	/* Alternative solution in this commented block, simply comment out the
	 * current code and uncomment this block.
	public static class Interval implements Comparable {
		public int l, r;
		public Interval(int lo, int hi) {
			l = lo;
			r = hi;
		}
		
		@Override
		public String toString() {
			return "[" + l + "," + r + "]";
		}

		@Override
		public int compareTo(Object obj) {
			return l - ((Interval)obj).l;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("milk2.out")));

        int N = Integer.parseInt(f.readLine());
        Interval[] ivs = new Interval[N];
        for (int i=0; i<N; i++) {
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	ivs[i] = new Interval(Integer.parseInt(st.nextToken()), 
        			Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(ivs);
        
        ArrayList<Interval> sortedIvs = new ArrayList<Interval>();
        
        for (int i=0; i<N; i++) {
        	Interval i1 = ivs[i];
        	boolean combined = false;
        	for (int j=0; j<sortedIvs.size(); j++) {
        		Interval i2 = sortedIvs.get(j);
        		if (i1.l <= i2.r) {
        			sortedIvs.remove(j);
        			sortedIvs.add(new Interval(i2.l, Math.max(i2.r, i1.r)));
        			combined = true;
        			break;
        		}
        	}
        	if (!combined)
        		sortedIvs.add(i1);
        }
        
        int busy = 0, idle = 0, prev = sortedIvs.get(0).l;
        for (int i=0; i<sortedIvs.size(); i++) {
        	Interval iv = sortedIvs.get(i);
        	busy = Math.max(busy, iv.r - iv.l);
        	idle = Math.max(idle, iv.l - prev);
        	prev = iv.r;
        }
        out.write(busy + " " + idle + "\n");
        
        f.close();
        out.close();
        System.exit(0);
    }
    */
}
