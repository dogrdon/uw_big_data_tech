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

val df = spark.read.format("csv").option("header", "true").option("mode", "DROPMALFORMED").load("hdfs://sandbox.hortonworks.com:8020/tmp/home_data.csv")
val res = df.filter(df("yr_built") < 1979)
res.count //11991

//5.

val res = df.filter("sqft_lot > (sqft_living*3)") //change these
res.count //14267
res.write.csv("hdfs://sandbox.hortonworks.com:8020/tmp/home_data_large_lots.csv")

//Bonus:

//1.
val zip_df = spark.read.format("csv").option("header", "true").option("mode", "DROPMALFORMED").load("hdfs://sandbox.hortonworks.com:8020/tmp/wa_zipcodes.csv")
val join_df = df.join(zip_df, df.col("zipcode") === zip_df.col("Zipcode"))
val res = join_df.filter(join_df("City")==="Seattle")
res.count //8977

//2.
val map_rdd = df.rdd.map(row=>(row.toSeq.head.toString, row.toSeq.tail.toList)) //something like this
map_rdd.take(3)

