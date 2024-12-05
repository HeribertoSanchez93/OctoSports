package com.fetchEquipos.octoSports.services.implementation;

import com.fetchEquipos.octoSports.config.ConfigApiKey;
import com.fetchEquipos.octoSports.consts.Constant;
import com.fetchEquipos.octoSports.models.ResponseTeam;
import com.fetchEquipos.octoSports.models.TeamDto;
import com.fetchEquipos.octoSports.models.TeamsDto;
import com.fetchEquipos.octoSports.repository.interfaces.RepositoryTeam;
import com.fetchEquipos.octoSports.services.interfaces.IFetchUrl;
import com.fetchEquipos.octoSports.services.interfaces.ITeams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Data
@AllArgsConstructor
@Builder
public class ImplTeams implements ITeams {

    private final TransactionTemplate transactionTemplate;
    private final RepositoryTeam repositoryTeam;
    private final KafkaTemplate<String,Boolean> kafkaTemplate;
    private final IFetchUrl<ResponseTeam> fetchUrl;


    @Override
    public List<TeamsDto> fetch(String url) {
        Type type = new TypeToken<ResponseTeam>() {
        }.getType();
        ResponseTeam responseTeam = fetchUrl.fetch(url, type);
        return responseTeam !=null ? responseTeam.getTeams():null;
    }


    @Override
    public void saveOrUpdateAll(List<TeamsDto> teamFetch) {
        if(teamFetch!=null)
        {
            List<CompletableFuture<Void>> futures = teamFetch
                    .stream()
                    .map(teamDto -> CompletableFuture.runAsync(() -> saveOrUpdateTeam(teamDto)))
                    .toList();

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            sendStatus("fetchAllTeams-Topic",Boolean.TRUE);
        }

    }

    @Async
    @Transactional
    private void saveOrUpdateTeam(TeamsDto teamsDto) {
        TeamDto teamDto = teamsDto.getTeam();
        Optional<TeamDto> existingTeam = Optional.ofNullable(repositoryTeam.findByIdTeam(teamDto.getIdTeam()));
        if (existingTeam.isPresent()) {
            TeamDto update = existingTeam.get();
            update.setIdTeam(teamDto.getIdTeam());
            update.setNameTeam(teamDto.getNameTeam());
            update.setCountry(teamDto.getCountry());
            update.setCode(teamDto.getCode());
            repositoryTeam.save(update);
        } else {
            repositoryTeam.save(teamDto);
        }
    }

    private void sendStatus(String nameTopic,Boolean status){
        kafkaTemplate.send(nameTopic,status);
    }
}
