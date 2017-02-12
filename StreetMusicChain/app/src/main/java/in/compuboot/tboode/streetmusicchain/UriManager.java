package in.compuboot.tboode.streetmusicchain;

/**
 * Created by tboode on 11-2-2017.
 */
public class UriManager {
    private String uri = "http://compuboot.in:9090/contact";

    private static UriManager ourInstance = new UriManager();

    public static UriManager getInstance() {
        return ourInstance;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
