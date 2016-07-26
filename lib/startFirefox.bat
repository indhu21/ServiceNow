cd c:\ServiceNow\lib
java -jar selenium-server-standalone-2.53.0.jar -role wd -port 5555 -browser browserName=firefox,maxInstances=3

phantomjs  --webdriver=8910 --webdriver-selenium-grid-hub=http://localhost:4444


java -jar selenium-server-standalone-2.53.0.jar -role node  -hub http://localhost:4444/grid/register