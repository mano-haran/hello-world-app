package com.example.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.FibonacciResource;
import java.util.logging.Logger;
import com.example.helloworld.health.TemplateHealthCheck;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    private static final Logger LOGGER = Logger.getLogger(HelloWorldApplication.class.getName());
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final FibonacciResource fibResource = new FibonacciResource(
            configuration.getDefaultNumber()
        );
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(helloWorldResource);
        environment.jersey().register(fibResource);
        registerMetrics(environment);
    }

    private void registerMetrics(Environment environment) {
        CollectorRegistry collectorRegistry = new CollectorRegistry();
        collectorRegistry.register(new DropwizardExports(environment.metrics(), new com.codahale.metrics.MetricFilter() {
            @Override
            public boolean matches(final String name, final com.codahale.metrics.Metric metric) {
                if(name.indexOf("jvm") == 0 || name.indexOf("io_dropwizard_jetty_MutableServletContextHandler_requests_count") == 0) {
                    //LOGGER.info(name);
                    return true;
                } else {
                    return false;
                }
            }
         }));
        environment.admin().addServlet("metrics-prometheus", new MetricsServlet(collectorRegistry))
                .addMapping("/metrics-prometheus");
      
    }    
}