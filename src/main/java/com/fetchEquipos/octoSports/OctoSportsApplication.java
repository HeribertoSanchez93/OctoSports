package com.fetchEquipos.octoSports;

import com.fetchEquipos.octoSports.models.TeamsDto;
import com.fetchEquipos.octoSports.services.interfaces.ITeams;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OctoSportsApplication {


	public static void main(String[] args) {
		SpringApplication.run(OctoSportsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ITeams teams){
		return args -> {
			TeamsDto listTeams=teams.fetch("https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=English%20Premier%20League");
			teams.saveOrUpdateAll(listTeams);

		};
	}
}
