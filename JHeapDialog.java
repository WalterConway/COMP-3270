import javax.swing.JDialog;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class JHeapDialog extends JDialog{
	JTable heapTable;

	/**
	 * @param Heap
	 */
	public JHeapDialog(heap Heap) {
		setTitle("Heap Viewer");
		setSize(100, 400);
		String[][] heapArray = new String[Heap.heapSize()][1];
		for(int i=0; i<Heap.heapSize(); i++){
			if(Heap.getHeapArray()[i] != null){
				heapArray[i][0] = String.valueOf(Heap.getHeapArray()[i].getmObject_ID());
			}else{
				heapArray[i][0] ="";
			}
		}
		heapTable = new JTable(heapArray, new String[] {"Object ID"});
		heapTable.setEnabled(false);
		add(heapTable);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
