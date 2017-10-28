//1.

val df = spark.read.format("csv").option("header", "true").option("mode", "DROPMALFORMED").load("hdfs://sandbox.hortonworks.com:8020/tmp/home_data.csv")
val res = df.filter(df("yr_built") < 1979)
res.count //11991

//2.


//3. 

val res = df.select(df("zipcode")).distinct
res


//4. 

val alteredDF = df.drop("sqft_living15").drop("sqft_lot15")
alteredDF.take(3)

//5.

val zip_df = spark.read.format("csv").option("header", "true").option("mode", "DROPMALFORMED").load("GET_FROM_HIVE:/wa_zipcodes.csv")
val join_df = df.join(zip_df, df.col("zipcode") === zip_df.col("Zipcode"))
join_df.take(3)

//Bonus:

//1.


//2.
