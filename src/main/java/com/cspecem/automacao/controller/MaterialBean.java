package com.cspecem.automacao.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cspecem.automacao.model.Material;
import com.cspecem.automacao.repository.Materiais;
import com.cspecem.automacao.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MaterialBean extends ExtensaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Materiais materiais;
	private Material material;
	private List<Material> materiaisLista;
	private Material materialSelecionado;
	private List<Material> materiaisSelecionados;
	
	public MaterialBean() {
		limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			getProdutosLista();
		}
		System.out.println("inicializacao");
	}
	
	public void limpar() {
		material = new Material();
	}
	
	public void salvar() {
		try {
			this.material = materiais.salvar(material);
			limpar();
			FacesUtil.addInfoMessage("Salvo com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar salvar. " + e.getMessage());
		}
	}
	
	public void atualizar() {
		try {
			this.material = materiais.atualizar(material);
			limpar();
			FacesUtil.addInfoMessage("Atualizado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar atualizar " + e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			this.materiais.deletar(material.getId());
			FacesUtil.addInfoMessage("Equipamento " + this.material.getId() + " removido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao tentar remover " + e.getMessage());
		}
	}
	
	public boolean isEditando() {
		return this.material.getId() != null;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public List<Material> getMateriaisLista() {
		if (this.materiaisLista == null) {
			this.materiaisLista = materiais.listar("id");
		}
		return materiaisLista;
	}
	
	public Material getMaterialSelecionado() {
		return materialSelecionado;
	}

	public void setMaterialSelecionado(Material materialSelecionado) {
		this.materialSelecionado = materialSelecionado;
	}

	public List<Material> getMateriaisSelecionados() {
		return materiaisSelecionados;
	}

	public void setMateriaisSelecionados(List<Material> materiaisSelecionados) {
		this.materiaisSelecionados = materiaisSelecionados;
	}


}
