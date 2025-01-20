* StockDataProcessor is a service that processes the stock data and returns the processed data. It can work with XML files only.
* We fetch the real data from the MetabaseService that gives us the data in the form of JSON.
* AnalyticsService performs analysis on the processed stock data. It can work with JSON only.
* We use two different adapters that hides the complexity of conversion and helps to connect between incompatible services.
