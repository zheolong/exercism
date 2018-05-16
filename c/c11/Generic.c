#define cbrt(x) _Generic((x), long double: cbrtl, \
                              default: cbrt, \
                              float: cbrtf)(x)

int main()
{
}
