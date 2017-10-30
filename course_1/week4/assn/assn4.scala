import spark.implicits._

//1.

case class Home (id:String,
				 date:String,
				 price:Integer,
				 bedrooms:String,
				 bathrooms:String,
				 sqft_living:Integer,
				 sqft_lot:Integer,
				 floors:String,
				 waterfront:Boolean,
				 view:Boolean,
				 condition:Integer,
				 grade:Integer,
				 sqft_above:Integer,
				 sqft_basement:Integer,
				 yr_built:Integer,
				 yr_renovated:Integer,
				 zipcode:String,
				 lat:Double,
				 longitude:Double,
				 sqft_living15:Integer,
				 sqft_lot15:Integer
				)
val home_df = spark.read.
			option("inferSchema", "true").
			option("header", "true").
			csv("hdfs://sandbox.hortonworks.com:8020/tmp/home_data_long_modified.csv").
			as[Home].
			toDF //using ...long_modified because csv would not load with reserved word in header
val res = home_df.filter($"yr_built" < 1979)
res.count 
//res0: Long = 11991

//2.

val res = home_df.
		  groupBy($"zipcode").
		  agg(avg($"price").as("avg_price")).
		  sort($"avg_price".desc)
res.show(1)
//+-------+---------+
//|zipcode|avg_price|
//+-------+---------+
//|  98039|2160606.6|
//+-------+---------+
//only showing top 1 row


//3. 

val res = home_df.select(home_df("zipcode")).distinct
res.show()
//+-------+
//|zipcode|
//+-------+
//|  98002|
//|  98155|
//|  98198|
//|  98146|
//|  98122|
//|  98077|
//|  98006|
//|  98001|
//|  98005|
//|  98112|
//|  98115|
//|  98059|
//|  98075|
//|  98023|
//|  98109|
//|  98136|
//|  98052|
//|  98011|
//|  98014|
//|  98058|
//+-------+
//only showing top 20 rows


//4. 

val alteredDF = home_df.drop("sqft_living15").drop("sqft_lot15")
alteredDF.columns
//res6: Array[String] = Array(id, date, price, bedrooms, bathrooms, sqft_living, sqft_lot, floors, waterfront, view, condition, grade, sqft_above, sqft_basement, yr_built, yr_renovated, zipcode, lat, longitude)

//5.


val zip_df = spark.sql("select * from wa_zipcodes")
val join_df = home_df.join(zip_df, home_df.col("zipcode") === zip_df.col("Zipcode"))
join_df.take(3)

//res2: Array[org.apache.spark.sql.Row] = Array([7129300520,20141013T000000,221900,3,1.0,1180,5650,1.0,0,0,3,7,1180,0,1955,0,98178,47.5112,-122.257,1340,5650,98178,Seattle,Washington], [6414100192,20141209T000000,538000,3,2.25,2570,7242,2.0,0,0,3,7,2170,400,1951,1991,98125,47.721,-122.319,1690,7639,98125,Seattle,Washington], [5631500400,20150225T000000,180000,2,1.0,770,10000,1.0,0,0,3,6,770,0,1933,0,98028,47.7379,-122.233,2720,8062,98028,Kenmore,Washington])

//Bonus:

//1.

import org.apache.spark.sql.functions.udf

case class Airport (ident:String,
					category:String,
					name:String,
					latitude_deg:Double,
					longitude_deg:Double,elevation_ft:Integer,
					continent:String,
					iso_country:String,
					iso_region:String,
					municipality:String,
					gps_code:String,
					iata_code:String,
					local_code:String)

val airports = spark.read.
			option("inferSchema", "true").
			option("header", "true").
			csv("hdfs://sandbox.hortonworks.com:8020/tmp/airport_codes.csv").
			as[Airport].
			toDF

val state_strip: String => String = _.split("-")(1)
val state_stripUDF = udf(state_strip)

val states = airports.select(state_stripUDF(airports.col("iso_region")))

states.show()
//+---------------+
//|UDF(iso_region)|
//+---------------+
//|             PA|
//|             AK|
//|             AL|
//|             AR|
//|             AZ|
//|             CA|
//|             CO|
//|             FL|
//|             FL|
//|             FL|
//|             GA|
//|             GA|
//|             HI|
//|             ID|
//|             IN|
//|             IL|
//|             IN|
//|             IL|
//|             KS|
//|             KY|
//+---------------+
//only showing top 20 rows


//2.

//create rows
val data = (Seq(
	("7838744", "Paul", "Orange", 89),
	("0092829", "Peter", "Blue", 77),
	("0033754", "Mary", "Orange", 91),
	("8837839", "Val", "Green", 95),
	("9873792", "Hirk", "Red", 76)
))

//convert to RDD
val dataRDD = sc.makeRDD(data)

//define schema
case class Datum(
	ident:String,
	name:String,
	color:String,
	score:Integer
)

//map row items to schema and convert to Data Frame
val df = dataRDD.map {
	case (s0,s1,s2,i1) => Datum(s0,s1,s2,i1) }.toDF()

df.show

//+-------+-----+------+-----+
//|  ident| name| color|score|
//+-------+-----+------+-----+
//|7838744| Paul|Orange|   89|
//|0092829|Peter|  Blue|   77|
//|0033754| Mary|Orange|   91|
//|8837839|  Val| Green|   95|
//|9873792| Hirk|   Red|   76|
//+-------+-----+------+-----+
