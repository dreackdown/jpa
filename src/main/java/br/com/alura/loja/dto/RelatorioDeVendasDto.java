package br.com.alura.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RelatorioDeVendasDto {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;

    @Override
    public String toString() {
        return "RelatorioDeVendasVo [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida + ", dataUltimaVenda=" + dataUltimaVenda + "]";
    }
}
