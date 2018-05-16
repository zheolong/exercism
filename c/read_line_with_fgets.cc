#include <iostream>
#include <cstdio>
#include <fstream>
#include <string>
using namespace std;

#define LINE_MAX_BYTES 1000
 
void process(char* line) {
    cout << "line read: " << *line << endl;
    }
 
int main(int argc, char* argv[]) {
    if(argc != 2) {
        cerr << "One argument is required." << endl;
        return 1;
    }

    string filename(argv[1]);
    cout << "* trying to open and read: " << filename << endl;

    FILE *f = fopen(argv[1], "r");
    if (!f)
        perror(("error while opening file " + filename).c_str());

    char line[LINE_MAX_BYTES];
    while(fgets(line, LINE_MAX_BYTES, f)) {
        process(line);
    }

    fclose(f);
    return 0;
}
