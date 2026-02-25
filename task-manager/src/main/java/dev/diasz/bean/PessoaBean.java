package dev.diasz.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


import dev.diasz.model.Pessoa;
import dev.diasz.service.PessoaService;

@Named
@ViewScoped
public class PessoaBean implements Serializable{

    @Inject
    private PessoaService service;

    private Pessoa pessoa;
    private List<Pessoa> lista;

    @PostConstruct
    public void init() {
        pessoa = new Pessoa();
        lista = service.listar();
    }

    public void salvar() {
        service.salvar(pessoa);
        pessoa = new Pessoa();
        lista = service.listar(); // atualiza tabela
    }

    public void remover(Long id){
        service.remover(id);
        lista = service.listar();
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
