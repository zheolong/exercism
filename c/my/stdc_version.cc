int main() {
#if defined(__STDC_VERSION__) && __STDC_VERSION__ >= 199409L
printf("C95 compatible source code.")
#elif defined(__ANSI__)
printf("C89 compatible source code.")
#endif
}
