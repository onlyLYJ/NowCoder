package epg;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class IOUtil {
	
	
	private static final FileSystemView fsv = FileSystemView.getFileSystemView();
	public static final String HOME_DIRECTORY = "" + fsv.getHomeDirectory();

	
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}


	public static void checkDir(String add) {
		File file = new File(add);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
