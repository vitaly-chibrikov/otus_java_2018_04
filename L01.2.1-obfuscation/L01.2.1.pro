#java -jar proguard\proguard-base-6.0.2.jar @L01.2.1.pro

-injars       target/L01.2.1.jar
-outjars      target/L01.2.1-out.jar

-printmapping pgmapout.map
-dontwarn

-keep public class ru.otus.l0111.Main {public static void main(java.lang.String[]);}