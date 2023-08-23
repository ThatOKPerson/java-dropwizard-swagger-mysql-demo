package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.OrderController;
import org.kainos.ea.resources.ProductController;

public class DropWizardServiceApplication extends Application<DropWizardServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizardServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizardService";
    }

    @Override
    public void initialize(final Bootstrap<DropWizardServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropWizardServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropWizardServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropWizardServiceConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new OrderController());
        environment.jersey().register(new ProductController());
    }

}
