package com.fetchEquipos.octoSports.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class TeamsDto {
    @SerializedName("teams")
    private List<TeamDto> teams;
}
