package com.fetchEquipos.octoSports.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class TeamsDto {
    @SerializedName(value="team")
    private TeamDto team;
}
