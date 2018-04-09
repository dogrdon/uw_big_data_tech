Emerging Technologies Class 1
==============================

### Project

- proposal due May 2
- end to end data pipeline, collect and analyze towards a business problem
- can present 2 weeks before the last class (which is June 6)


### Course set up

- 6/10 attendance
- 65% is passing

### Hypothesis Testing & Linear Regression 

- Binomial Dist

	- # of successes in n trials


- Normal Dist

- Degrees of freedom: the number of ___ in a system that are free to vary

- t Dist: 

	- used to define the normal distribution?

<SEE THE SLIDES FOR ABOVE>


#### Hypothesis Testing:

- initial belief = null hypothesis
- alternative hypothesis = rival to null hypothesis
- Mean = 0 or Mean is not 0
- "If probability is small, below a threshold, then we reject the null hypothesis " it was only due to random chance.

- Type I and Type II errors

- T-test:

	-  diff between observed mean and mean of the theoretical distribution
	- divide distance by sample std. dev
	- p-value = probability that t statistic is less than <SEE slides>
	- compar two samples e.g. weekday v weekend

- Paired data v unpaired:

	- `ttest_rel` in scipy
	- independant - equal variance (original t-test)
	- welches t-test (does python do this? - it's the defualt in R)
	- safer to use welches t test

- AA test = complement to AB testing
- running the same thing at different times but still seeing differences? I don't know

#### Regression analysis

- predictor variables = data used to make a prediction, independant of response

- response vars  = data that we would like to predict - dependant on the predictor

- positive association - both increase
- neg association - both decrease
- linear regression - linear relationship???

- hat means predicted rather than observed

- least sqaures to find the regression equation

- machine learning is optimizing loss functions -> sum of squares and residuals , loss function

- look at r-squared  for accuracy (0.9 means accurate, explains variation). lower is worse

### Data sources & Notebooks



### Week 1 notebook

- only use linear regression where it is welcome, won't fit for every analysis

- why was equal_var set to false in week1 notebook? Becuase we don't know the relationship between the variables...

- small p-values mean reject null hypothesis

- Trade offs:
	- lots of variables = more accurate, better r-squared, but might be too resource instensive.
	- use less variables, get similar results, save resources

- **multi-colinearity**:
	- model will not be accurate
	- ranking, everything becomes zero...
	- 

- if you use dummy varibles, you have to get rid of one otherwise you get multi-colinearity (so get rid of infant)

- intercepts?

- mlstudio?
