;; Initializes swank and starts a server on the given port
(require 'swank.swank)

(let [[file port protocol-version] *command-line-args*]
  (swank.swank/ignore-protocol-version protocol-version)
  (swank.swank/start-server file
			    :dont-close true
			    :port (Integer/parseInt port)))
