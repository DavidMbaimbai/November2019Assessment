package com.mbaimbai.assessment.config;

import com.mbaimbai.assessment.model.repos.MarkerInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {MarkerInterface.class})
public class DbConfig {
}
