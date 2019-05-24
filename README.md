# Eclipse Milo
![Jenkins](https://img.shields.io/jenkins/build/https/ci.eclipse.org/milo/job/Milo_Deploy.svg)
[![Maven Central](https://img.shields.io/maven-central/v/org.eclipse.milo/milo.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.eclipse.milo%22%20AND%20a%3A%22milo%22)

Milo is an open-source implementation of OPC UA. It includes a high-performance stack (channels, serialization, data structures, security) as well as client and server SDKs built on top of the stack.

While this project has existed for some time, it is new to the Eclipse foundation and is therefore considered to be in [incubation](https://eclipse.org/projects/dev_process/development_process.php#6_2_3_Incubation). While in incubation it will continue to use `0.x.x` versions.

Stack Overflow tag: [milo](http://stackoverflow.com/questions/tagged/milo)

Mailing list: https://dev.eclipse.org/mailman/listinfo/milo-dev


## Maven

### Building Milo

**Using JDK 8**, run `mvn clean install` from the project root.

### Releases

Releases are published to Maven Central and snapshots to Sonatype.

#### OPC UA Client SDK

```xml
<dependency>
    <groupId>org.eclipse.milo</groupId>
    <artifactId>sdk-client</artifactId>
    <version>0.3.0</version>
</dependency>
```

#### OPC UA Server SDK

```xml
<dependency>
    <groupId>org.eclipse.milo</groupId>
    <artifactId>sdk-server</artifactId>
    <version>0.3.0</version>
</dependency>
```

Referencing a `SNAPSHOT` release requires the Sonatype snapshot repository be added to your pom file:

```xml
<repository>
    <id>oss-sonatype</id>
    <name>oss-sonatype</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
</repository>
```

## High Level Roadmap
### Version 0.4
#### Server
- Diagnostic Nodes


### Version 1.0
- Auditing Support?
- ???


### Version 2.0
#### General
- Java 9 + Modularization

#### Stack
- UA 1.04

#### Client
- UA 1.04

#### Server
- UA 1.04


### Future
- Javascript/NodeJS Bindings for GraalVM?
- Python Bindings for GraalVM?
- History "Connectors"?
