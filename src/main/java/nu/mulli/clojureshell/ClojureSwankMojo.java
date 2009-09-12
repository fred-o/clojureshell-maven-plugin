package nu.mulli.clojureshell;

import clojure.lang.Repl;
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
	 *            default-value="2008-09-28"
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
		Repl.main(new String[] { "@loader.clj", "--", file.getAbsolutePath(), Integer.toString(port), protocolVersion });
	}
}
