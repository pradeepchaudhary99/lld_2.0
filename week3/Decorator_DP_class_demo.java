package week3;
// package week2.week3;

import java.util.concurrent.TimeoutException;

interface Logger{
    void log(String message);
}

class ConsoleLogger implements Logger{
    public void log(String message){
        System.out.println("Console Logger" +message);
    }
}

class FileLogger implements Logger{
    public void log(String message){
        System.out.println("File Logging " + message);
    }
}

abstract class LoggerDecorator implements Logger{
    Logger logger; //Base concreate class
    LoggerDecorator(Logger logger){
        this.logger = logger;
    }
}


class TimeStampDecorator extends LoggerDecorator{

    public TimeStampDecorator(Logger logger){
        super(logger);
    }

    @Override
    public void log(String message){
        //Timestamps code
        System.out.println("TimeStemp [][][][] added");
        System.out.println("TimeStemp [][][][] added");
        logger.log(message);    //now the base class got the chance to run its code.
    }

}
    // TimeStempLogger()


class JsonParserDecorator extends LoggerDecorator{
    public JsonParserDecorator(Logger logger){
        super(logger);
    }
    @Override
    public void log(String message){

        //json parse will add its piece of code
        System.out.println("Json Parser [][][][] added");
        System.out.println("Json Parser [][][][] added");

        logger.log(message);




    }
}



public class Decorator_DP_class_demo {
    public static void main(String[] args) {
        // FileLogger fileLogger = new FileLogger();
        // // consoleLogger.log("Pradeep without any decorator");
        // TimeStampLogger timeStampLogger = new TimeStampLogger(fileLogger);
        // timeStampLogger.log("Message of the user");
        
        Logger log = new TimeStampDecorator(new JsonParserDecorator(new ConsoleLogger()));
        log.log("Pradeep teaching lld");
                                                    

    }
}
