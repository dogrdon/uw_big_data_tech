import scala.util.Random

//1.

val lines = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_and_peace.txt")
val lowlines = lines.map {line => line.toLowerCase}
val war_n_peace = lowlines.filter{line => line.contains("war")&&line.contains("peace")}

war_n_peace.count //9 - but look into this

//2.

val sentences = lines.map{line=>line.split(".")}

sentences.count //64920

//3. 

val sample = lines.takeSample(false,500,System.nanoTime.toInt)
sc.makeRDD(sample.toList).saveAsTextFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_n_peace_sample.txt")

// check to make sure we can reload it from hdfs
val sample_lines = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_n_peace_sample.txt")


//4. 

val df = spark.sql("SELECT * FROM csv.`hdfs://sandbox.hortonworks.com:8020/tmp/home_data.csv`")
val res = df.select("_c14 < 1979")
res.count //11991

//maybe try using this for headers: https://www.nodalpoint.com/spark-data-frames-from-csv-files-handling-headers-column-types/

//5.

val res = df.filter("_c6 > (_c5*3)")
res.count //14267
res.write.csv("hdfs://sandbox.hortonworks.com:8020/tmp/home_data_large_lots.csv")

//Bonus:

//1.


//2.