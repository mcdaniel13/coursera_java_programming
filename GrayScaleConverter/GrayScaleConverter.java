/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Moongee Cho
 * @version 1.0
 * @date 02/14/2019
 */
import edu.duke.*;
import java.io.*;

/* convert colored image to gray image */
public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        
        return outImage;
    }
    
    /* select image files, convert them to gray, and save them */
    public void selectConvertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            String fileName = f.getName();
            String newFileName = "gray-" + fileName;
            gray.setFileName(newFileName);
            gray.draw();
            gray.save();
        }

    }
}
