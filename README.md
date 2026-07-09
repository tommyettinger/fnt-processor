# fnt-processor
A drag-and-drop or command-line tool that processes AngelCode BMFont .fnt and .png files for TextraTypist usage.

# Usage
Try to drag and drop your .fnt file onto the .exe if you're using Windows. The .png file it loads should be in the same
folder as the .fnt file.

If the Windows .exe doesn't work for you, there's a JAR that does require Java to run (Java 8 or higher will work). This
needs to be run from the command line, as with: 

`java -jar fnt-processor.jar font.fnt`

That approach will also work or the .exe, though it doesn't use `java -jar ` at the start.

# Thanks
This project uses the great [PicoCLI](https://picocli.info/) library for clean command-line handling.
Of course, this uses [libGDX](https://libgdx.com/); I can't get by without it.
This uses Graal Native Image, which is wonderful for apps like this. I just have to thank some of the early pioneers
who combined Graal Native Image with libGDX: [Yi An](https://github.com/anyicomplex), [ByerN](https://github.com/ByerN),
and [Berstanio](https://github.com/Berstanio) have all made a great deal of knowledge available for working with what
has usually been a very complex tool.

# Notes

Build a Graal Native Image exe by finding the GraalVM 24 bin folder and using its native-image:

`native-image.cmd -cp picocli-4.7.7.jar --enable-native-access=ALL-UNNAMED -jar fnt-processor.jar`
