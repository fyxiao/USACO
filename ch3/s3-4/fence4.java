/*
ID: 
LANG: JAVA
TASK: fence4
*/

import java.io.*;
import java.util.*;

class Point {
    double x, y;
    public Point(double a, double b) {
        x=a;
        y=b;
    }
    public String toString() {
        String rep="("+x+", "+y+")";
        return rep;
    }
}

class LineSegment {
    Point a, b;
    public LineSegment(Point one, Point two) {
        a=one;
        b=two;
    }
    public String toString() {
        String rep=a.toString()+" | " + b.toString();
        return rep;
    }
}

class Ray {
    Point source;
    double slope;
}

public class fence4 {
    public static int n, count;
    public static Point person;
    public static Point[] vertices;
    public static LineSegment[] segs;
    public static boolean visible[];
    public static void main(String[] args) throws IOException {
        BufferedReader f=new BufferedReader(new FileReader("fence4.in"));
        PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));
        n=Integer.parseInt(f.readLine());
        StringTokenizer st=new StringTokenizer(f.readLine());
        double x=Integer.parseInt(st.nextToken());
        double y=Integer.parseInt(st.nextToken());
        person=new Point(x, y);
        vertices=new Point[n];
        segs=new LineSegment[n];
        visible=new boolean[n];
        count=0;
        for(int i=0; i<n; i++) {
            st=new StringTokenizer(f.readLine());
            x=Double.parseDouble(st.nextToken());
            y=Double.parseDouble(st.nextToken());
            vertices[i]=new Point(x, y);
        }
        for(int i=0; i<n; i++) {
            segs[i]=new LineSegment(vertices[i], vertices[(i+1)%n]);
        }
        boolean isFence=true;
        // go through all pairs of edges that don't share vertices and check if they intersect
        simpleFence:
        for(int i=0; i<n; i++) {
            for(int j=2; j+i<n; j++) {
                if(!(i==0 && j==n-1)) {
                    if(intersect(segs[i], segs[i+j])) {
                        System.out.println("segments " + i + " and " + (i+j) + " intersect");
                        System.out.println(segs[i]);
                        System.out.println(segs[i+j]);
                        isFence=false;
                        break simpleFence;
                    }
                }
            }
        }
        if(!isFence) {
            System.out.println("NOFENCE");
            out.write("NOFENCE\n");
        }
        else { // have a valid fence
            // first find all unblocked fences in view of person
            for(int i=0; i<n; i++) {
                Point midpoint=new Point((segs[i].a.x+segs[i].b.x)/2, (segs[i].a.y+segs[i].b.y)/2);
                LineSegment rayToMP=new LineSegment(person, midpoint);
                if(!intersectFence(rayToMP, i)) {
                    visible[i]=true;
                    count++;
                }
            }
            // find all fences partially blocked
            for(int i=0; i<n; i++) {

            }
            System.out.println(count);
            out.write(count+"\n");
        }
        out.close();
        System.exit(0);
    }
    // returns if segment X intersects any of the fences other than fence f
    public static boolean intersectFence(LineSegment X, int f) {
        boolean intersect=false;
        for(int i=0; i<n; i++) {
            if(i!=f) {
                if(intersect(X, segs[i])) {
                    intersect=true;
                    break;
                }
            }
        }
        return intersect;
    }

    // returns if segments X and Y intersect
    public static boolean intersect(LineSegment X, LineSegment Y) {
        if(!sameSide(X.a, X.b, Y) && !sameSide(Y.a, Y.b, X)) {
            return true;
        }
        else {
            return false;
        }
    }

    // returns if points M and N are on the same side of line L
    public static boolean sameSide(Point M, Point N, LineSegment L) {
        double mCross=crossProduct(M.x-L.a.x, M.y-L.a.y, 0, L.b.x-L.a.x, L.b.y-L.a.y, 0);
        double nCross=crossProduct(N.x-L.a.x, N.y-L.a.y, 0, L.b.x-L.a.x, L.b.y-L.a.y, 0);
        if(mCross>0 && nCross>0) {
            return true;
        }
        else if(mCross<0 && nCross<0) {
            return true;
        }
        else {
            return false;
        }
    }

    // return the z-component of cross product of two vectors (ax, ay, az) and (bx, by, bz)
    public static double crossProduct(double ax, double ay, double az, double bx, double by, double bz) {
        return ax*by-ay*bx;
    }
}
