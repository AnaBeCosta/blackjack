package Blackjack;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SimpleFileFilter extends FileFilter {
	private final String extension;
	private final String description;

	public SimpleFileFilter(String extension, String description) {
		this.extension = extension != null ? extension : "";
		this.description = description != null ? description : "";
	}

	@Override
	public boolean accept(File file) {
		return file != null && file.isFile() && file.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		return description;
	}
}
