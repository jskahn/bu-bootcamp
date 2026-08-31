#include <stdio.h>

void swap(int *a, int *b) {
  int temp = *b;
  *b = *a;
  *a = temp;
}

void broken_swap(int a, int b) {
  int temp = b;
  b = a;
  a = temp;
}

int main() {
  int a = 10;
  int b = 20;

  printf("Before swap: x = %d, y = %d\n", a, b);
  swap(&a, &b);
  printf("After swap: x = %d, y = %d\n", a, b);

  printf("\n---- broken swap ----\n");
  printf("Before swap: x = %d, y = %d\n", a, b);
  broken_swap(a, b);
  printf("After swap: x = %d, y = %d\n", a, b);
  return 0;
}
