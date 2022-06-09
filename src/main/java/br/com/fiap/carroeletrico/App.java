package br.com.fiap.carroeletrico;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.carroeletrico.controller.*;
import br.com.fiap.carroeletrico.dao.LocalDao;
import br.com.fiap.carroeletrico.model.Local;

public class App extends JFrame {
    public static void main(String[] args) {
		new App().init();
	}

	JPanel menu = new JPanel(new GridLayout(2, 0));
	JLabel img = new JLabel(new ImageIcon("GS.png"));
	
    JPanel registrar = new JPanel(new GridLayout(0, 3));
	JTabbedPane tabs = new JTabbedPane();
	JPanel tablePanel = new JPanel(new BorderLayout());
	
	JPanel address = new JPanel(new GridLayout(0, 1));
	JLabel nome = new JLabel("Nome da Estacao: ");
	JTextField nomeInput = new JTextField();
	JLabel rua = new JLabel("Rua");
	JTextField ruaInput = new JTextField();
	JLabel bairro = new JLabel("Bairro");
	JTextField bairroInput = new JTextField();
	JLabel cidade = new JLabel("Cidade");
	JTextField cidadeInput = new JTextField();
	JLabel estado = new JLabel("Estado");
	String[] estadosArr = { "Selecione um", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "PA",
			"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
	JComboBox<String> estadosSelector = new JComboBox<>(estadosArr);

	JPanel tomada = new JPanel(new GridLayout(0, 1));
	JCheckBox type1 = new JCheckBox("type1");
	JCheckBox type2 = new JCheckBox("type2");
	JCheckBox css2 = new JCheckBox("CSS2");
	JCheckBox chaDeMo = new JCheckBox("CHAdeMO");
	List<String> checkedBoxes = new ArrayList<String>();

	JPanel outros = new JPanel(new GridLayout(0, 1));
	JLabel preco = new JLabel("Preco");
	JTextField precoInput = new JTextField();
	JLabel avaliacaoLabel = new JLabel("Avaliacao");
	StarRater avaliacao = new StarRater();

	JPanel buttons = new JPanel();
	JButton salvar = new JButton("Salvar");
	JButton limpar = new JButton("Limpar");
	ButtonController buttonController = new ButtonController(this);

	String[] columns = { "Id", "Nome", "Rua", "Bairro", "Cidade", "Estado", "Tomada", "Preco", "Avaliacao" };
	DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
	JTable table = new JTable(tableModel);
	TableController tableController = new TableController(this);
	JButton ordenarEstado = new JButton("Ordenar por Estado");

	JPanel mapa = new JPanel();
	JLabel postos = new JLabel("Postos");
	private String[] valorInicialLista = {"Lista Vazia!"};
	JComboBox<String> postosSelector = new JComboBox<>(valorInicialLista);

	public App() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}
		setSize(515, 820);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("CarroEletrico");
	}

	public void init() {
		address.add(nome);
		address.add(nomeInput);
		address.add(rua);
		address.add(ruaInput);
		address.add(bairro);
		address.add(bairroInput);
		address.add(cidade);
		address.add(cidadeInput);
		address.add(estado);
		address.add(estadosSelector);
		registrar.add(address);

		tomada.add(type1);
		tomada.add(type2);
		tomada.add(css2);
		tomada.add(chaDeMo);
		registrar.add(tomada);

		outros.add(preco);
		outros.add(precoInput);
		outros.add(avaliacaoLabel);
		outros.add(avaliacao);
		salvar.setActionCommand("Salvar");
		salvar.addActionListener(buttonController);
		limpar.setActionCommand("Limpar");
		limpar.addActionListener(buttonController);
		buttons.add(salvar);
		buttons.add(limpar);
		outros.add(buttons);
		registrar.add(outros);

		ordenarEstado.setActionCommand("Ordenar");
		ordenarEstado.addActionListener(buttonController);
		tablePanel.add(ordenarEstado, BorderLayout.SOUTH);
		table.addMouseListener(tableController);
		table.setDefaultEditor(Object.class, null);
		tablePanel.add(new JScrollPane(table));

		tabs.add("Registrar", registrar);
		tabs.add("Tabela", tablePanel);

		mapa.add(postos);
		mapa.add(postosSelector);
		tabs.add("Mapa", mapa);
		menu.add(tabs);
		menu.add(img);
		this.add(menu);
		setVisible(true);
	}

	public JTextField getNomeInput() {
		return nomeInput;
	}

	public JTextField getRuaInput() {
		return ruaInput;
	}

	public JTextField getBairroInput() {
		return bairroInput;
	}

	public JTextField getCidadeInput() {
		return cidadeInput;
	}

	public JComboBox<String> getEstadosSelector() {
		return estadosSelector;
	}

	public List<String> getCheckedboxes() {
		for (java.awt.Component child : tomada.getComponents()) {
			if (child instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) child;
				if (checkBox.isSelected()) {
					checkedBoxes.add(checkBox.getText());
				}
			}
		}
		return checkedBoxes;
	}

	public JTextField getPrecoInput() {
		return precoInput;
	}

	public StarRater getAvaliacao() {
		return avaliacao;
	}

	public void cleanData() {
		nomeInput.setText("");
		ruaInput.setText("");
		bairroInput.setText("");
		cidadeInput.setText("");
		estadosSelector.setSelectedIndex(0);
		type1.setSelected(false);
		type2.setSelected(false);
		css2.setSelected(false);
		chaDeMo.setSelected(false);
		precoInput.setText("");
		avaliacao.setSelection(0);
	}

	public void loadData() {
		tableModel.setRowCount(0);
		List<Local> list = new LocalDao().showAll();
		list.forEach(local -> tableModel.addRow(local.getData()));

		postosSelector.removeAllItems();
		postosSelector.addItem("Selecione um");
		list.forEach(local -> postosSelector.addItem(local.getData().get(2)));
	}

	public void loadDataOrdered() {
		tableModel.setRowCount(0);
		List<Local> list = new LocalDao().orderByStates();
		list.forEach(local -> tableModel.addRow(local.getData()));
	}
}
