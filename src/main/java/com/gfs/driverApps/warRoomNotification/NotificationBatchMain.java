package com.gfs.driverApps.warRoomNotification;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, })
@EnableScheduling
public class NotificationBatchMain implements ApplicationRunner {

    @Autowired
    NotificationProcessor notificationProcessor;

    public static void main(final String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(NotificationBatchMain.class, args);
        ctx.close();
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        System.out.println("Batch Started");
        while (true) {
        }

    }

    @Scheduled(fixedDelay = 5000)
    public void checkSonarStatus() {
        System.out.println(LocalDateTime.now());
        notificationProcessor.processAllSystems();
    }

}
