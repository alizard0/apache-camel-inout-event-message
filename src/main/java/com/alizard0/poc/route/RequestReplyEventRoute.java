package com.alizard0.poc.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestReplyEventRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("direct:publishEvent")
            .setHeader("JMSCorrelationID", constant(UUID.randomUUID().toString()))
            .setHeader("JMSReplyTo", constant("reply"))
            .id("publishEventDirectRoute")
            .log("Publishing event ${body}")
            .convertBodyTo(String.class)
            .to(ExchangePattern.InOut,"rabbitmq://localhost?queue=request")
            .log("Received message: ${body}")
            .convertBodyTo(String.class)
            .end();
  }
}
