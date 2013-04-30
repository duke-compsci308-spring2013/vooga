package util.fileformatter.formatter;



import java.io.File;
import util.fileformatter.exceptions.*;



/**
 * This is a class that can switch between absolute and relative file paths,
 * and convert a filepath to a fully qualified java class name,
 * which can be used for reflection.
 * 
 * @author Will Nance
 * 
 */
public class FilePathFormatter {
    private static final String JAVA_FILE_EXTENSION = ".java";
    /*
     * If for some reason you don't keep your java files in a src folder,
     * then you should change this. However, I am under the impression that
     * src is almost always the root directory in a java project.
     * If I am wrong, then I may have to re-think the design
     * here a little.
     */
    public static final String SOURCE = "src";
    private String projectDir = System.getProperty("user.dir");
    
    /**
     * Takes a Relative Filepath and tacks on the local system file path leading to it. 
     * Note this does not ensure that the file exists.
     * @param relativePath
     * @return
     */
    public String relativeToAbsolute (String relativePath) {
        // this should be someone else's problem. deal with your own null pointer exceptions
        if (relativePath == null) return null;
        return projectDir + SOURCE + relativePath;
    }
    /**
     * Takes an absolute file path and returns a filepath that is relative to 
     * the project src folder.
     * @param absolutePath
     * @return a file path relative to the src folder 
     */
    public String absoluteToRelative (String absolutePath) {
        if (absolutePath == null) return null;
        return makePathRelativeToSrc(absolutePath);
    }
    /**
     * takes a filepath  to a Java class and converts it to a string that 
     * can be used for reflection.  
     * @param path
     * @return
     * @throws InvalidJavaClassFilePath if the filepath doesnt end in ".java"
     */
    public String formatClassFilePath (String path) throws InvalidJavaClassFilePath {
        if( ! path.endsWith(JAVA_FILE_EXTENSION)) throw new InvalidJavaClassFilePath();
        if (path == null || path.equals("")) return path;
        return replaceFileSeparatorWithDots(makePathRelativeToSrc(removeFileExtension(path)));
    }
    
    /**
     * removes the file extension of the filepath. This will only work on files 
     * that have only one file extension. if you wanted to do this on Embedded ruby files 
     * for instance where the file extension is name.html.erb this would only remove one file 
     * extension, not both. 
     * @param filepath
     * @return
     */
    private String removeFileExtension (String filepath) {
        return filepath.substring(0, filepath.indexOf("."));
    }

    /**
     * Iterate through the filepath and make sure to knock off everything
     * until the last occurrence of the src directory. This will not function as
     * expected if you for some reason have code in a package called src.
     * 
     * @param path a file path
     * @return a filepath relative to the project source folder
     */
    private String makePathRelativeToSrc (String path) {
        while (path.contains(SOURCE)) {
            path = path.substring(path.indexOf(SOURCE) + SOURCE.length() + 1, path.length());
        }
        return path;
    }

    /*
     * For some reason in the below method a string.replace method call
     * doesn't do what you would expect. Thus I had to do a somewhat ugly
     * iteration through the individual characters and replace them one by one. 
     * Not happy about it but this works.  
     */
    /**
     * A convenience method for replacing the file separators with dots 
     * like java likes for reflection. It is written to be system independent.
     * 
     * @param path
     * @return
     */
    private String replaceFileSeparatorWithDots (String path) {
        char[] letters = path.toCharArray();
        String ret = "";
        for (int i = 0; i < letters.length; i++) {
            char c = letters[i];
            if (c == File.separatorChar) {
                letters[i] = '.';
            }
            ret += letters[i];
        }
        return ret;
    }

}
