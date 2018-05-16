#include <iostream>
#include <cstdio>
#include <fstream>
#include <string>
using namespace std;
 
 
void process(string* line) {
    cout << "line read: " << *line << endl;
    }
 
 
int main(int argc, char* argv[]) {
    string line;
    if(argc != 2) {
        cerr << "One argument is required." << endl;
        return 1;
        }
    string filename(argv[1]);
    cout << "* trying to open and read: " << filename << endl;
    ifstream f (argv[1]);
    // After this attempt to open a file, we can safely use perror() only  
    // in case f.is_open() returns False.
    if (!f.is_open())
        perror(("error while opening file " + filename).c_str());
    // Read the file via std::getline(). Rules obeyed:
    //   - first the I/O operation, then error check, then data processing
    //   - failbit and badbit prevent data processing, eofbit does not
    while(getline(f, line)) {
        process(&line);
        }
    // Only in case of set badbit we are sure that errno has been set in
    // the current context. Use perror() to print error details.
    if (f.bad())
        perror(("error while reading file " + filename).c_str());
    f.close();
    return 0;
}
