Assignment 2
============

## 1. 

### Result

| home_data.id | home_data.date  | home_data.price | home_data.bedrooms | home_data.bathrooms | home_data.sqft_living | home_data.sqft_lot | home_data.floors | home_data.waterfront | home_data.view | home_data.condition | home_data.grade | home_data.sqft_above | home_data.sqft_basement | home_data.yr_built | home_data.yr_renovated | home_data.zipcode | home_data.lat | home_data.long | home_data.sqft_living15 | home_data.sqft_lot15 |
|--------------|-----------------|-----------------|--------------------|---------------------|-----------------------|--------------------|------------------|----------------------|----------------|---------------------|-----------------|----------------------|-------------------------|--------------------|------------------------|-------------------|---------------|----------------|-------------------------|----------------------|
| 6762700020   | 20141013T000000 | 7700000         | 6                  | 8.0                 | 12050                 | 27600              | 2                | 0                    | 3              | 4                   | 13              | 8570                 | 3480                    | 1910               | 1987                   | 98102             | 47.6298       | -122.323       | 3940                    | 8800                 |

### Query

	select * 
	from home_data 
	order by price desc 
	limit 1;

### Result

| home_data.id | home_data.date  | home_data.price | home_data.bedrooms | home_data.bathrooms | home_data.sqft_living | home_data.sqft_lot | home_data.floors | home_data.waterfront | home_data.view | home_data.condition | home_data.grade | home_data.sqft_above | home_data.sqft_basement | home_data.yr_built | home_data.yr_renovated | home_data.zipcode | home_data.lat | home_data.long | home_data.sqft_living15 | home_data.sqft_lot15 |
|--------------|-----------------|-----------------|--------------------|---------------------|-----------------------|--------------------|------------------|----------------------|----------------|---------------------|-----------------|----------------------|-------------------------|--------------------|------------------------|-------------------|---------------|----------------|-------------------------|----------------------|
| 3421079032   | 20150217T000000 | 75000           | 1                  | 0.0                 | 670                   | 43377              | 1                | 0                    | 0              | 3                   | 3               | 670                  | 0                       | 1966               | 0                      | 98022             | 47.2638       | -121.906       | 1160                    | 42882                |

### Query

	select * 
	from home_data 
	order by price asc 
	limit 1;


## 2.

### Result

| avg_price | zipcode |
|:---------:|---------|
| 2160606.6 | 98039   |

### Query

	select avg(price) as avg_price, zipcode 
	from home_data 
	group by zipcode 
	order by avg_price desc limit 1;


## 3.

### Result

| total_pre_1979 |
|:--------------:|
|     11991      | 

### Query

    select count(*) as total_pre_1979 
    from home_data 
    where yr_built < 1979;

## 4. 

### Result

| sold_in_seattle |
|-----------------|
| 8977            |

### Query

	select count(*) as sold_in_seattle
	from home_data hd 
	join wa_zipcodes wa on hd.zipcode=wa.zipcode 
	where wa.city="Seattle";

## 5.

### Result

See attached file: **bdt_uw_assn_2_asgor_20171017_05.csv**

### Query

	select top_price, zip, city 
	from (
		  select hd.price as top_price, hd.zipcode as zip, wa.city as city, 
		  rank() over ( partition by wa.city order by hd.price desc) as rank 
		  from home_data hd
		  join wa_zipcodes wa on hd.zipcode=wa.zipcode 
		 ) t 
	where rank = 1
	order by top_price desc;


## Bonus 1.

### Result

| avg_price_sqft     |
|--------------------|
| 264.15659380747553 |

### Query

	select AVG(res.psqft) as avg_price_sqft
	from (
		select (price/sqft_living) psqft 
		from home_data
	) res;


## Bonus 2.

### Result

| uniq_dates |
|------------|
| 2014-05-02 |
| 2014-05-03 |
| 2014-05-04 |
| 2014-05-05 |
| 2014-05-06 |
| 2014-05-07 |
| 2014-05-08 |
| 2014-05-09 |
| 2014-05-10 |
| 2014-05-11 |
| 2014-05-12 |
| 2014-05-13 |
| 2014-05-14 |
| 2014-05-15 |
| 2014-05-16 |
| 2014-05-17 |
| 2014-05-18 |
| 2014-05-19 |
| 2014-05-20 |
| 2014-05-21 |
| 2014-05-22 |
| 2014-05-23 |
| 2014-05-24 |
| 2014-05-25 |
| 2014-05-26 |
| 2014-05-27 |
| 2014-05-28 |
| 2014-05-29 |
| 2014-05-30 |
| 2014-05-31 |
| 2014-06-01 |
| 2014-06-02 |
| 2014-06-03 |
| 2014-06-04 |
| 2014-06-05 |
| 2014-06-06 |
| 2014-06-07 |
| 2014-06-08 |
| 2014-06-09 |
| 2014-06-10 |
| 2014-06-11 |
| 2014-06-12 |
| 2014-06-13 |
| 2014-06-14 |
| 2014-06-15 |
| 2014-06-16 |
| 2014-06-17 |
| 2014-06-18 |
| 2014-06-19 |
| 2014-06-20 |

### Query

    select distinct(
    	from_unixtime(unix_timestamp(
    		SUBSTR(`date`,1,8), 'yyyyMMdd'),'yyyy-MM-dd')
    	) as uniq_dates
    from home_data;

