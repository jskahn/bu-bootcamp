 #include <stdio.h>

void swap(int *a, int *b) {
  printf("Before swap: x = %d, y = %d\n", *a, *b);

  int temp = *b;
  *b = *a;
  *a = temp;

  printf("After swap: x = %d, y = %d\n", *a, *b);
}

int main() {
  int a = 10;
  int b = 20;

  swap(&a, &b);

  return 0;
}
