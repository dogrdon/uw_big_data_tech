Emerging Technologies Class 9
==============================


## Tensorflow

- Started at google
- Was integrated into all of their products


### Tensors

- n-dimensional mathematical objects
- vector = 1d tensor
- matrix = 2d tensor
- whatever else = nd tensor
- 3d, an array of matrices

### Neural nets

- not new, 
- making a resurgence
- have become important again bc computing power is good enough now
- nueral net is multiple math functions
- based on output of mathematical functions "nueron" fires or not
- layers take output of the layer before it

- 2 types of functions
	- linear
	- non linear (activation function), _combination of pixels as a cat is not linear_


- final output is a prediction of an outcome
- the more functions you comhbine, the more realistic the output will be.

- "don't use Tanh, it's not the 90s"

### Technical details

- model files
	- keras = h5 file (hierarchical data file)
	- tensorflow = protobuf file (serialized structured data - language nuetral)
	
### TensorFlow Serving API
- continuous training pipelines
- best to do this in a docker container
- model takes transformed, munged data (e.g., words have to be numbers)
- TensorFlow can take batch and streaming data

#### Servables

- Models = one or more servables
- and ML Model can have one or more algorithms
- TensorFlow serving can be parallelized
- Loader API (managing servables?)
- bazel in docker with kubernetes
- inception model
	- can take a big stream of images and give predictions in real time
	- 


**You can't use TensorFlow with Spark?**