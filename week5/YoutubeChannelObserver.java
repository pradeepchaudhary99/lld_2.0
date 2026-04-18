// package week5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;

// youtube channel 
// subscribers
// 

class YoutubeChannel{
    String name;
    List<Subsriber> subscribers;

    public YoutubeChannel(String name){
        this.name = name;
        this.subscribers = new ArrayList<>();

    }
    public void addSubsriber(Subsriber subsriber){
        subscribers.add(subsriber);
    }

    public void uploadVideo(){
        System.out.println("Video uploaded");
        notifySubsribers();
    }

    private void notifySubsribers(){
        for(Subsriber subsriber : subscribers){
            subsriber.notifyMe();
        }
    }
}

interface Subsriber{
    void notifyMe();
}

class Avinash implements Subsriber{

    @Override
    public void notifyMe() {
        System.out.println("Avinash is notified");
    }

}

class Ajay implements Subsriber{
    @Override
    public void notifyMe() {
        System.out.println("Ajay is notified");
    }

}

class Shekhar implements Subsriber{
    @Override
    public void notifyMe() {
        System.out.println("Shekhar is notified");
    }

}


public class YoutubeChannelObserver {
    public static void main(String[] args) {
        YoutubeChannel youtubeChannel = new YoutubeChannel("Pradeep kumar");
        youtubeChannel.addSubsriber(new Ajay());
        youtubeChannel.addSubsriber(new Shekhar());
        // youtubeChannel.addSubsriber(new Avinash());

        youtubeChannel.uploadVideo();
    }
}
