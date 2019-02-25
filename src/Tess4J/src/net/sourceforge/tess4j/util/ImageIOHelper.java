package Tess4J.src.net.sourceforge.tess4j.util;

import com.github.jaiimageio.plugins.tiff.*;
import com.recognition.software.jdeskew.ImageDeskew;
import com.recognition.software.jdeskew.ImageUtil;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoggHelper;
import net.sourceforge.tess4j.util.PdfUtilities;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.*;

public class ImageIOHelper {
    private static final Logger logger = LoggerFactory.getLogger((new LoggHelper()).toString());
    static final String OUTPUT_FILE_NAME = "Tesstmp";
    static final String TIFF_EXT = ".tif";
    static final String TIFF_FORMAT = "tiff";
    static final String JAI_IMAGE_WRITER_MESSAGE = "Need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core";
    static final String JAI_IMAGE_READER_MESSAGE = "Unsupported image format. May need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core";

    public ImageIOHelper() {
    }

    public static List<File> createTiffFiles(File var0, int var1) throws IOException {
        return createTiffFiles(var0, var1, false);
    }

    public static List<File> createTiffFiles(File var0, int var1, boolean var2) throws IOException {
        ArrayList var3 = new ArrayList();
        String var4 = var0.getName();
        String var5 = var4.substring(var4.lastIndexOf(46) + 1);
        Iterator var6 = ImageIO.getImageReadersByFormatName(var5);
        if (!var6.hasNext()) {
            throw new RuntimeException("Unsupported image format. May need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
        } else {
            ImageReader var7 = (ImageReader)var6.next();
            ImageInputStream var8 = ImageIO.createImageInputStream(var0);
            var7.setInput(var8);
            TIFFImageWriteParam var9 = new TIFFImageWriteParam(Locale.US);
            if (!var2) {
                var9.setCompressionMode(0);
            }

            Iterator var10 = ImageIO.getImageWritersByFormatName("tiff");
            if (!var10.hasNext()) {
                throw new RuntimeException("Need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
            } else {
                ImageWriter var11 = (ImageWriter)var10.next();
                IIOMetadata var12 = var11.getDefaultStreamMetadata(var9);
                int var13 = var7.getNumImages(true);

                for(int var14 = 0; var14 < var13; ++var14) {
                    if (var1 == -1 || var14 == var1) {
                        IIOImage var15 = var7.readAll(var14, var7.getDefaultReadParam());
                        File var16 = File.createTempFile("Tesstmp", ".tif");
                        ImageOutputStream var17 = ImageIO.createImageOutputStream(var16);
                        var11.setOutput(var17);
                        var11.write(var12, var15, var9);
                        var17.close();
                        var3.add(var16);
                    }
                }

                var11.dispose();
                var7.dispose();
                return var3;
            }
        }
    }

    public static List<File> createTiffFiles(List<IIOImage> var0, int var1) throws IOException {
        return createTiffFiles(var0, var1, 0, 0);
    }

    public static List<File> createTiffFiles(List<IIOImage> var0, int var1, int var2, int var3) throws IOException {
        ArrayList var4 = new ArrayList();
        TIFFImageWriteParam var5 = new TIFFImageWriteParam(Locale.US);
        var5.setCompressionMode(0);
        Iterator var6 = ImageIO.getImageWritersByFormatName("tiff");
        if (!var6.hasNext()) {
            throw new RuntimeException("Need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
        } else {
            ImageWriter var7 = (ImageWriter)var6.next();
            IIOMetadata var8 = var7.getDefaultStreamMetadata(var5);
            Iterator var9 = (var1 == -1 ? var0 : var0.subList(var1, var1 + 1)).iterator();

            while(var9.hasNext()) {
                IIOImage var10 = (IIOImage)var9.next();
                if (var2 != 0 && var3 != 0) {
                    ImageTypeSpecifier var11 = ImageTypeSpecifier.createFromRenderedImage(var10.getRenderedImage());
                    IIOMetadata var12 = var7.getDefaultImageMetadata(var11, (ImageWriteParam)null);
                    var12 = setDPIViaAPI(var12, var2, var3);
                    var10.setMetadata(var12);
                }

                File var13 = File.createTempFile("Tesstmp", ".tif");
                ImageOutputStream var14 = ImageIO.createImageOutputStream(var13);
                var7.setOutput(var14);
                var7.write(var8, var10, var5);
                var14.close();
                var4.add(var13);
            }

            var7.dispose();
            return var4;
        }
    }

    private static IIOMetadata setDPIViaAPI(IIOMetadata var0, int var1, int var2) throws IIOInvalidTreeException {
        TIFFDirectory var3 = TIFFDirectory.createFromMetadata(var0);
        BaselineTIFFTagSet var4 = BaselineTIFFTagSet.getInstance();
        TIFFTag var5 = var4.getTag(282);
        TIFFTag var6 = var4.getTag(283);
        TIFFField var7 = new TIFFField(var5, 5, 1, new long[][]{{(long)var1, 1L}});
        TIFFField var8 = new TIFFField(var6, 5, 1, new long[][]{{(long)var2, 1L}});
        var3.addTIFFField(var7);
        var3.addTIFFField(var8);
        IIOMetadata var9 = var3.getAsMetadata();
        IIOMetadataNode var10 = new IIOMetadataNode("javax_imageio_1.0");
        IIOMetadataNode var11 = new IIOMetadataNode("HorizontalPixelSize");
        var11.setAttribute("value", Double.toString((double)(25.4F / (float)var1)));
        IIOMetadataNode var12 = new IIOMetadataNode("VerticalPixelSize");
        var12.setAttribute("value", Double.toString((double)(25.4F / (float)var2)));
        IIOMetadataNode var13 = new IIOMetadataNode("Dimension");
        var13.appendChild(var11);
        var13.appendChild(var12);
        var10.appendChild(var13);
        var9.mergeTree("javax_imageio_1.0", var10);
        return var9;
    }

    public static ByteBuffer getImageByteBuffer(IIOImage var0) throws IOException {
        return getImageByteBuffer(var0.getRenderedImage());
    }

    public static ByteBuffer getImageByteBuffer(RenderedImage image) throws IOException {
        //Set up the writeParam
        if (image instanceof BufferedImage) {
            return convertImageData((BufferedImage) image);
        }
        ColorModel cm = image.getColorModel();
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = cm
                .createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = image.getPropertyNames();
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], image.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster,
                isAlphaPremultiplied, properties);
        image.copyData(raster);
        return convertImageData(result);
    }

    public static ByteBuffer convertImageData(BufferedImage var0) {
        DataBuffer var1 = var0.getRaster().getDataBuffer();
        if (!(var1 instanceof DataBufferByte)) {
            var0 = ImageHelper.convertImageToGrayscale(var0);
            var1 = var0.getRaster().getDataBuffer();
        }

        byte[] var2 = ((DataBufferByte)var1).getData();
        ByteBuffer var3 = ByteBuffer.allocateDirect(var2.length);
        var3.order(ByteOrder.nativeOrder());
        var3.put(var2);
        var3.flip();
        return var3;
    }

    public static List<BufferedImage> getImageList(File var0) throws IOException {
        ImageReader var1 = null;
        ImageInputStream var2 = null;

        try {
            ArrayList var3 = new ArrayList();
            String var4 = var0.getName();
            String var5 = var4.substring(var4.lastIndexOf(46) + 1);
            Iterator var6 = ImageIO.getImageReadersByFormatName(var5);
            if (!var6.hasNext()) {
                throw new RuntimeException("Unsupported image format. May need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
            } else {
                var1 = (ImageReader)var6.next();
                var2 = ImageIO.createImageInputStream(var0);
                var1.setInput(var2);
                int var7 = var1.getNumImages(true);

                for(int var8 = 0; var8 < var7; ++var8) {
                    BufferedImage var9 = var1.read(var8);
                    var3.add(var9);
                }

                ArrayList var17 = var3;
                return var17;
            }
        } finally {
            try {
                if (var2 != null) {
                    var2.close();
                }

                if (var1 != null) {
                    var1.dispose();
                }
            } catch (Exception var15) {
            }

        }
    }

    public static List<IIOImage> getIIOImageList(File var0) throws IOException {
        File var1 = null;
        ImageReader var2 = null;
        ImageInputStream var3 = null;

        try {
            if (var0.getName().toLowerCase().endsWith(".pdf")) {
                var1 = PdfUtilities.convertPdf2Tiff(var0);
                var0 = var1;
            }

            ArrayList var4 = new ArrayList();
            String var5 = var0.getName();
            String var6 = var5.substring(var5.lastIndexOf(46) + 1);
            if (var6.matches("(pbm|pgm|ppm)")) {
                var6 = "pnm";
            } else if (var6.matches("(jp2|j2k|jpf|jpx|jpm)")) {
                var6 = "jpeg2000";
            }

            Iterator var7 = ImageIO.getImageReadersByFormatName(var6);
            if (!var7.hasNext()) {
                throw new RuntimeException("Unsupported image format. May need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
            } else {
                var2 = (ImageReader)var7.next();
                var3 = ImageIO.createImageInputStream(var0);
                var2.setInput(var3);
                int var8 = var2.getNumImages(true);

                for(int var9 = 0; var9 < var8; ++var9) {
                    IIOImage var10 = var2.readAll(var9, var2.getDefaultReadParam());
                    var4.add(var10);
                }

                ArrayList var18 = var4;
                return var18;
            }
        } finally {
            try {
                if (var3 != null) {
                    var3.close();
                }

                if (var2 != null) {
                    var2.dispose();
                }
            } catch (Exception var16) {
            }

            if (var1 != null && var1.exists()) {
                var1.delete();
            }

        }
    }

    public static List<IIOImage> getIIOImageList(BufferedImage var0) throws IOException {
        ArrayList var1 = new ArrayList();
        IIOImage var2 = new IIOImage(var0, (List)null, (IIOMetadata)null);
        var1.add(var2);
        return var1;
    }

    public static void mergeTiff(File[] var0, File var1) throws IOException {
        if (var0.length != 0) {
            Iterator var2 = ImageIO.getImageWritersByFormatName("tiff");
            if (!var2.hasNext()) {
                throw new RuntimeException("Need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
            } else {
                ImageWriter var3 = (ImageWriter)var2.next();
                TIFFImageWriteParam var4 = new TIFFImageWriteParam(Locale.US);
                IIOMetadata var5 = var3.getDefaultStreamMetadata(var4);
                ImageOutputStream var6 = ImageIO.createImageOutputStream(var1);
                var3.setOutput(var6);
                boolean var7 = true;
                int var8 = 1;
                File[] var9 = var0;
                int var10 = var0.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    File var12 = var9[var11];
                    List var13 = getIIOImageList(var12);
                    Iterator var14 = var13.iterator();

                    while(var14.hasNext()) {
                        IIOImage var15 = (IIOImage)var14.next();
                        if (var7) {
                            var3.write(var5, var15, var4);
                            var7 = false;
                        } else {
                            var3.writeInsert(var8++, var15, var4);
                        }
                    }
                }

                var6.close();
                var3.dispose();
            }
        }
    }

    public static void mergeTiff(BufferedImage[] var0, File var1) throws IOException {
        mergeTiff((BufferedImage[])var0, var1, (String)null);
    }

    public static void mergeTiff(BufferedImage[] var0, File var1, String var2) throws IOException {
        ArrayList var3 = new ArrayList();
        BufferedImage[] var4 = var0;
        int var5 = var0.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            BufferedImage var7 = var4[var6];
            var3.add(new IIOImage(var7, (List)null, (IIOMetadata)null));
        }

        mergeTiff((List)var3, var1, var2);
    }

    public static void mergeTiff(List<IIOImage> var0, File var1) throws IOException {
        mergeTiff((List)var0, var1, (String)null);
    }

    public static void mergeTiff(List<IIOImage> var0, File var1, String var2) throws IOException {
        if (var0 != null && !var0.isEmpty()) {
            Iterator var3 = ImageIO.getImageWritersByFormatName("tiff");
            if (!var3.hasNext()) {
                throw new RuntimeException("Need to install JAI Image I/O package.\nhttps://github.com/jai-imageio/jai-imageio-core");
            } else {
                ImageWriter var4 = (ImageWriter)var3.next();
                TIFFImageWriteParam var5 = new TIFFImageWriteParam(Locale.US);
                if (var2 != null) {
                    var5.setCompressionMode(2);
                    var5.setCompressionType(var2);
                }

                IIOMetadata var6 = var4.getDefaultStreamMetadata(var5);
                ImageOutputStream var7 = ImageIO.createImageOutputStream(var1);
                var4.setOutput(var7);
                short var8 = 300;
                short var9 = 300;
                Iterator var10 = var0.iterator();

                while(var10.hasNext()) {
                    IIOImage var11 = (IIOImage)var10.next();
                    ImageTypeSpecifier var12 = ImageTypeSpecifier.createFromRenderedImage(var11.getRenderedImage());
                    IIOMetadata var13 = var4.getDefaultImageMetadata(var12, (ImageWriteParam)null);
                    var13 = setDPIViaAPI(var13, var8, var9);
                    var11.setMetadata(var13);
                }

                IIOImage var14 = (IIOImage)var0.remove(0);
                var4.write(var6, var14, var5);
                int var15 = 1;
                Iterator var16 = var0.iterator();

                while(var16.hasNext()) {
                    IIOImage var17 = (IIOImage)var16.next();
                    var4.writeInsert(var15++, var17, var5);
                }

                var7.close();
                var4.dispose();
            }
        }
    }

    public static File deskewImage(File var0, double var1) throws IOException {
        List var3 = getImageList(var0);

        for(int var4 = 0; var4 < var3.size(); ++var4) {
            BufferedImage var5 = (BufferedImage)var3.get(var4);
            ImageDeskew var6 = new ImageDeskew(var5);
            double var7 = var6.getSkewAngle();
            if (var7 > var1 || var7 < -var1) {
                var5 = ImageUtil.rotate(var5, -var7, var5.getWidth() / 2, var5.getHeight() / 2);
                var3.set(var4, var5);
            }
        }

        File var9 = File.createTempFile(FilenameUtils.getBaseName(var0.getName()), ".tif");
        mergeTiff((BufferedImage[])var3.toArray(new BufferedImage[0]), var9);
        return var9;
    }

    public static Map<String, String> readImageData(IIOImage var0) {
        HashMap var1 = new HashMap();
        IIOMetadata var2 = var0.getMetadata();
        if (var2 != null) {
            IIOMetadataNode var3 = (IIOMetadataNode)var2.getAsTree("javax_imageio_1.0");
            NodeList var4 = var3.getElementsByTagName("HorizontalPixelSize");
            int var5;
            if (var4.getLength() > 0) {
                float var6 = Float.parseFloat(var4.item(0).getAttributes().item(0).getNodeValue());
                var5 = Math.round(25.4F / var6);
            } else {
                var5 = Toolkit.getDefaultToolkit().getScreenResolution();
            }

            var1.put("dpiX", String.valueOf(var5));
            var4 = var3.getElementsByTagName("VerticalPixelSize");
            int var8;
            if (var4.getLength() > 0) {
                float var7 = Float.parseFloat(var4.item(0).getAttributes().item(0).getNodeValue());
                var8 = Math.round(25.4F / var7);
            } else {
                var8 = Toolkit.getDefaultToolkit().getScreenResolution();
            }

            var1.put("dpiY", String.valueOf(var8));
        }

        return var1;
    }
}

