package jp.ddd.server.other.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.apache.commons.lang.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-data-dynamodbに関するxmlによる定義方法
 * の情報量が少ないため、javaConfigで設定する。
 * Created by kohei on 2017/03/22.
 */
@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackages = "jp.ddd.server.usecase.gateway.dynamodb")
public class DynamoDbConfig {

    @Value("${aws.dynamoDb.endpoint}")
    private String dynamoDBEndpoint;
    @Value("${aws.dynamoDb.prefix}")
    private String tableNamePrefix;
    @Value("${aws.accessKey}")
    private String awsAccessKey;
    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials amazonAWSCredentials) {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials);

        if (StringUtils.isNotEmpty(dynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(dynamoDBEndpoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        // Or use an AWSCredentialsProvider/AWSCredentialsProviderChain
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverrider) {
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setTableNameOverride(tableNameOverrider);
        return builder.build();
    }

    @Bean
    public DynamoDBMapperConfig.TableNameOverride tableNameOverrider() {
        return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(tableNamePrefix);
    }
}
