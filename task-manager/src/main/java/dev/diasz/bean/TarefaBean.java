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

    // 
    private Tarefa tarefa; // Tarefa selecionada
    private List<Tarefa> lista; //Listagem para o front
    private List<Pessoa> pessoas; //Listagem para o front
    private Long responsavelId; 

    // Atributos de filtro
    private Long filtroId;
    private String filtroTitulo;
    private Long filtroResponsavelId;
    private SituacaoTarefa filtroSituacao;

    @PostConstruct
    public void init() {

        pessoas = pessoaService.listar();

        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        try {
            if (idParam != null && !idParam.isEmpty()) {
                tarefa = service.buscarID(Long.parseLong(idParam));
                if (tarefa != null && tarefa.getResponsavel() != null) {
                    responsavelId = tarefa.getResponsavel().getId();
                }
            } else {
                tarefa = new Tarefa();
            }
        } catch (NumberFormatException e) {
            tarefa = new Tarefa();
        }

        setFiltroSituacao(SituacaoTarefa.EM_ANDAMENTO);
        filtrar();


    }

    public String salvar() {
        if (responsavelId != null && responsavelId != 0 ){
            Pessoa responsavel = pessoaService.buscarID(responsavelId);
            tarefa.setResponsavel(responsavel);
        }
        service.salvar(tarefa);
        tarefa = new Tarefa();
        responsavelId = 0L;
        return "index.xhtml?faces-redirect=true";
    }

    public void editar(Tarefa t) {
        tarefa = t; 
        if (t.getResponsavel() != null){
            responsavelId = t.getResponsavel().getId(); 
        }else{
            responsavelId = 0L;
        }
    }

    public void remover(Long id){
        service.remover(id);
        filtrar();
    }
    
    public void concluir(Tarefa t) {
        service.concluir(t.getId());
        filtrar();
    }
    
    public void filtrar(){
     
        lista = service.buscarComFiltro(
            filtroId,
            filtroTitulo,
            filtroResponsavelId,
            filtroSituacao
        );
    }
    
    // Setters e Getters
    public List<Tarefa> getLista() {
        return lista;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }


    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
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

    public Long getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Long filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroTitulo() {
        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }


    public SituacaoTarefa getFiltroSituacao() {
        return filtroSituacao;
    }

    public void setFiltroSituacao(SituacaoTarefa filtroSituacao) {
        this.filtroSituacao = filtroSituacao;
    }

    public Long getFiltroResponsavelId() {
        return filtroResponsavelId;
    }

    public void setFiltroResponsavelId(Long filtroResponsavelId) {
        this.filtroResponsavelId = filtroResponsavelId;
    }

    
    
}
