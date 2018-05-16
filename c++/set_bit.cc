/*
 * 蚂蚁金服面试题
 * 二）对于一个数组 char array[N]，实现一个函数能对其任意第m bit位 置1
 */

#include<iostream>

const int LEN = 100;

void set_nth_bit_set(char* c, int n) {
    static char mask[] = {128, 64, 32, 16, 8, 4, 2, 1};
    *c |= mask[n];
}

bool set_bit(char *array, int len, int bit_pos)
{
    bit_pos--;
    if (bit_pos < 0)
        return false;

    int m = bit_pos >> 3;
    int n = bit_pos % 8;

    if (m > len) 
        return false;

    set_nth_bit_set(&array[m], n);

    return true;
}

int main()
{
    char array[LEN + 1] = "I am qjl";
    int n = 9;

    std::cout << "Original array: " << array << std::endl;
    if (set_bit(array, strlen(array), n))
        std::cout << "Set " << n << "th bit as 1: " << array << std::endl;

    return 0;
}
