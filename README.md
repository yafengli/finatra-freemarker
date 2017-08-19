finatra-freemarker
------------
#### Publish Ivy Repository
+ Local:`sbt +publishLocal`

#### Publish jCenter
+ jCenter:

        sbt
        >bintrayChangeCredentials   //first
        >+publish

#### Usage
+ `build.sbt`:

     libraryDependencies += "greatbit" %% "finatra-freemarker" % $("finatra")




