/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/user/qiaojunlong/input/README.md" // Should be some file on your system
    val conf = new SparkConf().setMaster("yarn-cluster").setAppName("Simple Application")
    val sc = new SparkContext(conf)
    //val logData = sc.textFile(logFile, 2).cache()
    val logData = sc.textFile(logFile, 2)
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    //val numAs = logData.count()
    //val numBs = logData.count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
