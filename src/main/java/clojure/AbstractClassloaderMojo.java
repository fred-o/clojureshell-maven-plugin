package clojure;

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
	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

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
		List<URL> urls = new ArrayList<URL>();
		getLog().debug("Creating class loader...");
		try {
			addClasspathElements(project.getRuntimeClasspathElements(), urls);
			if(classpath != null) {
				addClasspathElements(Arrays.asList(classpath.split(File.pathSeparator)), urls);
			}
		} catch (MalformedURLException e) {
			getLog().error(e);
		}
		return new URLClassLoader(urls.toArray(new URL[urls.size()]), parent);
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
