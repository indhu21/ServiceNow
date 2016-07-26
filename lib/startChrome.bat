java -jar selenium-server-standalone-2.53.0.jar -role wd -hub http://localhost:4444/grid/register -port 6666 -browser browserName=chrome,maxInstances=3 -Dwebdriver.chrome.driver=chromedriver.exe
