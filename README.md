# LabSeq Challenge

Implement a REST service, using a Java framework, returning the value from the `labseq` sequence.

Optionally implement a simple JavaScript Web GUI to invoke the service.

---
[Quarkus](https://quarkus.io) is suggested, but not mandatory.

[Angular](https://angular.io/) is suggested, but not mandatory.

---

## Specification

The `labseq – l(n)`  sequence is defined as follows:

```[]

n=0 => l(0) = 0

n=1 => l(1) = 1

n=2 => l(2) = 0

n=3 => l(3) = 1

n>3 => l(n) = l(n-4) + l(n-3)

```

Example of the first sequence values:

0

1

0

1

1

1

1

2

2

2

3
[…]

The endpoint created should be in the form `<baseurl>/labseq/{n}` where `{n}` represents the index of the sequence’s (single) value to return.

The index may be any non-negative integer number.

The implemented service should use a caching mechanism to take advantage of previous calculations to speed up future calculations.

This caching mechanism must be used in the algorithm’s intermediate calculations (if applicable), and not only in the endpoint’s invocations.

The answer must include:

- Source code
- REST API documentation – Open API format (Swagger)
- Execution instructions (containers or other)

Java code development best practices will be considered in the evaluation of the proposed resolution.

Calculation performance is also a plus. Calculation of `labseq(10000)` must be correctly returned under **10 seconds**.

If there are any doubts regarding specific issues of the exercise that may influence its implementation, the applicant must make assumptions and implement the exercise according to them (these assumptions should be included in the answer).

## Implementation

There are 2 implementations available, on different branches:

- Quarkus with Angular in branch [quarkus](https://github.com/apeixinho/labseq-challenge/tree/quarkus)
- Spring Boot with Angular in branch [springboot](https://github.com/apeixinho/labseq-challenge/tree/springboot)

More documentation available in corresponding branches.
