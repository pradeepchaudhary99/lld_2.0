package week1;


class Logger{
    private String filename;
    private static Logger instance=null;
    private Logger(String name){
        System.out.println("Instance created by :" + Thread.currentThread());
        this.filename = name;
    }
    void log(String log){
        filename+=log;
    }

    // static  Logger getInstance(){
    //     if(instance == null)
    //       instance = new Logger("pradeep.txt");
    //     return instance;
    // }

    static Logger getInstance(){

        if(instance == null){
           synchronized(Logger.class){
                if(instance == null){
                    instance = new Logger("pradeep.txt");
                }
            }
        }
        return instance;
    }
}


public class SingleTonDesignPattern {
    public static void main(String[] args) {

        Runnable task = () -> {
            Logger.getInstance();
          };
  
          // Thread t1 = new Thread(task, "thread-1");
          // Thread t2 = new Thread(task, "thread-2");
          // Thread t3 = new Thread(task, "thread-3");
  
          int THREAD_COUNT = 20;
          Thread[] threads = new Thread[THREAD_COUNT];
  
   
          for (int i = 0; i < THREAD_COUNT; i++) {
              threads[i] = new Thread(task, "Thread-" + (i + 1));
              threads[i].start();
          }
  
          for (int i = 0; i < THREAD_COUNT; i++) {
              try {
                threads[i].join();
              } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
          }
          
          System.out.println("Main thread finished after all threads completed");
  


        
    }
}
