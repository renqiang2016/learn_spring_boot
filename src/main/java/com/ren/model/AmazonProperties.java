package com.ren.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/26 1:21
 */
@Component
@ConfigurationProperties(prefix = "amazon")
public class AmazonProperties {
    private String amazonId;

    public String getAmazonId() {
        return amazonId;
    }

    public void setAmazonId(String amazonId) {
        this.amazonId = amazonId;
    }
}
