# Marquee effect in JavaFX

This is a very simple implementation of a marquee-like effect using JavaFX, to use in a RPi.Unfortunately after jdk1.8.0_33* Java stopped support for embedded JavaFX (RPi included), therefore we had to use jdk1.8.0
We will overlay video and marquee using the framebuffer therefore we need to pass `com.sun.javafx.transparentFramebuffer=true` flag to `java` to effectively have JavaFX run in translucent layer.

## To run:
Create a JAR file and then run: `java -Dcom.sun.javafx.transparentFramebuffer=true -jar <jar_file>.jar`