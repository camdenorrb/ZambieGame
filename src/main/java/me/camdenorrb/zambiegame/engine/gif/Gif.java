package me.camdenorrb.zambiegame.engine.gif;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrDefault;


/**
 * Represents a Gif image
 */
public class Gif {

    private List<Frame> frames = new ArrayList<>();


    /**
     * Adds a frame to the gif
     *
     * @param image The image to add to the gif
     * @param meta The meta to set on the gif
     */
    public void addFrame(BufferedImage image, GifFrameMeta meta) {
        frames.add(new Frame(frames.size(), image, meta));
    }

    /**
     * Reads a gif from a file
     *
     * @param file The file to read the gif from
     */
    public void read(File file) {
        read(attemptOrBreak(() -> ImageIO.createImageInputStream(file)));
    }

    /**
     * Reads a gif from an input stream
     *
     * @param inputStream The input stream to read the gif from
     */
    public void read(InputStream inputStream) {
        read(attemptOrBreak(() -> ImageIO.createImageInputStream(inputStream)));
    }

    /**
     * Reads a gif from an input stream
     *
     * @param inputStream The input stream to read the gif from
     */
    public void read(ImageInputStream inputStream) {

        final ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();
        reader.setInput(inputStream);

        final int numImages = attemptOrDefault(0, () -> reader.getNumImages(true));

        for (int i = 0; i < numImages; i++) {

            final int finalI = i; // Hacky lambda fix

            final BufferedImage image = attemptOrBreak(() -> reader.read(finalI));
            final IIOMetadata imageMeta = attemptOrBreak(() -> reader.getImageMetadata(finalI));

            addFrame(image, new GifFrameMeta(imageMeta));
        }
    }


    /**
     * Saves the gif to a file
     *
     * @param file The file to save to
     */
    public void save(File file) {

        final ImageOutputStream outputStream = attemptOrBreak(() ->
            ImageIO.createImageOutputStream(new FileOutputStream(file))
        );

        final ImageWriter writer = apply(ImageIO.getImageWritersBySuffix("gif").next(), it -> {
            it.setOutput(outputStream);
            attemptOrBreak(() -> it.prepareWriteSequence(null));
        });


        frames.forEach(it -> {
            final IIOImage outImage = new IIOImage(it.image, null, it.meta.getImageMeta());
            attemptOrBreak(() -> writer.writeToSequence(outImage, writer.getDefaultWriteParam()));
        });

        attemptOrBreak(writer::endWriteSequence);
        attemptOrBreak(outputStream::close);

        /*
        return attemptOrBreak(() ->
            Toolkit.getDefaultToolkit().createImage(file.toURI().toURL())
        );*/
    }

    /**
     * Gets the frames in the gif
     *
     * @return The frames in the gif
     */
    public List<Frame> getFrames() {
        return frames;
    }

    /**
     * Gets the duration of a gif
     *
     * @return The duration of the gif
     */
    public int getDuration() {
        return frames.stream().map(Frame::getDelay).reduce(0, Integer::sum);
    }


    /**
     * Represents a frame in a gif
     */
    public class Frame {

        private final int index;

        private final GifFrameMeta meta;

        private final BufferedImage image;


        /**
         * Constructs a gif frame
         *
         * @param index The index of the frame
         * @param image The image of the frame
         * @param meta The meta of the frame
         */
        private Frame(int index, BufferedImage image, GifFrameMeta meta) {
            this.meta = meta;
            this.index = index;
            this.image = image;
        }


        /**
         * Gets the delay for the frame
         *
         * @return The delay for the frame
         */
        public int getDelay() {
            return meta.getDelay();
        }

        /**
         * Sets the delay for the frame
         *
         * @param delay The new delay for the frame
         */
        public void setDelay(int delay) {
            meta.setDelay(delay);
        }


        /**
         * Gets the meta for the frame
         *
         * @return The meta for the frame
         */
        public GifFrameMeta getMeta() {
            return meta;
        }

        /**
         * Gets the image for the frame
         *
         * @return The image for the frame
         */
        public BufferedImage getImage() {
            return image;
        }

    }

}
