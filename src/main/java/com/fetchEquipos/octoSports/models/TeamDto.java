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
    private Long idDataBase;

    @SerializedName("id")
    private Long idTeam;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String nameTeam;

    @SerializedName("country")
    private String country;
}
