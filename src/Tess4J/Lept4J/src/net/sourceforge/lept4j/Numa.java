package OCR.Tess4J.Lept4J.src.net.sourceforge.lept4j;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.FloatByReference;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : array.h:7</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class Numa extends Structure {
	/**
	 * size of allocated number array<br>
	 * C type : l_int32
	 */
	public int nalloc;
	/**
	 * number of numbers saved<br>
	 * C type : l_int32
	 */
	public int n;
	/**
	 * reference count (1 if no clones)<br>
	 * C type : l_int32
	 */
	public int refcount;
	/**
	 * x value assigned to array[0]<br>
	 * C type : l_float32
	 */
	public float startx;
	/**
	 * change in x value as i --&gt; i + 1<br>
	 * C type : l_float32
	 */
	public float delx;
	/**
	 * number array<br>
	 * C type : l_float32*
	 */
	public FloatByReference array;
	public Numa() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("nalloc", "n", "refcount", "startx", "delx", "array");
	}
	/**
	 * @param nalloc size of allocated number array<br>
	 * C type : l_int32<br>
	 * @param n number of numbers saved<br>
	 * C type : l_int32<br>
	 * @param refcount reference count (1 if no clones)<br>
	 * C type : l_int32<br>
	 * @param startx x value assigned to array[0]<br>
	 * C type : l_float32<br>
	 * @param delx change in x value as i --&gt; i + 1<br>
	 * C type : l_float32<br>
	 * @param array number array<br>
	 * C type : l_float32*
	 */
	public Numa(int nalloc, int n, int refcount, float startx, float delx, FloatByReference array) {
		super();
		this.nalloc = nalloc;
		this.n = n;
		this.refcount = refcount;
		this.startx = startx;
		this.delx = delx;
		this.array = array;
	}
	public Numa(Pointer peer) {
		super(peer);
		read();
	}
	public static class ByReference extends Numa implements Structure.ByReference {
		
	};
	public static class ByValue extends Numa implements Structure.ByValue {
		
	};
}
