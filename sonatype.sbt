// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.gonitro"

// To sync with Maven central, you need to supply the following information:
pomExtra := {
  <url>https://github.com/Nitro/streamcollections</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/(your repository URL)</connection>
    <developerConnection>scm:git:git@github.com:https://github.com/Nitro/streamcollections/</developerConnection>
    <url>github.com/Nitro/streamcollections/</url>
  </scm>
  <developers>
    <developer>
      <id>0</id>
      <name>Greg Silin</name>
      <url>https://github.com/gregsilin</url>
    </developer>
    <developer>
      <id>1</id>
      <name>Paul Kinsky</name>
      <url>https://github.com/pkinsky</url>
    </developer>
    <developer>
      <id>2</id>
      <name>Malcolm Greaves</name>
      <url>https://github.com/malcolmgreaves</url>
    </developer>
    <developer>
      <id>3</id>
      <name>Javier Arrieta</name>
      <url>https://github.com/javierarrieta</url>
    </developer>
  </developers>
}
