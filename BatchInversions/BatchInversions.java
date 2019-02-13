/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Moongee Cho
 * @version 1.0
 * @date 02/14/2019
 */
import edu.duke.*;
import java.io.*;

/* convert colored image to inverted image */
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        
        return outImage;
    }
    
    /* select image files, convert them to inverted image, and save them */
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource ivertedImage = makeInversion(ir);
            String fileName = f.getName();
            String newFileName = "inverted-" + fileName;
            ivertedImage.setFileName(newFileName);
            ivertedImage.draw();
            ivertedImage.save();
        }

    }
}
