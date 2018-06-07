finatra-freemarker
-------
#### Publish Ivy Repository
+ Local:`sbt publishLocal`

#### Publish jCenter
+ `jCenter`:
                
                sbt
                >bintrayChangeCredentials   //first
                >+publish

#### Usage
+ `build.sbt`:

                resolvers +=  "Finatra-Freemarker Repository" at "https://dl.bintray.com/yafengli/maven/"
                libraryDependencies += "greatbit" %% "finatra-freemarker" % $("finatra")
