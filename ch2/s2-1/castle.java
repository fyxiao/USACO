/*
ID: 
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

/* object type for a room, which has a variable that keeps track of size of room */
class Room {
	private int size;
	public Room() {
		size = 0;
	}
	public void setSize(int a) {
		size = a;
	}
	public void incrementSize() {
		size++;
	}
	public int getSize() {
		return size;
	}
}

class RoomComparator implements Comparator {
	/* method to compare two fractions in String form */
	public int compare (Object a, Object b) {
		Room r1 = (Room)a;
		Room r2 = (Room)b;
		return ((Room)(a)).getSize() - ((Room)(b)).getSize();
	}
}

/* actual main method */
public class castle {
	
	private static int[][] castle;
	private static int w, h;
	private static ArrayList<Room> rooms = new ArrayList<Room>();
	private static Room[][] roomA;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		w = m;
		h = n;
		castle = new int[n][m];
		roomA = new Room[n][m];
		
		// fill out castle
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0; j<m; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// assign components
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(roomA[i][j] == null) {
					//System.out.println("new component at (" + i + ", " + j + ")");
					Room rm = new Room();
					floodfill(rm, i, j);
					rooms.add(rm);
				}
			}
		}
			
		Collections.sort(rooms, new RoomComparator());
		int maxS = rooms.get(rooms.size()-1).getSize();
		int maxCS = maxS;
		int maxX = -1;
		int maxY = -1;
		String o = "error";
		
		// find wall to remove
		for(int j=0; j<m; j++) {
			for(int i=n-1; i>-1; i--) {
				int x1 = i-1;
				int y1 = j+1;
				int test;
				if(x1>=0) {
					if((castle[i][j]/2)%2 == 1 && roomA[i][j] != roomA[x1][j]) {
						test = roomA[i][j].getSize() + roomA[x1][j].getSize();
						if(test > maxCS) { 
							maxCS = test;
							maxX = i;
							maxY = j;
							o = "N";
						}
					}
				}
				if(y1 < m) {
					if((castle[i][j]/4)%2 == 1 && roomA[i][j] != roomA[i][y1]) {
						test = roomA[i][j].getSize() + roomA[i][y1].getSize();
						if(test>maxCS) { 
							maxCS = test;
							maxX = i;
							maxY = j;
							o = "E";
						}
					}
				}
			}
		}
		
		/*
		System.out.println(rooms.size());
		for(int i=0; i<rooms.size(); i++) {
			System.out.println(rooms.get(i).getSize());
		}*/
		out.write(rooms.size() + "\n");
		out.write(maxS +"\n");
		out.write(maxCS + "\n");
		maxX++;
		maxY++;
		out.write(maxX + " " + maxY + " " + o + "\n");
		
		
		out.close();
		System.exit(0);
	}
	
	public static void floodfill(Room r, int x, int y) {
		if(x < 0 || x >= h) { return; }
		if(y < 0 || y >= w) { return; }
		if(roomA[x][y] == null) {
			roomA[x][y] = r;
			r.incrementSize();
			// west wall
			if(castle[x][y]%2 == 0) { 
				floodfill(r, x, y-1);
			}
			// north wall
			if((castle[x][y]/2)%2 == 0) { 
				floodfill(r, x-1, y); 
			}
			// east wall
			if((castle[x][y]/4)%2 == 0) { 
				floodfill(r, x, y+1); 
			}
			// south wall
			if((castle[x][y]/8)%2 == 0) { 
				floodfill(r, x+1, y); 
			}
		}
	}
}
