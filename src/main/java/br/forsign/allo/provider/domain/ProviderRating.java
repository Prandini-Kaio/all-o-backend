package br.forsign.allo.provider.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // LISTA DE RATING DE CLIENTES

    @Column(name = "TOTAL")
    private float total;

    @Column(name = "MEDIA")
    private float media;
}