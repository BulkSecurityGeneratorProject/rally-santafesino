package com.rally.santafesino.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.rally.santafesino.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.rally.santafesino.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Auto.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.AutoCarrera.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.AutoTiempoPrueba.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Carrera.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.CarreraEtapa.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Coordenadas.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.CoordenadaTrayecto.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Etapa.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.EtapaPrueba.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Localidad.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.LocalidadCarrera.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Persona.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Pruebas.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Tiempos.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Trayecto.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.TrayectoPrueba.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.Clase.class.getName(), jcacheConfiguration);
            cm.createCache(com.rally.santafesino.domain.CarreraClase.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
