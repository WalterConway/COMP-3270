import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
@SuppressWarnings("serial")
public class MaxPriorityQueueGUI extends JFrame {
	//Width of the gui window.
	private static final int WIDTH = 400;
	//Height of the gui window.
	private static final int HEIGHT = 300;
	//The max heap size.
	private static final int HEAPSIZE = 10;
	private Container pane;
	private JLabel object_ID_Label;
	private JLabel Priority_Label;
	private JPanel changeButtonsPanel;
	private JTextField enqueObject_Priority_TxtField;
	private JTextField changeObject_Priority_TxtField;
	private JComboBox<Integer> enqueObject_ID_ComboBox;
	private JComboBox<Integer> changeObject_ID_ComboBox;
	private JButton enqueue_Button;
	private JButton changeWeight_Button;
	private JButton currentWeight_Button;
	private JButton priorityQueue_Button;
	private JButton dequeue_Button;

	PriorityQueue priorityQue;
	ELEM[] elemArray;

	public MaxPriorityQueueGUI() throws HeadlessException {
		setTitle("Max Priority Queue");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane = getContentPane();
		pane.setLayout(new GridLayout(4, 3));

		//Create Labels
		object_ID_Label = new JLabel("OBJECT ID",SwingConstants.CENTER);
		Priority_Label = new JLabel("Priority Weight",SwingConstants.CENTER);

		//Create Text Fields
		enqueObject_Priority_TxtField = new JTextField(10);
		changeObject_Priority_TxtField = new JTextField(10);
		changeButtonsPanel = new JPanel();

		//Create an instance of the heap
		elemArray =  new ELEM[HEAPSIZE];		
		priorityQue = new PriorityQueue(elemArray, HEAPSIZE);

		//Create Buttons
		enqueue_Button = new JButton("Enqueue");
		changeWeight_Button = new JButton("Change Weight");
		dequeue_Button = new JButton("Dequeue");
		currentWeight_Button = new JButton("Current Weight");
		priorityQueue_Button = new JButton("View The Heap");

		//Create combo Boxes
		changeObject_ID_ComboBox = new JComboBox<Integer>();

		//Populating the enqueue possibilities
		Integer[] IntegerPopulation = new Integer[HEAPSIZE];
		for(int i = 0; i<HEAPSIZE; i++){
			IntegerPopulation[i] = new Integer(i);
		}
		//loading the population in the combo box
		enqueObject_ID_ComboBox = new JComboBox<Integer>(IntegerPopulation);

		//Add Listeners for the Buttons
		//enqueue button listener
		enqueue_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enqueObject_ID_ComboBox.getItemCount()>0){
					//Get input from the enqueue text fields
					String tempObjectPriority = enqueObject_Priority_TxtField.getText();
					int objectid;
					int objectpriority;
					//need to check for negative input
					try{					
						objectid = enqueObject_ID_ComboBox.getItemAt(enqueObject_ID_ComboBox.getSelectedIndex());
						objectpriority = Integer.parseInt(tempObjectPriority);
						try {
							priorityQue.enqueue(objectid, objectpriority);
						} catch (FullHeapException e1) {
							JOptionPane.showMessageDialog(pane, e1.getMessage(), "Full Heap", JOptionPane.ERROR_MESSAGE);
							return;
						} catch(ObjectIDOutOfBoundsException e1){
							JOptionPane.showMessageDialog(pane, e1.getMessage(), "Object ID is invalid", JOptionPane.ERROR_MESSAGE);
							return;
						} 
					} catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(pane, "Please use integer values", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						return;

					}catch (DuplicateObjectIDException e1) {
						JOptionPane.showMessageDialog(pane, e1.getMessage(), "Duplicate ID", JOptionPane.ERROR_MESSAGE);
						return;
					}

					changeObject_ID_ComboBox.addItem(enqueObject_ID_ComboBox.getItemAt(enqueObject_ID_ComboBox.getSelectedIndex()));
					enqueObject_ID_ComboBox.removeItemAt(enqueObject_ID_ComboBox.getSelectedIndex());
					enqueObject_Priority_TxtField.setText("");
				} else{
					JOptionPane.showMessageDialog(pane, "Heap is full, inorder to enqueue at this point you must dequeue.", "Full Heap", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		//dequeue button listener
		dequeue_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dequeObject = -1;
				try {
					dequeObject = priorityQue.dequeue();
				} catch (EmptyHeapException e1) {
					JOptionPane.showMessageDialog(pane, "Queue is empty", "Dequeue Failed", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (dequeObject != -1) {			
					//adding the object id to the enqueue combobox
					enqueObject_ID_ComboBox.addItem(new Integer(dequeObject));

					//finding the object id in the combobox to remove it 
					for(int i = 0;i<changeObject_ID_ComboBox.getItemCount(); i++){
						if(changeObject_ID_ComboBox.getItemAt(i).intValue() == dequeObject){
							changeObject_ID_ComboBox.removeItemAt(i);
							break;
						}
					}
					JOptionPane.showMessageDialog(pane, "Object ID:" + dequeObject,"Dequeue Object ID", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		//Change Weight Button listener
		changeWeight_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(changeObject_ID_ComboBox.getItemCount()>0){
					String tempNewPriority = changeObject_Priority_TxtField.getText();
					int tempNewWeight;
					try{
						tempNewWeight = Integer.parseInt(tempNewPriority);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(pane, "Please use integer values", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						priorityQue.changeWeight(changeObject_ID_ComboBox.getItemAt(changeObject_ID_ComboBox.getSelectedIndex()).intValue(), tempNewWeight);
					}catch (EmptyHeapException e1){
						JOptionPane.showMessageDialog(pane, "Queue is empty", "Change Weight Failed", JOptionPane.ERROR_MESSAGE);
						return;
					} catch (DuplicateObjectIDException e1) {
						JOptionPane.showMessageDialog(pane, e1.getMessage(), "Duplicate ID", JOptionPane.ERROR_MESSAGE);
						return;
					} catch (FullHeapException e1) {
						JOptionPane.showMessageDialog(pane, e1.getMessage(), "Full Heap", JOptionPane.ERROR_MESSAGE);
						return;
					} catch (ObjectIDOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(pane, e1.getMessage(), "Object ID is invalid", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else{
					JOptionPane.showMessageDialog(pane, "Items must be enququed before you can change the weight.", "Empty Heap", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		//Checking the current weight button listener
		currentWeight_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(changeObject_ID_ComboBox.getItemCount()>0){
					changeObject_Priority_TxtField.setText(String.valueOf(priorityQue.getWeightOfObject_ID((changeObject_ID_ComboBox.getItemAt(changeObject_ID_ComboBox.getSelectedIndex())).intValue())));
				} else{
					JOptionPane.showMessageDialog(pane, "Items must be enququed before you can get the current weight.", "Empty Heap", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		//Viewing the heap data structure button listener.
		priorityQueue_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(changeObject_ID_ComboBox.getItemCount()>0){
					new JHeapDialog(priorityQue.getHeap());

				}else{
					JOptionPane.showMessageDialog(pane, "Cannot view an empty Queue. Enqueue Something.", "Queue Is Empty", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		//Adding the java swing components to the frame.
		pane.add(new JLabel("<html>Authors:<br>Matthew Garmon<br>Walter Conway</html>"));
		pane.add(object_ID_Label);
		pane.add(Priority_Label);
		pane.add(enqueue_Button);
		pane.add(enqueObject_ID_ComboBox);
		pane.add(enqueObject_Priority_TxtField);
		pane.add(changeButtonsPanel);
		changeButtonsPanel.add(changeWeight_Button);
		changeButtonsPanel.add(currentWeight_Button);
		pane.add(changeObject_ID_ComboBox);
		pane.add(changeObject_Priority_TxtField);
		pane.add(dequeue_Button);
		//Adding a spacer
		pane.add(new JLabel());
		pane.add(priorityQueue_Button);
		setVisible(true);
	}

	/**
	 * @param arg0
	 */
	public MaxPriorityQueueGUI(GraphicsConfiguration arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public MaxPriorityQueueGUI(String arg0) throws HeadlessException {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MaxPriorityQueueGUI(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MaxPriorityQueueGUI mpqGUI = new MaxPriorityQueueGUI();
		//making the gui centered.
		mpqGUI.setLocationRelativeTo(null);
		mpqGUI.setResizable(false);
	}
}

