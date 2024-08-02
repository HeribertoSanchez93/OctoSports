package com.fetchEquipos.octoSports.models;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("idTeam")
    private Long idTeam;

    @SerializedName("strTeam")
    private String nameTeam;

    @SerializedName("strCountry")
    private String country;
}
