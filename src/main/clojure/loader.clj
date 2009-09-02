;; Initializes swank and starts a server on the given port

(require 'swank.loader)
(swank.loader/init)
(require 'swank.swank)

(let [[file port protocol-version] *command-line-args*]
  (swank.swank/ignore-protocol-version protocol-version)
  (swank.swank/start-server file
			    :port (Integer/parseInt port)))
