package io.advance.poker.simulation.rest;

import io.advance.poker.simulation.entity.HandDTO;
import io.advance.poker.simulation.api.PokerSimulationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/v1")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PokerSimulationRestApi {
    @Inject
    private PokerSimulationService pokerSimulationService;


    @GET
    @Path("shuffle")
    @ApiOperation(value = "Shuffles deck", code = 200,
            notes = "This service call shuffles a deck of standard 52-Cards.")
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "Validation failed", responseHeaders = {
                    @ResponseHeader(name = "MESSAGE", description = "Reason for failure", response = String.class)
            })
    })
    public Response shuffleDeck() {
        pokerSimulationService.shuffleDeck();
        return Response.ok()
                .header("MESSAGE", "Deck shuffled").build();
    }

    @GET
    @Path("deal/{numberOfCards}")
    @ApiOperation(value = "Deals a hand of cards", code = 200,
            notes = "This method deals a hand of cards.")
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "Validation failed", responseHeaders = {
                    @ResponseHeader(name = "MESSAGE", description = "Reason for failure", response = String.class)
            })
    })
    public Response dealHand(@PathParam("numberOfCards") int numberOfCards) {
                return Response.ok(pokerSimulationService.dealHand(numberOfCards))
                .header("MESSAGE", "Deal hand successful").build();
    }

    @POST
    @Path("evaluate")
    @ApiOperation(value = "Evaluates hand", code = 200,
            notes = "This service call a hand.")
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "Validation failed", responseHeaders = {
                    @ResponseHeader(name = "MESSAGE", description = "Reason for failure", response = String.class)
            })
    })
    public Response evaluateHand(@RequestBody HandDTO hand) {
        return Response.ok(pokerSimulationService.evaluateHand(hand)).build();
    }

}
