

// Data Intensive application --> 
/*
What is URL shortner?
    Functional:
        -> should take longURL and return a shortURL 
        -> should resolve the shortURL to longURL
        -> should give a unique shortCode 
        -> should keep track of the number of clicks    // observability related.
        --> should support multiple algorithm to convert L --> S
        --> 
    
    Non-Functional:
        --> uniqueCode
        --> latency should be low
        --> user should not abuse the system


    Core entities:
        URL_Map
            shortCode
            longURL
            click_count
        
        URL_Repository
            Map<ShortCode, URL_MAP>
        
        ShortCodeConversionStrategy
            - HashingStratetegy
            - Base62EncodingStrategy

        URL_Shortner_Service
            - List<Observers>
            - ShortCodeConversionStrategy 
            - getLongURL(shortCode)
            - getShortURL(longCode)

        
        Observer
            notify()

        ClickCountObservability implements Observer
            DataAnalayticsRepository

        DataAnalayticsRepository


*/



public class URL_Shortner_Demo {
    
}
