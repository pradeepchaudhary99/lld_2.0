// package week2.week3;

import java.security.Timestamp;

import netscape.javascript.JSException;

interface Logger{
    void log(String message);
}

class ConsoleLogger implements Logger{
    public void log(String message){
        System.out.println(message);
    }
}

class FileLogger implements Logger{
    public void log(String message){
        System.out.println("File Logging " + message);
    }
}

// thre will be different types of factory --> interface 

// Abstract Decorator --> it can be generalized to create any decorator for this baseClass logger

abstract class LoggerDecorator implements Logger{
    protected Logger logger;
    LoggerDecorator(Logger logger){
        this.logger = logger;
    }
}

class TimeStampLogger extends LoggerDecorator{
    TimeStampLogger(Logger logger){
        super(logger);
    }

    @Override
    public void log(String message){
        // add timestamp
        String msg = System.currentTimeMillis() +" : "+message;
        logger.log(msg);
    }
}

class JsonParsing extends LoggerDecorator{
    JsonParsing(Logger logger){
        super(logger);
    }
    @Override
    public void log(String message){
        // add timestamp
        System.out.println("Json parsing happening for message");
        logger.log(message);
    }
}


public class Decorator_DP_class_demo {
    public static void main(String[] args) {
        // FileLogger fileLogger = new FileLogger();
        // // consoleLogger.log("Pradeep without any decorator");
        // TimeStampLogger timeStampLogger = new TimeStampLogger(fileLogger);
        // timeStampLogger.log("Message of the user");

        JsonParsing jsonParsing = new JsonParsing(
                                                new TimeStampLogger(
                                                        new ConsoleLogger())
                                                );
        jsonParsing.log("Pradeep sending message");
                                                    

    }
}
