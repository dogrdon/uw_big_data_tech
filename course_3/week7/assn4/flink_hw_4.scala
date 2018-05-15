import org.apache.flink.table.api._
import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.sources.CsvTableSource
import org.apache.flink.types.Row

//ref: https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/table/tableApi.html
//ref: // https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/table/sql.html


val tEnv = TableEnvironment.getTableEnvironment(benv)

// ignoreFirstLine if header so columns parse: https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/table/sourceSinks.html
val tbr_data = (CsvTableSource.builder.path("/root/data/teen_birth_rate.csv").fieldDelimiter(",")
					.field("yr", Types.INT)
					.field("state", Types.STRING)
					.field("county", Types.STRING)
					.field("state_fips", Types.STRING)
					.field("county_fips", Types.STRING)
					.field("combined_fips", Types.STRING)
					.field("birth_rate", Types.FLOAT)
					.field("lower_conf_lim", Types.FLOAT)
					.field("upper_conf_lim", Types.FLOAT)
					.ignoreFirstLine
					.build)


tbr_data.getTableSchema
tEnv.registerTableSource("tbrdata", tbr_data)

//a. 
val max_county_yr = tEnv.sqlQuery(s"select county, yr, birth_rate from tbrdata group by county, yr, birth_rate order by birth_rate desc limit 1")
var max_county_yr_res = max_county_yr.toDataSet[Row]

max_county_yr_res.print()


//b.
val max_county = tEnv.sqlQuery(s"select t.county, t.birth_rate from tbrdata t join (select MAX(birth_rate) as br from tbrdata) mx on t.birth_rate = mx.br")
val max_county_res = max_county.toDataSet[Row]

max_county_res.print()
