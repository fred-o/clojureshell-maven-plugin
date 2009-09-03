
Tired of managing lib directories packed with jar files for you
different clojure projects? This little maven plugin offers a simple
way of running a clojure REPL in the context of a maven project;
which means you will have access to all its runtime dependencies.

Usage
-----

Create a maven project and define your dependencies as you normally
would in the pom.xml file. Add the following lines:

	<repositories>
		<repository>
			<id>mulli.nu</id>
			<name>mulli.nu Maven Repository</name>
			<url>http://mulli.nu/maven</url>
		</repository>
	</repositories>

This tells maven where to look for the clojureshell plugin. Now, to
start a REPL for the current project, all you have to do is type:

      mvn nu.mulli:clojureshell-maven-plugin:repl

And presto! Instant REPL! What's that? Too much typing? Well, add the
following to your maven settings.xml file:

	<pluginGroups>
		<pluginGroup>nu.mulli</pluginGroup>
	</pluginGroups>

You'll now only have to type

       mvn clojureshell:repl

which is much shorter and should be easier to remember.

Swank
-----

Thanks to the wonderful swank-clojure project, you have another option
if you want to run clojure with emacs (and who doesn't?). Typing

   mvn clojureshell:swank

starts a swank server for the current project, and you can connect to
it from emacs with M-x slime-connect as usual. The default port is
4005, but can be changed with the -Dclojure.swank.port option.




