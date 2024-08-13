package com.fetchEquipos.octoSports.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ResponseTeam {
    @SerializedName(value="teams", alternate={"response"})
    private List<TeamsDto> teams;
}
