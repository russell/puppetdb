;; ## Version utility
;;
;; This simple command-line tool prints a JSON hash with some info about
;; the version of PuppetDB.  It is useful for testing and other situations
;; where you'd like to know some of the version details without having
;; a running instance of PuppetDB.

(ns com.puppetlabs.puppetdb.cli.version
  (:require [cheshire.core :as json])
  (:use [com.puppetlabs.puppetdb.version :only [version]]
        [com.puppetlabs.puppetdb.scf.migrate :only [desired-schema-version]]))

(def cli-description "Print out a JSON hash containing version info about PuppetDB")

;; TODO: Would like to add database info and some other things here, but that
;; will require us to have access to the configuration info.  At present, the
;; configuration parsing code is scattered throughout services.clj and not
;; cleanly accessible from here.  Perhaps we can revisit this once we've
;; refactored and cleaned up the configuration stuff a bit.
(defn -main
  [& args]
  (doseq [[key val] {"version" (version)
                     "target_schema_version" desired-schema-version}]
    (println (format "%s=%s" key val))))