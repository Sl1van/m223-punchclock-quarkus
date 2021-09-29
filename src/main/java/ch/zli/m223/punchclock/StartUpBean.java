package ch.zli.m223.punchclock;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.runtime.Startup;

@Startup
@ApplicationScoped
public class StartUpBean {
    // @Inject
    // DataInitializerBean dataInitializerBean;

    public StartUpBean(DataInitializerBean dataInitializerBean) {
        dataInitializerBean.initData();
    }

    // @Inject
    // EntityManager entityManager;

    // private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    // void onStart(@Observes StartupEvent ev) {
    // LOGGER.info("The application is starting...");
    // LOGGER.info("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    // // URL pathOfDataSQL = Main.class.getResource("data.sql");
    // // Path path = Paths.get(pathOfDataSQL.toURI());
    // // entityManager.createNativeQuery(Files.readString(path,
    // StandardCharsets.US_ASCII));
    // String sql = "INSERT INTO user (id, username, password) VALUES\n (1,'user',
    // 'pw');\nINSERT INTO Role (id, name) VALUES\n (1,'user'),\n
    // (2,'admin');\nINSERT INTO USER_ROLE (role_id, user_id) VALUES\n (1,1),\n
    // (2,1);\n";
    // entityManager.createNativeQuery(sql);
    // }

    // void onStop(@Observes ShutdownEvent ev) {
    // LOGGER.info("The application is stopping...");
    // }

}