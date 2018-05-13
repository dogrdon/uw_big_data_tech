	Emerging Technologies Class 6
==============================


### Streaming and Flink

#### Flink

- Handles streaming and batch in streaming system
- Spark handles streaming (microbatch) and batch in a batch system
- Hadoop -> Tez -> Spark -> Flink
- Flink has cyclic dataflows
- Flink lower latency than spark

- Flink: batch = Dataset, Stream = DataStream
- Datastream = Structured Stream

- Flink also has table?

- High level, but requires a lot more coding than spark. 

#### Flink libraries

- FlinkML (machine learning)
- Table (relational queries)
- Gelly (graph analytics)

#### Flink and kappa

- In flink it's all stream, no batches anywhere

#### Availibility and Fault tolerance

- It can pick up where it left off when things fail (provided some conditions (?) are met)


#### Spark v Flink

- Flink uses `env` rather than `sc` (spark context)
- `senv` for streams `benv` for batch

#### Flink v storm


#### Windowing

- Flink uses watermarks
- Operator, event, ingress (operator and event compromise) time
- synchronize between delta (when things arrive out of order)
- Tumbling window
- sliding window
- session windows (amazon uses these to understand what each customer does in a session)
- "after this watermark, our system is confident we don't need this data anymore"
- global window (custom triggers on how to move it), if nothing defined it's an infinite batch

#### Memory management

- spark has caught up with flink in terms of memory management, so less out of memory errors

### Flink Demo

#### installing flink

- documentation is difficult
- get tar file from flink site
- just put the folder somehwere and run the stuff in bin
- `./bin/start-scala-shell.sh local`

#### Joins

- left, right, and ___ joins are not supported in Flink?
- can do unions, (add one to the other)


#### Data sources

- iris
- read file
- flink doesn't like trailing empty lines (format not same for all rows)
- table api is not built in