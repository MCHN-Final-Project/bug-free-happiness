# Performance Testing


Here is а way to reproduce the performance testing:

1. Clone the docker images from the docker repo into a container with: docker pull -a simvelinov/final_project_docker. After pulling the images start the container. -> This will assure running the performance test against the required preset data in the app database.
2. Copy the "WeArePerformanceTestPlan.jmx" file from this folder and paste it in your JMeter bin folder. 
3. To run the tests open cmd in your JMeter bin folder and write the command:
 
 **jmeter.bat -n -t "*the path to your bin folder*\WeArePerformanceTesting.jmx" -l "*the path where you want to save the csv report*\WeAreJMReport.csv" -e -o "*the path where you want to save your html report*\WeArePerformanceReport"**  

4. Press ENTER

About the test plan: 
The conducted test plan is testing the Log In, Create Post, Comment on Post and Get all posts API calls. 
There are separate test scenarios for for every each of those functionalities and combined ones. 
There are also separate Test cases for Performance, Load and Spike Testing of those scenarios.

You can enable/disable diffent scenarios and test cases in order to test different desired conditions. 

**Actual reports from some of the scenarios are added to the Performance Testing folder.** 
**In order to visualize the reports you need to download the desired report folder on your machine first and then open the .html file in it.**     

---------------------------------------------------------------------------------------------------------------------------------------------------



Additional info on a second way to run the project:
In case you want to run the project against your own Docker image with empty database:
1. Create one user through the app UI, log it in and press personal profile to obtain the id from the generated url. 
2. Load "WeArePerformanceTestPlan.jmx" in JMeter.
3. Place the actual values of the just created username, password and userId in the User Defined Variablse on top of the project.
4. Save project. 
5. Run the desired tests through JMeter or through the cmd with the same command described above.  

If you want to test Create Post and Comment on post separately, you need to 
1. Run User Single Authentication thread to obtain a cookie from that call. 
2. Place it as Cookie value in the Header Manager. (now you can run Create post separately)
3. For single run of Comment on post, you will need to create a post through the UI and get the post Id from the URL of the new post.
4. Place the value against "postIdForCommentSingleTest" in the User Defined Variables. 
5. Run the desired tests. 

   

That is it. Thanks for reading. 
