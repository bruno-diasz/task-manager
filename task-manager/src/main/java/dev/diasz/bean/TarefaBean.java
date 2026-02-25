package dev.diasz.bean;

import dev.diasz.model.Pessoa;
import dev.diasz.model.Prioridade;
import dev.diasz.model.SituacaoTarefa;
import dev.diasz.model.Tarefa;
import dev.diasz.service.TarefaService;
import dev.diasz.service.PessoaService;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

    @Inject
    private TarefaService service;
    @Inject
    private PessoaService pessoaService;

    private Tarefa tarefa;
    private List<Tarefa> lista;
    private long responsavelId;
    private List<Pessoa> pessoas;

    @PostConstruct
    public void init() {

        pessoas = pessoaService.listar();

        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null) {
            tarefa = service.buscarID(Long.parseLong(idParam));

            if (tarefa.getResponsavel() != null) {
                responsavelId = tarefa.getResponsavel().getId();
            }
        } else {
            tarefa = new Tarefa();
        }

        lista = service.listar();
    }

    public String salvar() {
        Pessoa responsavel = pessoaService.buscarID(responsavelId);
        tarefa.setResponsavel(responsavel);
        service.salvar(tarefa);
        tarefa = new Tarefa();
        responsavelId = 0;
        lista = service.listar(); 
        return "index.xhtml?faces-redirect=true";
    }

    public void editar(Tarefa t) {
        tarefa = t; 
        responsavelId = t.getResponsavel().getId(); 
    }

    public void remover(long id){
        service.remover(id);
        lista = service.listar();
    }

    public List<Tarefa> getLista() {
        return lista;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }


    public long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public Prioridade[] getPrioridades() {
        return Prioridade.values();
    }

    public SituacaoTarefa[] getSituacoes() {
        return SituacaoTarefa.values();
    }
    
    public LocalDate getHoje() {
        return LocalDate.now();
    }

    public List<Pessoa> getPessoas(){
        return pessoas;
    }
}
