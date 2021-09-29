package ch.zli.m223.punchclock;

import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class StartUpBean {
    public StartUpBean(DataInitializerBean dataInitializerBean) {
        dataInitializerBean.initData();
    }
}