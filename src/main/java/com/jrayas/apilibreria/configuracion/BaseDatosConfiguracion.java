package com.jrayas.apilibreria.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class BaseDatosConfiguracion {

    private ConfiguracionVariablesEntorno configuracionVariablesEntorno;

    public BaseDatosConfiguracion(
            @Autowired ConfiguracionVariablesEntorno configuracionVariablesEntorno) {
        this.configuracionVariablesEntorno = configuracionVariablesEntorno;
    }

    @Bean("mysqlDataSource")
    public DataSource mysqlDataSource() {
        StringBuilder strBuilder = new StringBuilder("jdbc:mysql://")
                .append(configuracionVariablesEntorno.obtenerVariable("BD_SERVER")).append("/")
                .append(configuracionVariablesEntorno.obtenerVariable("BD_NOMBRE"));

        return DataSourceBuilder.create().url(strBuilder.toString())
                .username(configuracionVariablesEntorno.obtenerVariable("BD_USUARIO"))
                .password(configuracionVariablesEntorno.obtenerVariable("BD_CONTRASENA")).build();
    }

    @Bean("mysqlNamedParameterJdbcTemplate")
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
