package br.com.alura.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class RelatorioDeVendasDto {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;
}
