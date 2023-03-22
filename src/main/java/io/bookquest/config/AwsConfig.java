package io.bookquest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Value("aws.s3.key")
    private String s3Key;

    @Value("aws.s3.secret")
    private String s3Secret;

    @Bean
    S3Client configS3() {
        return S3Client.builder()
                .credentialsProvider(() -> new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return s3Key;
                    }

                    @Override
                    public String secretAccessKey() {
                        return s3Secret;
                    }
                }).region(Region.US_WEST_1)
                .endpointOverride(URI.create("https://gateway.storjshare.io"))
                .forcePathStyle(true)
                .build();
    }
}


