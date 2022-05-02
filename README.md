# Microservice Based - Exchange Rate and Currency Convertor Application

This is an application for exchange rate and currency conversion.

## Recording: 

•	Please view the demo recording to get to know more about the solution from the below URL.

    https://drive.google.com/file/d/1H9B-zJGvyGmB4lWGP_dBWswqkHCsyYJF/view

## Running application locally

Steps to run the service locally: (Note: I have used JDK 11) 

•	Import as a maven project.

•	Right-click on the project. Run as Spring boot app


### Note: 

Please import both the post collections to access the application endpoint.


## Application Ports:

•	Eureka:  http://localhost:8761/

•	Application URL: http://localhost:8082/exchangeapp


## Highlights – Addon.

•	Support for H2 databases, all the data would persist in Internal Database

•	Unit test Coverage: 96%

•	Support for Localization(i.e.) the messages can be maintained in different languages.

•	Effective Date handling (i.e.) user can view or calibrate results from historical data

•	Custom Exceptions: build application-specific exceptions.



## Endpoints: 

•	This endpoint is used to import data into our application, the supported file format is CSV please export the data from the below link

    https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html
	
    URL : http://localhost:8082/exchangeapp/import/upload/{BaseCurrency}

    Payload Params:
        
    •	Form – Data 

    •	CSVFile : Name of the CSV



•	This endpoint will fetch all the data stored in the system (Note: H2 doesn’t support performance) 

    URL :  http://localhost:8082/exchangeapp/read/getAllSavedData	

•	This endpoint will fetch both the exchange and the reference rates based on the effective dates.

    URL : http://localhost:8082/exchangeapp/read/getReferenceRate

    payload params:

    {
        "effectiveDate": "<Date format : 2022-04-08>",
        "sourceCurrency": "<Source Currency>",
        "targetCurrency": "<target Currency>"
    }

•	This endpoint is used to convert a currency to another, and also calibrate the rates.

    URL  http://localhost:8082/exchangeapp/read/getConvertedValue

    {
        "effectiveDate": "<Date format : YYYY-MM-DD>",
        "sourceCurrency": "<SourceCurrency>",
        "targetCurrency": "<TargetCurrency>",
        "sourceValue": <Amount>
    }


•   This endpoint will give you an external link to access the interactive chart.

    URL:

    http://localhost:8082/exchangeapp/read/getServiceURL


    {
        "sourceCurrency": "<SourceCurrecny>",
        "targetCurrency": "<TargetCurrency>",
        "chartView":"<ChartViewEnum>"
    }

•   The chart view is optional and when not provided, then the external link will default to one year.

    Supported Chart view Enums:

    	TWELVE_HOUR("12H"), 
    	ONE_DAY("1D"), 
    	ONE_WEEK("1W"), 
    	ONE_MONTH("1M"), 
    	ONE_YEAR("1Y"), 
    	TWO_YEAR("2Y"), 
    	FIVE_YEAR("5Y"),
    	TEN_YEAR("10Y")


•	This is a read endpoint that gives all the supported currencies in the system irrespective of the effective date. (ie)  the supported currencies overall. 

    URL: http://localhost:8082/exchangeapp/read/getAllSupportedCurrency


•	This is a read endpoint that gives all the supported currency in the system respective to the effective dates. (ie) the supported currencies as of that day.

    URL: http://localhost:8082/exchangeapp/read/getAllSupportedCurrency?effectiveDate=<DateFormat:YYYY-MM-DD>


•	This endpoint will give an overall detail on the currencies that were accessed for conversion including both reference and exchange rates.

    URL http://localhost:8082/exchangeapp/read/getReadAccessDetails



•	This endpoint will give details on the currencies that were accessed for conversion including both reference and exchange rates by its effective date.

    URL http://localhost:8082/exchangeapp/read/getReadAccessDetails?effectiveDate=<DateFormat-YYY-MM-DD>



