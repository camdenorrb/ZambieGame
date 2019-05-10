package me.camdenorrb.zambiegame.engine.gif;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Gif {

    private List<Frame> frames = new ArrayList<>();


    /*
    public int getDuration() {
        return frames.stream().map(Frame::getDelay).reduce(0, Integer::sum);
    }*/


    class Frame {

        private final int index;

        private final BufferedImage image;


        private Frame(int index, BufferedImage image) {
            this.index = index;
            this.image = image;
        }


        /*public int getDelay() {
            return
        }*/

        public void setDelay(int delay) {

        }


    }


}
