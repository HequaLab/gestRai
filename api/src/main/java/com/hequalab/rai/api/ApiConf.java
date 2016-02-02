package com.hequalab.rai.api;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hequalab.rai.api.utility.MailClientConf;

public class ApiConf extends Configuration
	implements AssetsBundleConfiguration {

    @Valid
    @NotNull
    @JsonProperty("mailClient")
    private MailClientConf mailClientConf;
    MailClientConf getMailClientConf(){
	return mailClientConf;
    }
    
    
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
	return database;
    }

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
	return assets;
    }

}