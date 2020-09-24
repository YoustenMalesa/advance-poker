package io.advance.client;

import io.advance.poker.simulation.entity.EvaluateResponse;
import io.advance.poker.simulation.entity.HandDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
public class AdvancePokerServiceClient extends AbstractRestClient implements AdvancePokerClient {
    @Inject
    @ConfigProperty(name = "url")
    private String url;

    @Override
    public void shuffleDeck() throws UnexpectedResponseException {
        Response response = getWebTarget().request(MediaType.APPLICATION_JSON_TYPE).get();
        handleUnexpectedResponse(response);
    }

    @Override
    public HandDTO dealHand(int numberOfCards) throws UnexpectedResponseException {

        return null;
    }

    @Override
    public EvaluateResponse evaluateHand(HandDTO handDTO) throws UnexpectedResponseException {
        Response response = invokeEvaluateHand(handDTO);
        handleUnexpectedResponse(response);

        return response.readEntity(EvaluateResponse.class);
    }

    private Response invokeEvaluateHand(HandDTO handDTO) {
        WebTarget webResource = getWebTarget();
        webResource = webResource.path("evaluate");

        return webResource.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(handDTO));
    }

    private Response invokeDealHand(int numberOfCards) {
        WebTarget webResource = getWebTarget();
        webResource = webResource.path("deal");
        webResource = webResource.path(String.valueOf(numberOfCards));

        return webResource.request(MediaType.APPLICATION_JSON_TYPE).get();
    }

    private WebTarget getWebTarget() {
        return getClient().target(url);
    }

    private void handleUnexpectedResponse(Response response) throws UnexpectedResponseException {
        try {
            int status = response.getStatus();
            if(status != 200) {
                Object header = response.getHeaders().getFirst("MESSAGE");
                String message = header == null ? "Server error" : header.toString();
                throw new UnexpectedResponseException(
                        String.format("Unexpected status code {0} returned from REST call: {1}", message, status));
            }
        }finally {
            response.close();
        }
    }
}
