package ch.zli.m223.punchclock;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.runtime.Startup;

@Startup
@ApplicationScoped
public class StartUpBean {
    public StartUpBean(DataInitializerBean dataInitializerBean) {
        dataInitializerBean.initData();
    }
}