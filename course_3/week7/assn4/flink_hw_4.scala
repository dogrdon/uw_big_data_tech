//flink_hw_4.scala

import org.apache.flink.table.api._
import org.apache.flink.table.sources.CsvTableSource
import org.apache.flink.types.Row


val tEnv = TableEnvironment.getTableEnvironment(benv)

val teenpreg = (CsvTableSource.builder.path("~/data/teen_birth_rates.csv").fieldDelimiter(",")
					.field("Year", Types.INT)
					.field("State", Types.STRING)
					.field("County", Types.STRING)
					.field("State FIPS Code", Types.STRING)
					.field("County FIPS Code", Types.STRING)
					.field("Combined FIPS Code", Types.STRING)
					.field("Birth Rate", Types.FLOAT)
					.field("Lower Confidence Limit", Types.FLOAT)
					.field("Upper Confidence Limit", Types.FLOAT)
					.build)

teenpreg.getTableSchema
tEnv.registerTableSource("tpreg", teenpreg)

val highest = tEnv.sqlQuery("select * from tpreg")
val ds = tp_table.toDataSet[Row]

// ERROR!: value toDataSet is not a member of org.apache.flink.table.api.Table

// https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/table/sql.html