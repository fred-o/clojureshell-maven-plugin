package nu.mulli.clojureshell;

import clojure.lang.Repl;

/**
 * Goal which starts a Clojure REPL.
 * 
 * @author fredrik
 * 
 * @goal repl
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class ClojureReplMojo extends AbstractClassloaderMojo {
	@Override
	protected void doExecute() throws Exception {
		Repl.main(new String[0]);
	}
}
