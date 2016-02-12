name := """ircClient"""

version := "1.0"

organization := "com.github.uryyyyyyy"

crossPaths := false

autoScalaLibrary := false

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= Seq(
	"com.sorcix" % "sirc" % "1.1.5"
)
