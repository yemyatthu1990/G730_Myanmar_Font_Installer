/*
 * Copyright © 2014 TAD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
