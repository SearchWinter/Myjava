package javaapi.indexapi;

public class EsConfig {
    private String host;
    private int port;

    public EsConfig() {
    }

    public EsConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static EsConfig[] createEsConfig(String... esStr) {
        EsConfig[] result = new EsConfig[esStr.length];
        for (int i = 0; i < esStr.length; i++) {
            String[] splits = esStr[i].split(":", -1);
            EsConfig esConfig = new EsConfig(splits[0], Integer.valueOf(splits[1]));
            result[i] = esConfig;
        }
        return result;

    }

}
