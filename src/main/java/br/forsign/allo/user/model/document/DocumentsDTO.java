package br.forsign.allo.user.model.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsDTO {
    private Long id;
    private String cpf_cnpj;
}
