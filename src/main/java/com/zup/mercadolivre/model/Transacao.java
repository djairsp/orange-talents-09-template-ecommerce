package com.zup.mercadolivre.model;

import com.zup.mercadolivre.retornocompra.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private String idTransacao;
    @NotNull
    @ManyToOne
    private Compra compra;
    private LocalDateTime instanteDate;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.instanteDate = LocalDateTime.now();
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                ", instanteDate=" + instanteDate +
                '}';
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;

        Transacao transacao = (Transacao) o;

        if (id != null ? !id.equals(transacao.id) : transacao.id != null) return false;
        if (status != transacao.status) return false;
        if (idTransacao != null ? !idTransacao.equals(transacao.idTransacao) : transacao.idTransacao != null)
            return false;
        return instanteDate != null ? instanteDate.equals(transacao.instanteDate) : transacao.instanteDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (idTransacao != null ? idTransacao.hashCode() : 0);
        result = 31 * result + (instanteDate != null ? instanteDate.hashCode() : 0);
        return result;
    }
}
