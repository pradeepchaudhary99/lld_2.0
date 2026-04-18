package week1;
interface SmartDevice {
    void turnOn();
    void turnOff();
    void playMusic();
    void takePhoto();
    void makeCall();
    void browseInternet();
}

class Smartphone implements SmartDevice {
    public void turnOn() { System.out.println("Phone ON"); }
    public void turnOff() { System.out.println("Phone OFF"); }
    public void playMusic() { System.out.println("Playing music"); }
    public void takePhoto() { System.out.println("Taking photo"); }
    public void makeCall() { System.out.println("Calling"); }
    public void browseInternet() { System.out.println("Browsing"); }
}

class SmartSpeaker implements SmartDevice {
    public void turnOn() { System.out.println("Speaker ON"); }
    public void turnOff() { System.out.println("Speaker OFF"); }

    public void playMusic() { System.out.println("Playing music"); }

    public void takePhoto() {
        throw new UnsupportedOperationException();
    }

    public void makeCall() {
        throw new UnsupportedOperationException();
    }

    public void browseInternet() {
        throw new UnsupportedOperationException();
    }
}

/* 
Problems
SmartSpeaker is forced to implement methods it does not support
Leads to runtime exceptions
Makes the system fragile and hard to maintain
Volation of LSP
*/



//SOLUTION

interface PowerDevice {
    void turnOn();
    void turnOff();
}

interface MusicPlayer {
    void playMusic();
}

interface Camera {
    void takePhoto();
}

interface CallingDevice {
    void makeCall();
}

interface InternetBrowser {
    void browseInternet();
}


class Smartphone implements PowerDevice, MusicPlayer, Camera, CallingDevice, InternetBrowser {

    public void turnOn() { System.out.println("Phone ON"); }
    public void turnOff() { System.out.println("Phone OFF"); }

    public void playMusic() { System.out.println("Playing music"); }

    public void takePhoto() { System.out.println("Taking photo"); }

    public void makeCall() { System.out.println("Calling"); }

    public void browseInternet() { System.out.println("Browsing"); }
}


class SmartSpeaker implements PowerDevice, MusicPlayer {

    public void turnOn() { System.out.println("Speaker ON"); }
    public void turnOff() { System.out.println("Speaker OFF"); }

    public void playMusic() { System.out.println("Playing music"); }
}


/*
Bad approach:
One large interface forces unnecessary implementation.

Good approach:
Multiple small, role-based interfaces allow flexibility and cleaner design.


Instead of one fat interface, create multiple role-based interfaces so classes only implement what they actually need.
*/

public class InterfaceSeggregation_Principle_Demo {
    
}
