package com.fetchEquipos.octoSports.services.interfaces;

import com.fetchEquipos.octoSports.models.TeamsDto;

public interface ITeams {

    TeamsDto fetch(String url);
    void saveOrUpdateAll(TeamsDto teams);
}
