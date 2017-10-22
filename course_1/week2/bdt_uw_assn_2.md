Assignment 2
============

1. 

from: https://stackoverflow.com/questions/20642654/get-row-with-max-value-in-hive-sql

Most Expensive

	select * 
	from home_data 
	order by price desc 
	limit 1;

	select * 
	from (
		select *, rank() over (order by price desc) as r from home_data 
		) res 
	where res.r = 1;

Least Expensive

	select * 
	from home_data 
	order by price asc 
	limit 1;


	select * 
	from (
		select *, rank() over (order by price asc) as r from home_data 
		) res 
	where res.r = 1;

2.

Let's look into this one:

	select avg(price) as avg_price, zipcode 
	from home_data 
	group by zipcode 
	order by avg_price desc;


3.

    select count(*) 
    from home_data 
    where yr_built < 1979;

4. 

	select count(*) 
	from home_data hd 
	join wa_zipcodes wa on hd.zipcode=wa.zipcode 
	where wa.city="Seattle";

5.

using: https://community.hortonworks.com/questions/24667/hive-top-n-records-within-a-group.html

	select top_price, zip, city 
	from (
		  select hd.price as top_price, hd.zipcode as zip, wa.city as city, 
		  rank() over ( partition by wa.city order by hd.price desc) as rank 
		  from home_data hd
		  join wa_zipcodes wa on hd.zipcode=wa.zipcode 
		 ) t 
	where rank = 1
	order by top_price desc;


6.

	select AVG(res.psqft) as avg_price_sqft
	from (
		select (price/sqft_living) psqft 
		from home_data
	) res;


7.

--takes a little longer but formats date for easier reading

    select distinct(
    	from_unixtime(unix_timestamp(
    		SUBSTR(`date`,1,8), 'yyyyMMdd'),'yyyy-MM-dd')
    	)
    from home_data;

