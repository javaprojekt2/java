package clientFTP;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

import javax.swing.*;

public class ListTransferHandler extends TransferHandler {
	DataFlavor localArrayListFlavor, serialArrayListFlavor;
	String localArrayListType = DataFlavor.javaJVMLocalObjectMimeType
			+ ";class=java.util.ArrayList";
	JList source = null;
	int[] indices = null;
	int addIndex = -1; // Location where items were added
	int addCount = 0; // Number of items added
	Boolean isdrag = false;
	private ClientFTPController controller;
	public ListTransferHandler(ClientFTPController controller) {
		this.controller=controller;
		try {
			localArrayListFlavor = new DataFlavor(localArrayListType);
		} catch (ClassNotFoundException e) {
			System.out
					.println("ReportingListTransferHandler: unable to create data flavor");
		}
		serialArrayListFlavor = new DataFlavor(ArrayList.class, "ArrayList");
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}
		if (support.isDrop()) {
			isdrag = true;// To know whether it is a drag and drop in exportdone

		}

		return true;
	}

	protected void exportDone(JComponent c, Transferable data, int action) {
		JList a = (JList) c;

		if ((action == MOVE) && (indices != null) && isdrag) {

			if ((a.getSelectedValue() instanceof File)
					&& ((File) a.getSelectedValue()).isFile()) {
				File f=(File) a.getSelectedValue();
				controller.put(f.getName());
			
			
			}
			if ((a.getSelectedValue() instanceof String)
					&& ((String) a.getSelectedValue()).contains(" file")) {
				String tekst=(String) a.getSelectedValue();
				String[] commandArray =tekst.split("\\s+");
				// read the command "CMD"
				String cmd = commandArray[0];
				controller.get(cmd);
			}
		}

	}

	private boolean hasLocalArrayListFlavor(DataFlavor[] flavors) {
		if (localArrayListFlavor == null) {
			return false;
		}

		for (int i = 0; i < flavors.length; i++) {
			if (flavors[i].equals(localArrayListFlavor)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasSerialArrayListFlavor(DataFlavor[] flavors) {
		if (serialArrayListFlavor == null) {
			return false;
		}

		for (int i = 0; i < flavors.length; i++) {
			if (flavors[i].equals(serialArrayListFlavor)) {
				return true;
			}
		}
		return false;
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		if (hasLocalArrayListFlavor(flavors)) {
			return true;
		}
		if (hasSerialArrayListFlavor(flavors)) {
			return true;
		}
		return false;
	}

	protected Transferable createTransferable(JComponent c) {
		if (c instanceof JList) {
			source = (JList) c;
			indices = source.getSelectedIndices();
			Object[] values = source.getSelectedValues();
			if (values == null || values.length == 0) {
				return null;
			}
			ArrayList<String> alist = new ArrayList<String>(values.length);
			for (int i = 0; i < values.length; i++) {
				Object o = values[i];
				String str = o.toString();
				if (str == null)
					str = "";
				alist.add(str);
			}
			return new ReportingListTransferable(alist);
		}
		return null;
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

	public class ReportingListTransferable implements Transferable {
		ArrayList data;

		public ReportingListTransferable(ArrayList alist) {
			data = alist;
		}

		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException {
			if (!isDataFlavorSupported(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return data;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { localArrayListFlavor,
					serialArrayListFlavor };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			if (localArrayListFlavor.equals(flavor)) {
				return true;
			}
			if (serialArrayListFlavor.equals(flavor)) {
				return true;
			}
			return false;
		}
	}
}