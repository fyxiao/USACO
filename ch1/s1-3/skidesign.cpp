/*
 ID:
 PROG: skidesign
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>

using namespace std;

#define MAX_COST 10000000

int N;
vector<int> hills;

int computeCost(int lo, int hi) {
    int cost = 0;
    for (int i = 0; i < N; i++) {
        if (hills[i] < lo)
            cost += pow(lo - hills[i], 2);
        else if (hills[i] > hi)
            cost += pow(hills[i] - hi, 2);
    }
    
    return cost;
}

int main() {
    ifstream fin ("skidesign.in");
    ofstream fout ("skidesign.out");
    
    fin >> N;
    
    for(int i = 0; i < N; i++) {
        int h;
        fin >> h;
        hills.push_back(h);
    }
    
    int res = MAX_COST;
    
    for (int lo = 0; lo <= 93; lo++) {
        for (int hi = lo; hi <= lo + 17; hi++) {
            int cost = computeCost(lo, hi);
            res = min(res, cost);
        }
    }
    
    fout << res << endl;
    
    return 0;
}
