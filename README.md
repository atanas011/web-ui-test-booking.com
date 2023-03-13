### Booking Test

#### A Maven test project with TestNG

Java and Maven should be installed and their bin dirs added to System variables Path.  
Tests were written to run with Microsoft Edge browser.

- Clone repo to local dir and open cmd.exe in it
- To get results and HTML report, run: mvn test
- Report is created in: target/surefire-reports/index.html

User story:

1. Go to www.booking.com
2. Enter "Paris" as destination
3. Choose the start and end dates between 19th and 25th of March (or other, if these aren't available)
4. Select: 2 Adults, 1 Child and 1 Room
5. Click on "Search" button
6. Select "Novotel Paris Les Halles" (or other, if this one isn't available)

Using Selenium framework perform the positive flow by the steps above.  
If there is time, perform some negative test cases.  
Use your own Selenium framework and preferable web browser.  
The test should be functional and presentable.