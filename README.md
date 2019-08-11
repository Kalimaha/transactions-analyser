# Transactions analyser

Please refer to [PROBLEM.md](./PROBLEM.md) for the details about the homework. Please follow the instructions in the 
following sections to build, test and run the project.


### Build

```
./gradlew clean build clean build
```

### Run
Four parametes are required to run the application:

* CSV's filepath
* account ID
* date from
* date to

Parameters must be passed in the correct order. Dates must be escaped with `\"`. Following an example with:

* CSV's filepath: `./src/test/resources/example.csv`
* account ID: `ACC998877`
* date from: `20/10/2018 12:00:00`
* date to: `20/10/2018 19:00:00`

```
./gradlew run --args "./src/test/resources/example.csv ACC998877 \"20/10/2018 12:00:00\" \"20/10/2018 19:00:00\""
```

The expected output is:

```
> Task :run
Relative balance for the period is: $.00
Number of transactions included is: 0
```

### Tests

```
./gradlew clean build test
```

## Potential improvements

* guard against CSV errors
* improve error handling in the `Main` function