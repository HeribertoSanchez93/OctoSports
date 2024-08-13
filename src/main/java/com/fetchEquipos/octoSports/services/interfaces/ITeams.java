package com.fetchEquipos.octoSports.services.interfaces;

import com.fetchEquipos.octoSports.models.TeamsDto;

import java.util.List;

public interface ITeams {

    List<TeamsDto> fetch(String url);
    void saveOrUpdateAll(List<TeamsDto> teams);
}
