package kh.spring.chat;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
// ServerEndpoint로 가기 전에 이 Handshake 를 거쳐 간다. 여기서
public class HttpSessionSetter extends ServerEndpointConfig.Configurator{
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession hSession = (HttpSession)request.getHttpSession();
		sec.getUserProperties().put("httpSession", hSession);
	}
}
