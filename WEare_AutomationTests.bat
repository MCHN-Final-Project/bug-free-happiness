@cd TestAutomationFramework

@mvn clean surefire-report:report -Dtest=* 
@mvn site -DgenerateReports=false

@cd ..
@echo Report is generated in temp\TestAutomationFramework\target\site

PAUSE