package de.turing85.service;

import io.quarkus.runtime.Startup;
import io.smallrye.common.annotation.Identifier;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Provider
class DiscoverableServiceProducer {
  DiscoverableServiceProducer() {
  }

  @Produces
  @ApplicationScoped
  @Startup
  @Identifier("a-blue")
  DiscoverableService aBlueService(
      @ConfigProperty(name = "service.hostname") String serviceHostname,
      @ConfigProperty(name = "service.a.blue.port") int port,
      @ConfigProperty(name = "consul.host") String consulHost,
      @ConfigProperty(name = "consul.port") int consulPort,
      Vertx vertx) {
    return new DiscoverableService(
        serviceHostname,
        "a",
        "blue",
        port,
        consulHost,
        consulPort,
        vertx)
        .start();
  }

  @Produces
  @ApplicationScoped
  @Startup
  @Identifier("a-red")
  DiscoverableService aRedService(
      @ConfigProperty(name = "service.hostname") String serviceHostname,
      @ConfigProperty(name = "service.a.red.port") int port,
      @ConfigProperty(name = "consul.host") String consulHost,
      @ConfigProperty(name = "consul.port") int consulPort,
      Vertx vertx) {
    return new DiscoverableService(
        serviceHostname,
        "a",
        "red",
        port,
        consulHost,
        consulPort,
        vertx)
        .start();
  }


  @Produces
  @ApplicationScoped
  @Startup
  @Identifier("b-green")
  DiscoverableService bGreenService(
      @ConfigProperty(name = "service.hostname") String serviceHostname,
      @ConfigProperty(name = "service.b.green.port") int port,
      @ConfigProperty(name = "consul.host") String consulHost,
      @ConfigProperty(name = "consul.port") int consulPort,
      Vertx vertx) {
    return new DiscoverableService(
        serviceHostname,
        "b",
        "green",
        port,
        consulHost,
        consulPort,
        vertx)
        .start();
  }

  @Produces
  @ApplicationScoped
  @Startup
  @Identifier("b-yellow")
  DiscoverableService bYellowService(
      @ConfigProperty(name = "service.hostname") String serviceHostname,
      @ConfigProperty(name = "service.b.yellow.port") int port,
      @ConfigProperty(name = "consul.host") String consulHost,
      @ConfigProperty(name = "consul.port") int consulPort,
      Vertx vertx) {
    return new DiscoverableService(
        serviceHostname,
        "b",
        "yellow",
        port,
        consulHost,
        consulPort,
        vertx)
        .start();
  }
}
