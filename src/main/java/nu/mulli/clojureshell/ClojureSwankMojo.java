package nu.mulli.clojureshell;

import clojure.main;
import java.io.*;

/**
 * Goal which starts a Clojure REPL and starts running Swank.
 * 
 * @author fredrik
 * 
 * @goal swank
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class ClojureSwankMojo extends AbstractClassloaderMojo {
	/**
	 * @parameter expression="${clojure.swank.port}" default-value="4005"
	 */
	protected int port;

	/**
	 * @parameter expression="${clojure.swank.protocolVersion}"
	 *            default-value="2009-09-14"
	 */
	protected String protocolVersion;

	/**
	 * @parameter expression="${clojure.swank.file}" 
	 *             
	 */
	protected File file;

	@Override
	protected void doExecute() throws Exception {
		if (file == null) {
		    file = new File(new File(System.getProperty("java.io.tmpdir")), "swank");
		}
		main.main(new String[] { "-i", "@loader.clj", "-r", file.getAbsolutePath(), 
								 Integer.toString(port), protocolVersion });
	}
}
