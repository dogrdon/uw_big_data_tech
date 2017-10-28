import scala.util.Random

//1.

val lines = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_and_peace.txt")
val lowlines = lines.map {line => line.toLowerCase}
val war_n_peace = lowlines.filter{line => line.contains("war")&&line.contains("peace")}

war_n_peace.count //9

//2.

val sentences = lines.map{line=>line.split(".")}

sentences.count //64920

//3. 

val sample = lines.takeSample(false,500,System.nanoTime.toInt)
sc.makeRDD(sample.toList).saveAsTextFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_n_peace_sample.txt")
// check to make sure we can reload it from hdfs
val sample_lines = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/war_n_peace_sample.txt")


//4. 

val home_rdd = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/home_data.csv")
val home_parsed = home_rdd.map(row => row.split(",").map(_.trim))
val home_header = home_parsed.first
val home_data = home_parsed.filter(_(0) != home_header(0))
val yr_built = home_header.indexOf("yr_built")
val res = home_data.filter(row => row(yr_built).toInt < 1979)
res.count //11991

//5.
val sqft_lot = home_header.indexOf("sqft_lot")
val sqft_living = home_header.indexOf("sqft_living")
val res = home_data.filter(row => row(sqft_lot).toInt > (row(sqft_living).toInt * 3))
res.count //14267
val home_rdd_header = sc.parallelize(Array(home_header))
val output = home_rdd_header.union(res)
val list_output = output.map(r=>r.toList)
list_output.saveAsTextFile("hdfs://sandbox.hortonworks.com:8020/tmp/home_data_large_lots_out.csv")
// check to make sure we can reload it from hdfs
val large_lots = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/home_data_large_lots_out.csv")

//Bonus:

//1.
val zip_rdd = sc.textFile("hdfs://sandbox.hortonworks.com:8020/tmp/wa_zipcodes.csv")
val zip_parsed = zip_rdd.map(row => row.split(",").map(_.trim))
val zip_header = zip_parsed.first
val zip_data = zip_parsed.filter(_(0) != zip_header(0))
val zip_map = zip_data.map(row => (row(0), row(1))).collectAsMap
val zip_ind = home_header.indexOf("zipcode")
val zip_processed = home_data.map(row => row :+ zip_map.getOrElse(row(zip_ind).toString, "n/a")) //some reason this does not work
val res = zip_processed.filter(row => row.lastOption == "Seattle") 

res.count //8977 should be the result, but this won't be the outcome due to bug in `zip_processed`

//2.
val map_rdd = home_data.map(row=>(row.toSeq.head.toString, row.toSeq.tail.toList)) //something like this using the home_data rdd from above
map_rdd.take(3)

