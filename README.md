A multithreaded application which get market data for multiple dates from rest api 
and saves it in cassandra.

Ex: In local cassandra installation program saved around 300,000 records
in 2 mins in a 4 core machine.

Technologies used:Java SE8, Spring Boot, Spring Data(Cassandra)