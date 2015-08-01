# Marquee effect in JavaFX

This is a very simple implementation of a marquee-like effect using JavaFX, to use in a RPi.Unfortunately after jdk1.8.0_33* Java stopped support for embedded JavaFX (RPi included), therefore we had to use jdk1.8.0

We will overlay video and marquee using the framebuffer therefore we need to pass `com.sun.javafx.transparentFramebuffer=true` flag to `java` to effectively have JavaFX run in translucent layer.

## To run:
1. Compile the JAVA file: `javac -classpath JAVAFX_SDK_HOME DisplayMarquee.java`
2. Create JAR file: `jar cmf MANIFEST.MF DisplayMarquee.jar DisplayMarquee.class`
3. Run: `java -Dcom.sun.javafx.transparentFramebuffer=true -jar DisplayMarquee.jar "Marquee text"`

> `JAVAFX_SDK_HOME` is the path to the JAR file for JavaFX. If you don't know where it is simple do a `find` at `/` level as: `:/$ find -name jfxrt.jar` 