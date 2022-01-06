package adm2022.pp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("adm2022.pp.domain")
@EnableJpaRepositories("adm2022.pp.repos")
@EnableTransactionManagement
public class DomainConfig {
}
