**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      | 34  |
| -------------- | --- |
| Student Names: | Ryan Huynh  |
|                |Jonas Wong |
|                |  Mason Harris   |
|                |   Mathew Pelletier  |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

In this lab, we expanded our unit test suite from the previous lab, in order to improve branch, statement, and method coverage. We planned and created several tests by implementing white-box test case design, where we reviewed the code for each method and wrote test cases based on the lines/branches/statements we missed in the previous lab.

The JUnit Tests can be found in the JFreeChart_Lab3 folder where the test files are in JFreeChart_Lab3/src/org/jfree/data/test. You can simply run the test as is in Eclipse.

# 2 Manual data-flow coverage calculations for DataUtilities.calculateColumnTotal and Y methods

## Range
![Range CFG](/media/range-cfg.jpg)
### DU-Pairs

    lower = (1,1)

    Upper = (1,1)


### Def-Use Sets Per Statement
du(1,lower) = 

    {[1]}
du(1,upper) = 

    {[1]}
    
### Test-Case DU-Pair Coverage  

All tests will cover every DU pair as the method is a single line with no conditional execution  
Therefore DU pair coverage should be 100%


## DataUtilities
![DataUtilities CFG](/media/data-utilities-cfg.jpg)
### DU-Pairs
| Variable	| Pairs	|
|-------------------|---------|
| data		| (1, 2), (1, 4), (1, 7) |
| column	| (1,7) |
| total		| (3, 11), (9, 11) |
| rowCount	| (4, 6) |
| r		| (5, 6), (5, 7), (5, 10), (10, 6), (10, 7) |
| n		| (7, 8) (7, 9) |
**Total: 14 DU-pairs**

### Def-Use Sets Per Statement
**Statement 1**
du(1, data)=
{[1, 2],
[1, 2, 3, 4],
[1, 2, 3, 4, 7]}

du(1, column) =
{[1, 2, 3, 4, 7]}

**Statement 3**
Du(3, total) =
{[3, 4, 5, 6, 7, 8, 9],
[9, 10, 6, 11]}

**Statement 4**
Du(4, rowCount)=
{[4, 5, 6]}

**Statement 5**
Du(5, r)=
{[5, 6],
[5, 6, 7],
[5, 6, 7, 8, 9, 10],
[10, 6],
[10, 6, 7]}

**Statement 7**
Du(7, n) =
{[7,8],
{[7,8,9]}}

**Statement 9**
Du(9, total)=
{[9, 10, 6, 11],
[9, 10, 6, 7, 8, 9]}

**Statement 10**
Du(10, r)=
{[10, 6],
[10, 6, 7, 8, 9, 10]}

### Test-Case DU-Pair Coverage
| Test | DU-Pairs covered | DU-Coverage |
|-------|--------------------------|-------------------|
| testBasicCalculateColumnTotal() | (3, 11), (9, 11), (4, 6), (5, 6), (5, 10), (10, 6), (10, 7), (7, 8), (7, 9) |9/14 = %64.3|
|testCalculateColumnTotalWithNull()|(3, 11), (9, 11), (4, 6), (5, 6), (5, 10), (10, 6), (10, 7), (7, 8), (7, 9) |9/14 = %64.3|
|testCalculateColumnTotalWithAllNulls()|(3, 11), (4, 6), (5, 6), (5, 10), (10, 6), (10, 7), (7, 8) |7/14 = %50 |
|testCalculateColumnTotalWithNoRows()|(3, 11), (4, 6) |2/14 = %14.3 |
|testCalculateColumnTotalOutOfBoundsNegative()|(3, 11), (4, 6), (5, 6), (5, 10), (10, 6), (10, 7), (7, 8)  |7/14 = %50 |
|testCalculateColumnTotalOutOfBoundsPositive()|(3, 11), (4, 6), (5, 6), (5, 10), (10, 6), (10, 7), (7, 8)  |7/14 = %50 |

# 3 A detailed description of the testing strategy for the new unit test

DataUtilities generally had high branch, line and method coverage with all of them being in the 60 percent range. Our plan was to increase the coverage in all three ways with our new tests.  

Range as a class had very low coverage for all metrics chosen mainly as a result of only being required to test 5 methods in assignment 2. As all the tested methods already had 100% coverage our plan was instead to write new tests for the remaining range methods to increase coverage.

This was mainly achieved by referencing the documentation for each method to determine the exected outcome then using the code coverage tools with the provided source code to determine which areas were not being tested and adjust our test suite accordingly.

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage
 

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report


The coverage metrics we reported were line (statement), branch, and method. We only used eclemma for coverage calculations and eclemma was very easy to use. It was also already automatically downloaded on eclipse which also made things easier.

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

| Test type | Advantages | Disadvantages |
|-------|--------------------------|-------------------|
| Coverage | Allows for all possible code paths to be tested. Increases effectiveness of existing test code. | Requires a tool to be used. You need to test for multiple types of coverage. |
| Requirements | Easier to plan. Easier to design tests. | Easier to miss sections of code |
# 8 A discussion on how the team work/effort was divided and managed

Every member had some part in each step specified in the instructions portion of the assignment. The team work/effort was divided based on the 2 classes; That is, for each instruction, one team worked on DataUtilities while the other team worked on Range.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

One of the difficulties encountered was finding and getting various coverage tools working with eclipse, as well as learning how to use them and interpret that coverage data they provided. Another challenge encountered was the sheer amount of additional unit tests that needed to be added in order to meet the coverage metrics as specified by the lab assignment document

# 10 Comments/feedback on the lab itself

The hamcrest library was again missing from this lab again even though it was required for the mocking in this lab. We were also not sure whether or not more coverage of the entire class of DataUtilities and Range were needed which caused some confusion so more explicitly stating that would have been helpful.
