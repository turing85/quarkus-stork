package de.turing85.stork.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "stork://b")
public interface BClient {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  String getGreeting();
}
