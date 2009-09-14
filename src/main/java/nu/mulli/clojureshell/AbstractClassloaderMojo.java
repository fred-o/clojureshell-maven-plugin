package nu.mulli.clojureshell;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

public abstract class AbstractClassloaderMojo extends AbstractMojo {
	enum Scope { RUNTIME, TEST };

	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * @parameter expression="${clojure.scope}" default-value="runtime"
	 */
	protected String scope;

	/**
	 * @parameter expression="${clojure.includeStdDirs}" default-value="true"
	 */
	protected boolean includeStdDirs;

	/**
	 * @parameter expression="${cp}"
	 */
	protected String classpath;

	protected abstract void doExecute() throws Exception;

	public void execute() throws MojoExecutionException {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(getClassLoader(cl));
			doExecute();
		} catch (Exception e) {
			getLog().error(e);
		} finally {
			Thread.currentThread().setContextClassLoader(cl);
		}
	}

	private ClassLoader getClassLoader(ClassLoader parent) throws DependencyResolutionRequiredException {
		Scope sc = Scope.valueOf(scope.toUpperCase());

		List<URL> urls = new ArrayList<URL>();
		getLog().debug("Creating class loader...");
		try {
			if(classpath != null) {
				addClasspathElements(Arrays.asList(classpath.split(File.pathSeparator)), urls);
			}
			if (includeStdDirs) {
				addClasspathElement("src/main/clojure", urls);
			}
			addClasspathElements(project.getRuntimeClasspathElements(), urls);
			if(sc == Scope.TEST) {
				if (includeStdDirs) {
					addClasspathElement("src/test/clojure", urls);
				}
				addClasspathElements(project.getTestClasspathElements(), urls);
			}
		} catch (MalformedURLException e) {
			getLog().error(e);
		}
		return new URLClassLoader(urls.toArray(new URL[urls.size()]), parent);
	}

	private void addClasspathElement(String path, List<URL> urls) throws MalformedURLException {
		addClasspathElements(Arrays.asList(path), urls);
	}

	private void addClasspathElements(List<String> ce, List<URL> urls) throws MalformedURLException {
		Iterator<String> iter = ce.iterator();
		while (iter.hasNext()) {
			URL u = new File(iter.next()).toURL();
			getLog().debug("Adding " + u);
			urls.add(u);
		}
	}
}
