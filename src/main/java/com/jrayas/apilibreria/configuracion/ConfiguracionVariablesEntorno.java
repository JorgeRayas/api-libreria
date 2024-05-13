package com.jrayas.apilibreria.configuracion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;

@Component
public class ConfiguracionVariablesEntorno {
    private Map<String, String> mapVariables = new HashMap<>();

    public ConfiguracionVariablesEntorno() {
        Set<String> setVariables = new HashSet<>(
                Arrays.asList("BD_SERVER", "BD_NOMBRE", "BD_USUARIO", "BD_CONTRASENA"));

        Map<String, String> mapEnvironment = System.getenv();

        if (!mapEnvironment.keySet().containsAll(setVariables)) {
            throw new NoSuchFieldError(
                    "Variables de configuración de Base de Datos no encontradas");
        }

        setVariables.forEach(variable -> {
            if (StringUtils.isBlank(mapEnvironment.get(variable))) {
                throw new NoSuchFieldError(
                        "Variables de configuración de Base de Datos no encontradas");
            }
            mapVariables.put(variable, mapEnvironment.get(variable));
        });
    }

    public String obtenerVariable(String variable) {
        return mapVariables.get(variable);
    }
}
