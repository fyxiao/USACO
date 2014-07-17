/*
ID:
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.*;

/* object type for a mono-chromatic room */
class Rectangle {
    public int llx, lly, urx, ury, color;
    public Rectangle(int x1, int y1, int x2, int y2, int c) {
        llx = x1;
        lly = y1;
        urx = x2;
        ury = y2;
        color = c;
    }
    public int getArea() {
        return (urx-llx)*(ury-lly);
    }
    public boolean isValid() {
     boolean good = true;
     if((urx <= llx) || (ury<=lly)) {
      good = false;
     }
     return good;
    }
}

public class rect1 {
    public static int a, b, n;
    public static ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
    public static ArrayList<Rectangle> finalrects = new ArrayList<Rectangle>();
    public static int[] colorcount = new int[2500+1];
    public static int[][] rectdim;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("rect1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
       
        rectdim = new int[n][5];
        // process data
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(f.readLine());
            for(int j=0; j<5; j++) {
                rectdim[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // initialize finalrects
        Rectangle paper = new Rectangle(0, 0, a, b, 1);
        finalrects.add(paper);
       
        for(int i=0; i<n; i++) {
            int x1 = rectdim[i][0];
            int y1 = rectdim[i][1];
            int x2 = rectdim[i][2];
            int y2 = rectdim[i][3];
            int c = rectdim[i][4];
            Rectangle newrect = new Rectangle(x1, y1, x2, y2, c);
            int size = finalrects.size();
            // go through the current rectangle division
            for(int j=0; j<size; j++) {
                Rectangle cur = finalrects.get(0);
                finalrects.remove(0);
                // intersection logic
                
                if(cur.llx>=x2 || cur.urx<=x1 || cur.lly>=y2 || cur.ury<=y1) {
                	finalrects.add(cur);
                }
                else {
                	Rectangle rect1 = new Rectangle(cur.llx, cur.lly, Math.min(cur.urx, x1), cur.ury, cur.color);
                	Rectangle rect2 = new Rectangle(Math.max(x1, cur.llx), Math.max(y2, cur.lly), Math.min(x2, cur.urx), cur.ury, cur.color);
                	Rectangle rect3 = new Rectangle(Math.max(x2, cur.llx), cur.lly, cur.urx, cur.ury, cur.color);
                	Rectangle rect4 = new Rectangle(Math.max(x1, cur.llx), cur.lly, Math.min(x2, cur.urx), Math.min(y1, cur.ury), cur.color);
                	if(rect1.isValid()) {
                		finalrects.add(rect1);
                	}
                	if(rect2.isValid()) {
                		finalrects.add(rect2);
                	}
                	if(rect3.isValid()) {
                		finalrects.add(rect3);
                	}
                	if(rect4.isValid()) {
                		finalrects.add(rect4);
                	}
                }
            }
            finalrects.add(newrect);
        }
       
        // final tally
        for(int i=0; i<finalrects.size(); i++) {
         Rectangle cur = finalrects.get(i);
         colorcount[cur.color]+=cur.getArea();
        }

        for(int i=1; i<2501; i++) {
            if(colorcount[i]!=0) {
                System.out.println(i + " " + colorcount[i]);
                out.write(i + " " + colorcount[i] + "\n");
            }
        }

        out.close();
        System.exit(0);
    }
}
