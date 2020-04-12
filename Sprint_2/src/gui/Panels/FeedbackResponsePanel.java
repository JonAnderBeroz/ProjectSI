package gui.Panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import businessLogic.BLFacade;
import domain.Feedback;
import gui.MainGUI;
import gui.components.NonEditableTableModel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

@SuppressWarnings("serial")
public class FeedbackResponsePanel extends JPanel {
	
	private JTable feedbackTable;
	private NonEditableTableModel feedbackTableModel;
	
	private JLabel senderLabel; 
	private JLabel emailLabel;
	private JLabel issuedescriptionLabel;
	private JTextPane detailsTextPane;
	
	private 
	
	BLFacade facade = MainGUI.getBusinessLogic();
	
	/**
	 * Create the panel.
	 */
	public FeedbackResponsePanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[20:69.00][146.00][227.00][10px:74.00][35px:n][][298.00,grow][55.00]", "[30:38.00][][20:48.00][][344.00,grow][39.00][40:39.00]"));
		
		JLabel titleLabel = new JLabel("Feedback");
		titleLabel.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 28));
		add(titleLabel, "cell 1 1 6 1,alignx left");
		
		String[] columnnames =new String[] {"Type", "Issue", "Sender", "Date"};
	
		
		
		
		//feedbackTableModel.setDataVector(null, columnnames);
		

		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 3 2 3,grow");
		
		feedbackTable = new JTable();
		feedbackTable.setFont(new Font("Source Sans Pro", Font.ITALIC, 16));
		feedbackTable.setRowHeight(40);
		feedbackTable.getTableHeader().setReorderingAllowed(false);
		feedbackTable.getTableHeader().setResizingAllowed(false);
		feedbackTable.getTableHeader().setFont(new Font("Source sans Pro", Font.BOLD, 16));
		feedbackTable.getTableHeader().setBackground(new Color(255,255,255));
		feedbackTableModel = new NonEditableTableModel(null, columnnames);
		feedbackTableModel.setColumnCount(5); // another column added to allocate fb objects
		feedbackTable.setModel(feedbackTableModel);
		feedbackTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		feedbackTable.getColumnModel().getColumn(0).setMinWidth(90);
		feedbackTable.getColumnModel().getColumn(0).setMaxWidth(90);
		feedbackTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		feedbackTable.getColumnModel().getColumn(1).setMinWidth(110);
		feedbackTable.getColumnModel().getColumn(1).setMaxWidth(999999);
		feedbackTable.getColumnModel().getColumn(2).setMinWidth(75);
		feedbackTable.getColumnModel().getColumn(2).setMaxWidth(75);
		feedbackTable.getColumnModel().getColumn(3).setPreferredWidth(110);
		feedbackTable.getColumnModel().getColumn(3).setMinWidth(110);
		feedbackTable.getColumnModel().getColumn(3).setMaxWidth(110);
		feedbackTable.getColumnModel().removeColumn(feedbackTable.getColumnModel().getColumn(4));

		Vector<Feedback> feedback = facade.getFeedback();
		
		for( Feedback fb: feedback) {
			System.out.println(fb.toString());
			Vector<Object> row = new Vector<Object>();

			row.add(fb.getFbtype());
			row.add(fb.getSummary());
			row.add(fb.getName());
			row.add(fb.getSubmissiondate());
			row.add(fb);
			feedbackTableModel.addRow(row);				
		}

		feedbackTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = feedbackTable.getSelectedRow();
				Feedback selectedfb = (Feedback)feedbackTableModel.getValueAt(i, 4);
				
				senderLabel.setText(selectedfb.getName());
				emailLabel.setText(selectedfb.getEmail());
				issuedescriptionLabel.setText(selectedfb.getSummary());
				detailsTextPane.setText(selectedfb.getDetails());		
			}
		});
		scrollPane.setViewportView(feedbackTable);
		
		TableRowSorter<NonEditableTableModel> sorter = new TableRowSorter<NonEditableTableModel>(feedbackTableModel);
		feedbackTable.setRowSorter(sorter);	
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		panel.setBackground(new Color(240, 248, 255));
		add(panel, "cell 4 3 3 3,grow");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{35, 0, 70, 43, 0, 24, 156, 29, 30, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 25, 0, 27, 0, 261, 0, 49, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSender = new JLabel("Sender:");
		lblSender.setFont(new Font("Source Sans Pro ExtraLight", Font.BOLD, 16));
		GridBagConstraints gbc_lblSender = new GridBagConstraints();
		gbc_lblSender.anchor = GridBagConstraints.WEST;
		gbc_lblSender.gridwidth = 3;
		gbc_lblSender.insets = new Insets(0, 0, 5, 5);
		gbc_lblSender.gridx = 1;
		gbc_lblSender.gridy = 1;
		panel.add(lblSender, gbc_lblSender);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Source Sans Pro ExtraLight", Font.BOLD, 16));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridwidth = 4;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 4;
		gbc_lblEmail.gridy = 1;
		panel.add(lblEmail, gbc_lblEmail);
		
		senderLabel = new JLabel("");
		GridBagConstraints gbc_senderLabel = new GridBagConstraints();
		gbc_senderLabel.anchor = GridBagConstraints.WEST;
		gbc_senderLabel.gridwidth = 3;
		gbc_senderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_senderLabel.gridx = 1;
		gbc_senderLabel.gridy = 2;
		panel.add(senderLabel, gbc_senderLabel);
		
		emailLabel = new JLabel("");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.WEST;
		gbc_emailLabel.gridwidth = 3;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 4;
		gbc_emailLabel.gridy = 2;
		panel.add(emailLabel, gbc_emailLabel);
		
		JLabel issueLabel = new JLabel("Issue:");
		issueLabel.setFont(new Font("Source Sans Pro ExtraLight", Font.BOLD, 17));
		GridBagConstraints gbc_issueLabel = new GridBagConstraints();
		gbc_issueLabel.anchor = GridBagConstraints.WEST;
		gbc_issueLabel.gridwidth = 7;
		gbc_issueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueLabel.gridx = 1;
		gbc_issueLabel.gridy = 3;
		panel.add(issueLabel, gbc_issueLabel);
		
		issuedescriptionLabel = new JLabel("");
		GridBagConstraints gbc_issuedescriptionLabel = new GridBagConstraints();
		gbc_issuedescriptionLabel.anchor = GridBagConstraints.WEST;
		gbc_issuedescriptionLabel.gridwidth = 7;
		gbc_issuedescriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issuedescriptionLabel.gridx = 1;
		gbc_issuedescriptionLabel.gridy = 4;
		panel.add(issuedescriptionLabel, gbc_issuedescriptionLabel);
		
		JLabel detailsLabel = new JLabel("Details:");
		detailsLabel.setFont(new Font("Source Sans Pro ExtraLight", Font.BOLD, 16));
		GridBagConstraints gbc_detailsLabel = new GridBagConstraints();
		gbc_detailsLabel.anchor = GridBagConstraints.WEST;
		gbc_detailsLabel.gridwidth = 7;
		gbc_detailsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_detailsLabel.gridx = 1;
		gbc_detailsLabel.gridy = 5;
		panel.add(detailsLabel, gbc_detailsLabel);
		
		detailsTextPane = new JTextPane();
		detailsTextPane.setEditable(false);
		detailsTextPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		GridBagConstraints gbc_detailsTextPane = new GridBagConstraints();
		gbc_detailsTextPane.gridwidth = 7;
		gbc_detailsTextPane.insets = new Insets(0, 0, 5, 5);
		gbc_detailsTextPane.fill = GridBagConstraints.BOTH;
		gbc_detailsTextPane.gridx = 1;
		gbc_detailsTextPane.gridy = 6;
		panel.add(detailsTextPane, gbc_detailsTextPane);
		
		JLabel fileLabel = new JLabel("Attached file:");
		fileLabel.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		GridBagConstraints gbc_fileLabel = new GridBagConstraints();
		gbc_fileLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileLabel.gridx = 1;
		gbc_fileLabel.gridy = 7;
		panel.add(fileLabel, gbc_fileLabel);
		
		JButton responseButton = new JButton("Write response");
		responseButton.setBackground(new Color(192, 192, 192));
		responseButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		responseButton.setFont(new Font("Source Sans Pro Light", Font.BOLD, 20));
		responseButton.setFocusPainted(false);
		GridBagConstraints gbc_responseButton = new GridBagConstraints();
		gbc_responseButton.fill = GridBagConstraints.BOTH;
		gbc_responseButton.insets = new Insets(0, 0, 5, 5);
		gbc_responseButton.gridx = 6;
		gbc_responseButton.gridy = 8;
		panel.add(responseButton, gbc_responseButton);
	}

}