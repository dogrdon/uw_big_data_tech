sqoop list-tables --connect 'jdbc:sqlserver://bigdata220w18.database.windows.net:1433;database=week3' --username 'bigdata' --password '***REMOVED***'


>> TestEmployees
>> product
>> coupon
>> campaign_table
>> campaign_desc
>> coupon_redempt
>> causal_data
>> hh_demographic
>> transaction_data


sqoop import --connect 'jdbc:sqlserver://bigdata220w18.database.windows.net:1433;database=week3' --username 'bigdata' --password '***REMOVED***' --query 'select * from transaction_data where $CONDITIONS' --split-by 'HOUSEHOLD_KEY' --target-dir 'hdfs://sandbox.hortonworks.com:8020/tmp/test_transactions' --as-parquetfile



val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val df = sqlContext.read.parquet("hdfs://sandbox.hortonworks.com:8020/tmp/test_transactions")