package kh.spring.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chat",configurator=HttpSessionSetter.class)
public class WebChat {
	private static Map<Session,String> clients= Collections.synchronizedMap(new HashMap<Session,String>());

	@OnMessage	// 상대방이든 나든 메세지 입력하는 순간 실행
	public void onMessage(String message, Session session) throws Exception{
		String loginId = clients.get(session);
		System.out.println(loginId);
		synchronized(clients) {
			for(Session each:clients.keySet()) {
				if(each != session) {
					each.getBasicRemote().sendText(loginId+":"+message);
				}
			}
		}
	}

	@OnOpen 	// 채팅방 jsp 에 들어오는 순간 실행 
	public void onOpen(Session session, EndpointConfig ec) {
		HttpSession hSession = (HttpSession)ec.getUserProperties().get("httpSession");
		System.out.println(hSession.getAttribute("loginId"));
		
		clients.put(session,(String)hSession.getAttribute("loginId"));
	}
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
