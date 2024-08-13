package com.fetchEquipos.octoSports;

import com.fetchEquipos.octoSports.models.TeamsDto;
import com.fetchEquipos.octoSports.services.interfaces.ITeams;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OctoSportsApplication {


	public static void main(String[] args) {
		SpringApplication.run(OctoSportsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ITeams teams){
		return args -> {
			List<TeamsDto> listTeams=teams.fetch("https://v3.football.api-sports.io/teams?league=39&season=2021");
			teams.saveOrUpdateAll(listTeams);

		};
	}
}
