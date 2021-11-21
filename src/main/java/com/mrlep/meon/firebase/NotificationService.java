package com.mrlep.meon.firebase;

import com.google.gson.Gson;
import com.mrlep.meon.firebase.model.Data;
import com.mrlep.meon.firebase.model.Notification;
import com.mrlep.meon.firebase.model.NotificationBO;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.utils.FnCommon;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationService {

    public void sendNotificationToShop(Data data, Integer shopId, String message) {

        NotificationBO notificationBO = new NotificationBO();
        notificationBO.setTo(NotificationBO.TOPIC + "shopnoti-" + shopId);

        Notification notification = new Notification();
        notification.setTitle("Thông báo");
        notification.setBody(message);
        notificationBO.setData(data);
        notificationBO.setNotification(notification);
        pushNotification(notificationBO);
    }

    public void pushNotification(NotificationBO body) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String serverKey = "key=AAAApVRBERQ:APA91bF4DlX9h1-ZQk0zrPtSQ9VZFXllV5ak9HdW6BqHJvGHLZUTeTZXeFheRulO2s5u9tYYvbF2NWUg5Z7MrFcr9PmSuV93rNcZ92-MPTuZbpCSO_M6UUN1dldeOpxb7HmYxatacfGI";

        try {
            String url = "https://fcm.googleapis.com/fcm/send";
            HttpPost request = new HttpPost(url);
            request.setHeader("Authorization", serverKey);
            request.setHeader("Content-type", "application/json");
            request.setHeader("Accept-Encoding", "UTF-8");

            request.setEntity(new StringEntity(new Gson().toJson(body), "UTF-8"));
            System.out.println("FireBase request: " + new Gson().toJson(body));
            response = httpClient.execute(request);
            System.out.println("FireBase response: " + response.getStatusLine().getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
