int main()
{
    int c = 2;
    int *restrict a = &c;
    int *b = &c;
}
