package ru.otus.spring.homework_29.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfiguration {
    @Bean
    public QueueChannel babyChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel ordinaryTransformationChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel metamorphosisChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel adultChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(1).get();
    }

    @Bean
    public IntegrationFlow lifeCycleFlow() {
        return IntegrationFlows
                .from("babyChannel")
                .split()
                .<BabyAnimal, Boolean>route(
                        BabyAnimal::isMetamorphosis,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf
                                        .channel("metamorphosisChannel")
                                        .handle("transformationService", "makeMetamorphosisTransformation"))
                                .subFlowMapping(false, sf -> sf
                                        .channel("ordinaryTransformationChannel")
                                        .handle("transformationService", "makeOrdinaryTransformation"))
                )
                .aggregate()
                .channel("adultChannel")
                .get();
    }
}