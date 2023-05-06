package de.turing85.stork.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "stork://a")
public interface AClient {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  String getGreeting();
}
