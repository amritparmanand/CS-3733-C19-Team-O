package OCR.Tess4J.Lept4J.src.net.sourceforge.lept4j;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;
import java.util.Arrays;
import java.util.List;
/**
 * Array of pix<br>
 * <i>native declaration : pix.h:83</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class Pixa extends Structure {
	/**
	 * number of Pix in ptr array<br>
	 * C type : l_int32
	 */
	public int n;
	/**
	 * number of Pix ptrs allocated<br>
	 * C type : l_int32
	 */
	public int nalloc;
	/**
	 * reference count (1 if no clones)<br>
	 * C type : l_uint32
	 */
	public int refcount;
	/**
	 * the array of ptrs to pix<br>
	 * C type : Pix**
	 */
	public PointerByReference pix;
	/**
	 * array of boxes<br>
	 * C type : Boxa*
	 */
	public net.sourceforge.lept4j.Boxa.ByReference boxa;
	public Pixa() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("n", "nalloc", "refcount", "pix", "boxa");
	}
	/**
	 * @param n number of Pix in ptr array<br>
	 * C type : l_int32<br>
	 * @param nalloc number of Pix ptrs allocated<br>
	 * C type : l_int32<br>
	 * @param refcount reference count (1 if no clones)<br>
	 * C type : l_uint32<br>
	 * @param pix the array of ptrs to pix<br>
	 * C type : Pix**<br>
	 * @param boxa array of boxes<br>
	 * C type : Boxa*
	 */
	public Pixa(int n, int nalloc, int refcount, PointerByReference pix, net.sourceforge.lept4j.Boxa.ByReference boxa) {
		super();
		this.n = n;
		this.nalloc = nalloc;
		this.refcount = refcount;
//		if ((pix.length != this.pix.length)) 
//			throw new IllegalArgumentException("Wrong array size !");
		this.pix = pix;
		this.boxa = boxa;
	}
	public Pixa(Pointer peer) {
		super(peer);
		read();
	}
	public static class ByReference extends Pixa implements Structure.ByReference {
		
	};
	public static class ByValue extends Pixa implements Structure.ByValue {
		
	};
}
