Project Assn Notes
==================

### Dataset shortlist

- Trademark cases https://www.kaggle.com/uspto/us-trademark-case-files-18702016/data
	- Fuller dataset: https://www.uspto.gov/learning-and-resources/electronic-data-products/trademark-case-files-dataset-0
	- Case-file documentation (codes): https://www.uspto.gov/sites/default/files/products/tmdailyapp-documentation.pdf
	- USPTO Design Search code: p.11:fn37
	- Goods and services identifications: p.12:fn40
	- Searchable db of trademark case files: p.34:fn158
	- http://www.trademarkandcopyrightlawblog.com/2016/05/alcohol-trademarks-of-the-1800s-california-uncorks-the-past/
	- http://www.letterology.com/2011/05/early-20th-century-trade-marks.html

- Patent litigations: https://www.kaggle.com/uspto/patent-litigations/data
	- Note: Can't seem to link cases back to the actual patents...
	- Article on rise of patent litigation https://www.uspto.gov/learning-and-resources/ip-policy/economic-research/drastic-rise-patent-litigation-2000-2015
	- Other patent office bulk data: https://www.uspto.gov/learning-and-resources/bulk-data-products


### Reminders

- Use `broadcast` and `persist` to better manage memory usage.
- Check on notes for other approaches
- Raw RDDs?

### Questions

- Patents:
	- do patent trolls emerge?
	- does classifying docs by desc tell us anything?
	- Most litigious parties
	- what are the most litigious years (though by the literature, it seems like 2000 - Present are the most because of software patents)
	- Possible to identify patents?
	- Is there a graph to be had anywhere? Plaintifs and defendants.
	- Text processing?

- Trademarks:
	- Types of trademarks over time
	- Really look at the event table for trends
	- Events and owners
	- Is there a graph to be had anywhere?
		- Graph ownership changes
		- over time...
	- Do mark lengths vary overtime?
	- Do we see another increase in service tradermarks much like the 1990's dotcom boom?
	- Types of owners: less corporations
	- Add design_search & classification?
	- Statements?
	- What was that set of information that examiners use to identify marks that are the same (can automate?)
	- Buckets:
		- 1870-1962
		- 1962-1977
		- 1977-1982
		- 1982-2016
