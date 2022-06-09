package br.com.fiap.carroeletrico.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.fiap.carroeletrico.dao.LocalDao;
import br.com.fiap.carroeletrico.model.Local;
import br.com.fiap.carroeletrico.App;

public class ButtonController implements ActionListener {

	LocalDao dao = new LocalDao();

	private App app;

	public ButtonController(App app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Salvar") {
			Local local = new Local();
			local.setNome(app.getNomeInput().getText());
			local.setRua(app.getRuaInput().getText());
			local.setBairro(app.getBairroInput().getText());
			local.setCidade(app.getCidadeInput().getText());
			local.setEstado(app.getEstadosSelector().getSelectedItem().toString());
			local.setTomada(app.getCheckedboxes());
			local.setPreco(Float.parseFloat(app.getPrecoInput().getText()));
			local.setAvaliacao(app.getAvaliacao().getSelection());
			dao.insert(local);
			app.loadData();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");		
			}
		if(e.getActionCommand() == "Limpar") {
			app.cleanData();
		}
        if(e.getActionCommand() == "Ordenar") {
			app.loadDataOrdered();
		}
	}	
}
