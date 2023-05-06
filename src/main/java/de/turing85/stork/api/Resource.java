package de.turing85.stork.api;

import de.turing85.stork.client.AClient;
import de.turing85.stork.client.BClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("greeting")
@Produces(MediaType.TEXT_PLAIN)
public class Resource {
  private final AClient aClient;
  private final BClient bClient;

  public Resource(
      @RestClient AClient aClient,
      @RestClient BClient bClient) {
    this.aClient = aClient;
    this.bClient = bClient;
  }

  @GET
  @Path("a")
  public String aGreeting() {
    return aClient.getGreeting();
  }

  @GET
  @Path("b")
  public String bGreeting() {
    return bClient.getGreeting();
  }
}
