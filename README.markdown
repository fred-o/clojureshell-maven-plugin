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
is 1.0.0). Functionality is pretty bare right now; you might
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

### Options and configuration

Clojureshell currently supports the following options that can be
configured as system properties:

<table>
	<tr>
		<th>Property</th>
		<th>Default value</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>cp</td>
		<td></td>
		<td>
			Additional resources that should be added to the classpath. Follows the 
			regular java classpath syntax, but doesn't understand the <code>dir/*</code> 
			idiom.
		</td>
	</tr>
	<tr>
		<td>clojure.scope</td>
		<td>runtime</td>
		<td>
			The maven scope that whose dependencies should be added to the 
			classpath. Possible values: <code>runtime</code> and <code>test</code>.
		</td>
	</tr>
	<tr>
		<td>clojure.includeStdDirs</td>
		<td>true</td>
		<td>
			If true, the standard directories <code>src/main/clojure</code> and 
			<code>src/test/clojure</code> will be added to the classpath (although the 
			latter is only applicable only if <code>clojure.scope=test</code>).
		</td>
	</tr>
	<tr>
		<td>clojure.swank.port</td>
		<td>4005</td>
		<td>
			Only applicable for the <code>clojureshell:swank</code> target.
			The port number that the Swank server should listen to.
		</td>
	</tr>
	<tr>
		<td>clojure.swank.file</td>
		<td>$TEMP/swank</td>
		<td>
			Only applicable for the <code>clojureshell:swank</code> target.
			Determines which file Swank should use for dumping connection info.			
		</td>
	</tr>
</table>

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
						<url>http://mulli.nu/maven/releases</url>
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
			<url>http://mulli.nu/maven/releases</url>
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

## Building instructions

You don't need to compile Clojureshell yourself in order to use it,
but in case anyone's interested, here is the procedure to compile and
install the plugin in your local repository:

 1. git clone  git://github.com/fred-o/clojureshell-maven-plugin.git
 2. cd clojureshell-maven-plugin
 3. git submodule init
 4. git submodule update
 5. mvn install

## License

Clojureshell is licensed under the GNU Public License v3.