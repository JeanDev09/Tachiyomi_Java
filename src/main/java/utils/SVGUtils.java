package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 *
 * @author jean
 */
public class SVGUtils {

    public static BufferedImage renderSVGToImage(String svgPath) {
        try {
            byte[] svgBytes = Files.readAllBytes(Paths.get(svgPath));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(svgBytes);

            PNGTranscoder transcoder = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(inputStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            transcoder.transcode(input, new TranscoderOutput(outputStream));
            outputStream.flush();

            byte[] pngBytes = outputStream.toByteArray();
            ByteArrayInputStream pngInputStream = new ByteArrayInputStream(pngBytes);
            BufferedImage bufferedImage = ImageIO.read(pngInputStream);
            
            return bufferedImage;
        } catch (IOException | TranscoderException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
