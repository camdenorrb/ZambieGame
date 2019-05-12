package me.camdenorrb.zambiegame.engine.gif.processing;

import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;
import me.camdenorrb.zambiegame.engine.gif.Gif;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.image.PixelGrabber;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


public class PGif extends PImage {

    private Thread loopThread;

    private boolean isPlaying;

    private int currentFrameIndex;


    private final Gif gif;

    private final PApplet applet;


    public PGif(Gif gif, PApplet applet) {

        apply(gif.getFrames().get(0).getImage(), it ->
            super.init(it.getWidth(), it.getHeight(), 2)
        );

        this.gif = gif;
        this.applet = applet;

        applet.registerMethod("dispose", this);
    }


    public void start() {

        if (isPlaying) return;
        isPlaying = true;

        startLoop();
    }

    public void stop() {

        if (!isPlaying) return;
        isPlaying = false;

        stopLoop();
    }

    private void startLoop() {

        loopThread = new Thread(() -> {

            while (isPlaying) {

                //attemptOrBreak(() -> Thread.sleep(5));

                final Gif.Frame currentFrame = getFrames().get(currentFrameIndex);

                //loadPixels();

                //System.arraycopy(currentFrame.pixels, 0, this.pixels, 0, this.width * this.height);

                //width = (int) getWidth();
                //height = (int) getHeight();

                //pixels = new int[width * height];

                //pixels = currentFrame.getImage().getData().getPixels(0, 0, width, height, new int[width * height]);

                apply(new PixelGrabber(currentFrame.getImage(), 0, 0, width, height, pixels, 0, width), (it) ->
                    attemptOrBreak((TypedTryBlock<Boolean>) it::grabPixels)
                );

                updatePixels();



                if (currentFrameIndex + 1 >= getFrames().size()) {
                    currentFrameIndex = 0;
                }
                else {
                    currentFrameIndex++;
                }

                attemptOrBreak(() -> Thread.sleep(currentFrame.getDelay() * 10));

            }

        });

        loopThread.start();
    }

    private void stopLoop() {
        loopThread = null;
    }


    public double getWidth() {
        return getFrames().get(currentFrameIndex).getImage().getWidth();
    }

    public double getHeight() {
        return getFrames().get(currentFrameIndex).getImage().getHeight();
    }


    public void dispose() {
        stop();
    }


    public boolean isPlaying() {
        return isPlaying;
    }


    public Gif.Frame get(int index) {
        return getFrames().get(index);
    }


    public Gif getGif() {
        return gif;
    }

    public List<Gif.Frame> getFrames() {
        return gif.getFrames();
    }



    /*public class PGifFrame {

        private final GifFrameMeta meta;

        private final BufferedImage image;


        private PGifFrame(BufferedImage image, GifFrameMeta meta) {
            new PImage(image);
            this.meta = meta;
            this.image = image;
        }


        public GifFrameMeta getMeta() {
            return meta;
        }

        public BufferedImage getImage() {
            return image;
        }

    }*/
}
