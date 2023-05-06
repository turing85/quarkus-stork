package de.turing85.service;

import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscoverableService {
  private static final String PROTOCOL = "http";

  private final String serviceHostName;
  private final String serviceName;
  private final String serviceColor;
  private final int port;

  private final String consulHost;
  private final int consulPort;

  private final Vertx vertx;

  public DiscoverableService start() {
    log.info("Starting and registering {}.{}", serviceName(), serviceColor());
    startService();
    registerServiceForDiscovery();
    return this;
  }

  private void startService() {
    vertx().createHttpServer()
        .requestHandler(req -> req.response().endAndForget(message()))
        .listenAndAwait(port());
  }

  private String message() {
    return "Hello from %s.%s!".formatted(serviceName(), serviceColor());
  }

  private void registerServiceForDiscovery() {
    ConsulClient.create(
            vertx(),
            new ConsulClientOptions().setHost(consulHost()).setPort(consulPort()))
        .registerServiceAndAwait(
            new ServiceOptions()
                .setPort(port())
                .setAddress(serviceHostName())
                .setPort(port())
                .setName(serviceName())
                .setId(serviceColor())
                .setCheckOptions(serviceReadyCheck())
                .setMeta(Map.of("color", serviceColor())));
  }

  private CheckOptions serviceReadyCheck() {
    return new CheckOptions()
        .setServiceId(serviceColor())
        .setName("Service Ready")
        .setId("service-%s-%s-ready".formatted(serviceName(), serviceColor()))
        .setHttp("%s://%s:%d".formatted(PROTOCOL, serviceHostName(), port()))
        .setNotes("Ready check for %s.%s".formatted(serviceName(), serviceColor()))
        .setInterval("10s");
  }
}
