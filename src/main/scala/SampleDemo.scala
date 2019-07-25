import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SampleDemo {
  def main(args: Array[String]): Unit = {

    // Create Spark Config
    val sparkConf = new SparkConf().
      setAppName("cloudera_quickstart_docker_demo").
      setMaster("local[*]")

    // Create Spark Session
    val sparkSession = SparkSession.
      builder.
      config(sparkConf).
      enableHiveSupport.
      getOrCreate

    // Execute query
    sparkSession.
      sql("SHOW DATABASES").
      show(100, false)

  }
}
