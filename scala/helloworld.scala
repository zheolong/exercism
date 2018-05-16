object HelloWorld {
  def main(args: Array[String]) {
    var y = (1,'2',3)
    println(y._1, y._2, y._3)
    for (j <- 0 until 1) println(j)
    var x: Seq[String] = Seq("1", "2");
    println(x(1))
    try {
      require('1' == '2', "oops")
    } catch { case e: Exception => print(e.getMessage)
    } 
    println(Seq(1, 2, 3, 4).count(haha _))
    println(Set('1') -- Set('1'))
    println("Hello, world! " + args.toList)
    println(1+2)
    println(args(0))
    var i:Int = 0
    while(i < args.length)
    {
        println(args(i))
        i += 1
    }

    var xs = List(1, 2, 3, 4)

    println("sum:" + sum(xs))
    println(max(xs))
    println(reverse("xs"))
    println(quickSort(List(2,1)))
    println(factorial(5))
    println(countChange(5, List(1,2)))

    args.foreach(arg => println(arg))

    val s = new String("Hello, world!")
    println(s)

    val greetStrings = new Array[String](3)
    
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"
    
    for (i <- 0 to 2)
      print(greetStrings(i))
  }
  def haha(x: Int) = {
    true 
  }
  def sum(xs: List[Int]): Int = {
    xs.foldLeft(0)((sum, x) => sum + x)
  }
  //def sum(xs: List[Int]): Int = {
  //  if (xs.isEmpty) 0 else xs.head + sum(xs.tail)
  //}
  def max(xs: List[Int]): Int = {
    if (xs.isEmpty)
      throw new java.util.NoSuchElementException
    if (xs.size == 1)
      xs.head
    else
      if (xs.head > max(xs.tail))  xs.head else max(xs.tail)
  }
  def reverse(xs: String): String = {
    if (xs.length == 1) xs else reverse(xs.tail) + xs.head
  }
  def quickSort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) xs
    else
      quickSort(xs.filter(x=>x<xs.head)):::xs.head::quickSort(xs.filter(x=>x>xs.head))
  }
  def factorial(n: Int) = {
    def loop(n: Int, acc: Int): Int = {
      if (n == 0) acc
      else 
        loop(n-1, n*acc)
    }
    loop(n, 1) 
  }
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (coins.size == 0 || money < 0) 0
    else 
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
