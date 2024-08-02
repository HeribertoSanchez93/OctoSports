package com.fetchEquipos.octoSports.repository.interfaces;

import com.fetchEquipos.octoSports.models.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTeam extends JpaRepository<TeamDto,Long> {
    TeamDto findByIdTeam(Long idTeam);
}
