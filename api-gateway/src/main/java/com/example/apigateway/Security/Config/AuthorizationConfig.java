package com.example.apigateway.Security.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties
public class AuthorizationConfig {
    private List<AuthorizationRule> rules;

    public List<AuthorizationRule> getRules() {
        return rules;
    }

    public void setRules(List<AuthorizationRule> rules) {
        this.rules = rules;
    }

    public static class AuthorizationRule {
        private String path;
        private List<String> methods;
        private List<String> roles;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<String> getMethods() {
            return methods;
        }

        public void setMethods(List<String> methods) {
            this.methods = methods;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
