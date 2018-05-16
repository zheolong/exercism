#include<iostream>
// function to call if operator new can't allocate enough memory 
void outOfMem()
{
    std::cerr << "Unable to satisfy request for memory\n";
    std::abort(); 
}

int main() {
    std::set_new_handler(outOfMem);
    int *pBigDataArray = new int[10000000000L];
}
