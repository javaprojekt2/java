package clientFTP;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * This code uses a JList in two forms (layout orientation vertical & horizontal
 * wrap) to display a File[]. The renderer displays the file icon obtained from
 * FileSystemView.
 */

class TextFileFilter implements FileFilter {

	public boolean accept(File file) {
		// implement the logic to select files here..
		String name = file.getName().toLowerCase();
		// return name.endsWith(".java") || name.endsWith(".class");
		return name.length() < 20;
	}
}

class FileRenderer extends DefaultListCellRenderer {

	private ClientFTPController controller;

	FileRenderer(ClientFTPController controller) {
		this.controller = controller;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		Component c = super.getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);
		JLabel l = (JLabel) c;
		File f = (File) value;

		String name;
		
		File tmp=new File(controller.getPwd());
		if (f.getAbsolutePath().equals(tmp.getParentFile().getAbsolutePath())) {
			name = "..";
			
		} else
			name = f.getName();
		
		l.setText(name);
		l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));

		return l;
	}
}

class FileRenderer1 extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);
		JLabel l = (JLabel) c;
		String item = (String) value;

		String[] lista = item.split("\\s+");
		File f;
		if (lista.length < 2)
			return l;
		if (lista[1].equals("directory")) {

			f = new File(".");
		} else {
			f = new File("clientFile");
			if (!f.exists())
				try {
					f.createNewFile();
				} catch (IOException e) {
					return l;
				}
		}
		l.setText(lista[0]);
		l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
		f.delete();
		return l;

	}
}