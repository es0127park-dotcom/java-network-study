package com.mtcoding.airplane;

import com.google.gson.Gson;
import com.mtcoding.ex10.Hello;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AirApp {

    // key = 무안, value = NAARKJB
    static Map<String, String> ports = new HashMap();

    public static void main(String[] args) {

        try {
            // 1. 공항 정보를 다운 - HttpURLConnection으로
            URL site1 = new URL("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=1d4b60644d4515082456e13d65d46448695cb8a300d92371e3147cbd27160160&_type=json");
            HttpURLConnection conn1 = (HttpURLConnection) site1.openConnection();

            conn1.setRequestMethod("GET");

            Scanner sc1 = new Scanner(conn1.getInputStream());
            String json1 = "";
            while (sc1.hasNextLine()) {
                String line = sc1.nextLine();
                if (line == null) break;
                json1 = json1 + line;
            }
            // 2. PortInfo로 오브젝트화 시켜!!
            Gson gson = new Gson();
            PortInfo portInfo = gson.fromJson(json1, PortInfo.class);
            // 3. ports에 airportId에 있는 값, airportNm에 있는 값 넣기
            List<PortInfo.Response.Body.Items.Item> itemList = portInfo.getResponse().getBody().getItems().getItem();
            String portKey;
            String portValue;
            for (int i = 0; i < itemList.size(); i++) {
                portKey = itemList.get(i).getAirportNm();
                portValue = itemList.get(i).getAirportId();
                ports.put(portKey,portValue);
            }

            String dep = ""; // 무안
            String depKey = "";
            String arr = ""; // 부산
            String arrKey = "";
            String time = ""; // 20251219
            // 4. 스캐너로 출발지 받기
            System.out.println("출발지를 입력하세요.");
            for (String n : ports.keySet()) {
                System.out.print(n + " ");
            }
            System.out.println();
            Scanner keySc = new Scanner(System.in);
            dep = keySc.nextLine();
            depKey = ports.get(dep);
            // System.out.println(depKey);
            System.out.println();
            // 5. 스캐너로 목적지 받기
            System.out.println("목적지를 입력하세요.");
            for (String n : ports.keySet()) {
                System.out.print(n + " ");
            }
            System.out.println();
            arr = keySc.nextLine();
            arrKey = ports.get(arr);
            // System.out.println(arrKey);
            System.out.println();
            // 6. 출발날짜 받기
            System.out.println("출발날짜를 입력하세요.YYYYMMDD(예시 : 20251218)");
            time = keySc.nextLine();
            // System.out.println(time);
            System.out.println();

            // 7. 동적인 URL 만들기
            String url = """
                https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=1d4b60644d4515082456e13d65d46448695cb8a300d92371e3147cbd27160160&pageNo=1&numOfRows=10&_type=json&depAirportId=${depKey}&arrAirportId=${arrKey}&depPlandTime=${time}
                """.replace("${depKey}", depKey).replace("${arrKey}", arrKey).replace("${time}", time);
            // 8. 항공스케줄 다운 - HttpURLConnection 호출
            URL site2 = new URL(url);
            HttpURLConnection conn2 = (HttpURLConnection) site2.openConnection();

            conn2.setRequestMethod("GET");

            Scanner sc2 = new Scanner(conn2.getInputStream());
            String json2 = "";
            while (sc2.hasNextLine()) {
                String line = sc2.nextLine();
                if (line == null) break;
                json2 = json2 + line;
            }
            // 9. 파싱 -> AirInfo로 파싱
            AirInfo airInfo = gson.fromJson(json2, AirInfo.class);
            // 10. 항송 스케줄 예쁘게 전체 출력
            List<AirInfo.Response.Body.Items.Item> airList = airInfo.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < airList.size(); i++) {
                System.out.println(i);
                System.out.println("항공사 : " + airList.get(i).getAirlineNm());
                System.out.println("도착공항 : " + airList.get(i).getArrAirportNm());
                System.out.println("도착시간 : " + airList.get(i).getArrPlandTime());
                System.out.println("출발공항 : " + airList.get(i).getDepAirportNm());
                System.out.println("출발시간 : " + airList.get(i).getDepPlandTime());
                System.out.println("일반석운임 : " + airList.get(i).getEconomyCharge());
                System.out.println("비즈니스석운임 : " + airList.get(i).getPrestigeCharge());
                System.out.println("항공편명 : " + airList.get(i).getVihicleId());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
