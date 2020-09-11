#include <stdio.h>
#include <malloc.h>
#include <libjpg>

int main() {
    short *arr = malloc(sizeof(short ) * 1000);
    FILE *f = fopen("picture.jpg", "rb");
    printf("%llu\n", fread(arr, 1000, 1, f));
    fclose(f);
    for (int i = 0; i < 1000; ++i) {
        printf("%d ", *(arr+i));
    }
    free(arr);
    return 0;
}
