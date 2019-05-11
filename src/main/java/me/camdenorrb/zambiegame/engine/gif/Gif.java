package me.camdenorrb.zambiegame.engine.gif;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.JavaUtils.apply;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrBreak;
import static me.camdenorrb.zambiegame.utils.TryUtils.attemptOrDefault;


public class Gif {

    private List<Frame> frames = new ArrayList<>();


    public void addFrame(BufferedImage image, GifFrameMeta meta) {
        frames.add(new Frame(frames.size(), image, meta));
    }


    public void read(File file) {
        read(attemptOrBreak(() -> ImageIO.createImageInputStream(file)));
    }

    public void read(InputStream inputStream) {
        read(attemptOrBreak(() -> ImageIO.createImageInputStream(inputStream)));
    }

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


    public Image save(File file) {

        final ImageOutputStream outputStream = attemptOrBreak(() ->
            ImageIO.createImageOutputStream(new FileOutputStream(file))
        );

        final ImageWriter writer = apply(ImageIO.getImageWritersBySuffix("gif").next(), (it) -> {
            it.setOutput(outputStream);
            attemptOrBreak(() -> it.prepareWriteSequence(null));
        });


        frames.forEach(it -> {
            final IIOImage outImage = new IIOImage(it.image, null, it.meta.getImageMeta());
            attemptOrBreak(() -> writer.writeToSequence(outImage, writer.getDefaultWriteParam()));
        });

        attemptOrBreak(writer::endWriteSequence);
        attemptOrBreak(outputStream::close);

        return attemptOrBreak(() ->
            Toolkit.getDefaultToolkit().createImage(file.toURI().toURL())
        );
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getDuration() {
        return frames.stream().map(Frame::getDelay).reduce(0, Integer::sum);
    }


    public class Frame {

        private final int index;

        private final GifFrameMeta meta;

        private final BufferedImage image;


        private Frame(int index, BufferedImage image, GifFrameMeta meta) {
            this.meta = meta;
            this.index = index;
            this.image = image;
        }


        public int getDelay() {
            return meta.getDelay();
        }

        public void setDelay(int delay) {
            meta.setDelay(delay);
        }


        public GifFrameMeta getMeta() {
            return meta;
        }

        public BufferedImage getImage() {
            return image;
        }

    }

}
