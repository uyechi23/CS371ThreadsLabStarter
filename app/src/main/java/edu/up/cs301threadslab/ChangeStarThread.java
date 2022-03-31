package edu.up.cs301threadslab;

import android.util.Log;

import java.util.Random;

public class ChangeStarThread extends Thread {

    // create a reference to the AnimationView of the app
    public StarAnimation sa;

    /**
     * constructor for ChangeStarThread object
     *
     * @param appSA - the app's AnimationView
     */
    public ChangeStarThread(StarAnimation appSA){
        // super constructor
        super();
        // assign the input argument to the instance variable
        this.sa = appSA;
    }

    @Override
    public void run(){
        // create a new random object
        Random rand = new Random();

        // infinite loop to randomly remove or add a star
        while(true){
            int randInt = rand.nextInt(100);
            if(randInt < 50){
                // remove a star
                this.sa.removeStar();
                Log.d("Change Stars", "Removed a star.");
            }else{
                // add a star
                this.sa.addStar();
                Log.d("Change Stars", "Added a star.");
            }
            // sleep
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
