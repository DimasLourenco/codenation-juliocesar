import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpConnection;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.impl.Http1StreamListener;
import org.apache.hc.core5.http.impl.bootstrap.HttpRequester;
import org.apache.hc.core5.http.impl.bootstrap.RequesterBootstrap;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.message.RequestLine;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.protocol.HttpCoreContext;
import org.apache.hc.core5.util.Timeout;

public class main {

	public static void main(String[] args) throws Exception {
		final HttpRequester httpRequester = RequesterBootstrap.bootstrap()
                .setStreamListener(new Http1StreamListener() {

                    @Override
                    public void onRequestHead(final HttpConnection connection, final HttpRequest request) {
                        System.out.println(connection.getRemoteAddress() + " " + new RequestLine(request));

                    }

                    @Override
                    public void onResponseHead(final HttpConnection connection, final HttpResponse response) {
                        System.out.println(connection.getRemoteAddress() + " " + new StatusLine(response));
                    }

                    @Override
                    public void onExchangeComplete(final HttpConnection connection, final boolean keepAlive) {
                        if (keepAlive) {
                            System.out.println(connection.getRemoteAddress() + " exchange completed (connection kept alive)");
                        } else {
                            System.out.println(connection.getRemoteAddress() + " exchange completed (connection closed)");
                        }
                    }

                })
                .setSocketConfig(SocketConfig.custom()
                        .setSoTimeout(5, TimeUnit.SECONDS)
                        .build())
                .create();

        final HttpCoreContext coreContext = HttpCoreContext.create();
        final HttpHost target = new HttpHost("httpbin.org");
        final String[] requestUris = new String[] {"/", "/ip", "/user-agent", "/headers"};

        for (int i = 0; i < requestUris.length; i++) {
            final String requestUri = requestUris[i];
            final ClassicHttpRequest request = new BasicClassicHttpRequest(Method.GET, target, requestUri);
            try (ClassicHttpResponse response = httpRequester.execute(target, request, Timeout.ofSeconds(5), coreContext)) {
                System.out.println(requestUri + "->" + response.getCode());
                System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println("==============");
            }
        }
	}
}