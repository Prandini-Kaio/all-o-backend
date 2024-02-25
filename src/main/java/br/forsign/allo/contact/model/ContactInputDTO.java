package br.forsign.allo.contact.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactInputDTO {
    @Schema(example = "matheusfrangote@org.com")
    private String email;
    @Schema(example = "+5535988568856")
    private String phone;
}
