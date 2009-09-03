# Maven Clojureshell

Tired of managing lib directories packed with jar files for you
different clojure projects? This little maven plugin offers a simple
way of running a clojure REPL in the context of a maven project; which
means that maven will manage all your dependencies for you,
downloading missing jars and upgrading to newer versions when
necessary. All dependencies in the *runtime* scope will be
automatically added to the classpath and available to the clojure
process.

*Note:* clojureshell is just that; a simple plugin for running
clojure. If you need something more more sophisticated for compiling
and packaging clojure code I suggest you take a look at the
[clojure-maven-plugin](https://github.com/fred-o/clojure-maven-plugin/tree)
project.

## Usage

Install the plugin (see below), then navigate to the root directory of
a maven project and execute the the follow command:

  	mvn clojureshell:repl

This starts an interactive clojure shell (the current clojure version
is 1.0.1-alpha). Functionality is pretty bare right now; you might
want to use `rlwrap` or `jline` to enhance your hacking experience. 

### Swank

Thanks to the wonderful [swank-clojure
project](https://github.com/jochu/swank-clojure/tree), you have another
option if you want to run clojure with emacs (and who
doesn't?). Typing

       mvn clojureshell:swank

starts a swank server for the current project, and you can connect to
it from emacs with `M-x slime-connect` as usual. The default port is
4005, but can be changed with the `-Dclojure.swank.port` option.

## Global installation

The easiest way to get started using clojureshell is to open up (or create)
your maven settings.xml file and add the following sections:

	<settings>
		...
		<pluginGroups>
			...
			<pluginGroup>nu.mulli</pluginGroup>
			...
		</pluginGroups>
		...
		<profiles>
			...
			<profile>
				<id>clojure-dev</id>
				<pluginRepositories>
					<pluginRepository>
						<id>mulli.nu</id>
						<url>http://mulli.nu/maven</url>
						<layout>default</layout>
					</pluginRepository>
				</pluginRepositories>
			</profile>
			...
		</profiles>
		...
		<activeProfiles>
			...
			<activeProfile>clojure-dev</activeProfile>
			...
		</activeProfiles>
		...
	</settings>

This will give you access to clojureshell in /any/ maven project.

## Project-specific installation
	
To enable clojureshell for use in a single project, add the following
lines to that project's pom.xml:

	<repositories>
		...
		<repository>
			<id>mulli.nu</id>
			<name>mulli.nu Maven Repository</name>
			<url>http://mulli.nu/maven</url>
		</repository>
		...
	</repositories>

In order to avoid having to spell out the qualified plugin name (which
is nu.mull:clojureshell-maven-plugin) every time you invoke it, you may
also want to add the following to you settings.xml file:

	<pluginGroups>
		...
		<pluginGroup>nu.mulli</pluginGroup>
		...
	</pluginGroups>

## License

Clojureshell is licensed under the GNU Public License v3.