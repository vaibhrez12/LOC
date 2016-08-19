import java.io.*;

public class LineCount {
    private static long mNumLines = 0;
    public static void main(String[] args) throws IOException{
        countLinesForAllFilesInDirectory("E:\\SVN\trunk\\src\\notify");
        System.err.println("mNumLines:"+mNumLines);
    }
    private static File[] countLinesForAllFilesInDirectory(String directoryName) throws IOException{

        File directory = new File(directoryName);
        // get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
                mNumLines = mNumLines + countLines(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                countLinesForAllFilesInDirectory(file.getAbsolutePath());
            }
        }
        //System.out.println(fList);
        return fList;
    }
    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
