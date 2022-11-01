# Assessment -> Tedesca 1.0

You can use swagger ui to text inputs here;
http://localhost:8080/swagger-ui/index.html

else postman or insomia endpoints

Example: <b>Text</b> -> Take a look at similar question with another 3rd party 
lib How to return N1qlQueryResult as the response of REST API for Couchbase databse?. 
You do not need to convert object to JSON manually. MVC layer should do that for you. 
In case no, there must be a way, check documentation. If you do not have a POJO you can always 
create a Map to represent JSON Object and List or array to represent JSON Array.

<b>word</b> -> array

<b>limit</b> -> 7

Calculate Frequency For Word
http://localhost:8080/api/calculateFrequencyForWord?text={text}&word={word}

Calculate Highest Frequency
http://localhost:8080/api/calculateHighestFrequency?text={text}

calculateMostFrequentNWords

http://localhost:8080/api/calculateMostFrequentNWords?text={text}&n={limit}
