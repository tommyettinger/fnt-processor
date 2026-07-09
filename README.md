# fnt-processor
A drag-and-drop or command-line tool that processes AngelCode BMFont .fnt and .png files for TextraTypist usage.

# Usage
Try to drag and drop your .fnt file(s) onto the .exe if you're using Windows. This will rewrite and (hopefully) shrink
the sizes of .png files, as well as add or change a few lines in the .fnt file (in-place).

The .png file each .fnt file loads should be in the same folder as the .fnt file, with the same name as the .fnt except
for the extension. Hiero already names .png files the same as their matching .fnt files. AngelCode BMFont will add an
underscore and a number to the names of .png files; you should rename the .png file and change the third line of the
.fnt using it to refer to the new name. This means changing this line:

```
page id=0 file="MyFont_0.png"
```

to look like:

```
page id=0 file="MyFont.png"
```

Renaming this line isn't critical for this program to run, but it is used by BitmapFont when loading a .fnt normally. If
you're using a TextureAtlas to load the .png from a TextureRegion, naming the files the same except for extension is a
good idea, anyway.

If the Windows .exe doesn't work for you, there's a JAR that does require Java to run (Java 8 or higher will work). This
needs to be run from the command line, as with: 

`java -jar fnt-processor.jar font.fnt`

That approach will also work or the .exe, though it doesn't use `java -jar ` at the start. Like with the .exe, this
rewrites the .fnt and .png files in-place. You can supply multiple .fnt files with either approach.

If you intend to ship the .png files without packing them into a TextureAtlas, consider running the .png files through a
PNG optimizer, such as [oxipng](https://github.com/oxipng/oxipng), which is lossless, or
[pngquant](https://pngquant.org), which is lossy. If you use oxipng, use the `--ng` (no grayscale) argument to ensure
the files will be loadable by libGDX when using OpenGL ES 3.0 compatibility. You can also run your texture atlases
through a PNG optimizer.

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
