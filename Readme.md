# Start developing Scala on bitnami spark cluster

Easy way to provision a development environment of spark cluster with SBT component to execute Scala code with Spark submit.
This docker-compose file is an Example of spark architecture using bitnami Spark image together with another SBT image hseeberger/scala-sbt
allows easy working with Scala dev environment on top of spark.

## _Pre request:_

- Docker installed
- Understand scala project folder structure

## _Installation instructions_

Open terminal/ CMD at the root folder of the project and type
"make start" in MAC / "docker-compose up" in windows.

## _Starting developmet in scala_

Below are the steps

### Step 1

### Create build.sbt file for the project

Under the path src/scalaSpark/ locate a folder with the project content (Build.sbt file and scala project folder).

**------------ Format of the file build.sbt --------------**

name := "hello"

version := "1.0"

scalaVersion := "2.12.18"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.1"

**-----End Of Example-------**

For update the build.sbt you'll need to define the version of the spark and scala environment.
The version detailes of Spark and Scala can be achieved by executing the following on the spark-master machine /opt/bitnami/spark/bin/spark-shell
The following content will appear.

Welcome to Spark version 3.5.1 Using Scala version 2.12.18

update your Spark and Scala versions at the build.sbt and prepare the scala project folder before executing sbt command

### Step 2

#### Create jar for spark submit

Before move on make sure you have the build.sbt and the Scala porject folder
Go to the sbt docker container at the following path /scala_app/YOUR_PROJECT_FOLDER and type "sbt package" this will create the relevant jar to be executed by spark submit.

### Step 3

#### Get Jar name

Check inside of your project directory for the NEW_CREATED_JAR at the path target/scala-2.12/NEW_CREATED_JAR.jar

### Step 4

#### Execute spark submit

Replace in the command bellow the NEW_CREATED_JAR with the name of your jar and execute the spark-submit command on Spark master machine

/opt/bitnami/spark/bin/spark-submit \
 --class "dataformat" \
 --master local[*] \
 target/scala-2.12/NEW_CREATED_JAR.jar
