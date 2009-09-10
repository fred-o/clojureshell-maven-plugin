package nu.mulli.clojureshell;

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

	/**
	 * @parameter expression="${clojure.swank.file}" default-value="/tmp/swank"
	 *             
	 */
	protected File file;

	@Override
	protected void doExecute() throws Exception {
		Repl.main(new String[] { "@loader.clj", "--", file, Integer.toString(port), protocolVersion });
	}
}
