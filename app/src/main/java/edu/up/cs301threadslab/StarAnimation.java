package edu.up.cs301threadslab;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.Vector;
import java.util.Random;

/**
 * StarAnimation
 *
 * displays a star field animation
 */
public class StarAnimation extends Animation {

    /* the field of stars */
    public static final int INIT_STAR_COUNT = 100;
    public static final int MAX_STAR_COUNT = 1000;
    public static final int MIN_STAR_COUNT = 100;
    private Vector<Star> field = new Vector<Star>();

    /* when this is set to 'false' the next animation frame won't twinkle */
    private boolean twinkle = true;

    /** ctor expects to be told the size of the animation canvas */
    public StarAnimation(int initWidth, int initHeight) {
        super(initWidth, initHeight);

        // Start a ChangeStarThread
        ChangeStarThread cst = new ChangeStarThread(this);
        cst.start();
    }

    /** whenever the canvas size changes, generate new stars */
    @Override
    public void setSize(int newWidth, int newHeight) {
        super.setSize(newWidth, newHeight);

        //Create the stars
        field = new Vector<Star>();
        for(int i = 0; i < INIT_STAR_COUNT; ++i) {
            addStar();
        }
    }

    /** adds a randomly located star to the field */
    public void addStar() {
        //Ignore this call if the canvas hasn't been initialized yet
        if ((width <= 0) || (height <= 0)) return;

        int x = rand.nextInt(width);
        int y = rand.nextInt(height);


        field.add(new Star(x, y));
    }//addStar

    /** removes a random star from the field */
    public void removeStar() {
        if (field.size() > 100) {
            int index = rand.nextInt(field.size());
            field.remove(index);
        }
    }//removeStar

    /** draws the next frame of the animation */
    @Override
    public void draw(Canvas canvas) {
        for (Star s : field) {
            s.draw(canvas);
            if (this.twinkle) {
                s.twinkle();
            }
        }

        this.twinkle = true;
    }//draw

    /** the seekbar progress specifies the brightness of the stars. */
    @Override
    public void progressChange(int newProgress) {
        // retrieve the progress of the SeekBar
        Log.d("Progress", "" + newProgress);

        // Stars should range from 100-1000
        // SeekBar ranges from 0-100
        // Extra stars should range from 0-900, 9 times the SeekBar scale
        int newStars = newProgress * 9;
        int targetStars = INIT_STAR_COUNT + newStars;

        // get the number of current stars
        int currStars = this.field.size();

        // while the target number of stars have not been reached,
        // add or remove stars
        while(currStars != targetStars){
            if(currStars < targetStars){
                addStar();
            }else{
                removeStar();
            }
            currStars = this.field.size();
        }

        // Log the number of stars
        Log.d("Star Count", "Stars: " + this.field.size() + ", Progress: " + newProgress);

    }
}//class StarAnimation
