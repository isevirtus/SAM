package br.edu.ufcg.embedded.sam.services.bayesiannetwork;

import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;
import br.edu.ufcg.embedded.sam.models.dto.NetworkDto;
import br.edu.ufcg.embedded.sam.repositories.NetworkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class NetworkApiService {

    private final static Logger LOGGER = Logger.getLogger(NetworkApiService.class.getCanonicalName());

    @Value("${bayesian.nodes.server.url}")
    private static final String serverUrl = "http://192.168.130.201/node/kaizentools/service/npt";

    @Autowired
    public NetworkApiService() {

    }

    public Network queryForNetwork(Network network) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("net", networkToJson(network));

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity<>(body, headers);

        NetworkDto networkDto = template.exchange(serverUrl, HttpMethod.POST, entity, NetworkDto.class).getBody();

        return networkDto.getNetwork();
    }

    private String networkToJson(Network network) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(network);
        } catch (JsonProcessingException e) {
            LOGGER.severe(e.getMessage());
        }

        return null;
    }
}
