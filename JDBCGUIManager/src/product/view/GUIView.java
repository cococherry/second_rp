package product.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import product.controller.ProductController;
import product.model.vo.Product;

public class GUIView extends JFrame {
	private Scanner sc = new Scanner(System.in);
	private String[] columnItem = { "Product_ID", "P_Name", "Price", "Description" };
	private JTable table;
	private JTextField searchTF;
	private JTextField pIDTF;
	private JTextField pName;
	private JTextField pDesTF;
	private JSpinner spinner;

	public GUIView() {
		ProductController controller = new ProductController();
		setTitle("상품관리 프로그램");
		setBounds(new Rectangle(250, 150, 800, 500));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 448, 471);
		add(panel1);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 448, 471);
		table = new JTable(controller.tableRow(controller.selectAll()), columnItem);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		panel1.add(scrollPane);

		JPanel panel2 = new JPanel();
		panel2.setBounds(448, 0, 346, 471);
		add(panel2);
		panel2.setLayout(null);

		JRadioButton rRB = new JRadioButton("Product ID");
		rRB.setBounds(8, 6, 94, 23);
		panel2.add(rRB);

		JRadioButton rRB2 = new JRadioButton("Product Name");
		rRB2.setBounds(116, 6, 121, 23);
		panel2.add(rRB2);

		ButtonGroup pGroup = new ButtonGroup();
		pGroup.add(rRB);
		pGroup.add(rRB2);

		JButton btnList = new JButton("목록보기");
		btnList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				table = new JTable(controller.tableRow(controller.selectAll()), columnItem);
				table.setBackground(Color.WHITE);
				scrollPane.remove(table);
				scrollPane.add(table);
				scrollPane.setViewportView(table);
			}
		});
		btnList.setBounds(245, 6, 97, 23);
		panel2.add(btnList);

		searchTF = new JTextField();
		searchTF.setFont(new Font("나눔고딕코딩", Font.PLAIN, 16));
		searchTF.setBounds(12, 52, 240, 31);
		panel2.add(searchTF);
		searchTF.setColumns(10);

		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(264, 55, 78, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!searchTF.getText().isEmpty()) {
					try {
						if (rRB.isSelected()) {
							if (controller.selectID(searchTF.getText()) != null) {
								Product p = controller.selectID(searchTF.getText());
								table = new JTable(controller.tableRow(p),
										columnItem);
								pIDTF.setText(p.getProductId());
								pName.setText(p.getpName());
								spinner.setValue(p.getPrice());
								pDesTF.setText(p.getDescription());
								scrollPane.remove(table);
								scrollPane.add(table);
								scrollPane.setViewportView(table);
							}
						} else if (rRB2.isSelected()) {
							if (controller.selectName(searchTF.getText()) != null) {
								Product p = controller.selectName(searchTF.getText());
								table = new JTable(controller.tableRow(p),
										columnItem);
								pIDTF.setText(p.getProductId());
								pName.setText(p.getpName());
								spinner.setValue(p.getPrice());
								pDesTF.setText(p.getDescription());
								scrollPane.remove(table);
								scrollPane.add(table);
								scrollPane.setViewportView(table);
							}
						} else {
							table = new JTable(controller.tableRow(controller.selectAll()), columnItem);
							scrollPane.remove(table);
							scrollPane.add(table);
							scrollPane.setViewportView(table);
						}
					} catch (NullPointerException e1) {
					}
				} else
					JOptionPane.showMessageDialog(null, "검색할 값을 입력해주세요.");
			}
		});
		panel2.add(btnSearch);

		JLabel lbl_Text = new JLabel("------ 상세보기 ------");
		lbl_Text.setBounds(107, 199, 145, 15);
		panel2.add(lbl_Text);

		JLabel lbl_pID = new JLabel("상품 ID :");
		lbl_pID.setBounds(12, 260, 57, 15);
		panel2.add(lbl_pID);

		JLabel lbl_pName = new JLabel("상품 명 :");
		lbl_pName.setBounds(12, 300, 57, 15);
		panel2.add(lbl_pName);

		JLabel lbl_pPrice = new JLabel("상품 가격 :");
		lbl_pPrice.setBounds(12, 339, 78, 15);
		panel2.add(lbl_pPrice);

		JLabel lbl_pDesc = new JLabel("상품 설명 :");
		lbl_pDesc.setBounds(12, 374, 78, 15);
		panel2.add(lbl_pDesc);

		pIDTF = new JTextField();
		pIDTF.setBounds(107, 257, 194, 21);
		panel2.add(pIDTF);
		pIDTF.setColumns(10);

		pName = new JTextField();
		pName.setColumns(10);
		pName.setBounds(107, 297, 194, 21);
		panel2.add(pName);

		pDesTF = new JTextField();
		pDesTF.setColumns(10);
		pDesTF.setBounds(107, 371, 194, 21);
		panel2.add(pDesTF);

		spinner = new JSpinner();
		spinner.setBounds(180, 336, 121, 22);
		panel2.add(spinner);

		JButton insertBtn = new JButton("추가");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product p = new Product();
				p.setProductId(pIDTF.getText());
				p.setpName(pName.getText());
				p.setPrice((int)spinner.getValue());
				p.setDescription(pDesTF.getText());
				controller.insertProduct(p);
			}
		});
		insertBtn.setBounds(56, 421, 67, 23);
		panel2.add(insertBtn);

		JButton updateBtn = new JButton("수정");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.updateProduct(new Product(pIDTF.getText(), pName.getText(),
						(int)spinner.getValue(),pDesTF.getText()));
			}
		});
		updateBtn.setBounds(139, 421, 67, 23);
		panel2.add(updateBtn);

		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setBounds(218, 421, 67, 23);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteProduct(pIDTF.getText());
			}
		});
		panel2.add(deleteBtn);

		setVisible(true);
	}

	public JTextField getpIDTF() {
		return pIDTF;
	}

	public void setpIDTF(JTextField pIDTF) {
		this.pIDTF = pIDTF;
	}

	public JTextField getpName() {
		return pName;
	}

	public void setpName(JTextField pName) {
		this.pName = pName;
	}

	public JTextField getpDesTF() {
		return pDesTF;
	}

	public void setpDesTF(JTextField pDesTF) {
		this.pDesTF = pDesTF;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
}
