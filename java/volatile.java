private static long value1;
  private static volatile long value2;

  public static void increment1() {
    while (value1 < 100000000L) {
      value1++;
    }
  }

  public static void increment2() {
    while (value2 < 100000000L) {
      value2++;
    }
  }

  public static void main(String[] args) {
    long start1 = System.currentTimeMillis();
    increment1();
    long end1 = System.currentTimeMillis();
    System.out.println("increment1 cost: " + (end1 - start1));
    
    long start2 = System.currentTimeMillis();
    increment2();
    long end2 = System.currentTimeMillis();
    System.out.println("increment2 cost: " + (end2 - start2));

  }
