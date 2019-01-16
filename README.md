# Turkish Gross Salary Calculator
This library calculates the net salary a person will have based on their gross salaries. 
Salaries can be entered by monthly basis, taxes are calculated based on the annual accumulations.

### Example Usage
```scala
TRSalary.annual(
  year = Year.of(2016), 
  salaries = SortedMap(
    Month.of(1)  -> Gross(2000),
    Month.of(2)  -> Gross(2000),
    Month.of(3)  -> Gross(3000),
    Month.of(4)  -> Gross(3000),
    Month.of(5)  -> Gross(5000),
    Month.of(6)  -> Gross(5000),
    Month.of(7)  -> Gross(5000),
    Month.of(8)  -> Gross(7000),
    Month.of(9)  -> Gross(7000),
    Month.of(10) -> Gross(7000),
    Month.of(11) -> Gross(3000),
    Month.of(12) -> Gross(3000)
  )
)
```

It is possible to skip some of the months. Those months will be accepted with **0** income