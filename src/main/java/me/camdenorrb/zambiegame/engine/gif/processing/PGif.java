package me.camdenorrb.zambiegame.engine.gif.processing;

import me.camdenorrb.zambiegame.base.tryblock.TypedTryBlock;
import me.camdenorrb.zambiegame.engine.gif.Gif;
import processing.core.PImage;

import java.awt.image.PixelGrabber;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;


/**
 * Represents a Processing Gif
 */
public class PGif extends PImage {

    private final Gif gif;


    private boolean shouldPlay;

    private int currentFrameIndex;


    /**
     * Constructs a PGif instance
     *
     * @param gif The gif to refer off of
     */
    public PGif(Gif gif) {

        apply(gif.getFrames().get(0).getImage(), it ->
            super.init(it.getWidth(), it.getHeight(), 2)
        );

        this.gif = gif;
    }

    /**
     * Whether or not the gif should play
     *
     * @return If the gif should play
     */
    public boolean shouldPlay() {
        return shouldPlay;
    }

    /**
     * Sets if the gif should play
     *
     * @param shouldPlay Whether or not the gif should play
     */
    public void setShouldPlay(boolean shouldPlay) {
        this.shouldPlay = shouldPlay;
    }

    /**
     * Sets the current frame index
     *
     * @param currentFrameIndex The new current frame index
     */
    public void setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
    }

    /*
    public void start() {

        if (isPlaying) return;
        isPlaying = true;

        startLoop();
    }

    public void stop() {

        if (!isPlaying) return;
        isPlaying = false;

        stopLoop();
    }*/

    /**
     * Gets the delay for the current frame
     *
     * @return The delay for the current frame
     */
    public int getDelayForCurrentFrame() {
        return getFrames().get(currentFrameIndex).getDelay();
    }

    /**
     * Plays the next frame
     */
    public void playNextFrame() {

        final Gif.Frame currentFrame = getFrames().get(currentFrameIndex);


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
    }

    /*private void startLoop() {

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
    }*/

    /**
     * Gets the width for the gif
     *
     * @return The gif's width
     */
    public double getWidth() {
        return getFrames().get(currentFrameIndex).getImage().getWidth();
    }

    /**
     * Gets the height for the gif
     *
     * @return The gif's height
     */
    public double getHeight() {
        return getFrames().get(currentFrameIndex).getImage().getHeight();
    }


    /**
     * Gets the frame at a specified index
     *
     * @param index The index to get the frame from
     *
     * @return The frame at the specified index
     */
    public Gif.Frame get(int index) {
        return getFrames().get(index);
    }


    /**
     * Gets the backend gif
     *
     * @return The backend gif
     */
    public Gif getGif() {
        return gif;
    }

    /**
     * Gets the frames in the gif
     *
     * @return The frames in the gif
     */
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
