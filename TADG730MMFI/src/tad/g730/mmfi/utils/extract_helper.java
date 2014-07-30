package tad.g730.mmfi.utils;
/*codes in this class are referenced from Koush Superuser
 * It's used to extract files from assets
 * */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.content.Context;

public class extract_helper {
	public static File extractAssets(Context paramContext,String paramString) throws IOException, InterruptedException {
        ZipFile zf = new ZipFile(paramContext.getPackageCodePath());
        ZipEntry su = zf.getEntry("assets/"+paramString);
        InputStream zin = zf.getInputStream(su);
        File ret = paramContext.getFileStreamPath(paramString);
        FileOutputStream fout = new FileOutputStream(ret);
        stream_utility.copyStream(zin, fout);
        zin.close();
        zf.close();
        fout.close();
        return ret;
    }
	
	
}
