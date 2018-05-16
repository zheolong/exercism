#include<iostream>
#include<vector>
#include<algorithm>
#include <algorithm>
#include <vector>

namespace {
  struct f {
    void operator()(int) {
      // do something
    }
  };
}

void func(std::vector<int>& v) {
  f f;
  std::for_each(v.begin(), v.end(), f);
}
int main() 
{
    std::vector<int> v (1,2);
    //std::for_each(v.begin(), v.end(), std::cout << _1 << std::endl);
    std::for_each(v.begin(), v.end(), [](int d){std::cout << d << std::endl;});
}

/*
void func3(std::vector<int>& v) {
    std::for_each(v.begin(), v.end(), [](int) { });
}
*/
