package clojure;

import clojure.lang.Repl;

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

	@Override
	protected void doExecute() throws Exception {
		Repl.main(new String[] { "@loader.clj", "--", "/tmp/swank", Integer.toString(port), protocolVersion });
	}
}
