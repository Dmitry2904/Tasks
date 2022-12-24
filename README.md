# Recruitment Tasks – Junior Java Programmer

1. Write an application that downloads only table Statistics Electricity Imbalance from
   https://www.ote-cr.cz/en/statistics/electricity-imbalances and store it as a HTML file.


2. Write an application that downloads xls file from https://www.ote-cr.cz/en/statistics/electricity-imbalances 
(bottom of the page – file Imbalances) and prints
output to the console. The output should go in the following format:
**table header (without units);date (with hour);value** 
<br/>Example:
**System Imbalance;19.09.2022 00:00; -1.123**
   -  <sub>Note that hours are marked from 1 to 24, so hour 1 is the first hour of a day=00:00 and 24 is the last hour
    of a day=23:00</sub>


3. Write an application that downloads and prints value for production on O3 (value in circle)
   from page: https://www.okg.se/en The output should look like the example below:
   **value:1384, time:2022-09-19 17:43**


4. Get the text file. Create an application which outputs storm name and maximum sustained
   wind-speed in knots for each of the storms after 2015 with name ending with A (maximum
   value from a year)

    - Format description: http://www.nhc.noaa.gov/data/hurdat/hurdat2-format-nencpac.pdf

    - Hurricane tracks file: http://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt

      
5. Using a table below write SQL commands that:
   - a) Will join Table 1 with Table 2 on "ID" and "CustomerID"
   - b) Will update the type of the product to corn for client with id=2
   - c) Get the total amount for UK

**Table 1:CUSTOMERS**

ID | ContactName | City | Country | CountryAmount | Type
--- | --- | --- | --- |--- |---
1 | Schmidt | Frankfurt | Germany | 150 | Barley
2 | Mereux | Paris | France | 24 | Soybean
3 | Petersen | Aarhus | Denmark | 89 | Cotton
4 | Hardy | London | UK | 32 | Potato
5 | Berglund | Lulea | Sweden | 78 | Cotton
6 | Fonda | Manchester | UK | 220 | Rice
7 | McBoatie | London | UK | 78 | Onion


**Table 2:ORDERS**

OrderID | CustomerID | OrderDate
--- | --- | ---
10308 | 1 | 2011-10-01
10309 | 2 | 2011-12-09
10310 | 4 | 2011-01-11
10311 | 5 | 2011-02-04
