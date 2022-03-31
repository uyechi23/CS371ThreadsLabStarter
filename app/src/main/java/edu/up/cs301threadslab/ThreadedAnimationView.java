package edu.up.cs301threadslab;

public class ThreadedAnimationView extends Thread {

    // instance variable to reference the AnimationView of the app
    public AnimationView av;

    /**
     * constructor for ThreadedAnimationView
     *
     * @param appAV - the AnimationView reference needed for the app
     */
    public ThreadedAnimationView(AnimationView appAV){
        // call the super constructor
        super();
        // save the app's AnimationView into the instance variable of this object
        this.av = appAV;
    }

    @Override
    public void run() {

        // infinite loop to continually update
        while(true){
            // invalidate the AnimationView
            this.av.postInvalidate();
            // try-catch to slow down the animation
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
