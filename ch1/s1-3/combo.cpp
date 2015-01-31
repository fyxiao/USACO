/*
 ID: 
 PROG: combo
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int passDiff(int x[], int y[], int n)
{
    int d = 0;
    for (int i = 0; i < 3; i++) {
        int hi = max(x[i], y[i]);
        int lo = min(x[i], y[i]);
        if (hi - lo > 2 && hi - lo < n-2)
            d++;
    }
    return d;
}

int main() {
    ofstream fout ("combo.out");
    ifstream fin ("combo.in");
    int N;
    int john[3];
    int master[3];
    fin >> N;
    
    for (int i=0; i<6; i++)
        fin >> ((i < 3) ? john[i] : master[i%3]);
    
    // try all N^3 possibilities; N <= 100 so no need to do any pruning, can simply test them all
    int count = 0;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            for (int k = 1; k <= N; k++) {
                int test[3] = {i, j, k};
                if (passDiff(test, john, N) == 0 || passDiff(test, master, N) == 0)
                    count++;
            }
        }
    }
    
    fout << count << endl;
    return 0;
}
